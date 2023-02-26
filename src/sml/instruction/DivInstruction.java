package sml.instruction;

import java.util.Objects;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

/**
 * Represents the div Instruction, the context in which statement run.
 * <p>
 * Divide the contents of registers result and source and stores the result in register result
 * <p>
 * An instance contains optional String label, Register result, Register source
 * <p>
 * Class contains methods to execute() div instruction, 
 * <p>
 * toString() String representation of the statement
 * @author Swati Jagtap
 */

public class DivInstruction extends Instruction {
	private final RegisterName result;
	private final RegisterName source;

	public static final String OP_CODE = "div";

	public DivInstruction(String label, RegisterName result, RegisterName source) {
		super(label, OP_CODE);
		this.result = result;
		this.source = source;
	}

	@Override
	public int execute(Machine m) {
		int value1 = m.getRegisters().get(result);
		int value2 = m.getRegisters().get(source);
		try {
		if(value2 == 0)
			throw new ArithmeticException("Can't be divided by zero!");
		}catch(ArithmeticException e) {
			System.err.println("Register "+source+" contains zero value. Can't be divided by zero. Terminating program execution.");
			System.exit(-1);
		}
		m.getRegisters().set(result, value1 / value2);
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}

	@Override
	public String toString() {
		return getLabelString() + getOpcode() + " " + result + " " + source;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(result, source, OP_CODE);
	}
	
	@Override
    public boolean equals(Object o) {
        if (o instanceof DivInstruction i) 
            return i.equals(o);
        return false;
    }
}

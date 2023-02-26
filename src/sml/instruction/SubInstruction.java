package sml.instruction;

import java.util.Objects;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

/**
 * Represents the sub Instruction, the context in which statement run.
 * <p>
 * Subtract the contents of registers result and source and stores the result in register result
 * <p>
 * An instance contains optional String label, Register result, Register source
 * <p>
 * Class contains methods to execute() sub instruction, 
 * <p>
 * toString() String representation of the statement
 * @author Swati Jagtap
 */

public class SubInstruction extends Instruction {
	private final RegisterName result;
	private final RegisterName source;

	public static final String OP_CODE = "sub";

	public SubInstruction(String label, RegisterName result, RegisterName source) {
		super(label, OP_CODE);
		this.result = result;
		this.source = source;
	}

	@Override
	public int execute(Machine m) {
		int value1 = m.getRegisters().get(result);
		int value2 = m.getRegisters().get(source);
		m.getRegisters().set(result, value1 - value2);
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
        if (o instanceof SubInstruction i) 
            return i.equals(o);
        return false;
    }
}

package sml.instruction;

import java.util.Objects;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

/**
 * Represents the out Instruction, the context in which statement run.
 * <p>
 * Out: Print the contents of register result on the console
 * <p>
 * An instance contains optional String label and Register result
 * <p>
 * Class contains methods to execute() out instruction, 
 * <p>
 * toString() String representation of the statement
 * @author Swati Jagtap
 */

public class OutInstruction extends Instruction {
	private final RegisterName result;

	public static final String OP_CODE = "out";

	public OutInstruction(String label, RegisterName result) {
		super(label, OP_CODE);
		this.result = result;
	}

	@Override
	public int execute(Machine m) {
		m.getRegisters().get(result);
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}

	@Override
	public String toString() {
		return getLabelString() + getOpcode() + " " + result;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(result, OP_CODE);
	}
	
	@Override
    public boolean equals(Object o) {
        if (o instanceof OutInstruction i) 
            return i.equals(o);
        return false;
    }
}

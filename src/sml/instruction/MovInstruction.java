package sml.instruction;

import java.util.Objects;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

/**
 * Represents the mov Instruction, the context in which statement run.
 * <p>
 * Move: Store integer source in register result
 * <p>
 * An instance contains optional String label, Register result, int source
 * <p>
 * Class contains methods to execute() mov instruction, 
 * <p>
 * toString() String representation of the statement
 * @author Swati Jagtap
 */

public class MovInstruction extends Instruction {
	private final RegisterName result;
	private final int source;

	public static final String OP_CODE = "mov";

	public MovInstruction(String label, RegisterName result, int source) {
		super(label, OP_CODE);
		this.result = result;
		this.source = source;
	}

	@Override
	public int execute(Machine m) {
		m.getRegisters().set(result, source);
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
        if (o instanceof MovInstruction i) 
            return i.equals(o);
        return false;
    }
}

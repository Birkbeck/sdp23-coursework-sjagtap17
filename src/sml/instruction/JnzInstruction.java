package sml.instruction;

import java.util.Objects;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

/**
 * Represents the jnz Instruction, the context in which statement run.
 * <p>
 * Jump Not Zero: If the contents of register 'result' is not zero, then make the statement labeled <nextStatement> as the next statement to execute
 * <p>
 * An instance contains optional String label, Register result, String nextStatement
 * <p>
 * Class contains methods to execute() jnz instruction, 
 * <p>
 * toString() String representation of the statement
 * @author Swati Jagtap
 */

public class JnzInstruction extends Instruction {
	private final RegisterName result;
	//label of the next statement to jump
	private final String nextStatement; 

	public static final String OP_CODE = "jnz";

	public JnzInstruction(String label, RegisterName result, String nextStatement) {
		super(label, OP_CODE);
		this.result = result;
		this.nextStatement = nextStatement;
	}

	@Override
	public int execute(Machine m) {
		if (m.getRegisters().get(result) != 0) {
			return m.getLabels().getAddress(nextStatement);
		}
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}

	@Override
	public String toString() {
		return getLabelString() + getOpcode() + " " + result + " " + nextStatement;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(result, nextStatement, OP_CODE);
	}
	
	@Override
    public boolean equals(Object o) {
        if (o instanceof JnzInstruction i) 
            return i.equals(o);
        return false;
    }
}

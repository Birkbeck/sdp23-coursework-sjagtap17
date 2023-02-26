package sml;

/**
 * Represents an abstract instruction. <p>
 * (This class represent abstract base class of the subclass for each of the machine instructions. <br>
 * This class is abstract, because it should not be instantiated. <br>
 * execute() is also abstract, forcing every subclass to implement it. <br>
 * Every instruction has an optional label and an operation — that is exactly what is common to every instruction.)
 * 
 * @author ...
 */

public abstract class Instruction {
	protected final String label;
	protected final String opcode;

	/**
	 * Constructor: an instruction with a label and an opcode
	 * (opcode must be an operation of the language)
	 *
	 * @param label optional label (can be null)
	 * @param opcode operation name
	 */
	public Instruction(String label, String opcode) {
		this.label = label;
		this.opcode = opcode;
	}

	public String getLabel() {
		return label;
	}

	public String getOpcode() {
		return opcode;
	}

	public static int NORMAL_PROGRAM_COUNTER_UPDATE = -1;

	/**
	 * Executes the instruction in the given machine.
	 *
	 * @param machine the machine the instruction runs on
	 * @return the new program counter (for jump instructions)
	 *          or NORMAL_PROGRAM_COUNTER_UPDATE to indicate that
	 *          the instruction with the next address is to be executed
	 */
	public abstract int execute(Machine machine);

	protected String getLabelString() {
		return (getLabel() == null) ? "" : getLabel() + ": ";
	}

	/**
	 * String representation of the statement under execution
	 * (Each instruction has optional label and different parameter-list to display)
	 * @return pretty formatted version of the code.
	 */
	@Override
	public abstract String toString();

	@Override
	public abstract int hashCode(); 
	
	@Override
    public abstract boolean equals(Object o);
}

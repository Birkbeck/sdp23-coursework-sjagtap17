package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Labels;
import sml.Machine;
import sml.Registers;
import static sml.Registers.Register.*;

class JnzInstructionTest {
	private Machine machine;
	private Registers registers;
	private Labels labels;

	@BeforeEach
	void setUp() {
		machine = new Machine(new Registers());
		registers = machine.getRegisters();
		labels = machine.getLabels();
		// ...
	}

	@AfterEach
	void tearDown() {
		machine = null;
		registers = null;
		labels = null;
	}

	@Test
	void executeValid() {
		registers.set(EAX, 5);
		labels.addLabel("f3", 3);
		Instruction instruction = new JnzInstruction(null, EAX, "f3");
		instruction.execute(machine);
		Assertions.assertEquals(3, machine.getLabels().getAddress("f3"));
	}

	@Test
	void executeValidTwo() {
		registers.set(EAX, 0);
		labels.addLabel("f4", 5);
		Instruction instruction = new JnzInstruction(null, EAX, "f4");
		int pc = instruction.execute(machine);
		Assertions.assertEquals(-1, pc);
	}

	
	@Test
	void toStringValid() {
		Assertions.assertEquals("f4: jnz EAX f3", new JnzInstruction("f4", EAX, "f3").toString());
	}

	@Test
  void toStringValidTwo() {
		Assertions.assertEquals("jnz EAX f3", new JnzInstruction(null, EAX, "f3").toString());
	}
}
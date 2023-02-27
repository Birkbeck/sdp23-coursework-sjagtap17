package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;
import static sml.Registers.Register.*;

class MovnstructionTest {
  private Machine machine;

  @BeforeEach
  void setUp() {
    machine = new Machine(new Registers());
  }

  @AfterEach
  void tearDown() {
    machine = null;
  }

  @Test
  void executeValid() {
    int valueToMove = 6;
    Instruction instruction = new MovInstruction(null, EAX, valueToMove);
    instruction.execute(machine);
    Assertions.assertEquals(6, machine.getRegisters().get(EAX));
  }

  @Test
  void executeValidTwo() {
	  int valueToMove = -5;
	    Instruction instruction = new MovInstruction(null, EAX, valueToMove);
	    instruction.execute(machine);
	    Assertions.assertEquals(-5, machine.getRegisters().get(EAX));
  }
  
  @Test
  void toStringValid() {
    Assertions.assertEquals("f3: mov EAX 6", new MovInstruction("f3", EAX, 6).toString());
  }
  
  @Test
  void toStringValidTwo() {
    Assertions.assertEquals("mov EAX 6", new MovInstruction(null, EAX, 6).toString());
  }
}
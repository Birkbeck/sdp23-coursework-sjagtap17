package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;
import static sml.Registers.Register.*;

class DivInstructionTest {
  private Machine machine;
  private Registers registers;

  @BeforeEach
  void setUp() {
    machine = new Machine(new Registers());
    registers = machine.getRegisters();
  }

  @AfterEach
  void tearDown() {
    machine = null;
    registers = null;
  }

  @Test
  void executeValid() {
    registers.set(EAX, 6);
    registers.set(EBX, 2);
    Instruction instruction = new DivInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(3, machine.getRegisters().get(EAX));
  }

  @Test
  void executeValidTwo() {
    registers.set(EAX, 5);
    registers.set(EBX, 3);
    Instruction instruction = new DivInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(1, machine.getRegisters().get(EAX));
  }
  
  @Test
  void executeValidThree() {
    registers.set(EAX, -6);
    registers.set(EBX, -3);
    Instruction instruction = new DivInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(2, machine.getRegisters().get(EAX));
  }
  
  @Test
  void executeValidFour() {
    registers.set(EAX, -6);
    registers.set(EBX, 2);
    Instruction instruction = new DivInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(-3, machine.getRegisters().get(EAX));
  }
 
  @Test
  void toStringValid() {
    Assertions.assertEquals("f3: div EAX EBX", new DivInstruction("f3", EAX, EBX).toString());
  }
  
  @Test
  void toStringValidTwo() {
    Assertions.assertEquals("div EAX EBX", new DivInstruction(null, EAX, EBX).toString());
  }
}
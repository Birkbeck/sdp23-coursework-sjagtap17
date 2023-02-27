package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;
import static sml.Registers.Register.*;

class SubInstructionTest {
  private Machine machine;
  private Registers registers;

  @BeforeEach
  void setUp() {
    machine = new Machine(new Registers());
    registers = machine.getRegisters();
    //...
  }

  @AfterEach
  void tearDown() {
    machine = null;
    registers = null;
  }

  @Test
  void executeValid() {
    registers.set(EAX, 6);
    registers.set(EBX, 5);
    Instruction instruction = new SubInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(1, machine.getRegisters().get(EAX));
  }

  @Test
  void executeValidTwo() {
    registers.set(EAX, 5);
    registers.set(EBX, 6);
    Instruction instruction = new SubInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(-1, machine.getRegisters().get(EAX));
  }
  
  @Test
  void executeValidThree() {
    registers.set(EAX, -5);
    registers.set(EBX, -6);
    Instruction instruction = new SubInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(1, machine.getRegisters().get(EAX));
  }
  
  @Test
  void toStringValid() {
    Assertions.assertEquals("f3: sub EAX EBX", new SubInstruction("f3", EAX, EBX).toString());
  }
  
  @Test
  void toStringValidTwo() {
    Assertions.assertEquals("sub EAX EBX", new SubInstruction(null, EAX, EBX).toString());
  }
}
package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;
import static sml.Registers.Register.*;

class MulInstructionTest {
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
    registers.set(EAX, 2);
    registers.set(EBX, 3);
    Instruction instruction = new MulInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(6, machine.getRegisters().get(EAX));
  }

  @Test
  void executeValidTwo() {
    registers.set(EAX, -2);
    registers.set(EBX, 3);
    Instruction instruction = new MulInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(-6, machine.getRegisters().get(EAX));
  }
  
  @Test
  void executeValidThree() {
    registers.set(EAX, -2);
    registers.set(EBX, -3);
    Instruction instruction = new MulInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(6, machine.getRegisters().get(EAX));
  }
  
  @Test
  void toStringValid() {
    Assertions.assertEquals("f3: mul EAX EBX", new MulInstruction("f3", EAX, EBX).toString());
  }
  
  @Test
  void toStringValidTwo() {
    Assertions.assertEquals("mul EAX EBX", new MulInstruction(null, EAX, EBX).toString());
  }
}
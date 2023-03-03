package sml;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import sml.Registers.Register;

/**
 * Get the implementation classes (sub-instruction) objects using java
 * reflection
 *
 * @author ...
 */
public class InstructionSupportFactory {

	public <T> T getInstructionInstance(String label, String line, String opcode, Class<T> klass) {

		String subInstructionClassName = "sml.instruction." + opcode.substring(0, 1).toUpperCase() + opcode.substring(1)
				+ "Instruction";

		String[] args = (label + " " + line).split(" ");

		Class<?>[] argTypes = getArgumentTypes(args);

		Class<? extends T> instructionClass;
		Constructor<? extends T> instructionConstructor;

		try {
			instructionClass = Class.forName(subInstructionClassName).asSubclass(klass);
			instructionConstructor = instructionClass.getDeclaredConstructor(argTypes);
		} catch (ClassNotFoundException e) {
			System.err.println("Class " + subInstructionClassName 
					+ " not found corresponding to opcode " + opcode);
			System.err.println(getProgramStatement(label, line, opcode));
			return null;
		} catch (NoSuchMethodException e) {
			System.err.println("Class " + subInstructionClassName
					+ " does not have declared constructor with argsTypes " + Arrays.toString(argTypes));
			System.err.println(getProgramStatement(label, line, opcode));
			return null;
		}

		// Construct sub instruction class constructor initialization parameters
		Object[] parameters = new Object[args.length];

		for (int i = 0; i < parameters.length; i++) {
			if (argTypes[i] == RegisterName.class) {
				parameters[i] = Register.valueOf(args[i]);
			} else if (argTypes[i] == int.class) {
				parameters[i] = Integer.parseInt(args[i]);
			} else {
				parameters[i] = args[i];
			}
		}
		
		//return new instance of the constructor's declaring class with the specified initialization parameters
		try {
			return instructionConstructor.newInstance(parameters);
		} catch (InstantiationException e) {
			System.err.println("Class " + subInstructionClassName + " constructor represents an abstract class");
			System.err.println(getProgramStatement(label, line, opcode));
		} catch (IllegalAccessException e) {
			System.err.println("Class " + subInstructionClassName + " constructor is private or inaccessible");
			System.err.println(getProgramStatement(label, line, opcode));
		} catch (InvocationTargetException e) {
			System.err.println("Class " + subInstructionClassName + " constructor is throwing an exception: "+e.getTargetException());
			System.err.println(getProgramStatement(label, line, opcode));
		} 
		return null;
	}


	private Class<?>[] getArgumentTypes(String[] args) {
		Class<?>[] Types = new Class[args.length];
		for (int i = 0; i < args.length; i++) {
			try {
				// check if the argument is of type RegisterName.class
				String key = args[i];
				if (Arrays.stream(Register.values()).anyMatch((t) -> t.name().equals(key))) {
					Types[i] = RegisterName.class;
				} else {
					// check if String argument can be parsed into Integer, if yes, then assign type
					// of argument as int.class
					Integer.parseInt(args[i]);
					Types[i] = int.class;
				}
			} catch (NumberFormatException e) {
				// get the class of argument if its of type other than int.class or
				// RegisterName.class
				Types[i] = args[i].getClass();
			}
		}
		return Types;
	}
	
	private String getProgramStatement(String label, String line, String opcode) {
		return label + ": "+opcode+ " "+line;
	}
}
package deserializer;

import java.util.*;
import java.lang.reflect.*;

// modified code from Assignment 2
public class Visualizer {
	
	private static void inspectDeclaredConstructors(Class classObject) {
		// print out header title for declared constructors of class
		System.out.println("Declared Constructors:");
		// use getDeclaredConstructors() to get all of the constructors the class declares
		Constructor[] classConstructors = classObject.getDeclaredConstructors();
		// iterate through each element in classConstructors
		for (Constructor classConstructor : classConstructors) {
			// print out the name of classConstructor
			System.out.println("\t" + classConstructor.getName());
			
			// use getParameterTypes() to get all of the parameter types of classConstructor
			Class[] classConstructorParameterTypes = classConstructor.getParameterTypes();
			// print out header title for parameter types of classMethod
			System.out.println("\t Parameter Types:");
			// iterate through each element in classMethodParameterTypes
			for (Class classConstructorParameterType : classConstructorParameterTypes) {
				// print out the name of each parameter type for classMethod
				System.out.println("\t\t" + classConstructorParameterType.getCanonicalName());
			}
			
			// use getModifiers() to get the int representation of modifiers applied to classConstructor
			int classConstructorModifiers = classConstructor.getModifiers();
			// print out the modifiers applied to classConstructor, using toString from Modifier class
			System.out.println("\t Modifiers: " + Modifier.toString(classConstructorModifiers));
		}
	}
	
	private static void inspectFields(Object obj, Class classObject, List<Object> objectsInspected, boolean recursive) {
		// locally keep track of fields that need to be inspected
		List<Field> classFieldsToInspect = new ArrayList<Field>();
		// print out header title for declared fields of class
		System.out.println("Declared Fields:");
		// use getFields() to get all of the fields the class declares
		Field[] classFields = classObject.getDeclaredFields();
		// iterate through each element in classFields
		for (Field classField : classFields) {
			// here we try to set the field to be accessible in case if it is non-public
			try {
				classField.setAccessible(true);
			} catch (SecurityException | InaccessibleObjectException e) {
				// print message stating current method is inaccessible
				System.out.println("\t" + classField.getName() + " is inaccessible");
				// continue to the next class method
				continue;
			}
			// print out the name of classField
			System.out.println("\t" + classField.getName());
			// use getType() to get the type of classField
			Class classFieldType = classField.getType();
			// print out the type of classField
			System.out.println("\t Type: " + classFieldType.getCanonicalName());
			
			// use getModifiers() to get the int representation of modifiers applied to classField
			int classFieldModifiers = classField.getModifiers();
			// print out the modifiers applied to classField, using toString from Modifier class
			System.out.println("\t Modifiers: " + Modifier.toString(classFieldModifiers));
			
			// check if classField is an array using isArray(), as these are handled differently
			if (classFieldType.isArray()) {
				// get component type of classField using getComponentType()
				Class classFieldComponentType = classFieldType.getComponentType();
				// print out the component type of classField
				System.out.println("\t Component Type: " + classFieldComponentType.getCanonicalName());
				
				// try to get length of classField using Array.getLength()
				try {
					int classFieldLength = Array.getLength(classField.get(obj));
					// print out the length of classField
					System.out.println("\t Length: " + classFieldLength);
					// check if recursive is false
					if (!recursive)
						// if so, print out the reference value of the array
						System.out.println("\t Reference Value: " + classObject.getCanonicalName() + "@" + obj.hashCode());
					// iterate through nested elements in classField
					List<List<Object>> classFieldArray = new ArrayList<List<Object>>();
					for (int i = 0; i < classFieldLength; i++) {
						// instantiate new ArrayList for each row
						classFieldArray.add(new ArrayList<Object>());
						Object classFieldRow = Array.get(classField.get(obj), i);
						// check to see if classFieldRow is null
						if (classFieldRow != null) {
							// check to see if classFieldRow is also an array
							if (classFieldRow.getClass().isArray()) {
								// calculate length of the row
								int classFieldRowLength = Array.getLength(classFieldRow);
								for (int j = 0; j < classFieldRowLength; j++) {
									// add element to classFieldArray
									System.out.println(Array.get(Array.get(classField.get(obj), i), j));
									classFieldArray.get(i).add(j, Array.get(Array.get(classField.get(obj), i), j));
								}
							}
						}
					}
					System.out.println("\t Contents: " + classFieldArray);
				} catch (IllegalAccessException e) { }
			} else {
				// if classField is not an array, then we just get the value of it and print it
				try {
					// print out the value of classField
					System.out.println("\t Value: " + classField.get(obj));
				} catch (IllegalAccessException e) { }
			}
			
			/*
			 * Add classField to classFieldsToInspect if it meets criteria:
			 * - recursive is true
			 * - classField is non-primitive
			 * - classField has not been inspected previously
			 * - value of classField is not null
			 */
			try {
				if (recursive && !classField.getType().isPrimitive() && !objectsInspected.contains(classField.get(obj)) && classField.get(obj) != null)
					classFieldsToInspect.add(classField);
			} catch (IllegalAccessException e) { }
		}
		
		// recurse on all fields that need to be inspected
		for (Field classFieldToInspect : classFieldsToInspect) {
			try {
				System.out.println("---------- FIELD INSPECTION: " + classObject.getCanonicalName() + " ----------");
				inspectObject(classFieldToInspect.get(obj), classFieldToInspect.get(obj).getClass(), objectsInspected, recursive);
			} catch (IllegalAccessException e) { }
		}
		
	}
	
	public static void inspectObject(Object obj, Class classObject, List<Object> objectsInspected, boolean recursive) {
		// base case - don't investigate object if it is null
		if (obj == null)
			return;
		// check to see if object we are currently inspecting is an Array (multidimensional field recursive case)
		if (classObject.isArray()) {
			// start by getting length of array
			int objectArrayLength = Array.getLength(obj);
			// iterate through each element in obj
			for (int i = 0; i < objectArrayLength; i++) {
				if (Array.get(obj, i) != null && !classObject.getComponentType().isPrimitive() && !objectsInspected.contains(Array.get(obj, i)))
					inspectObject(Array.get(obj, i), Array.get(obj, i).getClass(), objectsInspected, recursive);
			}
		}
		// add obj to objectsInspected
		objectsInspected.add(obj);
		// print out name of object being inspected during this execution
		System.out.println("Object Inspected: " + classObject.getCanonicalName());
		// print out current status of recursion
		System.out.println("Recursion Status: " + recursive);
		
		inspectDeclaredConstructors(classObject);
		
		inspectFields(obj, classObject, objectsInspected, recursive);
		
	}
	
	public static void inspect(Object obj, boolean recursive) {
		List<Object> objectsInspected = new ArrayList<Object>();
		// call helper method inspectObject() to begin recursion
		inspectObject(obj, obj.getClass(), objectsInspected, recursive);
	}
}

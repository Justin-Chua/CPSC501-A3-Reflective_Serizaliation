package application;

import java.util.*;
import objects.*;

public class TextGUI {
    
    // use Scanner library to handle user input
    private final Scanner INPUT = new Scanner(System.in);
    // create ArrayList of Object type to hold all user created objects
    private ArrayList<Object> userObjects = new ArrayList<Object>();

    // used to handle creation of object type 1 in text interface
    private void createPrimitive() {
        // initialize temp variable to store user inputs
        String userInput;
        // print message stating that (1) Primitive was chosen by user
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" + 
            "(1) Primitive selected by user.");
        // start by instantiating default instance of Primitive
        Primitive obj = new Primitive();
        // prompt user to set a value for the numeric field
        System.out.print("Set the value of field 'numeric' (int): ");
        userInput = this.INPUT.nextLine();
        // keep field as default value if nothing is inputted by user
        if (!userInput.isEmpty())
            obj.setNumeric(Integer.parseInt(userInput));
        
        // prompt user to set a value for the decimal field
        System.out.print("Set the value of field 'decimal' (float): ");
        userInput = this.INPUT.nextLine();
        // keep field as default value if nothing is inputted by user
        if (!userInput.isEmpty())
            obj.setDecimal(Float.parseFloat(userInput));

        // prompt user to set a value for the message field
        System.out.print("Set the value of field 'message' (String): ");
        userInput = this.INPUT.nextLine();
        // keep field as default value if nothing is inputted by user
        if (!userInput.isEmpty())
            obj.setMessage(userInput);

        // prompt user to set a value for condition
        System.out.print("Set the value of field 'condition' (true/false): ");
        userInput = this.INPUT.nextLine();
        // keep field as default value if nothing is inputted by user
        if (!userInput.isEmpty() && userInput == "true")
            obj.setCondition(true);

        // now, all fields have presumably been set. Add object to userObjects
        userObjects.add(obj);
        // print message stating that object has been created
        System.out.println("(1) Primitive object created.\n" + 
        "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    }

    // used to handle creation of object type 2 in text interface
    private void createReference() {
        // initialize temp variable to store user inputs
        String userInput;
        // print message stating that (2) Reference was chosen by user
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" + 
            "(2) Reference selected by user.");
        // start by instantiating default instance of Reference
        Reference obj = new Reference();
        // initialize temp variables for setting fields of Primitive and Circular1 objects
        Primitive objPrimitive = obj.getPrimitive();
        
        // print message stating that we are looking at the fields of Primitive field
        System.out.println("Fields of 'primitive' field: ");
        // prompt user to set a value for the numeric field
        System.out.print("Set the value of field 'numeric' (int): ");
        userInput = this.INPUT.nextLine();
        // keep field as default value if nothing is inputted by user
        if (!userInput.isEmpty())
            objPrimitive.setNumeric(Integer.parseInt(userInput));
        
        // prompt user to set a value for the decimal field
        System.out.print("Set the value of field 'decimal' (float): ");
        userInput = this.INPUT.nextLine();
        // keep field as default value if nothing is inputted by user
        if (!userInput.isEmpty())
            objPrimitive.setDecimal(Float.parseFloat(userInput));

        // prompt user to set a value for the message field
        System.out.print("Set the value of field 'message' (String): ");
        userInput = this.INPUT.nextLine();
        // keep field as default value if nothing is inputted by user
        if (!userInput.isEmpty())
            objPrimitive.setMessage(userInput);

        // prompt user to set a value for condition
        System.out.print("Set the value of field 'condition' (true/false): ");
        userInput = this.INPUT.nextLine();
        // keep field as default value if nothing is inputted by user
        if (!userInput.isEmpty() && userInput == "true")
            objPrimitive.setCondition(true);
        
        // print message stating that we are looking at the fields of Circular field
        System.out.println("Fields of 'circular' field: ");
        // prompt user to set a value for condition
        System.out.print("Set the value of field 'name' (String): ");
        userInput = this.INPUT.nextLine();
        // keep field as default value if nothing is inputted by user
        if (!userInput.isEmpty())
            obj.getCircular().setName(userInput);
        
        // setup a circular reference
        // create a second object of type Circular
        Circular obj2 = new Circular();
        obj2.setCircular(obj.getCircular());
        obj.getCircular().setCircular(obj2.getCircular());

        // now, all fields have presumably been set. Add object to userObjects
        userObjects.add(obj);
        // print message stating that object has been created
        System.out.println("(2) Reference object created.\n" + 
        "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    }

    private void createPrimitiveArray() {
        // initialize temp variable to store user inputs
        String userInput;
        // print message stating that (3) PrimitiveArray was chosen by user
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" + 
            "(3) PrimitiveArray selected by user.");
        // declare PrimitiveArray object
        PrimitiveArray obj;

        // prompt user to specify size of array
        System.out.print("Set the size of field arrays 'types' and 'numerics': ");
        userInput = this.INPUT.nextLine();
        // declare temp variable for arrayLength
        int arrayLength;
        // if nothing inputted, use default constructor and set arrayLength to 1
        if (userInput.isEmpty()) {
            arrayLength = 1;
            obj = new PrimitiveArray();
        } else {
            arrayLength = Integer.parseInt(userInput);
            obj = new PrimitiveArray(arrayLength, arrayLength);
        }

        for (int i = 0; i < arrayLength; i++) {
            // set element i of names
            System.out.print("Set the value for element " + i + 
                " of field 'names' (String): ");
            userInput = this.INPUT.nextLine();
            if (!userInput.isEmpty())
                obj.setElementNames(i, userInput);
            
            // set element i of numerics
            System.out.print("Set the value for element " + i + 
                " of field 'numerics' (int): ");
            userInput = this.INPUT.nextLine();
            if (!userInput.isEmpty())
                obj.setElementNumerics(i, Integer.parseInt(userInput));

        }

        // now, all fields have presumably been set. Add object to userObjects
        userObjects.add(obj);
        // print message stating that object has been created
        System.out.println("(3) PrimitiveArray object created.\n" + 
        "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        
    }

    private void createReferenceArray() {
        // initialize temp variable to store user inputs
        String userInput;
        // print message stating that (4) ReferenceArray was chosen by user
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" + 
            "(4) ReferenceArray selected by user.");
        // declare ReferenceArray obj
        ReferenceArray obj;

        // prompt user to specify size of array
        System.out.print("Set the size of field array 'primitiveArray': ");
        userInput = this.INPUT.nextLine();
        // declare temp variable for arrayLength
        int arrayLength;
        // if nothing inputted, use default constructor and set arrayLength to 1
        if (userInput.isEmpty()) {
            arrayLength = 1;
            obj = new ReferenceArray();
        } else {
            arrayLength = Integer.parseInt(userInput);
            obj = new ReferenceArray(arrayLength);
        }

        // prompt user to set a value for the message field
        System.out.print("Set the value of field 'unit' (byte): ");
        userInput = this.INPUT.nextLine();
        // keep field as default value if nothing is inputted by user
        if (!userInput.isEmpty())
            obj.setUnit(Byte.parseByte(userInput));

        // now we need to prompt user for each element of field array 'primitiveArray'
        for (int i = 0; i < arrayLength; i++) {
            // print out message stating what element of 'primitiveArray' we are dealing with
            System.out.println("Fields of element " + i + " of primitiveArray");

            // prompt user to set a value for the numeric field
            System.out.print("Set the value of field 'numeric' (int): ");
            userInput = this.INPUT.nextLine();
            // keep field as default value if nothing is inputted by user
            if (!userInput.isEmpty())
                obj.getElementPrimitiveArray(i).setNumeric(Integer.parseInt(userInput));
            
            // prompt user to set a value for the decimal field
            System.out.print("Set the value of field 'decimal' (float): ");
            userInput = this.INPUT.nextLine();
            // keep field as default value if nothing is inputted by user
            if (!userInput.isEmpty())
                obj.getElementPrimitiveArray(i).setDecimal(Float.parseFloat(userInput));

            // prompt user to set a value for the message field
            System.out.print("Set the value of field 'message' (String): ");
            userInput = this.INPUT.nextLine();
            // keep field as default value if nothing is inputted by user
            if (!userInput.isEmpty())
                obj.getElementPrimitiveArray(i).setMessage(userInput);

            // prompt user to set a value for condition
            System.out.print("Set the value of field 'condition' (true/false): ");
            userInput = this.INPUT.nextLine();
            // keep field as default value if nothing is inputted by user
            if (!userInput.isEmpty() && userInput == "true")
                obj.getElementPrimitiveArray(i).setCondition(true);
        }

        // now, all fields have presumably been set. Add object to userObjects
        userObjects.add(obj);
        // print message stating that object has been created
        System.out.println("(4) ReferenceArray object created.\n" + 
        "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    }

    private void createObjectCollection() {
        // initialize temp variable to store user inputs
        String userInput;
        // print message stating that (4) ReferenceArray was chosen by user
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" + 
            "(5) ObjectCollection selected by user.");
        // declare ReferenceArray obj
        ObjectCollection obj = new ObjectCollection();

        // prompt user to specify size of array
        System.out.print("Set the size of field array 'primitiveArrayList': ");
        userInput = this.INPUT.nextLine();
        // declare temp variable for arrayLength
        int arrayLength;
        // if nothing inputted, use default constructor and set arrayLength to 1
        if (userInput.isEmpty()) 
            arrayLength = 1;
        else
            arrayLength = Integer.parseInt(userInput);

        // prompt user to set a value for the message field
        System.out.print("Set the value of field 'number' (long): ");
        userInput = this.INPUT.nextLine();
        // keep field as default value if nothing is inputted by user
        if (!userInput.isEmpty())
            obj.setNumber(Long.parseLong(userInput));

        // now we need to prompt user for each element of field array 'primitiveArray'
        for (int i = 0; i < arrayLength; i++) {
            // create a temp Primitive object, which will get added to primitiveArraylist
            Primitive element = new Primitive();
            // print out message stating what element of 'primitiveArray' we are dealing with
            System.out.println("Fields of element " + i + " of primitiveArrayList");

            // prompt user to set a value for the numeric field
            System.out.print("Set the value of field 'numeric' (int): ");
            userInput = this.INPUT.nextLine();
            // keep field as default value if nothing is inputted by user
            if (!userInput.isEmpty())
                element.setNumeric(Integer.parseInt(userInput));
            
            // prompt user to set a value for the decimal field
            System.out.print("Set the value of field 'decimal' (float): ");
            userInput = this.INPUT.nextLine();
            // keep field as default value if nothing is inputted by user
            if (!userInput.isEmpty())
                element.setDecimal(Float.parseFloat(userInput));

            // prompt user to set a value for the message field
            System.out.print("Set the value of field 'message' (String): ");
            userInput = this.INPUT.nextLine();
            // keep field as default value if nothing is inputted by user
            if (!userInput.isEmpty())
                element.setMessage(userInput);

            // prompt user to set a value for condition
            System.out.print("Set the value of field 'condition' (true/false): ");
            userInput = this.INPUT.nextLine();
            // keep field as default value if nothing is inputted by user
            if (!userInput.isEmpty() && userInput == "true")
                element.setCondition(true);
            
            // add element to primitiveArrayList in obj
            obj.addElementPrimitiveArrayList(element);
        }

        // now, all fields have presumably been set. Add object to userObjects
        userObjects.add(obj);
        // print message stating that object has been created
        System.out.println("(5) ObjectCollection object created.\n" + 
        "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    private void run() {
        /* AUTHOR NOTE: runs off the presumption that only valid inputs are supplied
         * for each line as of now. Input handling to be added later
         */
        // print welcome message
        System.out.println("====================================\n" + 
            "|          Object Creator          |\n" + 
            "|       Author: Justin Chua        |\n" + 
            "====================================\n");
        
        // print out option select
        System.out.println("          Object Selection\n" + "====================================\n" +
            "(1) * Primitive *\n" + " A simple object with only\n primitives for instance variables.\n" + 
            "(2) * Reference *\n" + " An object that contains references\n to other objects.\n" + 
            "(3) * PrimitiveArray *\n" + " An object that contains an array\n of primitives.\n" + 
            "(4) * ReferenceArray *\n" + " An object that contains an array\n of object references.\n" +
            "(5) * ObjectCollection *\n" + " An object that uses a Collection\n (ArrayList) to hold object\n references.\n" +
            "====================================");

        // prompt user to make selection
        System.out.print("Please select an object to create (1-5): ");
        String objectSelection = this.INPUT.nextLine();
        switch(objectSelection) {
            case "1":
                this.createPrimitive(); break;
            case "2":
                this.createReference(); break;
            case "3":
                this.createPrimitiveArray(); break; 
            case "4":
                this.createReferenceArray(); break;
            case "5":
                this.createObjectCollection(); break;
            default:
                System.out.println("Invalid choice"); break;
        }
        
        // close scanner
        this.INPUT.close();

        // iterate through userObjects, invoke serialize on each element
    }
    
    public static void main(String[] args) {
        TextGUI gui = new TextGUI();
        gui.run();
    }
}

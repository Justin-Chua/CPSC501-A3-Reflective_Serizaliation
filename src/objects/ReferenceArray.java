package objects;

// Class satisfying bullet point 4
public class ReferenceArray {
    
    private byte unit;
    // array of Primitive objects
    private Primitive[] primitiveArray;

    // initialize instance variables to default values, and listOfPrimitives to size of 1
    public ReferenceArray() {
        this.unit = 100;
        this.primitiveArray = new Primitive[1];
    }

    // initialize instance variables to default values, and listOfPrimitives to size specified by user
    public ReferenceArray(int size) {
        this.unit = 100;
        this.primitiveArray = new Primitive[size];

        // initialize each element to a default Primitive object
        for (int i = 0; i < size; i++) {
            this.primitiveArray[i] = new Primitive();
        }
    }

    // setter method for unit instance variable
    public void setUnit(byte unit) {
        this.unit = unit;
    }

    // getter method for element of primitiveArray by index
    public Primitive getElementPrimitiveArray(int index) {
        return this.primitiveArray[index];
    }

}

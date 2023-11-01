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
    }

    // setter method for unit instance variable
    public void setUnit(byte unit) {
        this.unit = unit;
    }


    /* going off assumption that field of each object does not need to be settable.
       * WAITING ON RESPONSE FROM TA * */

}

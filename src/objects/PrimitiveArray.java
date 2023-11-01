package objects;

// Class satisfying bullet point 3
public class PrimitiveArray {
    
    private char[] types;
    private int[] numerics;

    // initialize instance variables to default values with size of 1
    public PrimitiveArray() {
        this.types = new char[1];
        this.numerics = new int[1];
    }

    // initialize instance variables to default values with size specified by user
    public PrimitiveArray (int typesSize, int numericsSize) {
        this.types = new char[typesSize];
        this.numerics = new int[numericsSize];
    }

    /* use setter methods to set value of specified index in each instance variable 
    during operation of text interface */
    public void setElementTypes(int index, char type) {
        this.types[index] = type;
    }

    public void setElementNumerics(int index, int numeric) {
        this.numerics[index] = numeric;
    }
}

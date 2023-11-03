package objects;

// Class satisfying bullet point 3
public class PrimitiveArray {
    
    private String[] names;
    private int[] numerics;

    // initialize instance variables to default values with size of 1
    public PrimitiveArray() {
        this.names = new String[1];
        this.numerics = new int[1];
    }

    // initialize instance variables to default values with size specified by user
    public PrimitiveArray (int typesSize, int numericsSize) {
        this.names = new String[typesSize];
        this.numerics = new int[numericsSize];
    }

    /* use setter methods to set value of specified index in each instance variable 
    during operation of text interface */
    public void setElementNames(int index, String name) {
        this.names[index] = name;
    }

    public void setElementNumerics(int index, int numeric) {
        this.numerics[index] = numeric;
    }
}

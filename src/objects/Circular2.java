package objects;

public class Circular2 {
    
    private int value;
    // circular reference for Circular1 object
    private Circular1 circular1;

    // initialize instance variables to arbitrary values in default constructor
    public Circular2() {
        this.value = 0;
        this.circular1 = new Circular1();
    }

    public void setValue(int value) {
        this.value = value;
    }

    // setter method to be used to set up circular reference to Circular1
    public void setCircular(Circular1 circular) {
        this.circular1 = circular;
    }

    // getter method for Circular1 to be used to inspect and set its fields
    public Circular1 getCircular() {
        return this.circular1;
    }
}

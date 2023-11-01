package objects;

public class Circular1 {
    
    private String name;
    // circular reference to Circular2 object
    private Circular2 circular2;

    // initialize instance variables to arbitrary values in default constructor
    public Circular1() {
        this.name = "Tom";
        this.circular2 = new Circular2();
    }

    public void setName(String name) {
        this.name = name;
    }

    // setter method to be used to set up circular reference to Circular1
    public void setCircular(Circular2 circular) {
        this.circular2 = circular;
    }

    // getter method for Circular2 to be used to inspect and set its fields
    public Circular2 getCircular() {
        return this.circular2;
    }

}

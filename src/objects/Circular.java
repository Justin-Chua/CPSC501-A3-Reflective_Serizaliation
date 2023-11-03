package objects;

public class Circular {
    
    private String name;
    // circular reference to Circular2 object
    private Circular itself;

    // initialize instance variables to arbitrary values in default constructor
    public Circular() {
        this.name = "Tom";
    }

    public void setName(String name) {
        this.name = name;
    }

    // setter method to be used to set up circular reference to Circular
    public void setCircular(Circular circular) {
        this.itself = circular;
    }

    // getter method for Circular to be used to inspect and set its fields
    public Circular getCircular() {
        return this.itself;
    }

}

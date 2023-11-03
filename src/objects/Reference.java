package objects;

// Class satisfying bullet point 2
public class Reference {
    
    // Primitive object
    private Primitive primitive;
    // Circular object
    private Circular circular;

    public Reference() {
        this.primitive = new Primitive();
        this.circular = new Circular();
    }

    // getter methods for all instance variables, so that we can access them and set their fields
    public Primitive getPrimitive() {
        return this.primitive;
    }

    public Circular getCircular() {
        return this.circular;
    }

}

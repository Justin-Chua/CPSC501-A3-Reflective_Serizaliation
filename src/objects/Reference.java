package objects;

// Class satisfying bullet point 3
public class Reference {
    
    // Primitive object
    private Primitive primitive;
    // Circular1 object - shares circular reference to Circular2
    private Circular1 circular;

    public Reference() {
        this.primitive = new Primitive();
        this.circular = new Circular1();
    }

    // getter methods for all instance variables, so that we can access them and set their fields
    public Primitive getPrimitive() {
        return this.primitive;
    }

    public Circular1 getCircular() {
        return this.circular;
    }

}

package objects;

// Class satisfying bullet point 1
public class Primitive {
    
    private int numeric;
    private float decimal;
    private String message;
    private boolean condition;

    // initialize instance variables to arbitrary values in default constructor
    public Primitive() {
        this.numeric = 0;
        this.decimal = 0.0f;
        this.message = "Hello";
        this.condition = false;
    }

    // use setter methods to set values of instance variables during operation of text interface
    public void setNumeric(int numeric) {
        this.numeric = numeric;
    }

    public void setDecimal(float decimal) {
        this.decimal = decimal;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCondition(boolean condition) {
        this.condition = condition;
    }
}

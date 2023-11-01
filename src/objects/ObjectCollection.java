package objects;

import java.util.*;

// Class satisfying bullet point 5 - identical to ReferenceArray, instead using ArrayList
public class ObjectCollection {
    private long number;
    // array of Primitive objects
    private ArrayList<Primitive> primitiveArrayList;

    // initialize instance variables to default values
    public ObjectCollection() {
        this.number = 1000;
        this.primitiveArrayList = new ArrayList<Primitive>();
    }

    // setter method for unit instance variable
    public void setUnit(long number) {
        this.number = number;
    }

    /* used during operation of text interface, to add instantiated Primitive object
       to primitiveArrayList. */
    public void addElementPrimitiveArrayList(Primitive object) {
        this.primitiveArrayList.add(object);
    }

    /* going off assumption that field of each object does not need to be settable.
       * WAITING ON RESPONSE FROM TA * */

}
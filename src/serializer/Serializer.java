package serializer;

import java.util.*;
import java.lang.reflect.*;
import org.jdom2.*;

public class Serializer {
    
    // keep track of objects already serialized
    private IdentityHashMap<Integer, Integer> serializedObjects = new IdentityHashMap<>();
    // create blank Document - will be edited recursively using serialize
    private Document doc;

    private void serializeObject(Object obj) {
        // initialize temp variable to keep track of objects that need to be serialized recursively
        ArrayList<Object> recursiveObjects = new ArrayList<Object>();
        // start by creating an element with label obj
        Element objElement = new Element("object");
        // assign attributes class and id to object
        objElement.setAttribute("class", obj.getClass().getName());
        // id based off of number of elements in serializedObjects
        objElement.setAttribute("id", Integer.toString(this.serializedObjects.size()));
        // place into serializedObjects - in case if we run into any non-primitive fields
        this.serializedObjects.put(obj.hashCode(), this.serializedObjects.size());
        // check if the object is an array
        if (obj.getClass().isArray()) {
            // if so, add additional attribute length
            objElement.setAttribute("length", Integer.toString(Array.getLength(obj)));
            // additionally, we need to check component type
            if (obj.getClass().getComponentType().isPrimitive()) {
                // loop for length of array
                for (int i = 0; i < Array.getLength(obj); i++) {
                    // need to add value Element for each element of obj array
                    Element value = new Element("value");
                    // set value
                    value.setText(Array.get(obj, i).toString());
                    // add value as content to our obj
                    objElement.addContent(value);
                }
            } else {
                // loop for length of array
                for (int i = 0; i < Array.getLength(obj); i++) {
                    // need to add reference Element for each element of obj array
                    Element reference = new Element("reference");
                    // set reference id
                    reference.setText(Integer.toString(this.serializedObjects.size() + i));
                    // add to recursiveObjects, only if it has not already been serialized
                    if (!this.serializedObjects.containsKey(Array.get(obj, i).hashCode()))
                        recursiveObjects.add(Array.get(obj, i));
                    // add reference as content to our obj
                    objElement.addContent(reference);
                }
            }
        } else {
            // now we need to look at the fields of object
            for (Field objField : obj.getClass().getDeclaredFields()) {
                // try to set the field accessible in case if it is non-public - from A2
                try {
                    objField.setAccessible(true);
                } catch (SecurityException | InaccessibleObjectException e) {
                    // print message stating that the object is inaccessible
                    System.out.println(objField.getName() + " is inaccessible");
                    // continue to next object
                    continue;
                }
                // create temp element for current field
                Element fieldElement = new Element("field");
                // assign name and declaringclass attributes
                fieldElement.setAttribute("name", objField.getName());
                fieldElement.setAttribute("declaringclass", objField.getDeclaringClass().getName());
                // now we need to check type of field, as primitives/object references are handled differently
                if (objField.getType().isPrimitive()) {
                    // in this case, we need to add value Element to our field
                    Element fieldValue = new Element("value");
                    try {
                        // try to set the text of fieldValue to the value of field, parsed as a string
                        fieldValue.setText(objField.get(obj).toString());
                    } catch (IllegalArgumentException | IllegalAccessException e) { }
                } else {
                    // otherwise, field is an object and we need to add reference Element to our field
                    Element fieldReference = new Element("reference");
                    // set the text of fieldReference to id of object
                    fieldReference.setText(Integer.toString(this.serializedObjects.size()));
                    try {
                        // afterwards, add it to fieldObjects
                        recursiveObjects.add(objField.get(obj));
                    } catch (IllegalArgumentException | IllegalAccessException e) { }
                }
                // add field as content to our obj
                objElement.addContent(fieldElement);
            }
        }
        // finally, we add obj as content to root element of doc
        this.doc.getRootElement().addContent(objElement);

        // recursively call serializeObjects on fields that are object references
        for (Object recursiveObject : recursiveObjects)
            this.serializeObject(recursiveObject);

    }

    public org.jdom2.Document serialize(Object obj) {
        // create new element, with label "serialized"
        Element rootElement = new Element("serialized");
        // create new document, with root element "serialized"
        doc = new Document(rootElement);
        // call helper function, to serialize objects recursively without resetting root element
        this.serializeObject(obj);

        return doc;
    }

}

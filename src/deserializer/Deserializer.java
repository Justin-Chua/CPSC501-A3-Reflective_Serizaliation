package deserializer;

import java.io.File;
import org.jdom2.*;
import java.lang.reflect.*;
import java.util.*;

public class Deserializer {

    // helper method for setting value of a field
    private void setField() {

    }

    public Object deserialize(org.jdom2.Document document) {
        // start by getting root element of our XML document
        Element rootElement = document.getRootElement();
        // now we get all children of serialized, which are objects
        List<Element> elementObjects = rootElement.getChildren("object");
        // iterate through each object
        for (Element elementObj : elementObjects) {
            // we will now attempt to create a new default instance of currentobject
            try {
                // get classObj instance using value of class attribute
                Class classObj = Class.forName(elementObj.getAttribute("class").getValue());
                // get default constructor of classObj
                Constructor constructorObj = classObj.getDeclaredConstructor(null);
                // set constructor accessible in case if it is private
                constructorObj.setAccessible(true);
                // create new instance of classObj
                Object obj = constructorObj.newInstance(null);
                
                // get all fields of obj
                List<Element> objFields = elementObj.getChildren("fields");
                // iterate through each field
                for (Element objField : objFields) {
                    String fieldName = objField.getAttribute("name").getValue();
                    Field field = classObj.getDeclaredField(fieldName);
                }

            } catch (InstantiationException | IllegalAccessException | 
                ClassNotFoundException | NoClassDefFoundError | 
                InvocationTargetException | NoSuchMethodException |
                NoSuchFieldException e) { }
        }
        return new Document(new Element("balls"));
    }

}

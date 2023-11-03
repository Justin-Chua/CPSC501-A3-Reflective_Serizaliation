package serializer;

import java.net.Socket;
import java.io.*;
import org.jdom2.*;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import application.*;

public class Sender {

    public static void main(String[] args) {
        // start by calling TextGUI so that we can create an object
        TextGUI gui = new TextGUI();
        gui.run();
        Object userObject = gui.getUserObject();

        // send userObject to the serializer
        Serializer serializer = new Serializer();
        // store result of serialize into doc
        Document doc = serializer.serialize(userObject);

        // create a socket to connect to the server
        try {
            Socket socket = new Socket("localhost", 1000);
            // get output stream of socket
            OutputStream outputStream = socket.getOutputStream();
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);

            // convert doc into bytes
            XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            xmlOutputter.output(doc, byteArrayOutputStream);
            byte[] documentBytes = byteArrayOutputStream.toByteArray();
            // send doc to server
            bufferedOutputStream.write(documentBytes);

            // close streams and socket
            bufferedOutputStream.flush();
            socket.close();
        } catch (Exception e) { }
    }
}

package deserializer;

import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
import org.jdom2.*;
import org.jdom2.input.*;
import org.jdom2.output.*;

public class Receiver {
    
    public static void main(String[] args) {
        
        try {
            // code referenced from tutorial

            // create a server socket
            ServerSocket serverSocket = new ServerSocket(1000);

            System.out.println("Waiting for a connection from client...");
            // accept connection from client
            Socket socket = serverSocket.accept();
            System.out.println("Connection established with client.");

            // get input stream of socket
            InputStream inputStream = socket.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

            // build the XML document
            SAXBuilder saxBuilder = new SAXBuilder();
            Document doc = saxBuilder.build(bufferedInputStream);

            // now we pass document into deserializer
            Deserializer deserializer = new Deserializer();
            Object obj = deserializer.deserialize(doc);

            // pass obj into visualizer
            Visualizer.inspect(obj, true);

            // close streams and socket
            bufferedInputStream.close();
            socket.close();
            serverSocket.close();

        } catch (Exception e) { }
    }
}

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.io.*;

public class Main {
	 
    public static void main(String[] args) {
        try{
            Socket socket = new Socket("localhost", 8866);
 
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
 
            while(true){       
 
            }
           } catch (UnknownHostException e) {
             System.out.println(e.getMessage());
             System.exit(1);
           } catch  (IOException e) {
             System.out.println("No I/O");
             System.exit(1);
           }
    }
 
 
}

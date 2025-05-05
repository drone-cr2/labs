package RMI.Socket;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        int port = 5000;  // Port number

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started. Waiting for a client...");

            Socket clientSocket = serverSocket.accept();  // Accept client connection
            System.out.println("Client connected!");

            // Input and output streams
            DataInputStream input = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());

            // Read two numbers from client
            int num1 = input.readInt();
            int num2 = input.readInt();

            int result = num1 + num2;

            // Send result back to client
            output.writeInt(result);

            System.out.println("Received: " + num1 + " and " + num2 + ". Sent result: " + result);

            // Close connections
            input.close();
            output.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


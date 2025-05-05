package RMI.Socket;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 5000;

        try (Socket socket = new Socket(serverAddress, port)) {
            System.out.println("Connected to server.");

            // Streams
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter first number: ");
            int num1 = scanner.nextInt();
            System.out.print("Enter second number: ");
            int num2 = scanner.nextInt();

            // Send numbers to server
            output.writeInt(num1);
            output.writeInt(num2);

            // Receive result
            int result = input.readInt();
            System.out.println("Result from server: " + result);

            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


server.java
import java.io.*;
import java.net.*;

public class server {
    public static void main(String[] args) {
        try {
            // Create server socket and wait for client to connect
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server listening on port 12345...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected!");

            // Create input and output streams for communication
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            String clientMessage, serverMessage;
            while (true) {
                // Receive message from client
                clientMessage = in.readLine();
                if (clientMessage == null || clientMessage.equalsIgnoreCase("exit")) {
                    break;
                }
                System.out.println("Client: " + clientMessage);

                // Send message to client
                System.out.print("Server: ");
                serverMessage = userInput.readLine();
                out.println(serverMessage);
            }

            // Close sockets and streams
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

client.java
import java.io.*;
import java.net.*;

public class client {
    public static void main(String[] args) {
        try {
            // Connect to server
            Socket socket = new Socket("localhost", 12345); // Replace SERVER_IP_ADDRESS with actual IP
            System.out.println("Connected to server!");

            // Create input and output streams for communication
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            String clientMessage, serverMessage;
            while (true) {
                // Send message to server
                System.out.print("Client: ");
                clientMessage = userInput.readLine();
                out.println(clientMessage);

                // Receive message from server
                serverMessage = in.readLine();
                if (serverMessage == null || clientMessage.equalsIgnoreCase("exit")) {
                    break;
                }
                System.out.println("Server: " + serverMessage);
            }

            // Close sockets and streams
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

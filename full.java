server.java
import java.io.*;
import java.net.*;

public class server1 {
    public static void main(String[] args) {
        try {
            // Create server socket
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server listening on port 12345...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected!");

            // Create threads for receiving and sending messages
            new Thread(new Receiver(clientSocket)).start();
            new Thread(new Sender(clientSocket)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Thread class for receiving messages
    static class Receiver implements Runnable {
        private Socket socket;

        public Receiver(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Client: " + message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Thread class for sending messages
    static class Sender implements Runnable {
        private Socket socket;

        public Sender(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
                String message;
                while ((message = userInput.readLine()) != null) {
                    out.println(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


client.java

import java.io.*;
import java.net.*;

public class client1 {
    public static void main(String[] args) {
        try {
            // Connect to server
            Socket socket = new Socket("localhost", 12345); // Replace SERVER_IP_ADDRESS with actual IP
            System.out.println("Connected to server!");

            // Create threads for receiving and sending messages
            new Thread(new Receiver(socket)).start();
            new Thread(new Sender(socket)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Thread class for receiving messages
    static class Receiver implements Runnable {
        private Socket socket;

        public Receiver(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Server: " + message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Thread class for sending messages
    static class Sender implements Runnable {
        private Socket socket;

        public Sender(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
                String message;
                while ((message = userInput.readLine()) != null) {
                    out.println(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

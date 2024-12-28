import java.io.*;
import java.net.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class MyTcpEchoServer {
    // Thread-safe list to store client sockets
    private static CopyOnWriteArrayList<ClientHandler> clients = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        int portNumber = 8888;
        
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Server is running on port " + portNumber);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());
                
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clients.add(clientHandler);
                
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    static class ClientHandler implements Runnable {
        private Socket clientSocket;
        private BufferedReader in;
        private PrintWriter out;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);
            } catch (IOException e) {
                System.out.println("Error initializing client handler: " + e.getMessage());
                closeConnection();
            }
        }

        @Override
        public void run() {
            try {
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Received from client: " + message);

                    // Broadcast the message to all clients
                    broadcastMessage("Client " + clientSocket.getInetAddress() + ":" + clientSocket.getPort() + " says: " + message);
                }
            } catch (IOException e) {
                System.out.println("Client handler error: " + e.getMessage());
            } finally {
                closeConnection();
            }
        }

        private void broadcastMessage(String message) {
            for (ClientHandler client : clients) {
                if (client == this) { // Do not send the message back to the sender
                    client.sendMessage(message);
                }
            }
        }

        private void sendMessage(String message) {
            out.println(message);
        }

        private void closeConnection() {
            try {
                clients.remove(this);
                if (clientSocket != null && !clientSocket.isClosed()) {
                    clientSocket.close();
                }
                System.out.println("Client disconnected: " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());
            } catch (IOException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}

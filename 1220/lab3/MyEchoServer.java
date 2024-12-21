import java.net.*;
import java.io.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class MyEchoServer {
    // Thread-safe list to store client addresses
    private static CopyOnWriteArrayList<ClientInfo> clients = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        int portNumber = 8888;
        try (DatagramSocket serverSocket = new DatagramSocket(portNumber)) {
            System.out.println("Server is running on port " + portNumber);

            byte[] receiveBuffer = new byte[1024];

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                serverSocket.receive(receivePacket);

                // Register the client in the list if not already present
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                ClientInfo newClient = new ClientInfo(clientAddress, clientPort);

                if (!clients.contains(newClient)) {
                    clients.add(newClient);
                    System.out.println("New client added: " + clientAddress + ":" + clientPort);
                }

                new Thread(new ClientHandler(receivePacket, serverSocket)).start();
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    static class ClientInfo {
        private InetAddress address;
        private int port;

        public ClientInfo(InetAddress address, int port) {
            this.address = address;
            this.port = port;
        }

        public InetAddress getAddress() {
            return address;
        }

        public int getPort() {
            return port;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            ClientInfo that = (ClientInfo) obj;
            return port == that.port && address.equals(that.address);
        }

        @Override
        public int hashCode() {
            return address.hashCode() * 31 + port;
        }
    }

    static class ClientHandler implements Runnable {
        private DatagramPacket receivePacket;
        private DatagramSocket serverSocket;

        public ClientHandler(DatagramPacket receivePacket, DatagramSocket serverSocket) {
            this.receivePacket = receivePacket;
            this.serverSocket = serverSocket;
        }

        public void run() {
            try {
                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received from client: " + message);


                // Broadcast the message to all clients
                for (ClientInfo client : clients) {
                    String clientInfoMessage = "Client " + receivePacket.getAddress() + ":" + receivePacket.getPort() + " says: " + message;
                    byte[] sendBuffer = clientInfoMessage.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, client.getAddress(), client.getPort());
                    serverSocket.send(sendPacket);
                    System.out.println("Sent to client: " + client.getAddress() + ":" + client.getPort());
                }
            } catch (IOException e) {
                System.out.println("Client handler error: " + e.getMessage());
            }
        }
    }
}

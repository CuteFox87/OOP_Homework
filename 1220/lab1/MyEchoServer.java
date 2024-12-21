import java.net.*;
import java.io.*;

public class MyEchoServer {
    public static void main(String[] args) {
        int portNumber = 8888;
        try (DatagramSocket serverSocket = new DatagramSocket(portNumber)) {
            System.out.println("Server is running on port " + portNumber);

            byte[] receiveBuffer = new byte[1024];

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                serverSocket.receive(receivePacket);

                // Create a new thread to handle the client
                new Thread(new ClientHandler(receivePacket, serverSocket)).start();
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}

class ClientHandler implements Runnable {
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

            // Echo the message back
            byte[] sendBuffer = message.getBytes();
            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();

            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress, clientPort);
            serverSocket.send(sendPacket);
        } catch (IOException e) {
            System.out.println("Client handler error: " + e.getMessage());
        }
    }
}
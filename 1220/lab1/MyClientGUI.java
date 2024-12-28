import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class MyClientGUI extends JFrame {
    private DatagramSocket clientSocket;
    private InetAddress serverAddress;
    private int serverPort = 8888;

    public MyClientGUI() {
        setTitle("MyClientGUI");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // GUI components
        JTextField textField = new JTextField();
        JTextArea textArea = new JTextArea();
        JButton addButton = new JButton("Send");
        JButton clearButton = new JButton("Clear");

        // Menu setup
        JMenuBar menuBar = new JMenuBar();
        JMenu func = new JMenu("Func");
        JMenuItem saveText = new JMenuItem("Save");
        JMenuItem loadText = new JMenuItem("Load");
        JMenu menu = new JMenu("Menu");
        JMenuItem clearMenuItem = new JMenuItem("Clear");

        func.add(saveText);
        func.add(loadText);
        menuBar.add(func);
        menu.add(clearMenuItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // textArea can't be edited
        textArea.setEditable(false);

        // Set up layout
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.add(addButton);
        panel.add(clearButton);

        add(textField, BorderLayout.NORTH);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        // Event listeners
        textField.addActionListener(e -> sendMessage(textField, textArea));
        addButton.addActionListener(e -> sendMessage(textField, textArea));

        clearButton.addActionListener(e -> textArea.setText(""));

        saveText.addActionListener(e -> saveContent(textArea));
        loadText.addActionListener(e -> loadContent(textArea));
        clearMenuItem.addActionListener(e -> textArea.setText(""));

        setVisible(true);

        initializeClient(textArea);
    }

    private void sendMessage(JTextField textField, JTextArea textArea) {
        String message = textField.getText();
        if (message.isEmpty()) return;

        try {
            byte[] sendBuffer = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, serverPort);
            clientSocket.send(sendPacket);

            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            clientSocket.receive(receivePacket);

            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            textArea.append("Server: " + response + "\n");
            while (receivePacket.getLength() == receiveBuffer.length) {
                clientSocket.receive(receivePacket);
                response = new String(receivePacket.getData(), 0, receivePacket.getLength());
                textArea.append("Server: " + response + "\n");
            }

            // Clear the text field
            textField.setText("");
        } catch (IOException ex) {
            textArea.append("Error communicating with server: " + ex.getMessage() + "\n");
        }
    }

    private void saveContent(JTextArea textArea) {
        try {
            FileWriter writer = new FileWriter("content.txt");
            writer.write(textArea.getText());
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loadContent(JTextArea textArea) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("content.txt"));
            textArea.read(reader, null);
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void initializeClient(JTextArea textArea) {
        try {
            clientSocket = new DatagramSocket();
            serverAddress = InetAddress.getByName("localhost");
            textArea.append("Client initialized.\n");
        } catch (SocketException | UnknownHostException ex) {
            textArea.append("Error initializing client: " + ex.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
        new MyClientGUI();
    }
}

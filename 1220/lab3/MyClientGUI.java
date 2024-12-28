import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class MyClientGUI extends JFrame {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private String serverHost = "localhost";
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
            out.println(message);
            out.flush();
            textField.setText("");
        } catch (Exception ex) {
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
            clientSocket = new Socket(serverHost, serverPort);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            textArea.append("Client initialized. Connected to server.\n");

            // Start a thread to listen for incoming messages
            new Thread(() -> {
                try {
                    String response;
                    while ((response = in.readLine()) != null) {
                        textArea.append(response + "\n");
                    }
                } catch (IOException ex) {
                    textArea.append("Error receiving data from server: " + ex.getMessage() + "\n");
                } finally {
                    closeConnection();
                }
            }).start();

        } catch (IOException ex) {
            textArea.append("Error initializing client: " + ex.getMessage() + "\n");
        }
    }

    private void closeConnection() {
        try {
            if (clientSocket != null && !clientSocket.isClosed()) {
                clientSocket.close();
            }
        } catch (IOException ex) {
            System.err.println("Error closing connection: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new MyClientGUI();
    }
}

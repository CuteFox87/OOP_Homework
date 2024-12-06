import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class lab1 extends JFrame{
    
    public lab1(){
        setTitle("lab1");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JTextField textField = new JTextField();
        JTextArea textArea = new JTextArea();
        JButton addButton = new JButton("Add");
        JButton clearButton = new JButton("Clear");

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

        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.append(textField.getText() + "\n");
                textField.setText("");
            }
        });
        
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.append(textField.getText() + "\n");
                textField.setText("");
            }
        });
        
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
            }
        });

        saveText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileWriter writer = new FileWriter("content.txt");
                    writer.write(textArea.getText());
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        loadText.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("content.txt"));
                    textArea.read(reader, null);
                    reader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        clearMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            textArea.setText("");
            }
        });
        
        setLayout(new BorderLayout());
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.add(addButton);
        panel.add(clearButton);
        
        add(textField, BorderLayout.NORTH);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new lab1();
    }
}

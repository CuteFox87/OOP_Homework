import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MyJFrame extends JFrame {
	
	public MyJFrame(String title) {
		super(title);
		this.setLocation(150,250);
		this.setSize(300,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = this.getContentPane();
		contentPane.setBackground(Color.WHITE);
		
		contentPane.setLayout(new FlowLayout());
		contentPane.setLayout(new GridLayout(3, 3));
		
		MyActionListener actionListener = new MyActionListener();
		
		for(int i = 0; i < 10; i++) {
			JButton button = new JButton(Integer.toString(i));
			button.addActionListener(actionListener);
			contentPane.add(button);
		}
		
		this.setVisible(true);
	}
	
    public static void main(String[] args) {
        MyJFrame frame1 = new MyJFrame("Frame 1");
	}
}

class MyActionListener implements ActionListener {
	
	public void actionPerformed(ActionEvent event) {
		JButton clickedButton = (JButton) event.getSource();
		String buttonText = clickedButton.getText();
		int value = Integer.parseInt(buttonText);
		
		clickedButton.setEnabled(false);
	}
}

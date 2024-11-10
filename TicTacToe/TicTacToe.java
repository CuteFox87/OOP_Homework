import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame implements ActionListener {
    private static final int SIZE = 3;
    private JButton[][] buttons = new JButton[SIZE][SIZE];
    private boolean playerXTurn = true; // True for X, False for O
    private String winner = null;

    public TicTacToe() {
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(SIZE, SIZE));
        initializeButtons();
        setVisible(true);
    }

    private void initializeButtons() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[row][col].setFocusPainted(false);
                buttons[row][col].addActionListener(this);
                add(buttons[row][col]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();

        if (!clickedButton.getText().equals("") || winner != null) {
            return;
        }

        if (playerXTurn) {
            clickedButton.setText("X");
        } else {
            clickedButton.setText("O");
        }

        playerXTurn = !playerXTurn;

        checkWinner();
    }

    private void checkWinner() {
        // Check rows and columns
        for (int i = 0; i < SIZE; i++) {
            if (buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                buttons[i][1].getText().equals(buttons[i][2].getText()) &&
                !buttons[i][0].getText().equals("")) {
                winner = buttons[i][0].getText();
                showWinner();
                return;
            }

            if (buttons[0][i].getText().equals(buttons[1][i].getText()) &&
                buttons[1][i].getText().equals(buttons[2][i].getText()) &&
                !buttons[0][i].getText().equals("")) {
                winner = buttons[0][i].getText();
                showWinner();
                return;
            }
        }

        // Check diagonals
        if (buttons[0][0].getText().equals(buttons[1][1].getText()) &&
            buttons[1][1].getText().equals(buttons[2][2].getText()) &&
            !buttons[0][0].getText().equals("")) {
            winner = buttons[0][0].getText();
            showWinner();
            return;
        }

        if (buttons[0][2].getText().equals(buttons[1][1].getText()) &&
            buttons[1][1].getText().equals(buttons[2][0].getText()) &&
            !buttons[0][2].getText().equals("")) {
            winner = buttons[0][2].getText();
            showWinner();
            return;
        }

        // Check for a tie
        boolean allFilled = true;
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (buttons[row][col].getText().equals("")) {
                    allFilled = false;
                    break;
                }
            }
        }

        if (allFilled && winner == null) {
            JOptionPane.showMessageDialog(this, "It's a Tie!");
            resetGame();
        }
    }

    private void showWinner() {
        JOptionPane.showMessageDialog(this, "Player " + winner + " wins!");
        resetGame();
    }

    private void resetGame() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                buttons[row][col].setText("");
            }
        }
        winner = null;
        playerXTurn = true;
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}

import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class tictactoe extends MyJFrame implements ActionListener{

    public tictactoe(){
        super("Tic Tac Toe");
        this.setVisible(true);
    }

    public boolean playerXTurn = true;
    public String Player1 = "X";
    public String Player2 = "O";
    public int count = 0;

    public int [][] dir = {{0,1},{1,0},{1,1},{-1,1}};

    public boolean checkWin() {

        for (int i=0; i<buttons.length; i++) {
            for (int j=0; j<buttons[i].length; j++) {
                if (buttons[i][j].getText().equals("")) {
                    continue;
                }
                for (int k=0; k<dir.length; k++) {
                    int x = i;
                    int y = j;
                    int count = 0;
                    while (x>=0 && x<buttons.length && y>=0 && y<buttons[x].length && buttons[x][y].getText().equals(buttons[i][j].getText())) {
                        count++;
                        x += dir[k][0];
                        y += dir[k][1];
                    }
                    if (count == 3) {
                        return true;
                    }
                }
            }
        }
    }

    public boolean checkTie() {
        return count == contentPane.length * contentPane[0].length;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        JButton clickedButton = (JButton) e.getSource();
        String winner = null;

        if(!clickedButton.getText().equals("") || winner != null){
            return;
        }

        if(playerXTurn){
            clickedButton.setText(Player1);
        }else{
            clickedButton.setText(Player2);
        }
        count++;

        if(checkWin()){
            if(playerXTurn){
                winner = Player1;
            }else{
                winner = Player2;
            }
            JOptionPane.showMessageDialog(null, "Player " + winner + " wins!");
        }else if(checkTie()){
            JOptionPane.showMessageDialog(null, "It's a tie!");
        }

        playerXTurn = !playerXTurn;
    }

    public static void main(String[] args){
        new tictactoe();
    }
}
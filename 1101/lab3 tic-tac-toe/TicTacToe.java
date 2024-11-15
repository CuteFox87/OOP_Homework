import java.util.ArrayList;
import java.util.Scanner;

public class TicTacToe {

    public int[][] board;

    public TicTacToe() {
        board = new int[3][3];
    }

    public int eval() {
        for (int i=0; i<3; i++){
            if (board[i][0] != 0 && board[i][0] == board[i][1] && board[i][1] == board[i][2] ) {
                return board[i][0];
            }
            if (board[0][i] != 0 && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return board[0][i];
            }
        }

        if (board[0][0] != 0 && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return board[0][0];
        }

        if (board[0][2] != 0 && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return board[0][2];
        }

        boolean allFilled = true;
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                if (board[i][j] == 0) {
                    allFilled = false;
                    break;
                }
            }
        }

        if (allFilled) {
            return -1;
        }

        return 0;
    }

    public int[] playerMove() {
        Scanner a = new Scanner(System.in);
        int x = a.nextInt();
        return new int[]{x/3, x%3};
    }

    public void show(){
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void startGame(){
        int turn = 1;
        while (eval() == 0) {
            show();
            if (turn == 1) {
                // player 1
    
                int []move = playerMove();
                while(board[move[0]][move[1]] != 0) {
                    System.out.println("Invalid move, try again");
                    move = playerMove();
                }
                board[move[0]][move[1]] = 1;
                turn = 2;
            } else {
                // player 2
                
                int []move = playerMove();
                while(board[move[0]][move[1]] != 0) {
                    System.out.println("Invalid move, try again");
                    move = playerMove();
                }
                board[move[0]][move[1]] = 2;
                turn = 1;
            }
        }
        if (eval() == -1) {
            System.out.println("It's a Tie!");
        } else {
            System.out.println("Player " + eval() + " wins!");
        }
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.startGame();
    }


}


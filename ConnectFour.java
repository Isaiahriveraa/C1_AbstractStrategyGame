import java.util.*;

/* Yonie Isaiah Rivera Aguilar
 * 
 * CSE 123
 * Professor Brunelle
 * Eeshani Shilamkar
 */
public class ConnectFour extends AbstractStrategyGame {
    private String[][] board;
    private int playerTurn;
    private static final String YEllOW = "🟡";
    private static final String RED = "🔴";
    private static final String WHITE = "⚪️";

    /*
     * Need a constructor 
     *    Creates a 7 by 6 2D array empty
     *    Goes t
     *  
     * I need the users to take turns
     * Clear end condition (Winner or tie)
     * Need to check streaks
     *    Horizontally, Vertically
     * 
     * WhoIsPlaying()
     *    If odd means that the player 1 is playing
     *    if even means that the player 2 is playing
     * 
     * MakeMove (int value)
     *    isNumberValid()
     *    isColumnFull()
     *    isGameOver()
     * 
     */
    public ConnectFour() {
        board = new String[6][7];
        playerTurn = 0;
        fillBoardWithDots();
    }

    public String instructions() {
        return "Welcome to the board game Connect Four If you are new to this game here are some "
            + "rules. \n\n  1) 2 Player game. \n  2) Pick your number as a player either 1 or 2. \n"
            + "  3) Try to get a streak of four of your number either vertically or horizontally.\n"
            + "  4) The goal is to win but also block the other player from getting a 4 color "
            + " streak. ";
    }

    public String toString() {
        String toString = "";
        for (int i = board.length - 1; i >= 0; i--) {
            if (i != board.length - 1) {
                toString += "\n";
            }

            for (int j = 0; j < board[0].length; j++) {
                if (j == 0) {
                    toString += "    [";
                }
                toString += board[i][j];
                if (j != board[0].length - 1) {
                    toString += ", ";
                }
            }
            toString += "]";
        }
        toString += "\n";
    
        return toString;
    }
    
    public void makeMove(Scanner scan) {
        int row;
        String color = "";

        if (playerTurn == 1) {
            System.out.print("Where would you like to place your " + RED + ": ");
            row = Integer.parseInt(scan.nextLine()) - 1;
            color = RED;
        } else {
            System.out.print("Where would you like to place your " + YEllOW + ": ");
            row = Integer.parseInt(scan.nextLine()) - 1;
            color = YEllOW;
        }

        while (isColumnFull(row)) {
            System.out.print("Try again, column " + row + " is full: ");
            row = Integer.parseInt(scan.nextLine()) - 1;
        }
        /*
         * start at the bottom and then check when we dont see white 
         * they give us the column we have to start at row 0 and increment.
         */
        int actualRow = 0;
        while (!board[actualRow][row].equals(WHITE) && actualRow < 5) {
            actualRow++;
        }
        board[actualRow][row] = color;

    }

    public int getWinner() {

        int streak = 0;
        String color = RED;
        for (int i = 0; i < board.length; i++) {
            streak = 0;
            for (int j = 0; j < board[i].length; j++) {
                if (streak == 4) {
                    return playerTurn;
                }
                if (board[i][j] == color) {
                    streak++;
                } else {
                    color = YEllOW;
                    streak = 1;
                }
            }
        }

        for (int i = 0; i < board[0].length; i++) {
            streak = 0;
            for (int j = 0; j < board.length; j++) {
                if (streak == 4) {
                    return playerTurn;
                }
                if (board[j][i] == color) {
                    streak++;
                } else {
                    color = YEllOW;
                    streak = 1;
                }
            }
        }

        return -1;
    }

    public int getNextPlayer() {
        if (isGameOver()) {
            return -1;
        } else if (playerTurn == 1) {
            playerTurn = 2;
        } else {
            playerTurn = 1;
        }
        return playerTurn;
    }

    public boolean isColumnFull(int column) {
        if (board[5][column].equals(WHITE)) {
            return false;
        }
        return true;
    }

    public void fillBoardWithDots() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = WHITE;
            }
        }
    }
}

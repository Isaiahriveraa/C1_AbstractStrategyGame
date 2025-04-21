import java.util.*;

public class Client {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        AbstractStrategyGame ConnectFour = new ConnectFour();/* TODO - construct your ConnectFour OR test out TicTacToe */;

        System.out.println(ConnectFour.instructions());
        System.out.println();

        while (!ConnectFour.isGameOver()) {
            System.out.println(ConnectFour);
            System.out.printf("Player %d's turn.\n", ConnectFour.getNextPlayer());
            try {
                ConnectFour.makeMove(console);
            } catch (IllegalArgumentException ex) {
                System.out.println("**Illegal move: " + ex.getMessage());
            }
            /**
             * Note - the above structure is a try/catch, which is something
             * we've included to help deal with the IllegalArgumentExceptions
             * in your abstract strategy ConnectFour!
             * We want to remind you that try/catch is a forbidden feature in 123,
             * so you SHOULD NOT INCLUDE IT in any code you submit (other than this file)!
             * Please see the Code Quality Guide for more info on this.
             */
        }
        System.out.println(ConnectFour);
        int winner = ConnectFour.getWinner();
        if (winner > 0) {
            System.out.printf("Player %d wins!\n", winner);
        } else {
            System.out.println("It's a tie!");
        }
    }
}

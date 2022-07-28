
package main.java.com.codecool.polish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class hungarianDraughts {
    public static void main(String[] args){
        Board board = new Board(10);
        hungarianDraughts play = new hungarianDraughts();
        board.createPawns();
        board.printBoard();
        play.inputPhase(board);
    }

    public void playRound() {

    }
    public void inputPhase(Board board) {
        System.out.println("Input pawn coordinates: ");
        Scanner input = new Scanner(System.in);
        String StringInput = input.nextLine().toUpperCase();
        int[] starterCoordinate = board.convertInputCoord(StringInput);
        ArrayList<int[]> validMoves = board.validMove(starterCoordinate);

        if (board.isMine("Black", starterCoordinate) && validMoves.size() > 0){
            boolean rightMove = true;
            while (rightMove){
                System.out.println("Input move coordinates: ");
                input = new Scanner(System.in);
                StringInput = input.nextLine().toUpperCase();
                int[] moveCoordinate = board.convertInputCoord(StringInput);

                for (int i = 0; i < validMoves.size(); i++) {
                    if (Arrays.equals(validMoves.get(i), moveCoordinate)){
                        rightMove = false;
                        break;
                    }
    //                System.out.println(Arrays.equals(validMoves.get(i), moveCoordinate));
            }
        }
        }


    }
}

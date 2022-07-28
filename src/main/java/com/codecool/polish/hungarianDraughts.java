
package main.java.com.codecool.polish;

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
        System.out.println();
        Scanner input = new Scanner(System.in);
        String StringInput = input.nextLine().toUpperCase();
        if (board.validateInput(StringInput)) {
            board.validSteps(board.convertInputCoord(StringInput));
        }

    }
}

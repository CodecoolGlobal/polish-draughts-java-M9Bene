
package main.java.com.codecool.polish;

import java.util.Arrays;
import java.util.Scanner;

public class hungarianDraughts {
    public static void main(String[] args){
        Board board = new Board(10);
        board.createPawns();
        board.printBoard();
        System.out.println(Arrays.toString(board.convertInputCoord("a1")));
    }
}

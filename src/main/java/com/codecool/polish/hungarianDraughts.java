
package main.java.com.codecool.polish;

public class hungarianDraughts {
    public static void main(String[] args){
        Board board = new Board(10);
        board.createPawns();
        board.printBoard();
    }
}

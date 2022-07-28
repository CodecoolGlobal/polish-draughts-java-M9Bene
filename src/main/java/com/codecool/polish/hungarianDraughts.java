
package main.java.com.codecool.polish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class hungarianDraughts {

    public static void main(String[] args) {
        Board board = new Board(10);
        hungarianDraughts play = new hungarianDraughts();
        board.createPawns();
        board.printBoard();
        String currentPlayer = "Black";
        String whoHasWon = board.whoHasWon();

        while (whoHasWon == "None") {
            if (currentPlayer == "Black") {
                currentPlayer = "White";
            } else {
                currentPlayer = "Black";
            }
            while(true) {
                System.out.println("Current player is: " + currentPlayer);
                System.out.println("Input pawn coordinates: ");
                Scanner input = new Scanner(System.in);
                String StringInput = input.nextLine().toUpperCase();
                int[] starterCoordinate = board.convertInputCoord(StringInput);
                if (board.isEmpty(starterCoordinate)){
                    continue;
                }
                ArrayList<int[]> validMoves = board.validMove(starterCoordinate);
                if (board.isMine(currentPlayer, starterCoordinate) && validMoves.size() > 0) {
                    boolean rightMove = true;
                    while (rightMove) {
                        System.out.println("Input move coordinates: ");
                        input = new Scanner(System.in);
                        StringInput = input.nextLine().toUpperCase();
                        int[] moveCoordinate = board.convertInputCoord(StringInput);

                        for (int i = 0; i < validMoves.size(); i++) {
                            if (Arrays.equals(validMoves.get(i), moveCoordinate)) {
                                board.movePawn(starterCoordinate, moveCoordinate);
                                play.hitPawn(starterCoordinate, moveCoordinate, board);
//                                play.ClearConsole();
                                board.printBoard();
                                rightMove = false;
                                break;
                            }
                            //                System.out.println(Arrays.equals(validMoves.get(i), moveCoordinate));
                        }
                        whoHasWon = board.whoHasWon();
                    }
                    break;
                }
            }
        }
        System.out.println("Congrats to " + currentPlayer);
    }

    public void hitPawn(int[] starter, int[] move, Board board){
        int[] removePawnCoordinates = new int[2];
        if(Math.abs(starter[0]-move[0]) == 2){
            if(starter[0] > move[0]){
                removePawnCoordinates[0] = starter[0]-1;
            } else {
                removePawnCoordinates[0] = starter[0]+1;
            }
            if(starter[1] > move[1]){
                removePawnCoordinates[1] = starter[1]-1;
            } else {
                removePawnCoordinates[1] = starter[1]+1;
            }
        }
        board.removePawn(removePawnCoordinates[0],removePawnCoordinates[1]);
    }

//    public static void ClearConsole(){
//        try{
//            String operatingSystem = System.getProperty("os.name"); //Check the current operating system
//
//            if(operatingSystem.contains("Windows")){
//                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
//                Process startProcess = pb.inheritIO.start();
//                startProcess.waitFor();
//            } else {
//                ProcessBuilder pb = new ProcessBuilder("clear");
//                Process startProcess = pb.inheritIO.start();
//
//                startProcess.waitFor();
//            }
//        }catch(Exception e){
//            System.out.println(e);
//        }
//    }
}
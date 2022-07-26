package main.java.com.codecool.polish;

import java.util.Arrays;

public class Board {
    private int size  = 10;
    private Pawn[][] fields;

    public Board(int size) {
        this.fields = new Pawn[size][size];
        this.size = size;

    }

    public void createPawns() {
       fields[0][0] = new Pawn("Black");
        fields[2][5] = new Pawn("White");
        fields[6][2] = new Pawn("Black");
        fields[8][8] = new Pawn("White");
    }

    public void printBoard() {
        // HEADER
        System.out.println();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        System.out.print("  ");
        for (int i=0; i<size; i++){
            System.out.print("  " + alphabet.charAt(i));
        }
        for(int i=0; i<size; i++){
            System.out.println();
            if (i <9){
                System.out.print(" ");
            }
            System.out.print(i+1);
            for(int j=0; j<size; j++){
              //  System.out.println(cell);
                if (fields[i][j] == null){
                    System.out.print("  .");
                } else if (fields[i][j].getColor() =="Black") {
                    System.out.print("  ●");
                }else if (fields[i][j].getColor() =="White"){
                    System.out.print("  ○" );
                }
            }
        }
    }
}

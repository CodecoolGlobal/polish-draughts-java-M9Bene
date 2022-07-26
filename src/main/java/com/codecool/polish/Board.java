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
            int[] emptyLines = new int[2];
            if (size % 2 == 1){
                emptyLines[0] = size/2+1;
                emptyLines[1] = 100000;
            } else {
                emptyLines[0] = size/2-1;
                emptyLines[1] = size/2;
            }
            System.out.println(emptyLines[0]);
            int fieldCounter = 1;
            for(int x = 0; x < this.size; x ++){
                fieldCounter++;
                for (int y = 0; y < this.size; y ++){
                    if (fieldCounter % 2 == 1){
                        fieldCounter ++;
                        if (x != emptyLines[0] && x != emptyLines[1]){
                            if (x > emptyLines[0]){
                                fields[x][y] = new Pawn("White");
                            } else {
                                fields[x][y] = new Pawn("Black");
                            }
                        }
                    } else {
                        fieldCounter ++;
                    }
                }
            }
//       fields[0][0] = new Pawn("Black");
            System.out.println(Arrays.deepToString(fields));
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

    public void removePawn (int x, int y){
        fields[x][y] = null;
    }
}

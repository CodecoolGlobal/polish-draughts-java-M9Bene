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
            emptyLines[0] = size/2+1;
            emptyLines[1] = size/2;
        }
        System.out.println(emptyLines[0]);
        int fieldCounter = 1;
            for(int x = 0; x < this.size; x ++){
                for (int y = 0; y < this.size; y ++){
                    if (fieldCounter % 2 == 1){
                        fieldCounter ++;
//                        if (y != emptyLines[0] && y != emptyLines[1]){
                            fields[y][x] = new Pawn("black");
//                        }
                    } else {
                        fieldCounter ++;
                    }
                }
            }
//       fields[0][0] = new Pawn("Black");
       System.out.println(Arrays.deepToString(fields));
    }

    public void printBoard() {
//        System.out.println(fields[0][0].getColor());
    }
}

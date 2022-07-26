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
       System.out.println(Arrays.deepToString(fields));
    }

    public void printBoard() {
        System.out.println(fields[0][0].getColor());
    }
}

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
//          Osszeszedjuk a nem megfelelo sorokat, ami paros magassag eseten ket sor, paratlannal 1
            int[] emptyLines;
            if (size % 2 == 1){
                emptyLines = new int[] {size/2};
            } else {
                emptyLines = new int[] {size/2, size/2-1};
            }
//          Vegigiteralunk a sorokon, oszlopokon, mindegyik mezot erintve
            for(int x = 0; x < this.size; x ++){
                for (int y = 0; y < this.size; y ++){
                    int finalY = y;
//                  Megnezzuk, hogy olyan sor van-e, amiben lehet babu
                    if (!(Arrays.stream(emptyLines).anyMatch(i -> i == finalY)) && y != emptyLines[1]){
//                      Megnezzuk, hogy 1 titltott sor van-e, ha igen, akkor fent, es lent mashogy kell indulnia
                        if ((y+x) % 2 == 0){
                            if (y > emptyLines[0]){
                                fields[y][x] = new Pawn("White");
                            } else {
                                fields[y][x] = new Pawn("Black");
                            }
                        }
                    }
                }
            }
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

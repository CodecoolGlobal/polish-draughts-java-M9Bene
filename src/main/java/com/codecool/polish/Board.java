package main.java.com.codecool.polish;

import java.util.ArrayList;
import java.util.Arrays;


public class Board {
    private int size  = 10;
    private Pawn[][] fields;

    private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public Board(int size) {
        this.fields = new Pawn[size][size];
        this.size = size;

    }
    public Pawn getBoard(int x, int y) {
        return fields[x][y];
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
            fields[4][1] = new Pawn("White");
        }

    public void printBoard() {
        // HEADER
        System.out.println();

        System.out.print(" ");
        for (int i=1; i<=size; i++){
            System.out.print("  " + i);
        }
        for(int i=0; i<size; i++){
            System.out.println();
            System.out.print(alphabet.charAt(i));
            for(int j=0; j<size; j++){
              //  System.out.println(cell);
                if (fields[i][j] == null){
                    System.out.print("  .");
                } else if (fields[i][j].getColor() =="White") {
                    System.out.print("  ●");
                }else if (fields[i][j].getColor() =="Black"){
                    System.out.print("  ○" );
                }
            }
        }
    }

    public void removePawn (int x, int y){
        fields[x][y] = null;
    }

    public void movePawn(int starterX, int starterY, int targetX, int targetY){
        fields[targetX][targetY] = fields[starterX][starterY];
        removePawn(targetX, targetY);
    }


    public int [] convertInputCoord(String input){
        Character firstPartInput = input.charAt(0);
        int secondPartInput = Integer.parseInt(input.substring(1));
        int firstConvertedCoord = alphabet.indexOf(firstPartInput);
        int secondConvertedCoord = secondPartInput - 1;
        int [] coord = {firstConvertedCoord, secondConvertedCoord};
        return coord;
    }

    public boolean validateInput(String input) {
        Character firstPartInput = input.charAt(0);
        try {
            int secondPartInput = Integer.parseInt(input.substring(1));
            if (Character.isLetter(firstPartInput) && alphabet.indexOf(firstPartInput) < size && secondPartInput <= size ) {
                return true;
            }
            return false;

        } catch (Exception e) {
            return false;
        }

    }

    public Boolean isMine(String  color, int[] coordinate) {
        try {
            return fields[coordinate[0]][coordinate[1]].getColor() == color;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isEmpty(int[] coordinate) {
        try {
            return fields[coordinate[0]][coordinate[1]] == null;
        } catch (Exception e) {
            return false;
        }
    }

    public int[][] validMove(int[] coordinate) {

        return new int[0][];
    }

    public int[][] validSteps(int[] coordinate) {
        int x = coordinate[0];
        int y = coordinate[1];
        String currentPlayer = fields[x][y].getColor();

        ArrayList<int[]> possibleMoves = new ArrayList<>();

        int[] targetField;
        if (currentPlayer == "Black") {

            // jobb lent
                targetField = new int[]{x + 1, y + 1};
                if (isEmpty(targetField) && validateCoordinate(targetField)) {
                    possibleMoves.add(targetField);
                }

            // bal lent
                targetField = new int[]{x + 1, y - 1};
                if (isEmpty(targetField) && validateCoordinate(targetField)) {
                    possibleMoves.add(targetField);
                }
        } else {

            // jobb fent
            targetField = new int[]{x - 1, y + 1};
            if (isEmpty(targetField) && validateCoordinate(targetField)) {
                possibleMoves.add(targetField);
            }

            // bal fent
            targetField = new int[]{x - 1, y - 1};
            if (isEmpty(targetField) && validateCoordinate(targetField)) {
                possibleMoves.add(targetField);
            }
        }


        for (int i = 0; i < possibleMoves.size(); i++) {
            System.out.println(Arrays.toString(possibleMoves.get(i)));
        }
        return new int[0][];
    }

    public boolean validateCoordinate(int[] coordinate) {
        int x = coordinate[0];
        int y = coordinate[1];

        return x >= 0 && x < size && y >= 0 && y < size;
    }

    public int[][] validHits(int[] coordinate) {

        return new int[0][];
    }

}

   /* output:  select pawn    a1
    inout(a1) --> conecrt coord
    output : select target   a3
    input(a3) --> convert coord

    */

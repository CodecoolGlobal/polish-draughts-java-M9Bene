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
        System.out.println();
    }

    public void removePawn (int x, int y){
        fields[x][y] = null;
    }

    public void movePawn(int[] starter, int[] target){
        fields[target[0]][target[1]] = fields[starter[0]][starter[1]];
        removePawn(starter[0], starter[1]);
    }


    public int [] convertInputCoord(String input){
        Character firstPartInput = input.charAt(0);
        int secondPartInput = Integer.parseInt(input.substring(1));
        int firstConvertedCoord = alphabet.indexOf(firstPartInput);
        int secondConvertedCoord = secondPartInput - 1;
        int [] coord = {firstConvertedCoord, secondConvertedCoord};
        return coord;
    }

    public boolean isOnBoard(String input) {
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

    public Boolean isEnemy(String  color, int[] coordinate) {
        try {
            return fields[coordinate[0]][coordinate[1]].getColor() != color;
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

    public ArrayList<int[]> validMove(int[] coordinate) {
        ArrayList<int[]> validMoves = new ArrayList<>();
        ArrayList<int[]> validSteps = validSteps(coordinate);
        ArrayList<int[]> validHits = validHits(coordinate);
        validMoves.addAll(validHits);
        validMoves.addAll(validSteps);
//        System.out.println("Valid coordinates are: ");
//        for (int i = 0; i < validMoves.size(); i++) {
//            System.out.println(Arrays.toString(validMoves.get(i)));
//        }
        return validMoves;
    }

    public ArrayList<int[]> validSteps(int[] coordinate) {
        int x = coordinate[0];
        int y = coordinate[1];
        String currentPlayer = fields[x][y].getColor();
//Todo use try except

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


//        for (int i = 0; i < possibleMoves.size(); i++) {
//            System.out.println(Arrays.toString(possibleMoves.get(i)));
//        }
        return possibleMoves;
    }

    public boolean validateCoordinate(int[] coordinate) {
        int x = coordinate[0];
        int y = coordinate[1];

        return x >= 0 && x < size && y >= 0 && y < size;
    }

    public String whoHasWon(){
        int whites = 0;
        int blacks = 0;
        for(int x = 0; x < this.size; x ++){
            for (int y = 0; y < this.size; y ++){
                try {
                    if (fields[x][y].getColor() == "Black"){
                        blacks ++;
                    } else {
                        whites ++;
                    }
                } catch (Exception e){}
            }
        }
        if (blacks == 0){
            return "White";
        } else if (whites ==0) {
            return "Black";
        } else {
            return "None";
        }
    }

    public ArrayList<int[]> validHits(int[] coordinate) {
        int x = coordinate[0];
        int y = coordinate[1];
        String currentPlayer = fields[x][y].getColor();
        ArrayList<int[]> possibleEnemies = new ArrayList<>();

//        We check if there are enemies next to us
        int[] targetField;
        int[] jumpToField;
        // jobb lent
        targetField = new int[]{x + 1, y + 1};
        jumpToField = new int[]{x + 2, y + 2};
        if (isEnemy(currentPlayer, targetField) && isEmpty(jumpToField)) {
            possibleEnemies.add(jumpToField);
        }
        // bal lent
        targetField = new int[]{x + 1, y - 1};
        jumpToField = new int[]{x + 2, y - 2};
        if (isEnemy(currentPlayer, targetField) && isEmpty(jumpToField)) {
            possibleEnemies.add(jumpToField);
        }
        // jobb fent
        targetField = new int[]{x - 1, y + 1};
        jumpToField = new int[]{x - 2, y + 2};
        if (isEnemy(currentPlayer, targetField) && isEmpty(jumpToField)) {
            possibleEnemies.add(jumpToField);
        }
        // bal fent
        targetField = new int[]{x - 1, y - 1};
        jumpToField = new int[]{x - 2, y - 2};
        if (isEnemy(currentPlayer, targetField) && isEmpty(jumpToField)) {
            possibleEnemies.add(jumpToField);
        }
//        for (int i = 0; i < possibleEnemies.size(); i++) {
//            System.out.println(Arrays.toString(possibleEnemies.get(i)));
//        }
        return possibleEnemies;
    }


}

   /* output:  select pawn    a1
    inout(a1) --> conecrt coord
    output : select target   a3
    input(a3) --> convert coord

    */

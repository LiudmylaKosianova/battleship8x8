package com.epam.rd.autotasks;

public class Battleship8x8 {
    private final long ships;
    private long shots = 0L;

    public Battleship8x8(final long ships) {
        this.ships = ships;
    }

    public boolean shoot(String shot) {

        //create a map of a battleship field
        String [][] battleMap = longToBattlemap(ships);

        //convert the "shot" into location on the map
        String rowAndColumn = shotTolocation(shot);
        int row = (int) rowAndColumn.charAt(0) - '0';
        int column = (int) rowAndColumn.charAt(1) - '0';

        //I need to register a shot
        String[][]shotsMap = new String[8][8];
        if(shots ==0){
            for(int x=0; x<8; x++){
                for(int y=0; y<8; y++){
                    shotsMap[x][y] = "0";
                }
            }
        }else{
            shotsMap = longToBattlemap(shots);
        }
        shotsMap[row][column] = "1";
        shots = battlemapToLong(shotsMap);


        //check if there is a ship at the location, represented by a "shot"
        return "1".equals(battleMap[row][column]);
    }

    /*
    * takes a long integer as an argument
    * turns it into binary literal
    * then turns the binary into a two-dimentional array 8x8
    */
    private String[][] longToBattlemap (long longN){
        //long into binary
        String map = Long.toBinaryString(longN);

        //one-dimentional array representing only 8 rows
        String[] onlyRows = new String[8];
        for (int i = 0; i < 8; i++) {
            onlyRows[i] = map.substring(i * 8, (i * 8) + 8);
        }

        //two-dimentional array 8x8
        String[][] battleMap = new String[8][8];
        for(int x = 0; x<8; x++){
            battleMap[x] = onlyRows[x].split("");
        }

        return battleMap;
    }

    private long battlemapToLong (String[][] map){
        String binaryLong = "";
        for(int x=0; x<8; x++){
            for(int y=0; y<8; y++){
                binaryLong += map[x][y];
            }
        }
        return Long.parseLong(binaryLong,2);
    }


/*
* takes a String "shot" of the form eg. "A2"
* and converts it into a String "01"
* where "0" and "1" are indexes of the location
* of a point on a battle map
*/
    private String shotTolocation (String shot){
        int row = shot.charAt(1) - '0' - 1;
        char columnLetter = shot.charAt(0);
        int column = 0;
        if(columnLetter == 'A'){
            column = 0;
        }else if(columnLetter == 'B'){
            column = 1;
        }else if(columnLetter == 'C'){
            column = 2;
        }else if(columnLetter == 'D'){
            column = 3;
        }else if(columnLetter == 'E'){
            column = 4;
        }else if(columnLetter == 'F'){
            column = 5;
        }else if(columnLetter == 'G'){
            column = 6;
        }else if(columnLetter == 'H'){
            column = 7;
        }
        return String.valueOf(row) + String.valueOf(column);

    }
    public String state() {
        //throw new UnsupportedOperationException();
        return "not done yet";
    }

    public static void main(String[] args) {
        Battleship8x8 myTry = new Battleship8x8(-1150950973573693440L);
        System.out.println(myTry.shoot("B1"));
    }
}

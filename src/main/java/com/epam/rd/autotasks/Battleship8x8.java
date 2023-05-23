package com.epam.rd.autotasks;

public class Battleship8x8 {
    private final long ships;
    private long shots = 0L;

    public Battleship8x8(final long ships) {
        this.ships = ships;
    }

    public boolean shoot(String shot) {
        //turn ships to binary so that it represents the map with ships location
        String map = Long.toBinaryString(ships);


        //my aim is to create a two-dimensional array to represent map with ships
        String[] onlyRows = new String[8];
        for (int i = 0; i < 8; i++) {
            onlyRows[i] = map.substring(i * 8, (i * 8) + 8);
        }

        String[][] battleMap = new String[8][8];
        for(int x = 0; x<8; x++){
            battleMap[x] = onlyRows[x].split("");
        }

        //check if there is a ship at the location, represented by a "shot"
        int row = ((int) shot.charAt(1)) - 1;
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

        return "1".equals(battleMap[row][column]);
    }

    public String state() {
        throw new UnsupportedOperationException();
    }
}

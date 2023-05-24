package com.epam.rd.autotasks;

public class Battleship8x8 {
    private final long ships;
    private long shots = 0L;

    public Battleship8x8(final long ships) {
        this.ships = ships;
    }

    public boolean shoot(String shot) {
        String shipsMap = Long.toBinaryString(ships);
        int index = shotToIndex(shot);

        //I need to register the shot
        StringBuilder shotsMap = new StringBuilder();
        if (shots != 0){
            shotsMap = new StringBuilder(Long.toBinaryString(shots));
        }else{
            shotsMap.append("0".repeat(63));
        }
        shotsMap.setCharAt(index,'1');
        shots = Long.parseLong(shotsMap.toString(),2);

        return shipsMap.charAt(index) == '1';
    }

/*
* takes a String "shot" of the form eg. "A2"
* and converts it into an int, representing
* the index
*/
    private int shotToIndex (String shot){

        char letter = shot.charAt(0);
        int column = 0;
        if(letter == 'A'){
            column = 0;
        }else if(letter == 'B'){
            column = 1;
        }else if(letter == 'C'){
            column = 2;
        }else if(letter == 'D'){
            column = 3;
        }else if(letter == 'E'){
            column = 4;
        }else if(letter == 'F'){
            column = 5;
        }else if(letter == 'G'){
            column = 6;
        }else if(letter == 'H'){
            column = 7;
        }

        String number = String.valueOf(shot.charAt(1));
        int row = Integer.valueOf(number);

        return (8 * (row -1)) + column;

    }
    public String state() {
        //throw new UnsupportedOperationException();
         return "not yet";
    }

    public static void main(String[] args) {
        Battleship8x8 myTry = new Battleship8x8(-1150950973573693440L);
        System.out.println(myTry.shoot("B1"));
        //myTry.shoot("A1");
        //myTry.shoot("F3");
        System.out.println(myTry.shoot("A2"));
    }
}

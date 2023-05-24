package com.epam.rd.autotasks;

import java.math.BigInteger;

public class Battleship8x8 {
    private final long ships;
    private long shots = 0L;

    public Battleship8x8(final long ships) {
        this.ships = ships;
    }

    public boolean shoot(String shot) {
        StringBuilder shipsMap = new StringBuilder( Long.toBinaryString(ships) );
        if(shipsMap.length() < 64){
            shipsMap.insert(0,"0");
        }

        int index = shotToIndex(shot);

        registerTheShot(shot);

        return shipsMap.charAt(index) == '1';
    }

/*
* takes a String "shot" of the form like "A2"
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
        int row = Integer.parseInt(number);

        return (8 * (row -1)) + column;

    }

    private void registerTheShot (String shot){
        int index = shotToIndex(shot);//I will need to put '1' into this location

        //convert long integer "shots" to a string representing the battleship field
        StringBuilder shotsMap = new StringBuilder();

        if(shots == 0){//if it is the first shot
            shotsMap.append("0".repeat(64));
        }else{
            String leadingZeros = String.format("%64s", Long.toBinaryString(shots)).replace(' ','0');//I tried to keep the leading zeros
            shotsMap.append(leadingZeros);
        }


        //I will put '1' into the location "index"
        if(shotsMap.length()>index){
            shotsMap.setCharAt(index,'1');
        }else if(shotsMap.length()==index){
            shotsMap.append('1');
        }else {
            for(int i = shotsMap.length(); i<index; i++){
                shotsMap.append('0');
            }
            shotsMap.append('1');
        }


        BigInteger shotsMapB = new BigInteger(shotsMap.toString(), 2);
        shots = shotsMapB.longValue();

    }
    public String state() {


        StringBuilder shipsMap = new StringBuilder( Long.toBinaryString(ships) );

        if(shipsMap.length() < 64){
            shipsMap.insert(0,"0");
        }



        StringBuilder shotsMap = new StringBuilder(Long.toBinaryString(shots));

        if(shotsMap.length()<64){
            for(int i = shotsMap.length(); i<64; i++){
                shotsMap.append('0');
            }
        }


        StringBuilder answer = new StringBuilder();
        for(int i = 0; i<64; i++){

            if(shipsMap.charAt(i)== '0' && shotsMap.charAt(i) == '0'){
                answer.append('.');
            }else if( shipsMap.charAt(i)== '0' && shotsMap.charAt(i) == '1'){
                answer.append('×');
            }else if(shipsMap.charAt(i)== '1' && shotsMap.charAt(i) == '0'){
                answer.append('☐');
            }else if(shipsMap.charAt(i)== '1' && shotsMap.charAt(i) == '1'){
                answer.append('☒');
            }
        }
        answer.insert(8,"\n");
        answer.insert(17, "\n");
        answer.insert(26,"\n");
        answer.insert(35,"\n");
        answer.insert(44,"\n");
        answer.insert(53,"\n");
        answer.insert(62,"\n");


         return answer.toString();
    }

    public static void main(String[] args) {
        Battleship8x8 myTry = new Battleship8x8(0b01010000_01000010_01000100_01000000_00001110_00110001_10000100_11100000L);
        myTry.shoot("A3");
        myTry.shoot("D7");
        myTry.shoot("A5");
        myTry.shoot("F3");
        myTry.shoot("H8");

        myTry.shoot("D3");
        myTry.shoot("B3");
        myTry.shoot("G8");
        myTry.shoot("C3");

        System.out.println(myTry.state());

    }
}

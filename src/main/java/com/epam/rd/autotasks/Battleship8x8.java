package com.epam.rd.autotasks;

import java.math.BigInteger;

public class Battleship8x8 {
    private final long ships;
    private long shots = 0L;

    public Battleship8x8(final long ships) {
        this.ships = ships;
    }

    public boolean shoot(String shot) {

        //I turn long int "ships" into a string, representing the battlefield, where '0' is an empty cell and '1' the cell occupied by a ship
        StringBuilder shipsMap = new StringBuilder( Long.toBinaryString(ships) );
        if(shipsMap.length() < 64){
            shipsMap.insert(0,"0");
        }

        //I turn string "shot" into int "index" so that I can find this location in the string "shipsMap"
        int column = shot.charAt(0) - 65;
        int row = Integer.parseInt( String.valueOf(shot.charAt(1)) );
        int index = (8 * (row -1)) + column;

        registerTheShot(shot);//this was the hardest

        return shipsMap.charAt(index) == '1';
    }

    private void registerTheShot (String shot){

        //I turn "shot" into int "index"
        int column = shot.charAt(0) - 65;
        int row = Integer.parseInt( String.valueOf(shot.charAt(1)) );
        int index = (8 * (row -1)) + column;

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

        //turn string into long integer
        BigInteger shotsMapB = new BigInteger(shotsMap.toString(), 2);
        shots = shotsMapB.longValue();

    }
    public String state() {


        StringBuilder shipsMap = new StringBuilder( Long.toBinaryString(ships) );

        if(shipsMap.length() < 64){
            shipsMap.insert(0,"0");
        }

        StringBuilder shotsMap = new StringBuilder();
        String leadingZeros = String.format("%64s", Long.toBinaryString(shots)).replace(' ','0');//I tried to keep the leading zeros
        shotsMap.append(leadingZeros);

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

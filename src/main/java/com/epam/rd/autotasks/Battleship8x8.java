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

        //convert long "shots" to string of 0s and 1s
        if (shots != 0){
            shotsMap = new StringBuilder(Long.toBinaryString(shots));

        }else{
            shotsMap.append("0".repeat(index+1));
        }

        //put '1' into the place of current shot
        if(index<shotsMap.length()){
            shotsMap.setCharAt(index,'1');
        }else {
            for(int i=shotsMap.length(); i<=index; i++){
                shotsMap.append('0');
            }
            shotsMap.setCharAt(index,'1');
        }

        //turn string shotsMap into long integer again
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
        String shipsMap = Long.toBinaryString(ships);

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
        Battleship8x8 myTry = new Battleship8x8(-1150950973573693440L);
        System.out.println(myTry.shoot("A1"));
        myTry.shoot("D1");
        myTry.shoot("H1");
        System.out.println(myTry.shoot("A2"));
        System.out.println(myTry.state());
    }
}

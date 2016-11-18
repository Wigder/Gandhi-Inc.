/**
 *Represents the Pub methods, ie the gambling system of the game.
 *@author Luke Jenkinson
 *@version 1.0
 */

import java.util.Random;

public class Pub{
    private int priceOfPlayingLottery = 10;
    private int priceOfPlayingScratchCard = 20;
    private int priceOfPlayingOneArmBandit = 30;

// temperary variable, needs to be replaced with the method to get player money.
    private int playerMoney = 1000;

    public static void main(String args[]){
        Pub instance = new Pub();
        instance.getPriceOfPlayingLottery();
    }

    /**
    *
    *@return priceOfPlayingLottery This returns the price of playing the lottery 
    *
    **/
    public int getPriceOfPlayingLottery(){
    	System.out.println(priceOfPlayingLottery);
        return priceOfPlayingLottery;
        
    }

    /**
    *
    *@return priceOfPlayingScratchcard returns the price of playing a scratchcard
    *
    **/
    public int getPriceOfPlayingScratchCard(){
    	System.out.println(priceOfPlayingScratchCard);
        return priceOfPlayingScratchCard;
    }

    /**
    *
    *@return priceOfPlayingOneArmBandit returns the price of playing one armed bandit
    *
    **/

    public int getPriceOfPlayingOneArmBandit(){
        System.out.println(priceOfPlayingOneArmBandit);
        return priceOfPlayingOneArmBandit;
    }

    /**
    *
    *@param number1 the first number in the lottery, should be between 1 and 20 incluseive
    *@param number2 the second number in the lottery, should be between 1 and 20 inclusive
    *@param number3 the third number in the lottery, should be between 1 and 20 inclusive
    *@return money returns the amount of money returned to the playe4r, will be 0 for loss
    *
    **/

    public int playLottery(int number1,int number2,int number3){
        if(number1 == number2)throw new IllegalArgumentException();
        else if(number1 == number3)throw new IllegalArgumentException();
        else if(number2 == number3)throw new IllegalArgumentException();
        else if(number1<1 || number1>20)throw new IllegalArgumentException();
        else if(number2<1 || number2>20)throw new IllegalArgumentException();
        else if(number3<1 || number3>20)throw new IllegalArgumentException();
        else{
//          add this code in!!!!!
//          need to know will's way of collecting moneys
            System.out.println("hello");
            return 0;    
         }
    }

    /**
    *
    *@return returns a value of how much money has been won returns 0 for a loss
    *
    **/

    public int playScratchcard(){
        Random rand = new Random();
        int random = rand.nextInt(10) + 1;
        if(random == 1)return 10;
        else return 0;
    }

    /**
    *
    *@return returns a class consisting of a string of the output, and an int of the amout of money won, 0 of a loss
    *
    **/

    public int playOneArmBandit(){
//      add code here
        return 1;
    }
}

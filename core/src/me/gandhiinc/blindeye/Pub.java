package me.gandhiinc.blindeye;

import java.util.Random;
import java.util.Arrays;
/** 
 *Represents the Pub methods, ie the gambling system of the game.
 * 
 *Represents the Pub methods, ie. the gambling system of the game.
 *
 *@author Luke Jenkinson
 *@version 1.0
*/
public class Pub{
    private int priceOfPlayingLottery = 10;
    private int priceOfPlayingScratchCard = 20;
    private int priceOfPlayingOneArmBandit = 30;

    /**
    *
    *@return priceOfPlayingLottery This returns the price of playing the lottery 
    *
    **/
    public int getPriceOfPlayingLottery(){
//    	System.out.println(priceOfPlayingLottery);
        return priceOfPlayingLottery;
        
    }

    /**
    *
    *@return priceOfPlayingScratchcard returns the price of playing a scratch card
    *
    **/
    public int getPriceOfPlayingScratchCard(){
        return priceOfPlayingScratchCard;
    }

    /**
    *
    *@return priceOfPlayingOneArmBandit returns the price of playing one armed bandit
    *
    **/

    public int getPriceOfPlayingOneArmBandit(){
        return priceOfPlayingOneArmBandit;
    }

    /**
    *
    *@param number1 the first number in the lottery, should be between 1 and 20 inclusive
    *@param number2 the second number in the lottery, should be between 1 and 20 inclusive
    *@param number3 the third number in the lottery, should be between 1 and 20 inclusive
    *@return money returns the amount of money returned to the player, will be 0 for loss
    *@throws Exception Throws an exception if any 2 numbers passed to it are the same or if any number is below 1 ore greater than 20
    **/
    public int playLottery(int number1,int number2,int number3) throws Exception
    {
    	if(number1 == number2)throw new IllegalArgumentException();
        else if(number1 == number3)throw new IllegalArgumentException();
        else if(number2 == number3)throw new IllegalArgumentException();
        else if(number1<1 || number1>20)throw new IllegalArgumentException();
        else if(number2<1 || number2>20)throw new IllegalArgumentException();
        else if(number3<1 || number3>20)throw new IllegalArgumentException();
        else{
            Random rand = new Random();
            int random1 = rand.nextInt(20) + 1;
            int random2 = rand.nextInt(20) + 1;
            int random3 = rand.nextInt(20) + 1;
            int[] arrayOfRandomNumbers;
            arrayOfRandomNumbers = new int[3];
            arrayOfRandomNumbers[0] = random1;
            arrayOfRandomNumbers[1] = random2;
            arrayOfRandomNumbers[2] = random3;
            Arrays.sort(arrayOfRandomNumbers);
            int[] arrayOfInputNumbers;
            arrayOfInputNumbers = new int[3];
            arrayOfInputNumbers[0] = number1;
            arrayOfInputNumbers[1] = number2;
            arrayOfInputNumbers[2] = number3;
            Arrays.sort(arrayOfInputNumbers);
            if(arrayOfRandomNumbers[0]==arrayOfInputNumbers[0] && arrayOfRandomNumbers[1]==arrayOfInputNumbers[1] && arrayOfRandomNumbers[2]==arrayOfInputNumbers[2])return (1331*getPriceOfPlayingLottery());
            else{
                return(-1*getPriceOfPlayingLottery());
            }
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
        if(random == 1){
            return(9*getPriceOfPlayingScratchCard());
        }
        else{
            return(-1*getPriceOfPlayingScratchCard());
        }
    }

    /**
    *
    *@return returns a array of 4 ints the first 3 of which are ints relating to emoji, the final int is the amount of money won, -price for a loss.
    *@return returns a class consisting of a string of the output, and an int of the amount of money won, 0 of a loss
    *
    **/

    public int[] playOneArmBandit(){
        Random rand = new Random();
        int random1 = rand.nextInt(10) + 1;
        int random2 = rand.nextInt(10) + 1;
        int random3 = rand.nextInt(10) + 1;
        int[] outputArray = new int[4];
        outputArray[0] = (128512 + random1);
        outputArray[1] = (128512 + random2);
        outputArray[2] = (128512 + random3);
        if(random1 == random2 && random1 == random3){
            outputArray[3] = (100*getPriceOfPlayingOneArmBandit());
            return outputArray;         
        }
        else{
            outputArray[3] = (-1*getPriceOfPlayingOneArmBandit());
            return outputArray;
        }
    }
}

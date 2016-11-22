/**
 *Represents the Pub methods, ie the gambling system of the game.
 *@author Luke Jenkinson
 *@version 1.0
*/
import java.util.Random;
import java.util.Arrays;
import java.lang.Character;

public class Pub{
    private int priceOfPlayingLottery = 10;
    private int priceOfPlayingScratchCard = 20;
    private int priceOfPlayingOneArmBandit = 30;


    public static void main(String args[]){
        Pub instance = new Pub();
        System.out.println(instance.playLottery(1, 2, 3));
//        System.out.println(instance.playOneArmBandit());
        System.out.println(instance.playScratchcard());
        System.out.println(Character.toString(Character.toChars(128513)));
    }

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
    *@return priceOfPlayingScratchcard returns the price of playing a scratchcard
    *
    **/
    public int getPriceOfPlayingScratchCard(){
//    	System.out.println(priceOfPlayingScratchCard);
        return priceOfPlayingScratchCard;
    }

    /**
    *
    *@return priceOfPlayingOneArmBandit returns the price of playing one armed bandit
    *
    **/

    public int getPriceOfPlayingOneArmBandit(){
//        System.out.println(priceOfPlayingOneArmBandit);
        return priceOfPlayingOneArmBandit;
    }

    /**
    *
    *@param number1 the first number in the lottery, should be between 1 and 20 incluseive
    *@param number2 the second number in the lottery, should be between 1 and 20 inclusive
    *@param number3 the third number in the lottery, should be between 1 and 20 inclusive
    *@return money returns the amount of money returned to the player, will be 0 for loss
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
//          need to know will's way of collecting money
            Random rand = new Random();
            int random1 = rand.nextInt(20) + 1;
            int random2 = rand.nextInt(20) + 1;
            int random3 = rand.nextInt(20) + 1;
//            System.out.println(random1 + " " + random2 + " " + random3);
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
//            for (int index = 0; index < arrayOfRandomNumbers.length; index++) System.out.println(arrayOfRandomNumbers[index]);    
            if(arrayOfRandomNumbers[0]==arrayOfInputNumbers[0] && arrayOfRandomNumbers[1]==arrayOfInputNumbers[1] && arrayOfRandomNumbers[2]==arrayOfInputNumbers[2])return 80000;
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

// need to change the money variable i think 

    public int playScratchcard(){
        Random rand = new Random();
        int random = rand.nextInt(10) + 1;
        if(random == 1){
//            System.out.println("win")
            return(10*getPriceOfPlayingScratchCard());
        }
        else{
//            System.out.println("loose");
            return(-1*getPriceOfPlayingScratchCard());
        }
    }

    /**
    *
    *@return returns a class consisting of a string of the output, and an int of the amout of money won, 0 of a loss
    *
    **/
/*
    public int playOneArmBandit(){
        Random rand = new Random();
        int random1 = rand.nextInt(10) + 1;
        int random2 = rand.nextInt(10) + 1;
        int random3 = rand.nextInt(10) + 1;
        String[] outputArray = new String[4];
        outputArray[0] = Character.toChars(128512 + random1);
        outputArray[1] = Character.toChars(128512 + random2);
        outputArray[2] = Character.toChars(128512 + random3);
        if(random1 == random2 && random1 == random3){
            outputArray[3] = (30*getPriceOfPlayingOneArmBandit());
            return outputArray;         
        }
        else{
            return (-1*getPriceOfPlayingOneArmBandit());
        }
    }
**/
}

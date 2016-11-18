/**
 *Represents the Pub methods, ie the gambling system of the game.
 *@author Luke Jenkinson
 *@version 1.0
 */


public class Pub{
    private int priceOfPlayingLottery = 10;
    private int priceOfPlayingScratchCard = 20;
    private int priceOfPlayingOneArmBandit = 30;

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

    public playLottery(number1, number2, number3){
        if(number1 == number2)throw new IlligalArgumentException();
        else if(number1 == number3)throw new IlligalArgumentException;
        else if(number2 == number3)throw new IlligalArgumentException;
        else{
//          add this code in!!!!!
//          need to know will's way of collecting moneys
            System.out.println("hello");
        }
    }

    public playScratchcard(){
//      add code here
        return;
    }

    public playOneArmBandit(){
//      add code here
        return;
    }
}

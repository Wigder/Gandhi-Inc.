/**
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

    public int getPriceOfPlayingLottery(){
    	System.out.println(priceOfPlayingLottery);
        return priceOfPlayingLottery;
        
    }

    public int getPriceOfPlayingScratchCard(){
    	System.out.println(priceOfPlayingScratchCard);
        return priceOfPlayingScratchCard;
    }

    public int getPriceOfPlayingOneArmBandit(){
        System.out.println(priceOfPlayingOneArmBandit);
        return priceOfPlayingOneArmBandit;
    }

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

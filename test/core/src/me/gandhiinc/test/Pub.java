package me.gandhiinc.test;

public class Pub
{
    int priceOfPlayingLottery;
    int priceOfPlayingScratchCard;
    int priceOfPlayingOneArmBandit;

    public static void main(String args[]){
        Pub instance = new Pub();
    }

    public int getPriceOfPlayingLottery()
    {
    	System.out.println(priceOfPlayingLottery);
        return priceOfPlayingLottery;
        
    }

    public int getPriceOfPlayingScratchCard()
    {
    	System.out.println(priceOfPlayingScratchCard);
        return priceOfPlayingScratchCard;
    }

    public int getPriceOfPlayingOneArmBandit()
    {
        System.out.println(priceOfPlayingOneArmBandit);
        return priceOfPlayingOneArmBandit;
    }
}
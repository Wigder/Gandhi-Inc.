package me.gandhiinc.blindeye;

public class BlindEye 
{
	//Run tests in this class
	
	public static void main(String args[])
	{
		Player p1 = new Player(16, 0, 0, null, null);
		MarketPlace market = new MarketPlace();
		
		// TEST BUYING
		System.out.println("MarketPlace Stock:");
		System.out.println("Market stock: /n Ore: " + market.getMarketOreStock() + "/n Energy: " + market.getMarketEnergyStock() + "/n Roboticons: " + market.getMarketRoboticonStock());
		System.out.println("Adding 5 of each resource");
		market.setMarketOreStock(5);
		market.setMarketEnergyStock(5);
		market.setMarketRoboticonStock(5);
		System.out.println("Market stock: /n Ore: " + market.getMarketOreStock() + "/n Energy: " + market.getMarketEnergyStock() + "/n Roboticons: " + market.getMarketRoboticonStock());
		System.out.println("Set the market's selling price for ore to 1");
		market.setMarketOreSellPrice(1);
		System.out.println("Market's ore price: " + market.getMarketOreSellPrice());
		System.out.println("Player's money: " + p1.getMoney());
		System.out.println("Player's ore stock: " + p1.getOre());
		System.out.println("Let player buy 5 ore");
		try 
		{
			market.buyOre(p1, 5);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		System.out.println("Market's ore stock: " + market.getMarketOreStock());
		System.out.println("Player's ore stock: " + p1.getOre());
		
		
		
		
		// TEST SELLING

	}

}

package me.gandhiinc.blindeye;

public class BlindEye 
{
	//Run tests in this class
	
	public static void main(String args[])
	{
		Player p1 = new Player("Player", 16, 0, 0);
		MarketPlace market = new MarketPlace();
		
		// TEST BUYING
		System.out.println("---------------------------");
		System.out.println("TESTS FOR THE MARKET PLACE");
		System.out.println("---------------------------");
		System.out.println("");
		System.out.println("MarketPlace Stock Test \n");
		System.out.println("Market stock: \n Ore: " + market.getMarketOreStock() + "\n Energy: " + market.getMarketEnergyStock() + "\n Roboticons: " + market.getMarketRoboticonStock());
		System.out.println("\nAdding 5 of each resource...\n");
		market.setMarketOreStock(5);
		market.setMarketEnergyStock(5);
		market.setMarketRoboticonStock(5);
		System.out.println("Market stock: \n Ore: " + market.getMarketOreStock() + "\n Energy: " + market.getMarketEnergyStock() + "\n Roboticons: " + market.getMarketRoboticonStock());
		System.out.println("\nSetting the market's selling price for ore to 2...");
                System.err.println("Setting max and min ore price to 10 and 2 respectively.");
                market.setMaxOrePrice(10);
                market.setMinOrePrice(2);
		market.setMarketOreSellPrice(3);
		System.out.println("Market's ore price: " + market.getMarketOreSellPrice());
		System.out.println("\nPlayer's money: " + p1.getMoney());
		System.out.println("Player's ore stock: " + p1.getOre());
		System.out.println("\nLet player buy 5 ore...\n");
		try 
		{
			market.buyOre(p1, 5);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		System.out.println("Market's ore stock: " + market.getMarketOreStock());
		System.out.println("Player's ore stock: " + p1.getOre() + "\n");
		
		System.out.println("Market's selling price: " + market.getMarketOreSellPrice());
		System.out.println("Market's buying price: " + market.getMarketOreBuyPrice());
		
		// TEST SELLING
		
		System.out.println("\nLet player sell 5 ore...");
		try 
		{
			market.sellOre(p1, 5);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		System.out.println("Market's ore stock: " + market.getMarketOreStock());
		System.out.println("Player's ore stock: " + p1.getOre());
                System.out.println("Player's money : " + p1.getMoney() + "\n");
		
		System.out.println("Market's selling price: " + market.getMarketOreSellPrice());
		System.out.println("Market's buying price: " + market.getMarketOreBuyPrice());

	}

}

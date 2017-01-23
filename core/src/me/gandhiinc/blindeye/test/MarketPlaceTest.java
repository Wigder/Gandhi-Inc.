package me.gandhiinc.blindeye.test;

import static org.junit.Assert.*;
import me.gandhiinc.blindeye.MarketPlace;
import me.gandhiinc.blindeye.Player;

import org.junit.Test;

public class MarketPlaceTest 
{
	Player testPlayer = new Player("Player", 10, 0, 0);
	MarketPlace market = new MarketPlace();
	
	@Test
	// TEST 1
	public void testBuyOre() 
	{
		/* Set the player's money to 10 and their ore to 0
		 * The market sell price is set to 2
		 * Then we get the player to buy 5 ore at the cost of 10
		 * The players money should be 0 once the sale has proceeded
		*/
		testPlayer.setMoney(10);
		testPlayer.setOre(0);
        int desiredPlayerMoney = 0;
        int desiredPlayerOre = 5;
        market.setMarketOreSellPrice(2);
        
        try 
        {
			market.buyOre(testPlayer, 5);
		} catch (Exception e) 
        {
			e.printStackTrace();
		}
        
        assertEquals(testPlayer.getOre(), desiredPlayerOre);
        assertEquals(testPlayer.getMoney(), desiredPlayerMoney);
	}

	
	@Test
	// TEST 2
	public void testBuyEnergy() 
	{
		testPlayer.setMoney(10);
		testPlayer.setEnergy(0);
		int desiredPlayerMoney = 0;
        int desiredPlayerEnergy = 5;
        market.setMarketEnergySellPrice(2);
        
        try 
        {
			market.buyEnergy(testPlayer, 5);
		} 
        catch (Exception e) 
        {
			e.printStackTrace();
		}
        
        assertEquals(testPlayer.getEnergy(), desiredPlayerEnergy);
        assertEquals(testPlayer.getMoney(), desiredPlayerMoney);
	}

	@Test
	// TEST 3
	public void testBuyRoboticon() 
	{
		int desiredPlayerRoboticons = 1;
		int desiredMarketRoboticons = 0;
		
		testPlayer.setMoney(30);
		testPlayer.getRoboticons().clear();
		market.setMarketRoboticonStock(1);
		market.setMarketRoboticonSellPrice(30);
		
		try 
		{
			market.buyRoboticon(testPlayer, 1);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		assertEquals(testPlayer.getRoboticons().size(), desiredPlayerRoboticons);
		assertEquals(market.getMarketRoboticonStock(), desiredMarketRoboticons);
	}

	@Test
	// TEST 4
	public void testSellOre() 
	{
		testPlayer.setMoney(0);
		testPlayer.setOre(5);
        int desiredPlayerMoney = 10;
        int desiredPlayerOre = 0;
        int desiredMarketOreStock = 5;
        
        market.setMarketOreStock(0);
        market.setMarketOreBuyPrice(2);
        
        try 
        {
			market.sellOre(testPlayer, 5);
		} 
        catch (Exception e) 
        {
			e.printStackTrace();
		}
        
        assertEquals(testPlayer.getOre(), desiredPlayerOre);
        assertEquals(testPlayer.getMoney(), desiredPlayerMoney);
        assertEquals(market.getMarketOreStock(), desiredMarketOreStock);
	}

	@Test
	// TEST 5
	public void testSellEnergy() 
	{
		testPlayer.setMoney(0);
		testPlayer.setEnergy(5);
        int desiredPlayerMoney = 10;
        int desiredPlayerEnergy = 0;
        int desiredMarketEnergyStock = 5;
        
        market.setMarketEnergyStock(0);
        market.setMarketEnergyBuyPrice(2);
        
        try 
        {
			market.sellEnergy(testPlayer, 5);
		} 
        catch (Exception e) 
        {
			e.printStackTrace();
		}
        
        assertEquals(testPlayer.getEnergy(), desiredPlayerEnergy);
        assertEquals(testPlayer.getMoney(), desiredPlayerMoney);
        assertEquals(market.getMarketEnergyStock(), desiredMarketEnergyStock);
	}

	@Test
	// TEST 6
	public void testProduceRoboticon() 
	{
		int desiredRoboticons = 2;
		int desiredOreStock = 0;
		
		market.setMarketRoboticonStock(0);
		market.setMarketOreStock(16);
		
		market.produceRoboticon();
		
		assertEquals(market.getMarketRoboticonStock(), desiredRoboticons);
		assertEquals(market.getMarketOreStock(), desiredOreStock);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	// TEST 7
	public void testMarketExchangeRate()
	{
		double desiredFinalOreBuyPrice = 2;
		double desiredFinalOreSellPrice = 5;
		
		market.setPrices();
		
		try 
		{
			market.buyOre(testPlayer, 1);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		assertEquals(market.getMarketOreBuyPrice(), desiredFinalOreBuyPrice);
		assertEquals(market.getMarketEnergySellPrice(), desiredFinalOreSellPrice);
		
		
	}
	
	
	
	// TEST ERRORS
	
	public void testForErrors()
	{
		//THE FOLLOWING SHOULD RETURN INVALID INPUTS
		
		market.setMarketOreBuyPrice(-1);
		market.setMarketEnergyBuyPrice(-1);
		market.setMarketRoboticonSellPrice(-1);
		market.setMarketOreSellPrice(-1);
		market.setMarketEnergySellPrice(-1);
	
		// RETURN ERROR THAT PLAYER HASN'T GOT ENOUGH MONEY
		
		testPlayer.setMoney(0);
		testPlayer.setOre(0);
		testPlayer.setEnergy(0);
		
		try
		{
			market.buyOre(testPlayer, 1);
		} 
		catch (Exception e) 
		{
			// Exception should be caught
			e.printStackTrace();
		}
		
	}
}

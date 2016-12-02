package me.gandhiinc.blindeye.test;

import static org.junit.Assert.*;
import me.gandhiinc.blindeye.MarketPlace;
import me.gandhiinc.blindeye.Player;

import org.junit.Test;

public class MarketPlaceTest {

	Player testPlayer = new Player("Player", 10, 0, 0);
	MarketPlace market = new MarketPlace();
	
	@Test
	public void testBuyOre() 
	{
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
		} catch (Exception e) 
        {
			e.printStackTrace();
		}
        
        assertEquals(testPlayer.getEnergy(), desiredPlayerEnergy);
        assertEquals(testPlayer.getMoney(), desiredPlayerMoney);
	}

	@Test
	public void testBuyRoboticon() 
	{
		int desiredPlayerRoboticons = 1;
		int desiredMarketRoboticons = 0;
		
		testPlayer.getRoboticons().clear();
		market.setMarketRoboticonStock(1);
		
		try 
		{
			market.buyRoboticon(testPlayer, 1);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		assertEquals(testPlayer.getRoboticons().size(), desiredPlayerRoboticons);
		assertEquals(market.getMarketRoboticonStock(), desiredMarketRoboticons);
	}

	@Test
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
		} catch (Exception e) 
        {
			e.printStackTrace();
		}
        
        assertEquals(testPlayer.getOre(), desiredPlayerOre);
        assertEquals(testPlayer.getMoney(), desiredPlayerMoney);
        assertEquals(market.getMarketOreStock(), desiredMarketOreStock);
	}

	@Test
	public void testSellEnergy() 
	{
		fail("Not yet implemented");
	}

	@Test
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
	
	@Test
	public void testMarketExchangeRate()
	{
		
	}
	
	// SEE PUB CLASS

	@Test
	public void testScratchCard() 
	{
		fail("Not yet implemented");
	}

	@Test
	public void testLottery() {
		fail("Not yet implemented");
	}

	@Test
	public void testOneArmBandit() 
	{
		fail("Not yet implemented");
	}

	// GETTERS & SETTERS
	
	@Test
	public void testGetMarketOreStock() 
	{
		fail("Not yet implemented");
	}

	@Test
	public void testSetMarketOreStock() 
	{
		fail("Not yet implemented");
	}

	@Test
	public void testGetMarketFoodStock() 
	{
		fail("Not yet implemented");
	}

	@Test
	public void testSetMarketFoodStock() 
	{
		fail("Not yet implemented");
	}

	@Test
	public void testGetMarketEnergyStock() 
	{
		fail("Not yet implemented");
	}

	@Test
	public void testSetMarketEnergyStock() 
	{
		fail("Not yet implemented");
	}

	@Test
	public void testGetMarketRoboticonStock() 
	{
		fail("Not yet implemented");
	}

	@Test
	public void testSetMarketRoboticonStock() 
	{
		fail("Not yet implemented");
	}

	@Test
	public void testGetMarketOreBuyPrice() 
	{
		fail("Not yet implemented");
	}

	@Test
	public void testSetMarketOreBuyPrice() 
	{
		fail("Not yet implemented");
	}

	@Test
	public void testGetMarketEnergyBuyPrice() 
	{
		fail("Not yet implemented");
	}

	@Test
	public void testSetMarketEnergyBuyPrice() 
	{
		fail("Not yet implemented");
	}

	@Test
	public void testGetMarketOreSellPrice() 
	{
		fail("Not yet implemented");
	}

	@Test
	public void testSetMarketOreSellPrice() 
	{
		fail("Not yet implemented");
	}

	@Test
	public void testGetMarketEnergySellPrice() 
	{
		fail("Not yet implemented");
	}

	@Test
	public void testSetMarketEnergySellPrice() 
	{
		fail("Not yet implemented");
	}

	@Test
	public void testGetMarketRoboticonSellPrice() 
	{
		fail("Not yet implemented");
	}

	@Test
	public void testSetMarketRoboticonSellPrice() 
	{
		fail("Not yet implemented");
	}

	@Test
	public void testGetPub() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMaxOrePrice() 
	{
		fail("Not yet implemented");
	}

	@Test
	public void testSetMaxOrePrice() 
	{
		fail("Not yet implemented");
	}

	@Test
	public void testGetMinOrePrice() 
	{
		fail("Not yet implemented");
	}

	@Test
	public void testSetMinOrePrice() 
	{
		fail("Not yet implemented");
	}

	@Test
	public void testGetMaxEnergyPrice() 
	{
		fail("Not yet implemented");
	}

	@Test
	public void testSetMaxEnergyPrice() 
	{
		fail("Not yet implemented");
	}

	@Test
	public void testGetMinEnergyPrice() 
	{
		fail("Not yet implemented");
	}

	@Test
	public void testSetMinEnergyPrice() 
	{
		fail("Not yet implemented");
	}

}

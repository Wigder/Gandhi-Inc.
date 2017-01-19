package me.gandhiinc.blindeye;

import java.util.Random;

/**
 * ---------------------------------------------------------
 * --------------------   AIPLAYER   -----------------------
 * ---------------------------------------------------------
 * 
 * This class extends Player and builds upon to all AI functionality for a player to make choices by itself
 * It contains the following methods:
 * 
 * 		-	CompleteTurn(Plot[] plots, MarketPlace market)
 * 
 * @author Steven Tomlinson
 * @version 1.0
 *
 *@see Player
 */
public class AIPlayer extends Player {
	
	/**
	 * The is the constructor of the AIPlayer class to instantiate a new instance of the AIPlayer class
	 * @param name The name of the aiplayer object i.e Player 1
	 * @param money The starting money of the player
	 * @param ore The starting ore of the player
	 * @param energy The starting energy of the player
	 */
	public AIPlayer(String name, int money, int ore, int energy) 
	{
		super(name, money, ore, energy);
	}

	/**
	 * The function for the ai to complete their turn automatically given a list of available plots and the market so that it can interact with them.
	 * @param plots The list of available plots
	 * @param market The market reference to interact with
	 */
	public void CompleteTurn(Plot[] plots, MarketPlace market)
	{
		AIAcquirePlot(plots);
		AIAcquireRoboticon(market);
		ProduceResources();
		//AIGamble(market);
	}
	
	/**
	 * Allows the AIPlayer to acquire a random plot given a list of available plots
	 * @param plots List of available plots
	 */
	private void AIAcquirePlot(Plot[] plots)
	{
		//If there is a valid plot to acquire, then acquire a random plot from the list of valid plots
		if (plots.length >= 1)
		{
			Random random = new Random();
			int plotIndex = random.nextInt(plots.length);
			try {
				AcquirePlot(plots[plotIndex]);
			} catch (Exception e) {
				return;
			}
		}
	}
	
	/**
	 * Allows the AIPlayer to buy a roboticon from the market given. So if the AIPlayer has enough money to buy a roboticon then it has a 50/50 chance of buying one and then specialising it
	 * @param market The market to buy the roboticon from
	 */
	private void AIAcquireRoboticon(MarketPlace market)
	{
		//If the player has enough money to buy a roboticon
		if (this.money > market.getMarketRoboticonSellPrice())
		{
			//The player has a 50% chance of buying a roboticon
			Random random = new Random();	
			boolean isBuying = random.nextBoolean();
			if (isBuying)
			{
				//If they are buying a roboticon then attempt to buy a roboticon and speciliase it with it being a 50:50 chance of either resource
				try {
					market.buyRoboticon(this, 1);
					if (random.nextBoolean())
						roboticons.get(roboticons.size() - 1).setSpec(Resource.ENERGY);
					else
						roboticons.get(roboticons.size() - 1).setSpec(Resource.ORE);
					
					//Then assign the roboticon to the last plot that was acquired
					Plot lastPlot = plots.get(plots.size() - 1);
					if (lastPlot.hasRoboticon() == false)
					{
						roboticons.get(roboticons.size() - 1).setPlot(lastPlot);
						lastPlot.addRoboticon(roboticons.get(roboticons.size() - 1));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * This allows the AIPlayer to gamble at a pub given a market to go to
	 * @param market The market to access the pub
	 */
	private void AIGamble(MarketPlace market)
	{
		Random random = new Random();
		boolean isGambling = random.nextBoolean();
		if (isGambling)
		{
			int gambleType = random.nextInt(3);
			switch (gambleType)
			{
			case 0:
				if (this.money > market.getPub().getPriceOfPlayingLottery())
				{
					int num1, num2, num3;
					num1 = random.nextInt(20) + 1;
					num2 = random.nextInt(20) + 1;
					num3 = random.nextInt(20) + 1;
					try {
						int diff = market.getPub().playLottery(num1, num2, num3);
						this.money += diff;
					} catch (Exception e) {
						break;
					}
				}
				break;
			case 1:
				if (this.money > market.getPub().getPriceOfPlayingOneArmBandit())
				{
					int diff = market.getPub().playOneArmBandit()[3];
					this.money += diff;
				}
				break;
			case 2:
				if (this.money > market.getPub().getPriceOfPlayingScratchCard())
				{
					int diff = market.getPub().playScratchcard();
					this.money += diff;
				}
				break;
			}
		}
	}
	
	public void SellResources(MarketPlace market)
	{
		if (ore > 0)
		{
			try {
				market.sellOre(this, ore);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (energy > 0)
		{
			try {
				market.sellEnergy(this, energy);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
}

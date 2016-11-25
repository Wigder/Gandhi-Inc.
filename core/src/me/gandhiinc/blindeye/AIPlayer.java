package me.gandhiinc.blindeye;

import java.util.Random;

public class AIPlayer extends Player {
	
	public void CompleteTurn(Plot[] plots, MarketPlace market)
	{
		AIAquirePlot(plots);
		AIAquireRoboticon(market);
		ProduceResources();
		AIGamble(market);
	}
	
	private void AIAquirePlot(Plot[] plots)
	{
		if (plots.length >= 1)
		{
			Random random = new Random();
			int plotIndex = random.nextInt(plots.length);
			try {
				AquirePlot(plots[plotIndex]);
			} catch (Exception e) {
				return;
			}
		}
	}
	
	private void AIAquireRoboticon(MarketPlace market)
	{
		if (this.money > market.getMarketRoboticonSellPrice())
		{
			Random random = new Random();	
			boolean isBuying = random.nextBoolean();
			if (isBuying)
			{
				try {
					market.buyRoboticon(this, 1);
				} catch (Exception e) {
					return;
				}
			}
		}
	}
	
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
	
}

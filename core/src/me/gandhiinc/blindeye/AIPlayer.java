package me.gandhiinc.blindeye;

import java.util.Random;

public class AIPlayer extends Player {

	public static void main(String[] a)
	{
		AIPlayer p = new AIPlayer();
		p.tempSetMoney(1000000000);
		int winCount = 0;
		for (int x = 0; x < 1000000; x++)
		{
			int prevMoney = p.getMoney();
			System.out.println(prevMoney);
			p.CompleteTurn(null, new MarketPlace());
			int newMoney = p.getMoney();
			System.out.println(newMoney);
			if (newMoney > prevMoney)
			{
				winCount++;
			}
		}

		System.out.println(winCount);
	}
	
	public void tempSetMoney(int money)
	{
		this.money = money;
	}
	
	public void CompleteTurn(Plot[] plots, MarketPlace market)
	{
		
		AIGamble(market);
	}
	
	private void AIGamble(MarketPlace market)
	{
		Random random = new Random();
		int isGambling = random.nextInt(2);
		System.out.println(isGambling);
		if (isGambling == 1)
		{
			int gambleType = random.nextInt(3);
			System.out.println(gambleType);
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
						System.out.println(diff);
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
					System.out.println(diff);
				}
				break;
			case 2:
				if (this.money > market.getPub().getPriceOfPlayingScratchCard())
				{
					int diff = market.getPub().playScratchcard();
					this.money += diff;
					System.out.println(diff);
				}
				break;
			}
		}
	}
	
}

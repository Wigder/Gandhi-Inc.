package me.gandhiinc.test;

/* ---------------------------------------------------------
 * -------------------	MARKET PLACE	--------------------
 * ---------------------------------------------------------
 * 
 * This is the Market Place class.
 * 
 * Author: 	Will Wood	
 * Date:	15/11/16
 */

public class MarketPlace extends Game 
{
	
/* ---------------------------------------------------------
 * -----------	Initiating the internal values	------------
 * ---------------------------------------------------------
 */	
	private int marketOreStock;
	private int marketFoodStock;
	private int marketEnergyStock;
	private int marketRoboticonsStock;
	
	private int marketOreBuyPrice;
	private int marketFoodBuyPrice;
	private int marketEnergyBuyPrice;

	private int marketOreSellPrice;
	private int marketFoodSellPrice;
	private int marketEnergySellPrice;

	//////// TEMP PLAYER VALUE TO BE REPLACED AND IMPORTED FROM PLAYER !!!!
	private int playersMoney;
	private int playersOre;
	private int playersFood;
	private int playersEnergy;

/* ---------------------------------------------------------
 * --------------	 The buying functions	----------------
 * ---------------------------------------------------------
 */		
	public void buyOre(int quantity)							//Function to buy Ore from the market
	{
		if(quantity > getMarketOreStock()) 						//Check the Market has enough stock
		{
			//Return error to say the market doesn't have enough
		}
		int totalAmount = quantity * getMarketOreSellPrice(); 	//Calculate the total price
		if(playersMoney > totalAmount)							//Check if the player has enough money to pay
		{
			//Add the ore to the players ore
			//Remove the money from his account
			
			int newStock = getMarketOreStock() + quantity;		//Calculates what the new stock is
			setMarketOreStock(newStock);						//Sets the new stock value
			
			int newSellPrice = getMarketOreSellPrice();			//Calculates the new sell price
			setMarketOreSellPrice(newSellPrice);				//Sets the new sell price
		
			int newBuyPrice = getMarketOreBuyPrice();			//Calculates the new buy price
			setMarketOreBuyPrice(newBuyPrice);					//Sets the new buy price
		}
		else
		{
			//Return error that player does not have enough
		}
	}
	
	public void buyFood(int quantity)							//Function to buy Food from the market
	{
		if(quantity > getMarketFoodStock()) 					//Check the Market has enough stock
		{
			//Return error to say the market doesn't have enough
		}
		int totalAmount = quantity * getMarketFoodSellPrice(); 	//Calculate the total price
		if(playersMoney > totalAmount)							//Check if the player has enough money to pay
		{
			//Add the ore to the players ore
			//Remove the money from his account
		}
		else
		{
			//Return error that player does not have enough
		}
	}
	
	public void buyEnergy(int quantity)							//Function to buy Energy from the market
	{
		if(quantity > getMarketEnergyStock()) 					//Check the Market has enough stock
		{
			//Return error to say the market doesn't have enough
		}
		int totalAmount = quantity * getMarketEnergySellPrice(); //Calculate the total price
		if(playersMoney > totalAmount)							//Check if the player has enough money to pay
		{
			//Add the ore to the players ore
			//Remove the money from his account
		}
		else
		{
			//Return error that player does not have enough
		}
	}

/* ---------------------------------------------------------
 * --------------	 The selling functions	----------------
 * ---------------------------------------------------------
 */
	
	public void sellOre(int quantity)
	{
		if(playersOre < quantity)
		{
			//Display error that the player doesn't have enough Ore
		}
		int totalAmount = getMarketOreBuyPrice();
		
	}
	
	public void sellFood(int quantity)
	{
		
	}
	
	public void sellEnergy(int quantity)
	{
		
	}
	
/* ---------------------------------------------------------
 * -------	Getters & Setters for the Market Place	--------
 * ---------------------------------------------------------
 */
	
	public int getMarketOreStock() 
	{
		return marketOreStock;
	}
	public void setMarketOreStock(int marketOreStock) 
	{
		this.marketOreStock = marketOreStock;
	}
	public int getMarketFoodStock() 
	{
		return marketFoodStock;
	}
	public void setMarketFoodStock(int marketFoodStock) 
	{
		this.marketFoodStock = marketFoodStock;
	}
	public int getMarketEnergyStock() 
	{
		return marketEnergyStock;
	}
	public void setMarketEnergyStock(int marketEnergyStock) 
	{
		this.marketEnergyStock = marketEnergyStock;
	}
	public int getMarketRoboticonsStock() 
	{
		return marketRoboticonsStock;
	}
	public void setMarketRoboticonsStock(int marketRoboticonsStock) 
	{
		this.marketRoboticonsStock = marketRoboticonsStock;
	}
	public int getMarketOreBuyPrice() 
	{
		return marketOreBuyPrice;
	}
	public void setMarketOreBuyPrice(int marketOreBuyPrice) 
	{
		this.marketOreBuyPrice = marketOreBuyPrice;
	}
	public int getMarketFoodBuyPrice() 
	{
		return marketFoodBuyPrice;
	}
	public void setMarketFoodBuyPrice(int marketFoodBuyPrice) 
	{
		this.marketFoodBuyPrice = marketFoodBuyPrice;
	}
	public int getMarketEnergyBuyPrice() 
	{
		return marketEnergyBuyPrice;
	}
	public void setMarketEnergyBuyPrice(int marketEnergyBuyPrice) 
	{
		this.marketEnergyBuyPrice = marketEnergyBuyPrice;
	}
	public int getMarketOreSellPrice() 
	{
		return marketOreSellPrice;
	}
	public void setMarketOreSellPrice(int marketOreSellPrice) 
	{
		this.marketOreSellPrice = marketOreSellPrice;
	}
	public int getMarketFoodSellPrice() 
	{
		return marketFoodSellPrice;
	}
	public void setMarketFoodSellPrice(int marketFoodSellPrice) 
	{
		this.marketFoodSellPrice = marketFoodSellPrice;
	}
	public int getMarketEnergySellPrice() 
	{
		return marketEnergySellPrice;
	}
	public void setMarketEnergySellPrice(int marketEnergySellPrice) 
	{
		this.marketEnergySellPrice = marketEnergySellPrice;
	}
}

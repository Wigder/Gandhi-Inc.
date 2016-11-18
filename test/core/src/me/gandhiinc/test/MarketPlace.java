package me.gandhiinc.test;

/** ---------------------------------------------------------
  * -------------------	MARKET PLACE	--------------------
  * ---------------------------------------------------------
  * 
  * This is the Market Place class. It contains the following methods:
  * 	-	buyOre(int quantity)
  * 	-	buyFood(int quantity)
  * 	-	buyEnergy(int quantity)
  * 	-	buyRoboticon(int quantity)
  * 
  * 	-	sellOre(int quantity)
  * 	-	sellFood(int quantity)
  * 	-	sellEnergy(int quantity)
  * 
  * 	-	produceRoboticon()
  * 
  *  The produceRoboticon function should be called at the end of each round of the game
  *  It will produce new roboticons to the market stock depending on the market ore stock.
  *  
  *  There are also the getters and setters for each of the variables. This is to ensure
  *  they are set correctly and that they can remain private variables. We have decided to 
  *  keep all the variables in integers as this is how they will be displayed. When calculating
  *  we will convert the doubles back into integers to avoid problems.
  * 
  * @author 		Will Wood
  * @version		1.0	
  * @date			15/11/16	
  */

public class MarketPlace extends Game 
{
	
/* ---------------------------------------------------------
 * ---------	Initiating the internal variables	--------
 * ---------------------------------------------------------
 */	
	private int marketOreStock;
	private int marketFoodStock;
	private int marketEnergyStock;
	private int marketRoboticonStock;
	
	private int maxOrePrice;
	private int minOrePrice;
	
	private int maxFoodPrice;
	private int minFoodPrice;
	
	private int maxEnergyPrice;
	private int minEnergyPrice;
	
	private int marketOreBuyPrice;
	private int marketFoodBuyPrice;
	private int marketEnergyBuyPrice;

	private int marketOreSellPrice;
	private int marketFoodSellPrice;
	private int marketEnergySellPrice;
	private int marketRoboticonSellPrice;
	


	//////// TEMP PLAYER VALUE TO BE REPLACED AND IMPORTED FROM PLAYER !!!!
	private int playersMoney;
	private int playersOre;
	private int playersFood;
	private int playersEnergy;

/* ---------------------------------------------------------
 * --------------	 The buying functions	----------------
 * ---------------------------------------------------------
 */		
	public void buyOre(int quantity)																	//Function to buy Ore from the market
	{
		if(quantity > getMarketOreStock()) 																//Check the Market has enough stock
		{
			//Return error to say the market doesn't have enough
		}
		int totalAmount = quantity * getMarketOreSellPrice(); 											//Calculate the total price
		if(playersMoney > totalAmount)																	//Check if the player has enough money to pay
		{
			//Add the ore to the players ore
			//Remove the money from his account
			
			int newStock = getMarketOreStock() + quantity;												//Calculates what the new stock is
			setMarketOreStock(newStock);																//Sets the new stock value
			
			int newPrice = (int)(maxOrePrice * Math.pow(0.7, getMarketOreSellPrice()) + minOrePrice);	//Calculates the new sell price
			setMarketOreSellPrice(newPrice + 1);														//Sets the new sell price
			setMarketOreBuyPrice(newPrice - 1);															//Sets the new buy price
		}
		else
		{
			//Return error that player does not have enough
		}
	}
	
	public void buyFood(int quantity)																	//Function to buy Food from the market
	{
		if(quantity > getMarketFoodStock()) 															//Check the Market has enough stock
		{
			//Return error to say the market doesn't have enough
		}
		int totalAmount = quantity * getMarketFoodSellPrice(); 											//Calculate the total price
		if(playersMoney > totalAmount)																	//Check if the player has enough money to pay
		{
			//Add the ore to the players ore
			//Remove the money from his account
			
			int newStock = getMarketOreStock() + quantity;												//Calculates what the new stock is
			setMarketOreStock(newStock);																//Sets the new stock value
			
			int newPrice = (int)(maxFoodPrice * Math.pow(0.7, getMarketFoodSellPrice()) + minFoodPrice);//Calculates the new sell price
			setMarketFoodSellPrice(newPrice + 1);														//Sets the new sell price
			setMarketFoodBuyPrice(newPrice - 1);														//Sets the new buy price
		}
		else
		{
			//Return error that player does not have enough
		}
	}
	
	public void buyEnergy(int quantity)																	//Function to buy Energy from the market
	{
		if(quantity > getMarketEnergyStock()) 															//Check the Market has enough stock
		{
			//Return error to say the market doesn't have enough
		}
		int totalAmount = quantity * getMarketEnergySellPrice();										//Calculate the total price
		if(playersMoney > totalAmount)																	//Check if the player has enough money to pay
		{
			//Add the ore to the players ore
			//Remove the money from his account
			
			int newStock = getMarketOreStock() + quantity;												//Calculates what the new stock is
			setMarketOreStock(newStock);																//Sets the new stock value
			
			int newPrice = (int)(maxEnergyPrice * Math.pow(0.7, getMarketEnergySellPrice()) + minEnergyPrice);	//Calculates the new sell price
			setMarketEnergySellPrice(newPrice + 1);														//Sets the new sell price
			setMarketEnergyBuyPrice(newPrice - 1);														//Sets the new buy price
			
		}
		else
		{
			//Return error that player does not have enough
		}
	}

	public void buyRoboticon(int quantity) throws Exception
	{
		Exception eMoney = new Exception("You don't have enough money!");
		Exception eStock = new Exception("The market doesn't have enough stock");
		if(getMarketRoboticonSellPrice() > playersMoney)
		{
			throw eMoney;																		//Error the player doesn't have enough money
		}
		if(getMarketRoboticonStock()-quantity >= 0)
		{
			throw eStock;																		//Error the market doesn't have enough stock
		}
		else
		{
			setMarketRoboticonStock(getMarketRoboticonStock() - quantity); 						//Reduce the stock by the quantity purchased
			
			//Player.setRoboticonStock(Player.getRoboticonStock() + quantity)					// Add the roboticon to the player
		}
	}
	
	
	
/* ---------------------------------------------------------
 * --------------	 The selling functions	----------------
 * ---------------------------------------------------------
 */
	
	public void sellOre(int quantity) throws Exception
	{
		Exception e = new Exception("You don't have enough ore to sell!");
		if(playersOre < quantity)
		{
			throw e;												//Display error that the player doesn't have enough Ore
		}
		
		int totalAmount = getMarketOreBuyPrice();					//Calculate total amount player will receive
		playersMoney += totalAmount;								//Need to use a setter for players money
		
	}
	
	public void sellFood(int quantity) throws Exception
	{
		Exception e = new Exception("You don't have enough food to sell!");
		if(playersFood < quantity)
		{
			throw e;												//Display error that the player doesn't have enough Ore
		}
		int totalAmount = getMarketFoodBuyPrice();					//Calculate total amount player will receive
		playersMoney += totalAmount;								//Need to use a setter for players money
		
	}
	
	public void sellEnergy(int quantity) throws Exception
	{
		Exception e = new Exception("You don't have enough energy to sell!");
		if(playersEnergy < quantity)
		{
			throw e;												//Display error that the player doesn't have enough Ore
		}
		int totalAmount = getMarketEnergyBuyPrice();				//Calculate total amount player will receive
		playersMoney += totalAmount;								//Need to use a setter for players money
		
	}
	
	
/* ---------------------------------------------------------
 * ---------------	Generating Roboticons	----------------
 * ---------------------------------------------------------
 */
	
	public void produceRoboticon()
	{
		if(getMarketOreStock() < 16)
		{
			return;
		}
		else if(getMarketOreStock() >= 16 && getMarketOreStock() < 24)
		{
			setMarketRoboticonStock(getMarketRoboticonStock() + 2);
			setMarketOreStock(getMarketOreStock()-16);
			return;
		}
		else if(getMarketOreStock() >= 24 && getMarketOreStock() < 32)
		{
			setMarketRoboticonStock(getMarketRoboticonStock() + 4);
			setMarketOreStock(getMarketOreStock()-24);
			return;
		}
		else if(getMarketOreStock() >= 32)
		{
			setMarketRoboticonStock(getMarketRoboticonStock() + 6);
			setMarketOreStock(getMarketOreStock()-32);
			return;
		}
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
	public int getMarketRoboticonStock() 
	{
		return marketRoboticonStock;
	}
	public void setMarketRoboticonStock(int marketRoboticonsStock) 
	{
		this.marketRoboticonStock = marketRoboticonsStock;
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
	public int getMarketRoboticonSellPrice() {
		return marketRoboticonSellPrice;
	}

	public void setMarketRoboticonSellPrice(int marketRoboticonSellPrice) {
		this.marketRoboticonSellPrice = marketRoboticonSellPrice;
	}
}

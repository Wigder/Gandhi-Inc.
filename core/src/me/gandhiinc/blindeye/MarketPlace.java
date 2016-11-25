package me.gandhiinc.blindeye;

/** ---------------------------------------------------------
  * -------------------	MARKET PLACE	---------------------
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
  *  The produceRoboticon function should be called at the end of each round of the game.
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

public class MarketPlace
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
	
	private int maxEnergyPrice;
	private int minEnergyPrice;
	
	private int marketOreBuyPrice;
	private int marketFoodBuyPrice;
	private int marketEnergyBuyPrice;

	private int marketOreSellPrice;
	private int marketFoodSellPrice;
	private int marketEnergySellPrice;
	private int marketRoboticonSellPrice;
	
	private Pub pub = new Pub();
	Exception eMoney = new Exception("You do not have enough Money!");
	Exception eStockBuy = new Exception("The market doesn't have enough stock");
	

/* ---------------------------------------------------------
 * --------------	 The buying functions	----------------
 * ---------------------------------------------------------
 */
	
	/**
	 * This method is used for a player to buy ore. It needs to have a player and a quantity to purchase for the parameters. 
	 * 
	 * @param player - Imports the player from the Player class. This allows resources to interact with the player's assets.
	 * @param quantity - A defined quantity with input from the user
	 * @throws Exception - Exception thrown if the Market is out of stock or if the player doesn't have sufficient funds
	 */
	public void buyOre(Player player, int quantity)	throws Exception									//Function to buy Ore from the market
	{	
		if(quantity > getMarketOreStock()) 																//Check the Market has enough stock
		{
			throw eStockBuy;																			//Return error to say the market doesn't have enough
		}
		int totalAmount = quantity * getMarketOreSellPrice(); 											//Calculate the total price
		if(player.getMoney() >= totalAmount)															//Check if the player has enough money to pay
		{
			player.setOre(player.getOre() + quantity);													//Add the ore to the players ore
			player.setMoney(player.getMoney() - totalAmount);											//Remove the money from his account
			
			int newStock = getMarketOreStock() + quantity;												//Calculates what the new stock is
			setMarketOreStock(newStock);																//Sets the new stock value
			
			int newPrice = (int)(maxOrePrice * Math.pow(0.7, getMarketOreSellPrice()) + minOrePrice);	//Calculates the new sell price
			setMarketOreSellPrice(newPrice + 1);														//Sets the new sell price
			setMarketOreBuyPrice(newPrice - 1);															//Sets the new buy price
		}
		else
		{
			throw eMoney;																				//Return error that player does not have enough
		}
	}
	
	/**
	 * This method is used for a player to buy energy. It needs to have a player and a quantity to purchase for the parameters. 
	 * 
	 * @param player - Imports the player from the Player class. This allows resources to interact with the player's assets.
	 * @param quantity - A defined quantity with input from the user
	 * @throws Exception - Exception thrown if the Market is out of stock or if the player doesn't have sufficient funds
	 */
	public void buyEnergy(Player player, int quantity) throws Exception											//Function to buy Energy from the market
	{
		if(quantity > getMarketEnergyStock()) 																	//Check the Market has enough stock
		{
			throw eStockBuy;																					//Return error to say the market doesn't have enough
		}
		int totalAmount = quantity * getMarketEnergySellPrice();												//Calculate the total price
		if(player.getMoney() > totalAmount)																		//Check if the player has enough money to pay
		{
			player.setOre(quantity);																			//Add the ore to the players ore
			player.setMoney(player.getMoney() -  totalAmount);													//Remove the money from his account
			
			int newStock = getMarketOreStock() + quantity;														//Calculates what the new stock is
			setMarketOreStock(newStock);																		//Sets the new stock value
			
			int newPrice = (int)(maxEnergyPrice * Math.pow(0.7, getMarketEnergySellPrice()) + minEnergyPrice);	//Calculates the new sell price
			setMarketEnergySellPrice(newPrice + 1);																//Sets the new sell price
			setMarketEnergyBuyPrice(newPrice - 1);																//Sets the new buy price
			
		}
		else
		{
			throw eMoney;																						//Return error that player does not have enough
		}
	}

	
	/**
	 * This method is used for a player to buy roboticons. It needs to have a player and a quantity to purchase for the parameters.
	 * 
	 * @param player - Imports the player from the Player class. This allows resources to interact with the player's assets.
	 * @param quantity - A defined quantity with input from the user
	 * @throws Exception - Exception thrown if the Market is out of stock or if the player doesn't have sufficient funds
	 */
	public void buyRoboticon(Player player, int quantity) throws Exception
	{
		if(getMarketRoboticonSellPrice() > player.getMoney())
		{
			throw eMoney;																		//Error the player doesn't have enough money
		}
		if(getMarketRoboticonStock()-quantity >= 0)
		{
			throw eStockBuy;																	//Error the market doesn't have enough stock
		}
		else
		{
			setMarketRoboticonStock(getMarketRoboticonStock() - quantity); 						//Reduce the stock by the quantity purchased
			
			for (int i = 0; i < quantity; i++)
				player.addRoboticon(new Roboticon());											// Add the roboticon to the player
		}
	}
	
	
	
/* ---------------------------------------------------------
 * --------------	 The selling functions	----------------
 * ---------------------------------------------------------
 */
	
	/**
	 * This method is used for a player to sell ore. It needs to have a player and a quantity to purchase for the parameters.
	 * 
	 * @param player - Imports the player from the Player class. This allows resources to interact with the player's assets.
	 * @param quantity - A defined quantity with input from the user
	 * @throws Exception
	 */
	public void sellOre(Player player, int quantity) throws Exception
	{
		Exception e = new Exception("You don't have enough ore to sell!");
		if(player.getOre() < quantity)
		{
			throw e;												//Display error that the player doesn't have enough Ore
		}
		
		int totalAmount = getMarketOreBuyPrice();					//Calculate total amount player will receive
		player.setMoney(player.getMoney() + totalAmount);			//Need to use a setter for players money
		
	}
	
	/**
	 * This method is used for a player to sell energy. It needs to have a player and a quantity to purchase for the parameters.
	 * 
	 * @param player - Imports the player from the Player class. This allows resources to interact with the player's assets.
	 * @param quantity - A defined quantity with input from the user
	 * @throws Exception
	 */
	public void sellEnergy(Player player, int quantity) throws Exception
	{
		Exception e = new Exception("You don't have enough energy to sell!");
		if(player.getEnergy() < quantity)
		{
			throw e;												//Display error that the player doesn't have enough Ore
		}
		int totalAmount = getMarketEnergyBuyPrice();				//Calculate total amount player will receive
		player.setMoney(player.getMoney() + totalAmount);			//Need to use a setter for players money
		
	}	
	
/* ---------------------------------------------------------
 * ---------------	Generating Roboticons	----------------
 * ---------------------------------------------------------
 */
	/**
	 * This function should be called at the end of each round to regenerate the Roboticon stock based on the amount of ore the market has
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
 * ------------------	 The Pub Games	--------------------
 * ---------------------------------------------------------
 */
		
	/**
	 * 
	 * @param player
	 * @throws Exception
	 */
	public void scratchCard(Player player) throws Exception
	{
		if(player.getMoney() < pub.getPriceOfPlayingScratchCard())
		{
			throw eMoney;
		}
		else
		{
			player.setMoney(player.getMoney() + pub.playScratchcard());
		}
	}
	
	/**
	 * 
	 * @param player
	 * @param num1
	 * @param num2
	 * @param num3
	 * @throws Exception
	 */
	public void Lottery(Player player, int num1, int num2, int num3) throws Exception
	{
		if(player.getMoney() < pub.getPriceOfPlayingLottery())
		{
			throw eMoney;
		}
		else
		{
			player.setMoney(player.getMoney() + pub.playLottery(num1, num2, num3));
		}
	}
	
	/**
	 * 
	 * @param player
	 * @return
	 * @throws Exception
	 */
	public int[] OneArmBandit(Player player) throws Exception
	{
		if(player.getMoney() < pub.getPriceOfPlayingLottery())
		{
			throw eMoney;
		}
		else
		{
			int[] array = pub.playOneArmBandit();
			int cost = array[3];
			player.setMoney(player.getMoney() + cost);
			return array;
		}
	}
		
		
/* ---------------------------------------------------------
 * -------	Getters & Setters for the Market Place	--------
 * ---------------------------------------------------------
 */
	
	/**
	 * Getter for the stock of the market's ore
	 * @return - returns the stock value of the market's ore 
	 */
	public int getMarketOreStock() 
	{
		return marketOreStock;
	}

	/**
	 * Setter for the stock of the market's ore
	 * @param marketOreStock
	 */
	public void setMarketOreStock(int marketOreStock) 
	{
		this.marketOreStock = marketOreStock;
	}
	
	/**
	 * Getter for the stock of the market's food
	 * @return - returns the stock value of the market's food 
	 */
	public int getMarketFoodStock() 
	{
		return marketFoodStock;
	}

	/**
	 * Setter for the stock of the market's food
	 * @param marketFoodStock
	 */
	public void setMarketFoodStock(int marketFoodStock) 
	{
		this.marketFoodStock = marketFoodStock;
	}
	
	/**
	 * Getter for the stock of the market's energy
	 * @return - returns the stock value of the market's energy
	 */
	public int getMarketEnergyStock() 
	{
		return marketEnergyStock;
	}

	/**
	 * Setter for the stock of the market's energy
	 * @param marketEnergyStock
	 */
	public void setMarketEnergyStock(int marketEnergyStock) 
	{
		this.marketEnergyStock = marketEnergyStock;
	}
	
	/**
	 * Getter for the stock of the market's Roboticons
	 * @return - returns the stock value of the market's roboticons 
	 */
	public int getMarketRoboticonStock() 
	{
		return marketRoboticonStock;
	}

	/**
	 * Setter for the stock of the market's roboticons
	 * @param marketRoboticonsStock
	 */
	public void setMarketRoboticonStock(int marketRoboticonsStock) 
	{
		this.marketRoboticonStock = marketRoboticonsStock;
	}
	
	/**
	 * Getter for the purchase price of the market's ore
	 * @return
	 */
	public int getMarketOreBuyPrice() 
	{
		return marketOreBuyPrice;
	}

	/**
	 * Setter for the purchase price of the market's ore
	 * @param marketOreBuyPrice
	 */
	public void setMarketOreBuyPrice(int marketOreBuyPrice) 
	{
		this.marketOreBuyPrice = marketOreBuyPrice;
	}
	/**
	 * Getter for the purchase price of the market's food
	 * @return
	 */
	public int getMarketFoodBuyPrice() 
	{
		return marketFoodBuyPrice;
	}
	
	/**
	 * Setter for the purchase price of the market's food
	 * @param marketFoodBuyPrice
	 */
	public void setMarketFoodBuyPrice(int marketFoodBuyPrice) 
	{
		this.marketFoodBuyPrice = marketFoodBuyPrice;
	}
	
	/**
	 * Getter for the purchase price of the market's energy
	 * @return
	 */
	public int getMarketEnergyBuyPrice() 
	{
		return marketEnergyBuyPrice;
	}
	
	/**
	 * Setter for the purchase price of the market's energy
	 * @param marketEnergyBuyPrice
	 */
	public void setMarketEnergyBuyPrice(int marketEnergyBuyPrice) 
	{
		this.marketEnergyBuyPrice = marketEnergyBuyPrice;
	}
	
	/**
	 * Getter for the selling price of the market's ore
	 * @return
	 */
	public int getMarketOreSellPrice() 
	{
		return marketOreSellPrice;
	}
	
	/**
	 * Setter for the selling price of the market's ore
	 * @param marketOreSellPrice
	 */
	public void setMarketOreSellPrice(int marketOreSellPrice) 
	{
		this.marketOreSellPrice = marketOreSellPrice;
	}
	
	/**
	 * Getter for the selling price of the market's food
	 * @return
	 */
	public int getMarketFoodSellPrice() 
	{
		return marketFoodSellPrice;
	}
	
	/**
	 * Setter for the selling price of the market's food
	 * @param marketFoodSellPrice
	 */
	public void setMarketFoodSellPrice(int marketFoodSellPrice) 
	{
		this.marketFoodSellPrice = marketFoodSellPrice;
	}
	
	/**
	 * Getter for the selling price of the market's energy
	 * @return
	 */
	public int getMarketEnergySellPrice() 
	{
		return marketEnergySellPrice;
	}
	
	/**
	 * Setter for the selling price of the market's energy
	 * @param marketEnergySellPrice
	 */
	public void setMarketEnergySellPrice(int marketEnergySellPrice) 
	{
		this.marketEnergySellPrice = marketEnergySellPrice;
	}
	
	/**
	 * Getter for the selling price of the market's Roboticons
	 * @return
	 */
	public int getMarketRoboticonSellPrice() 
	{
		return marketRoboticonSellPrice;
	}

	/**
	 * Setter for the selling price of the market's roboticons
	 * @param marketRoboticonSellPrice
	 */
	public void setMarketRoboticonSellPrice(int marketRoboticonSellPrice) 
	{
		this.marketRoboticonSellPrice = marketRoboticonSellPrice;
	}
}

package me.gandhiinc.blindeye;

/** 
  * This is the Market Place class. It contains the following methods:
  * 	-	buyOre(int quantity)
  * 	-	buyEnergy(int quantity)
  * 	-	buyRoboticon(int quantity)
  * 
  * 	-	sellOre(int quantity)
  * 	-	sellEnergy(int quantity)
  * 
  * 	-	produceRoboticon()
  * 	-	setPrices()
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
  */ 


public class MarketPlace
{
	
/* ---------------------------------------------------------
 * ---------	Initiating the internal variables	--------
 * ---------------------------------------------------------
 */	
	private int marketOreStock = 100;				// Setting the Market Ore Stock
	private int marketFoodStock = 100;				// Setting the Market Food Stock
	private int marketEnergyStock = 100;			// Setting the Market Energy Stock
	private int marketRoboticonStock = 10;			// Setting the Market Roboticon Stock
	
	private int prevOreStock = 100;					// Setting the last known Market Ore Stock
	private int prevEnergyStock = 100;				// Setting the last known Market Energy Stock
	
	private float marketOreBuyPrice = 1.5f;			// Setting the market ore buying price
	private float marketEnergyBuyPrice = 1.5f;		// Setting the market energy buying price

	private float marketOreSellPrice = 1.5f;		// Setting the market ore selling price
	private float marketEnergySellPrice = 1.5f;		// Setting the market energy selling price
	private int marketRoboticonSellPrice = 30;		// Setting the market roboticon sell price
	
	private Pub pub = new Pub();					// Initiating a variance of the pub
	
	Exception eMoney = new Exception("You do not have enough Money!");				// The exception thrown if a user has insufficient funds when purchasing something
	Exception eStockBuy = new Exception("The market doesn't have enough stock");	// The exception thrown when the market doesn't have enough stock when selling something
	

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
		int totalAmount = (int) (quantity * getMarketOreSellPrice());                                   //Calculate the total price
		if(player.getMoney() >= totalAmount)                                                            //Check if the player has enough money to pay
		{
			player.setOre(player.getOre() + quantity);                                                  //Add the ore to the players ore
			player.setMoney(player.getMoney() - totalAmount);											//Remove the money from his account
			
			int newStock = getMarketOreStock() - quantity;												//Calculates what the new stock is
			setMarketOreStock(newStock);                                                                //Sets the new stock value

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
	public void buyEnergy(Player player, int quantity) throws Exception							//Function to buy Energy from the market
	{
		if(quantity > getMarketEnergyStock()) 													//Check the Market has enough stock
		{
			throw eStockBuy;																	//Return error to say the market doesn't have enough
		}
		float totalAmount = quantity * getMarketEnergySellPrice();                              //Calculate the total price
		if(player.getMoney() >= totalAmount)                                                     //Check if the player has enough money to pay
		{
			player.setEnergy(player.getEnergy() + quantity);									//Add the ore to the players ore
			player.setMoney(player.getMoney() -  (int) totalAmount);							//Remove the money from his account
			
			int newStock = getMarketEnergyStock() - quantity;									//Calculates what the new stock is
			setMarketEnergyStock(newStock);														//Sets the new stock value
			
		}
		else
		{
			throw eMoney;																		//Return error that player does not have enough
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
		if(getMarketRoboticonSellPrice() * quantity > player.getMoney())	//Checks if the player has sufficient funds to purchase the quantity of roboticons
		{
			throw eMoney;									//Error the player doesn't have enough money
		}
		if(getMarketRoboticonStock()-quantity < 0)			//Checks if there is enough stock to purchase the given quantity
		{
			throw eStockBuy;								//Error the market doesn't have enough stock
		}
		else
		{
			setMarketRoboticonStock(getMarketRoboticonStock() - quantity); 						//Reduce the stock by the quantity purchased
			player.setMoney(player.getMoney() - (getMarketRoboticonSellPrice() * quantity));	//Sets the Player's new money (taking away the value of the Roboticons they purchased)
			for (int i = 0; i < quantity; i++)													//For each roboticon purchased
				player.addRoboticon(new Roboticon());											//Add a new roboticon to the player
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
	 * @throws Exception If the quantity they want to sell is greater than the ore they actually have
	 */
	public void sellOre(Player player, int quantity) throws Exception
	{
		Exception e = new Exception("You don't have enough ore to sell!");
		if(player.getOre() < quantity)									//If the player doesn't have enough ore
		{
			throw e;													//Display error that the player doesn't have enough Ore
		}
		
		float totalAmount = getMarketOreBuyPrice() * quantity;          //Calculate total amount player will receive
		player.setMoney(player.getMoney() + (int)totalAmount);          //Set the player's money (adding the value of ore sold)
		player.setOre(player.getOre() - quantity);						//Set the player's new ore amount (taking away the ore sold)
        setMarketOreStock(getMarketOreStock() + quantity);				//Set the new stock of ore for the market
                     
	}
	
	/**
	 * This method is used for a player to sell energy. It needs to have a player and a quantity to purchase for the parameters.
	 * 
	 * @param player - Imports the player from the Player class. This allows resources to interact with the player's assets.
	 * @param quantity - A defined quantity with input from the user
	 * @throws Exception If the quantity they want to sell is greater than the ore they actually have
	 */
	public void sellEnergy(Player player, int quantity) throws Exception
	{
		Exception e = new Exception("You don't have enough energy to sell!");
		if(player.getEnergy() < quantity)
		{
			throw e;															//Display error that the player doesn't have enough Ore
		}
		float totalAmount = getMarketEnergyBuyPrice() * quantity;            	//Calculate total amount player will receive
		
        player.setMoney(player.getMoney() + (int) totalAmount);                 //Set the player's money (adding the value of energy sold)
		player.setEnergy(player.getEnergy() - quantity);						//Set the player's new energy amount (taking away the energy sold)
        setMarketEnergyStock(getMarketEnergyStock() + quantity);				//Set the new stock of energy for the market
	}

/* ---------------------------------------------------------
 * --------------	 Setting Market Prices	----------------
 * ---------------------------------------------------------
 */
	
	public void setPrices()
	{
		System.out.println("Current Ore Price: " + getMarketOreSellPrice() + " prevOreStock: " + prevOreStock + " marketOreStock: " + marketOreStock + " log: " + Math.log((double)prevOreStock / (double)marketOreStock));
		if (prevOreStock != 0 && marketOreStock != 0)														//If the last know ore stock and current market ore stock are different to 0
		{
			float newOrePrice = (float) Math.log((double)prevOreStock / (double)marketOreStock) * 10;		//The new ore price is calculated by using the log(previous amount/current amount)*10
			if (newOrePrice < 0.5f) 																		// If the new ore price is under 0.5
			{
				newOrePrice = 0.5f; 																		// Set the new ore price to 0.5
			}
			setMarketOreBuyPrice(newOrePrice - 0.1f); 														// Set the buying price to the ore price - 0.1
			setMarketOreSellPrice(newOrePrice + 0.1f);														//Set the selling price to the ore price + 0.1
		}
		
		if (prevEnergyStock != 0 && marketEnergyStock != 0)
		{
			float newEnergyPrice = (float) Math.log((double)prevEnergyStock / (double)marketEnergyStock) * 10;
			if (newEnergyPrice < 0.5f)
			{
				newEnergyPrice = 0.5f;
			}
			setMarketEnergyBuyPrice(newEnergyPrice - 0.1f);
			setMarketEnergySellPrice(newEnergyPrice + 0.1f);
		}
		prevOreStock = marketOreStock;
		prevEnergyStock = marketEnergyStock;
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
		if(getMarketOreStock() < 16) 											//If the market ore stock is below 16
		{
			return;																//Then exit function
		}
		else if(getMarketOreStock() >= 16 && getMarketOreStock() < 24) 			//Else if the market ore stock is between 16 and under 24
		{
			setMarketRoboticonStock(getMarketRoboticonStock() + 2);				//Add 2 roboticons to the market roboticon stock
			setMarketOreStock(getMarketOreStock()-16);							//Remove 16 ore from the market ore stock
			return;
		}
		else if(getMarketOreStock() >= 24 && getMarketOreStock() < 32) 			//Else if the market ore stock is between 24 and under 32
		{
			setMarketRoboticonStock(getMarketRoboticonStock() + 4);				//Add 4 roboticons to the market ore roboticon stock
			setMarketOreStock(getMarketOreStock()-24);							//Remove 24 ore from the market ore stock
			return;
		}
		else if(getMarketOreStock() >= 32) 										//Else if the market ore stock is 32 or over
		{
			setMarketRoboticonStock(getMarketRoboticonStock() + 6);				//Add 6 roboticons to the market roboticon stock
			setMarketOreStock(getMarketOreStock()-32);							//Remove 32 ore from the market ore stock
			return;
		}
	}

/* ---------------------------------------------------------
 * ------------------	 The Pub Games	--------------------
 * ---------------------------------------------------------
 */
		
	/**
	 * 
	 * @param player -- takes the player that wants to play as input
	 * @throws Exception -- throws exeption if the player doesnt have enough many
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
	 * @param player -- takes the player that wants to play as input
	 * @param num1 -- first lottery number
	 * @param num2 -- second lottery number
	 * @param num3 -- third lottery number 
	 * @throws Exception -- if the player doesnt have enough money
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
	 * @param player -- takes the player that wants to play as input
	 * @return returns an int array relating to the emoji value
	 * @throws Exception -- if the player doesnt have enough money
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
	 * @param marketOreStock -- set ore stock
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
	 * @param marketFoodStock -- set market food stock
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
	 * @param marketEnergyStock -- set market energy stock
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
	 * @param marketRoboticonsStock -- set the roboticon market stock
	 */
	public void setMarketRoboticonStock(int marketRoboticonsStock) 
	{
		this.marketRoboticonStock = marketRoboticonsStock;
	}
	
	/**
	 * Getter for the purchase price of the market's ore
	 * @return -- gets the market ore buy price
	 */
	public float getMarketOreBuyPrice() 
	{
		return marketOreBuyPrice;
	}

	/**
	 * Setter for the purchase price of the market's ore
	 * @param marketOreBuyPrice -- sets the markey ore buy price
	 */
	public void setMarketOreBuyPrice(float marketOreBuyPrice) 
	{
		this.marketOreBuyPrice = marketOreBuyPrice;
	}
	
	/**
	 * Getter for the purchase price of the market's energy
	 * @return -- gets the market energy buy price
	 */
	public float getMarketEnergyBuyPrice() 
	{
		return marketEnergyBuyPrice;
	}
	
	/**
	 * Setter for the purchase price of the market's energy
	 * @param marketEnergyBuyPrice -- sets the market energy buy price
	 */
	public void setMarketEnergyBuyPrice(float marketEnergyBuyPrice) 
	{
		this.marketEnergyBuyPrice = marketEnergyBuyPrice;
	}
	
	/**
	 * Getter for the selling price of the market's ore
	 * @return -- gets the market ore sell price
	 */
	public float getMarketOreSellPrice() 
	{
		return marketOreSellPrice;
	}
	
	/**
	 * Setter for the selling price of the market's ore
	 * @param marketOreSellPrice -- sets the market ore sell price
	 */
	public void setMarketOreSellPrice(float marketOreSellPrice) 
	{
		this.marketOreSellPrice = marketOreSellPrice;
	}
	
	
	/**
	 * Getter for the selling price of the market's energy
	 * @return gets the market ore sell price
	 */
	public float getMarketEnergySellPrice() 
	{
		return marketEnergySellPrice;
	}
	
	/**
	 * Setter for the selling price of the market's energy
	 * @param marketEnergySellPrice -- sets the market ore sell price
	 */
	public void setMarketEnergySellPrice(float marketEnergySellPrice) 
	{
		this.marketEnergySellPrice = marketEnergySellPrice;
	}
	
	/**
	 * Getter for the selling price of the market's Roboticons
	 * @return -- gets the market roboticon sell price 
	 */
	public int getMarketRoboticonSellPrice() 
	{
		return marketRoboticonSellPrice;
	}

	/**
	 * Setter for the selling price of the market's roboticons
	 * @param marketRoboticonSellPrice -- sets the market roboticon sell price
	 */
	public void setMarketRoboticonSellPrice(int marketRoboticonSellPrice) 
	{
		this.marketRoboticonSellPrice = marketRoboticonSellPrice;
	}
	
	/**
	 * Getter for access the pub for this market
	 * @return Gives back the pub
	 */
	public Pub getPub()
	{
		return this.pub;
	}
}

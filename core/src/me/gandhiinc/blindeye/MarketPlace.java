package me.gandhiinc.blindeye;

/** ---------------------------------------------------------
  * -------------------	MARKET PLACE	---------------------
  * ---------------------------------------------------------
  * 
  * This is the Market Place class. It contains the following methods:
  * 	-	buyOre(int quantity)
  * 	-	buyEnergy(int quantity)
  * 	-	buyRoboticon(int quantity)
  * 
  * 	-	sellOre(int quantity)
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
	private int marketOreStock = 100;
	private int marketFoodStock = 100;
	private int marketEnergyStock = 100;
	private int marketRoboticonStock = 10;
	
	private int prevOreStock = 100;
	private int prevEnergyStock = 100;
	
	private float maxOrePrice;
	private float minOrePrice;
	
	private float maxEnergyPrice;
	private float minEnergyPrice;
	
	private float marketOreBuyPrice = 1.5f;
	private float marketEnergyBuyPrice = 1.5f;

	private float marketOreSellPrice = 1.5f;
	private float marketEnergySellPrice = 1.5f;
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
	public void buyOre(Player player, int quantity)	throws Exception						//Function to buy Ore from the market
	{	
		if(quantity > getMarketOreStock()) 									//Check the Market has enough stock
		{
			throw eStockBuy;																			//Return error to say the market doesn't have enough
		}
		int totalAmount = (int) (quantity * getMarketOreSellPrice());                                                   //Calculate the total price
		if(player.getMoney() >= totalAmount)                                                                    //Check if the player has enough money to pay
		{
			player.setOre(player.getOre() + quantity);                                                      //Add the ore to the players ore
			player.setMoney(player.getMoney() - totalAmount);						//Remove the money from his account
			
			int newStock = getMarketOreStock() - quantity;							//Calculates what the new stock is
			setMarketOreStock(newStock);                                                                    //Sets the new stock value
			
			int newPrice = (int)(maxOrePrice * Math.pow(0.7, getMarketOreSellPrice()) + minOrePrice);	//Calculates the new sell price
                        setMarketOreBuyPrice(newPrice + 1);								//Sets the new sell price
			setMarketOreSellPrice(newPrice - 1);								//Sets the new buy price
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
		if(quantity > getMarketEnergyStock()) 										//Check the Market has enough stock
		{
			throw eStockBuy;																					//Return error to say the market doesn't have enough
		}
		float totalAmount = quantity * getMarketEnergySellPrice();                                                        //Calculate the total price
		if(player.getMoney() > totalAmount)                                                                             //Check if the player has enough money to pay
		{
			player.setEnergy(player.getEnergy() + quantity);																			//Add the ore to the players ore
			player.setMoney(player.getMoney() -  (int) totalAmount);							//Remove the money from his account
			
			int newStock = getMarketEnergyStock() - quantity;							//Calculates what the new stock is
			setMarketEnergyStock(newStock);										//Sets the new stock value
			
			int newPrice = (int)(maxEnergyPrice * Math.pow(0.7, getMarketEnergySellPrice()) + minEnergyPrice);	//Calculates the new sell price
			setMarketEnergySellPrice(newPrice + 1);									//Sets the new sell price
			setMarketEnergyBuyPrice(newPrice - 1);                                                                  //Sets the new buy price
			
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
		if(getMarketRoboticonSellPrice() * quantity > player.getMoney())
		{
			throw eMoney;									//Error the player doesn't have enough money
		}
		if(getMarketRoboticonStock()-quantity < 1)
		{
			throw eStockBuy;								//Error the market doesn't have enough stock
		}
		else
		{
			setMarketRoboticonStock(getMarketRoboticonStock() - quantity); 			//Reduce the stock by the quantity purchased
			player.setMoney(player.getMoney() - (getMarketRoboticonSellPrice() * quantity));
			for (int i = 0; i < quantity; i++)
				player.addRoboticon(new Roboticon());					// Add the roboticon to the player
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
			throw e;										//Display error that the player doesn't have enough Ore
		}
		
		float totalAmount = getMarketOreBuyPrice() * quantity;                                                       //Calculate total amount player will receive
		player.setMoney(player.getMoney() + (int)totalAmount);                                               //Need to use a setter for players money
		player.setOre(player.getOre() - quantity);
        setMarketOreStock(getMarketOreStock() + quantity);
                     
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
			throw e;										//Display error that the player doesn't have enough Ore
		}
		float totalAmount = getMarketEnergyBuyPrice() * quantity;                                                    //Calculate total amount player will receive
		
        player.setMoney(player.getMoney() + (int) totalAmount);                                               //Need to use a setter for players money
		player.setEnergy(player.getEnergy() - quantity);
        setMarketEnergyStock(getMarketEnergyStock() + quantity);
	}
	
	public void setPrices()
	{
		System.out.println("Current Ore Price: " + getMarketOreSellPrice() + " prevOreStock: " + prevOreStock + " marketOreStock: " + marketOreStock + " log: " + Math.log((double)prevOreStock / (double)marketOreStock));
		if (prevOreStock != 0 && marketOreStock != 0)
		{
			float newOrePrice = (float) Math.log((double)prevOreStock / (double)marketOreStock) * 10;
			if (newOrePrice < 0.5f)
			{
				newOrePrice = 0.5f;
			}
			setMarketOreBuyPrice(newOrePrice - 0.1f);
			setMarketOreSellPrice(newOrePrice + 0.1f);	
		}
		
		if (prevOreStock != 0 && marketOreStock != 0)
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
	public float getMarketOreBuyPrice() 
	{
		return marketOreBuyPrice;
	}

	/**
	 * Setter for the purchase price of the market's ore
	 * @param marketOreBuyPrice
	 */
	public void setMarketOreBuyPrice(float marketOreBuyPrice) 
	{
		this.marketOreBuyPrice = marketOreBuyPrice;
	}
	
	/**
	 * Getter for the purchase price of the market's energy
	 * @return
	 */
	public float getMarketEnergyBuyPrice() 
	{
		return marketEnergyBuyPrice;
	}
	
	/**
	 * Setter for the purchase price of the market's energy
	 * @param marketEnergyBuyPrice
	 */
	public void setMarketEnergyBuyPrice(float marketEnergyBuyPrice) 
	{
		this.marketEnergyBuyPrice = marketEnergyBuyPrice;
	}
	
	/**
	 * Getter for the selling price of the market's ore
	 * @return
	 */
	public float getMarketOreSellPrice() 
	{
		return marketOreSellPrice;
	}
	
	/**
	 * Setter for the selling price of the market's ore
	 * @param marketOreSellPrice
	 */
	public void setMarketOreSellPrice(float marketOreSellPrice) 
	{
		this.marketOreSellPrice = marketOreSellPrice;
	}
	
	
	/**
	 * Getter for the selling price of the market's energy
	 * @return
	 */
	public float getMarketEnergySellPrice() 
	{
		return marketEnergySellPrice;
	}
	
	/**
	 * Setter for the selling price of the market's energy
	 * @param marketEnergySellPrice
	 */
	public void setMarketEnergySellPrice(float marketEnergySellPrice) 
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
	
	/**
	 * Getter for access the pub for this market
	 * @return Gives back the pub
	 */
	public Pub getPub()
	{
		return this.pub;
	}
        
        public float getMaxOrePrice() 
	{
		return maxOrePrice;
	}

	public void setMaxOrePrice(float maxOrePrice) 
	{
		this.maxOrePrice = maxOrePrice;
	}

	public float getMinOrePrice() {
		return minOrePrice;
	}

	public void setMinOrePrice(float minOrePrice) 
	{
		this.minOrePrice = minOrePrice;
	}

	public float getMaxEnergyPrice() 
	{
		return maxEnergyPrice;
	}

	public void setMaxEnergyPrice(float maxEnergyPrice) 
	{
		this.maxEnergyPrice = maxEnergyPrice;
	}

	public float getMinEnergyPrice() 
	{
		return minEnergyPrice;
	}

	public void setMinEnergyPrice(float minEnergyPrice) {
		this.minEnergyPrice = minEnergyPrice;
	}
}
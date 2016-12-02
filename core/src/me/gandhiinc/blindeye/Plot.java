package me.gandhiinc.blindeye;

/**
 * ---------------------------------------------------------
 * ----------------------  	PLOT   -------------------------
 * ---------------------------------------------------------
 * 
 * This is the Plot class. It contains the following methods:
 * 		-	getFoodMod()
 * 		-	getEnergyMod()
 * 		-	getOreMod()
 * 
 * 		-	getTerrain()
 * 		-	changeTerrain(int newTerrain)
 * 
 * 		-	getPlayer()
 * 		-	setPlayer(Player player)
 * 
 * 		-	addRoboticon(Roboticon roboticon)
 * 		-	removeRoboticon()
 * 		-	hasRoboticon()
 * 		-	getRoboticon()
 * 
 * The modifiers are in place so that a roboticon working on some plot can yield different volumes
 * of some resource according to such plot, so a certain plot will be naturally more "useful" for
 * producing some type of resource among the three types we have.
 * The terrain functions are used to alter plot production modifiers in the case of a random event.
 * The player functions are used to control the ownership of each plot object in our code, so that
 * we can facilitate binding the players together with the plots.
 * The roboticon functions have similar functionality to the player functions, but are used to bind
 * the plot class to roboticons instead.
 * 
 * @author 		Pedro Wigderowitz
 * @version 	1.0
 * @date		25/11/2016
 */

public class Plot
{

/* ---------------------------------------------------------
 * ---------	Initiating the internal variables	--------
 * ---------------------------------------------------------
 */	
	
	/*
	 * The object modifiers are initialised below.
	 * The object constructor will later handle assigning their values.
	 * These modifiers should not change after initialisation, except
	 * in the case of a random event.
	 */
	private int foodMod;
	private int energyMod;
	private int oreMod;
	
	/*
	 * The terrain type is initialised here. It is represented as an int,
	 * with a terrain's properties varying according to its type. It will
	 * be changed according to random events.
	 */
	/*
	 * is every plot initialised as "default type", or can a plot be initialised
	 * with another type of terrain? that is, are random events the only mechanism
	 * that can trigger a change of terrain to another type, or is it possible to have
	 * plots with varying terrain types straight from round 1?
	 */
	//define types of terrain and their codes (0 for type 1, 1 for type 2, etc...)
	private int terrain;
	
	/*
	 * The cost to purchase a plot is defined as constant and is initialised and finalised here.
	 */
	public static final int cost = 50;
	
	/*
	 * The plot object's assigned roboticon and owner are initialised here.
	 * These values may change throughout the game.
	 */
	private Roboticon roboticon;
	private Player player;
	
	/**
	 * The is the constructor of the Plot class to instantiate a new instance of the Plot class.
	 * @param foodModifier		The value of the food modifier of a Plot instance.
	 * @param energyModifier	The value of the energy modifier of a Plot instance.
	 * @param oreModifier 		The value of the ore modifier of a Plot instance.
	 * @param terrain 			The terrain type of a Plot instance.
	 */
	public Plot(int foodModifier, int energyModifier, int oreModifier, int terrainType)
	{
		foodMod = foodModifier;
		energyMod = energyModifier;
		oreMod = oreModifier;
		terrain = terrainType; 
	}

/* ---------------------------------------------------------
 * -------------    Getters and setters 	----------------
 * ---------------------------------------------------------
 */	

	/**
	 * A getter for the food modifier.
	 * 
	 * @return	The object's food modifier.
	 */
	public int getFoodMod()
	{
		return foodMod;
	}
	
	/**
	 * A getter for the energy modifier.
	 * 
	 * @return	The object's energy modifier.
	 */
	public int getEnergyMod()
	{
		return energyMod;
	}
	
	/**
	 * A getter for the ore modifier.
	 * 
	 * @return	The object's ore modifier.
	 */
	public int getOreMod()
	{
		return oreMod;
	}
	
	/**
	 * A getter for the terrain type.
	 * 
	 * @return	The object's terrain type.
	 */
	public int getTerrain()
	{
		return terrain;
	}
	
	/**
	 * A setter for the terrain type.
	 * 
	 * @param newTerrain	The new terrain to be assigned to the object.
	 */
	public void changeTerrain(int newTerrain)
	{
		terrain = newTerrain;
	}
	
	/**
	 * A getter for the player who owns the plot.
	 * 
	 * @return	The player object of the player who owns the plot object.
	 */
	public Player getPlayer()
	{
		return player;
	}
	
	/**
	 * A setter for the player who owns the plot.
	 * 
	 * @param player	The new player to be set as owner of the plot.
	 */
	public void setPlayer(Player player)
	{
		this.player = player;
	}
	
	/**
	 * A setter for the roboticon working on the plot.
	 * 
	 * @param roboticon	The roboticon to be assigned to work on the plot object.
	 */
	public void addRoboticon(Roboticon roboticon)
	{
		this.roboticon = roboticon;
	}
	
	/**
	 * A setter to remove the assigned roboticon from some plot object.
	 */
	public void removeRoboticon()
	{
		this.roboticon = null;
	}
	
	/**
	 * A test to check whether some plot has a roboticon assigned to it or not.
	 * Used to facilitate implementation in other classes, and to make code more
	 * easily readable.
	 * 
	 * @return The boolean True if the plot has a roboticon; False otherwise.
	 */
	public boolean hasRoboticon()
	{
		if (this.roboticon != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * A getter to retrieve a plot's assigned roboticon.
	 * 
	 * @return	A plot's assigned roboticon.
	 */
	public Roboticon getRoboticon()
	{
		return this.roboticon;
	}
	
}

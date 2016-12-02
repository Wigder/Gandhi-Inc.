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
 * 		-	changeTerrain()
 * 
 * 		-	getAssignment()
 * 		-	setAssigned()
 * 		-	setUnassigned()
 * 
 * 		-	addRoboticon(Roboticon roboticon)
 * 		-	removeRoboticon()
 * 		-	hasRoboticon()
 * 		-	getRoboticon()
 * 
 * The terrain functions are used to alter plot production modifiers in the case of a random event.
 * There are also getters and setters for all of the object's variables, so that other classes
 * can access them if necessary while having them remain private.
 * 
 * @author 		Pedro Wigderowitz
 * @version 	0.15
 * @date		25/11/2016
 */

public class Plot
{

/* ---------------------------------------------------------
 * ---------	Initiating the internal variables	--------
 * ---------------------------------------------------------
 */	
	private int foodMod;
	private int energyMod;
	private int oreMod;
	//define types of terrain and their codes (0 for type 1, 1 for type 2, etc...)
	private int terrain;
	
	//cost to purchase a plot; should be fixed; define this later on
	public static final int cost = 50;
	
	private Roboticon roboticon;
	private Player player;
	
	public Plot(int foodModifier, int energyModifier, int oreModifier, int terrainType)
	{
		foodMod = foodModifier;
		energyMod = energyModifier;
		oreMod = oreModifier;
		terrain = terrainType; 
	}

/* ---------------------------------------------------------
 * -------------     Getters and setters 	------------
 * ---------------------------------------------------------
 */	

	public int getFoodMod()
	{
		return foodMod;
	}
	
	public int getEnergyMod()
	{
		return energyMod;
	}
	
	public int getOreMod()
	{
		return oreMod;
	}
	
	public int getTerrain()
	{
		return terrain;
	}
	
	public void changeTerrain(int newTerrainType)
	{
		terrain = newTerrainType;
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	public void setPlayer(Player player)
	{
		this.player = player;
	}
	
	public void addRoboticon(Roboticon roboticon)
	{
		this.roboticon = roboticon;
	}
	
	public void removeRoboticon()
	{
		this.roboticon = null;
	}
	
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
	
	public Roboticon getRoboticon()
	{
		return this.roboticon;
	}
	
}

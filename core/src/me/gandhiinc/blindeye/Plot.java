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
	
	private Roboticon plotRoboticon;
	private Player player;
	
	public Plot(int foodModifier, int energyModifier, int oreModifier, int terrainType)
	{
		this.foodMod = foodModifier;
		this.energyMod = energyModifier;
		this.oreMod = oreModifier;
		this.terrain = terrainType; 
	}

/* ---------------------------------------------------------
 * -------------	Getters and setters		----------------
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
		plotRoboticon = roboticon;
	}
	
	public void removeRoboticon()
	{
		plotRoboticon = null;
	}
	
	public boolean hasRoboticon()
	{
		if (plotRoboticon != null)
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
		return plotRoboticon;
	}
	
	//Fixed plots from x, y coordinates
	//Need to create the plot objects and fix their coordinates will do this next Friday perhaps
	//We will have to fix their terrain type and modifiers also
	
	//TOP ROW STARTING FROM LEFT
	
	//Plot 1: if(x < 175 && y < 175)
	//Plot 2: if(175 < x < 349 && y < 175)
	//Plot 3: if(349 < x < 523 && y < 175)
	//Plot 4: if(523 < x < 697 && y < 175)
	//Plot 5: if(697 < x < 870 && y < 175)
	//Plot 6: if(870 < x < 1043 && y < 175)
	
	//SECOND ROW STARTING FROM LEFT
	
	//Plot 7: if(x < 175 && 175 < y < 349)
	//Plot 8: if(175 < x < 349 && 175 < y < 349)
	//Plot 9: if(349 < x < 523 && 175 < y < 349)
	//Plot 10: if(523 < x < 697 && 175 < y < 349)
	//Plot 11: if(697 < x < 870 && 175 < y < 349)
	//Plot 12: if(870 < x < 1043 && 175 < y < 349)
	
	// THIRD ROW STARTING FROM LEFT
	
	//Plot 13: if(x < 175 && 349 < y < 523)
	//Plot 14: if(175 < x < 349 && 349 < y < 523)
	//Plot 15: if(349 < x < 523 && 349 < y < 523)
	//Plot 16: if(523 < x < 697 && 349 < y < 523)
	//Plot 17: if(697 < x < 870 && 349 < y < 523)
	//Plot 18: if(870 < x < 1043 && 349 < y < 523)
	
	// FOURTH ROW STARTING FROM LEFT
	
	//Plot 19: if(x < 175 && 523 < y < 697)
	//Plot 20: if(175 < x < 349 && 523 < y < 697)
	//Plot 21: if(349 < x < 523 && 523 < y < 697)
	//Plot 22: if(523 < x < 697 && 523 < y < 697)
	//Plot 23: if(697 < x < 870 && 523 < y < 697)
	//Plot 24: if(870 < x < 1043 && 523 < y < 697)

	//Can't actually reach the bottom on my small screen lol so the rest will have to wait...
}
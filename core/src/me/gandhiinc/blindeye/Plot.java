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
	private float foodMod;
	private float energyMod;
	private float oreMod;
	//define types of terrain and their codes (0 for type 1, 1 for type 2, etc...)
	private int terrain;
	
	//cost to purchase a plot; should be fixed; define this later on
	public static final int cost = 50;
	
	private Roboticon plotRoboticon;
	private boolean isAssigned = false;
	
	public Plot(float foodModifier, float energyModifier, float oreModifier, int terrainType)
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

	public float getFoodMod()
	{
		return foodMod;
	}
	
	public float getEnergyMod()
	{
		return energyMod;
	}
	
	public float getOreMod()
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
	
	public boolean getAssignment()
	{
		return isAssigned;
	}
	
	public void setAssigned()
	{
		isAssigned = true;
	}
	
	public void setUnassigned()
	{
		isAssigned = false;
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

}
package me.gandhiinc.test;

/**
 * 
 * @author Pedro Wigderowitz
 * @version 0.1
 *
 */

public class Plot
{
	private int foodMod;
	private int energyMod;
	private int oreMod;
	//define types of terrain and their codes (0 for type 1, 1 for type 2, etc...)
	private int terrain;
	
	//cost to purchase a plot; should be fixed; define this later on
	private static final int cost = 50;
	
	private Roboticon plotRoboticon;
	private boolean isAssigned = false;
	
	public Plot(int foodModifier, int energyModifier, int oreModifier, int terrainType)
	{
		this.foodMod = foodModifier;
		this.energyMod = energyModifier;
		this.oreMod = oreModifier;
		this.terrain = terrainType; 
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
	
	public void setAssigned(Player player)
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
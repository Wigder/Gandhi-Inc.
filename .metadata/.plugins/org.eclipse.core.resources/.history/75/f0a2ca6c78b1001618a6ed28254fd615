package me.gandhiinc.test;

/**
 * 
 * @author Pedro Wigderowitz
 * @version 0.1
 *
 */

public class Plot (int foodModifier, int energyModifier, int oreModifier, int terrainType)
{
	
	private int foodMod = foodModifier;
	private int energyMod = energyModifier;
	private int oreMod = oreModifier;
	//define types of terrain and their codes (0 for type 1, 1 for type 2, etc...)
	private int terrain = terrainType;
	
	//cost to purchase a plot; should be fixed; define this later on
	private static final int cost;
	
	private Roboticon plotRoboticon;
	private boolean isAssigned = False;
	
	public int getTerrain()
	{
		return terrain;
	}
	
	public void changeTerrain(int newTerrainType)
	{
		terrain = newTerrainType;
	}
	
	public boolean retAssignment()
	{
		return isAssigned;
	}
	
	public void setAssigned(Player player)
	{
		isAssigned = True;
	}
	
	public void setUnassigned()
	{
		isAssigned = False;
	}
	
	public void addRoboticon(Roboticon roboticon)
	{
		plotRoboticon = True;
	}
	
	public void removeRoboticon()
	{
		plotRoboticon = null;
	}
	
	public boolean hasRoboticon()
	{
		if (plotRoboticon != null)
		{
			return True;
		}
		else
		{
			return False;
		}
	}
	
	public Roboticon retRoboticon()
	{
		return plotRoboticon;
	}

}
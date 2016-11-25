package me.gandhiinc.blindeye;

import java.util.List;

/**
 * ---------------------------------------------------------
 * --------------------   PLAYER   -------------------------
 * ---------------------------------------------------------
 * 
 * @author Steven Tomlinson
 * @version 1.0
 *
 */

public class Player 
{
	private int money;
	private int ore;
	private int energy;

	private List<Roboticon> roboticons;
	private List<Plot> plots;

	public void AquirePlot(Plot plot) throws Exception
	{
		if (plot.getAssignment() == false)
		{
			plots.add(plot);
			plot.setAssigned(this);
		}
		else
			throw new Exception("The plot has already been assigned to a player");
	}

	public void SpecialiseRoboticon(Roboticon roboticon, Resource resource)
	{
		roboticon.setSpec(resource);
	}

	public void ProduceResources()
	{
		return;
	}

	public int getMoney()
	{
		return money;
	}

	public int getOre()
	{
		return ore;
	}

	public int getEnergy()
	{
		return energy;
	}

	public void setMoney(int money) 
	{
		this.money = money;
	}

	public void setOre(int ore) 
	{
		this.ore = ore;
	}

	public void setEnergy(int energy) 
	{
		this.energy = energy;
	}

	public List<Roboticon> getRoboticons() 
	{
		return roboticons;
	}

	public void addRoboticon(Roboticon roboticon)
	{
		roboticons.add(roboticon);
	}
	
} 
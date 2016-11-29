package me.gandhiinc.blindeye;

import java.util.Iterator;
import java.util.ArrayList;

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
	protected int money;
	protected int ore;
	protected int energy;

	protected ArrayList<Roboticon> roboticons;
	protected ArrayList<Plot> plots;
	

	public Player(int money, int ore, int energy, ArrayList<Roboticon> roboticons, ArrayList<Plot> plots) 
	{
		this.money = money;
		this.ore = ore;
		this.energy = energy;
		this.roboticons = roboticons;
		this.plots = plots;
	}

	public void AquirePlot(Plot plot) throws Exception
	{
		if (plot.getAssignment() == false)
		{
			plots.add(plot);
			plot.setAssigned();
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
		int oreProduction = 0;
		int energyProduction = 0;
		for (Iterator<Roboticon> IterativeRoboticons = roboticons.iterator(); IterativeRoboticons.hasNext();)
		{
			Roboticon roboticon = IterativeRoboticons.next();
			Resource roboticonSpec = roboticon.getSpec();
			switch (roboticonSpec)
			{
			case ENERGY:
				energyProduction = roboticon.calcProd(roboticon.getSpec(), 1);
				break;
			case ORE:
				oreProduction = roboticon.calcProd(roboticon.getSpec(), 1);
				break;
			}
		}
		ore += oreProduction;
		energy += energyProduction;
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

	public ArrayList<Roboticon> getRoboticons() 
	{
		return roboticons;
	}

	public void addRoboticon(Roboticon roboticon)
	{
		roboticons.add(roboticon);
	}
	
} 

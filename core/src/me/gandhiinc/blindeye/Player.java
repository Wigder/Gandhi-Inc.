package me.gandhiinc.blindeye;

import java.util.Iterator;
import java.util.ArrayList;

/**
 * ---------------------------------------------------------
 * --------------------   PLAYER   -------------------------
 * ---------------------------------------------------------
 * 
 * This where all of the information for each player is along with all of the actions they can perform.
 * It contains the following methods:
 * 
 * 		-	AcquirePlot(Plot plot)
 * 		-	SpecialiseRoboticon(Roboticon roboticon, Resource resource)
 * 		-	ProduceResources()
 * 
 * And then getters and setters for the resources along with a getter for a list of roboticons and functionality to add a roboticon
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
	
	protected String name;

	/**
	 * The is the constructor of the Player class to instantiate a new instance of the Player class
	 * @param name The name of the player object i.e Player 1
	 * @param money The starting money of the player
	 * @param ore The starting ore of the player
	 * @param energy The starting energy of the player
	 */
	public Player(String name, int money, int ore, int energy) 
	{
		this.name = name;
		this.money = money;
		this.ore = ore;
		this.energy = energy;
		this.roboticons = new ArrayList<Roboticon>();
		this.plots = new ArrayList<Plot>();
	}

	/**
	 * This allows the player to acquire a new plot which in turn adds the plot to the list of plots the player owns
	 * @param plot The plot which is going to be acquired.
	 * @throws Exception If the plot already belongs to a player
	 */
	public void AcquirePlot(Plot plot) throws Exception
	{
		if (plot.getPlayer() == null)
		{
			plots.add(plot);
			plot.setPlayer(this);
		}
		else
			throw new Exception("The plot has already been assigned to a player");
	}

	/**
	 * Allow the player to specialise their roboticon to a given a resource
	 * @param roboticon The roboticon to be specialised
	 * @param resource The resource the roboticon will be specialised for
	 */
	public void SpecialiseRoboticon(Roboticon roboticon, Resource resource)
	{
		roboticon.setSpec(resource);
	}

	/**
	 * This is the function to automatically produce the resources which is based upon the plots the player owns and the Roboticons which have been assigned.
	 */
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
			default:
				break;
			}
		}
		ore += oreProduction;
		energy += energyProduction;
	}

	/**
	 * This gets the amount of money the player currently has
	 * @return the Integer for the money
	 */
	public int getMoney()
	{
		return money;
	}

	/**
	 * This gets the amount of ore the player currently has
	 * @return the Integer for the ore
	 */
	public int getOre()
	{
		return ore;
	}

	/**
	 * This gets the amount of energy the player currently has
	 * @return the Integer for the energy
	 */
	public int getEnergy()
	{
		return energy;
	}

	public String getName()
	{
		return name;
	}
	
	/**
	 * This sets the money the player has to specific value
	 * @param money The value for the money to be set to
	 */
	public void setMoney(int money) 
	{
		this.money = money;
	}

	/**
	 * This sets the ore the player has to specific value
	 * @param ore The value for the ore to be set to
	 */
	public void setOre(int ore) 
	{
		this.ore = ore;
	}

	/**
	 * This sets the energy the player has to specific value
	 * @param energy The value for the energy to be set to
	 */
	public void setEnergy(int energy) 
	{
		this.energy = energy;
	}

	/**
	 * Gets the list of Roboticons the player owns
	 * @return returns the roboticons the player owns as an ArrayList
	 * @see ArrayList
	 */
	public ArrayList<Roboticon> getRoboticons() 
	{
		return roboticons;
	}

	/**
	 * This adds a roboticon to the list of roboticons the player owns
	 * @param roboticon The roboticon to be added
	 */
	public void addRoboticon(Roboticon roboticon)
	{
		roboticons.add(roboticon);
	}
	
	/**
	 * This returns the list of plots that the player owns
	 * @return ArrayList of plots
	 */
	public ArrayList<Plot> getPlots()
	{
		return plots;
	}
	
	/**
	 * This returns the list of roboticons that currently have no specialisation
	 * @return ArrayList of roboticons
	 */
	public ArrayList<Roboticon> getUnspecialisedRoboticons()
	{
		ArrayList<Roboticon> retList = new ArrayList<Roboticon>();
		for (Iterator<Roboticon> roboticonIterator = roboticons.iterator(); roboticonIterator.hasNext(); )
		{
			Roboticon r = roboticonIterator.next();
			if (r.getSpec() == Resource.NONE)
			{
				retList.add(r);
			}
		}
		return retList;
	}
	
	/**
	 * This returns the list of roboticons that currently have no assigned plot
	 * @return ArrayList of roboticons
	 */
	public ArrayList<Roboticon> getUnassignedRoboticons()
	{
		ArrayList<Roboticon> retList = new ArrayList<Roboticon>();
		for (Iterator<Roboticon> roboticonIterator = roboticons.iterator(); roboticonIterator.hasNext(); )
		{
			Roboticon r = roboticonIterator.next();
			if (r.getPlot() == null && r.getSpec() != Resource.NONE)
			{
				retList.add(r);
			}
		}
		return retList;
	}
	
} 

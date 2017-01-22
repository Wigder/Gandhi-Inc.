package me.gandhiinc.blindeye;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ---------------------------------------------------------
 * --------------------   GameEngine -----------------------
 * ---------------------------------------------------------
 * 
 * This is where all of the game logic will be done allowing a game to be played 
 * It contains the following methods:
 * 
 * 		-	Start()
 * 		-	Stop()
 * 		-	update(float deltaTime)
 * 
 * @author Steven Tomlinson
 * @version 1.0
 *
 * @date 29/11/16
 */
public class GameEngine 
{
	// Lists of all the players
	private List<Player> humanPlayers;
	private List<AIPlayer> aiPlayers;

	// Array of all the plots
	private Plot[] plots;
	
	// The currently selected plot
	private Plot activePlot;
	
	// The market place object
	private MarketPlace market;

	// The dimensions of the map
	private final int mapWidth;
	private final int mapHeight;

	// The the time that each phase gets set to at the start of that phase (apart from phase 1)
	private final float PHASE_TIME_CONST = 2 * 60;

	// current phase
	private int phase = 1;
	
	// the player whose turn it currently is
	private Player currentPlayer;
	
	//is the game running
	private boolean running = false;
	
	// the time of the current phase
	private float phaseTime;

	/**
	 * This allows a new instance of the GameEngine object to be instantiated allow a game to be played.
	 * @param humanPlayers The list of human players who are player as Player objects
	 * @param aiPlayers The list of ai players who are playing as AIPlayer objects
	 * @param mapWidth The with of the map
	 * @param mapHeight The height of the map
	 */
	public GameEngine(ArrayList<Player> humanPlayers, ArrayList<AIPlayer> aiPlayers, int mapWidth, int mapHeight)
	{
		this.humanPlayers = humanPlayers;
		this.aiPlayers = aiPlayers;
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;
		
		market = new MarketPlace();
		
		plots = new Plot[mapWidth * mapHeight];
		
		for (int i = 0; i < plots.length; i++)
		{
			plots[i] = new Plot(1, 1, 1, 1);
		}
		
		activePlot = null;
		
		phaseTime = PHASE_TIME_CONST;
	}

	/**
	 * Starts the game
	 */
	public void start()
	{
		currentPlayer = humanPlayers.get(0);
		phase = 1;
		phaseTime = -1;
		running = true;
	}
	
	/**
	 * Checks to see if the game is running
	 * @return The running variable
	 */
	public boolean isRunning()
	{
		return running;
	}
	
	/**
	 * Stops the game
	 */
	public void stop()
	{
		running = false;
	}
	
	/**
	 * Updates the data in the game given a time since the last update
	 * @param deltaTime Time since last update
	 */
	public void update(float deltaTime)
	{
		// only update the phase time if the current phase is not the first phase
		if (phase != 1)
			phaseTime -= deltaTime;

		// if the phase time is less that 0 
		if (phaseTime < 0)
		{
			// if phase 2 then produce the resources for the player
			if (phase == 2)
			{
				currentPlayer.ProduceResources();
				// if the current player is the last human player then complete the turns for the AI players
				if (humanPlayers.indexOf(currentPlayer) == humanPlayers.size() - 1)
				{
					for (Iterator<AIPlayer> playerIterator = aiPlayers.iterator(); playerIterator.hasNext(); )
					{
						AIPlayer player = playerIterator.next();
						ArrayList<Plot> validPlots = new ArrayList<Plot>();
						for (Plot plot: plots)
						{
							if (plot.getPlayer() == null)
								validPlots.add(plot);
						}
						player.CompleteTurn(validPlots.toArray(new Plot[validPlots.size()]), market);
						market.produceRoboticon();
					}
					// when all players (human and ai) have finished their turns have the market produce the roboticons, set the current player to the first human player and set the phase to 3 and reset the timer

					currentPlayer = humanPlayers.get(0);
					phase = 3;
					phaseTime = PHASE_TIME_CONST;
				}
				// else set the current player to the next human player and set the phase to one and phase time to -1 (i.e infinite)
				else
				{
					currentPlayer = humanPlayers.get(humanPlayers.indexOf(currentPlayer) + 1);
					phase = 1;
					phaseTime = -1;
					market.produceRoboticon();
				}
			}
			else if (phase == 3)
			{
				// if phase 3 has ended for the current player and there are no human players left to go have the ai players sell their resources and then go back to phase 1 with player 1
				if (humanPlayers.indexOf(currentPlayer) == humanPlayers.size() - 1)
				{
					for (Iterator<AIPlayer> playerIterator = aiPlayers.iterator(); playerIterator.hasNext(); )
					{
						AIPlayer player = playerIterator.next();
						player.SellResources(market);
					}
					currentPlayer = humanPlayers.get(0);
					phase = 1;
					phaseTime = -1;
				}
				// otherwise go to phase 3 for the next human player
				else
				{
					currentPlayer = humanPlayers.get(humanPlayers.indexOf(currentPlayer) + 1);
					phase = 3;
					phaseTime = PHASE_TIME_CONST;
				}
			}
		}
	}
	
	/*public void updateTest()
	{
		for (Iterator<AIPlayer> playerIterator = aiPlayers.iterator(); playerIterator.hasNext(); )
		{
			AIPlayer player = playerIterator.next();
			ArrayList<Plot> validPlots = new ArrayList<Plot>();
			for (Plot plot: plots)
			{
				if (plot.getPlayer() == null)
					validPlots.add(plot);
			}
			
			player.CompleteTurn(validPlots.toArray(new Plot[validPlots.size()]), market);	
		}
		
		ArrayList<Plot> validPlots = new ArrayList<Plot>();
		for (Plot plot: plots)
		{
			if (plot.getPlayer() == null)
				validPlots.add(plot);
		}
		
		market.produceRoboticon();
		market.setPrices();
		if (validPlots.size() == 0)
			stop();
		
		for (Iterator<AIPlayer> playerIterator = aiPlayers.iterator(); playerIterator.hasNext();)
		{
			AIPlayer player = playerIterator.next();
			System.out.println("Name: " + player.getName() + " Money: " + player.getMoney() + " Energy: "
			+ player.getEnergy() + " Ore: " + player.getOre() + " Roboticons: " + player.getRoboticons().size() + " Plots: " + player.getPlots().size());
		}
		
	}*/
	
	public Plot[] getPlots()
	{
		return plots;
	}
	
	public int getMapWidth()
	{
		return mapWidth;
	}
	
	public int getMapHeight()
	{
		return mapHeight;
	}
	
	public List<Player> getHumanPlayers()
	{
		return humanPlayers;
	}
	
	public List<AIPlayer> getAIPlayers()
	{
		return aiPlayers;
	}
	
	public Plot getActivePlot()
	{
		return activePlot;
	}
	
	public void setActivePlot(Plot plot)
	{
		activePlot = plot;
	}
	
	public float getPhaseTime()
	{
		return phaseTime;
	}
	
	public void setPhaseTime(float phaseTime)
	{
		this.phaseTime = phaseTime;
	}
	
	public int getPhase()
	{
		return phase;
	}
	
	public void setPhase(int phase)
	{
		this.phase = phase;
		if (phase != 1)
		{
			phaseTime = PHASE_TIME_CONST;
		}
	}
	
	public Player getCurrentPlayer()
	{
		return currentPlayer;
	}
	
	public MarketPlace getMarket()
	{
		return market;
	}
}
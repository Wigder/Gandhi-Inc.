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

	public static void main(String[] args) {
		ArrayList<AIPlayer> players = new ArrayList<AIPlayer>();
		players.add(new AIPlayer("Player 1", 50, 0, 0));
		players.add(new AIPlayer("Player 2", 50, 0, 0));
		GameEngine game = new GameEngine(null, players, 5, 6);

		game.start();
		
		while(game.running)
		{
			game.updateTest();
			for (int i = 0; i < game.plots.length; i++)
			{
				System.out.print("[");
				int x = 1;
				for (Iterator<AIPlayer> playerIterator = game.aiPlayers.iterator(); playerIterator.hasNext(); )
				{
					AIPlayer player = playerIterator.next();
					if (player.plots.contains(game.plots[i]))
						System.out.print(x);
					x++;
				}
				System.out.print("]");
			}
			System.out.println("");
		}
		
		System.out.println("End");
		
	}

	private List<Player> humanPlayers;
	private List<AIPlayer> aiPlayers;

	private Plot[] plots;
	
	private MarketPlace market;

	private final int mapWidth;
	private final int mapHeight;

	private final long phase2Time = 2 * 60 * 1000;
	private final int phase3Time = 2 * 60;

	private boolean running = false;
	
	private long phaseTime;

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
		
		phaseTime = phase2Time;
	}

	/**
	 * Starts the game
	 */
	void start()
	{
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
	void stop()
	{
		for (Iterator<AIPlayer> playerIterator = aiPlayers.iterator(); playerIterator.hasNext();)
		{
			AIPlayer player = playerIterator.next();
			System.out.println("Name: " + player.getName() + " Money: " + player.getMoney() + " Energy: "
			+ player.getEnergy() + " Ore: " + player.getOre() + " Roboticons: " + player.getRoboticons().size() + " Plots: " + player.getPlots().size());
		}
		running = false;
	}
	
	/**
	 * Updates the data in the game given a time since the last update
	 * @param deltaTime Time since last update
	 */
	public void update(long deltaTime)
	{
		phaseTime -= deltaTime;
		System.out.println((phaseTime / 1000));
		if (phaseTime < 0 )
			System.out.println("Game End");
	}
	
	public void updateTest()
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
		
		if (validPlots.size() == 0)
			stop();
		
		
		
	}
	
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

}
package me.gandhiinc.blindeye;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameEngine 
{

	public static void main(String[] args) {
		ArrayList<AIPlayer> players = new ArrayList<AIPlayer>();
		players.add(new AIPlayer(50, 50, 50, new ArrayList<Roboticon>(), new ArrayList<Plot>()));
		players.add(new AIPlayer(50, 50, 50, new ArrayList<Roboticon>(), new ArrayList<Plot>()));
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

	List<Player> humanPlayers;
	List<AIPlayer> aiPlayers;

	Plot[] plots;
	
	MarketPlace market;

	public final int mapWidth;
	public final int mapHeight;

	public final long phase2Time = 2 * 60 * 1000;
	public final int phase3Time = 2 * 60;

	boolean running = false;
	
	long phaseTime;

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

	void start()
	{
		running = true;
	}
	
	void stop()
	{
		running = false;
	}
	
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
				if (plot.getAssignment() == false)
					validPlots.add(plot);
			}
			
			player.CompleteTurn(validPlots.toArray(new Plot[validPlots.size()]), market);	
		}
		
		ArrayList<Plot> validPlots = new ArrayList<Plot>();
		for (Plot plot: plots)
		{
			if (plot.getAssignment() == false)
				validPlots.add(plot);
		}
		
		if (validPlots.size() == 0)
			stop();
		
	}

}
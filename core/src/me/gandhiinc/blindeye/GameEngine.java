package me.gandhiinc.blindeye;

import java.util.List;

public class GameEngine 
{

	public static void main(String[] args) {
		GameEngine game = new GameEngine(null, null, 0, 0);
		long prevTime = System.currentTimeMillis();
		while (true)
		{
			long currentTime = System.currentTimeMillis();
			game.update(currentTime - prevTime);
			prevTime = currentTime;
		}

	}

	List<Player> humanPlayers;
	List<AIPlayer> aiPlayers;

	Plot[] plots;

	public final int mapWidth;
	public final int mapHeight;

	public final long phase2Time = 2 * 60 * 1000;
	public final int phase3Time = 2 * 60;

	long phaseTime;

	public GameEngine(List<Player> humanPlayers, List<AIPlayer> aiPlayers, int mapWidth, int mapHeight)
	{
		this.humanPlayers = humanPlayers;
		this.aiPlayers = aiPlayers;
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;

		phaseTime = phase2Time;
		System.out.println("DOne");	
	}

	public void update(long deltaTime)
	{
		phaseTime -= deltaTime;
		System.out.println((phaseTime / 1000));
		if (phaseTime < 0 )
			System.out.println("Game End");
	}

}
/*url: http://gandhi-inc.me/downloads/blindeye.jar*/
package me.gandhiinc.blindeye.test;

import java.util.ArrayList;
import java.util.Iterator;

import me.gandhiinc.blindeye.AIPlayer;
import me.gandhiinc.blindeye.GameEngine;
import me.gandhiinc.blindeye.Player;

public class GameTest {

	public static void main(String[] arg) throws Exception
	{
		for (int i = 0; i < 10000; i++)
		{
			ArrayList<AIPlayer> players = new ArrayList<AIPlayer>();
			ArrayList<Player> dummy = new ArrayList<Player>();
            dummy.add(new Player("",0,0,0));
            players.add(new AIPlayer("1", 50, 50, 50));
			players.add(new AIPlayer("2", 50, 50, 50));
			GameEngine game = new GameEngine(dummy, players, 6, 5);
			game.start();
			while (game.isRunning())
			{
				game.updateTest();
				for (Iterator<AIPlayer> playerIterator = game.getAIPlayers().iterator(); playerIterator.hasNext();)
				{
					AIPlayer player = playerIterator.next();
					if (player.getMoney() > 10000)
					{
						throw new Exception("Oh no!");
					}
					if (player.getMoney() < 0)
					{	
						throw new Exception("Oh No!");
					}
				}
			}
		}
	}
	
}

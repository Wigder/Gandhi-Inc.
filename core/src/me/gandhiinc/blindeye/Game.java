package me.gandhiinc.blindeye;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends ApplicationAdapter implements InputProcessor
{
	SpriteBatch batch;
	Texture mapImg;
	Texture roboticonImg;
	
	GameEngine gameEngine;
	
	@Override
	public void create () 
	{
		ArrayList<AIPlayer> players = new ArrayList<AIPlayer>();
		players.add(new AIPlayer("Player 1", 50, 50, 50));
		players.add(new AIPlayer("Player 2", 50, 50, 50));
		gameEngine = new GameEngine(null, players, 6, 5);
		gameEngine.start();
		batch = new SpriteBatch();
		mapImg = new Texture("YorkUniMap.png");
		roboticonImg = new Texture("Graphics/Roboticon.png");
		
		Gdx.input.setInputProcessor(this); //This handles all the processing input (The processor of the input is "this")
	}
	
	@Override
	public void render () 
	{
		gameEngine.updateTest();
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		
		batch.draw(mapImg, 0, 0);
		
		for (int i = 0; i < gameEngine.getPlots().length; i++)
		{
			if (gameEngine.getPlots()[i].hasRoboticon())
				batch.draw(roboticonImg, (i % gameEngine.getMapWidth()) * 175, (i / gameEngine.getMapWidth()) * 175);
		}
		batch.end();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void dispose () 
	{
		batch.dispose();
		mapImg.dispose();
	}

	@Override
	public boolean keyUp(int keycode) 
	{
		return false;
	}

	@Override
	public boolean keyTyped(char character) 
	{
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) //any mouse click
	{ 
		if (button == Input.Buttons.LEFT) //if left mouse click
			if (screenX <= mapImg.getWidth() && screenY <= mapImg.getHeight())
			{
				int x = (int)(((float)screenX / mapImg.getWidth()) * gameEngine.getMapWidth());
				int y = (int)(((float)screenY / mapImg.getHeight()) * gameEngine.getMapHeight());
				//add check to get plot from list of plots and draw selection highlight around it
				System.out.print("Point: (" + x + ", " + y + ") = Index: " + (x + y * gameEngine.getMapWidth()) + "\t|\t");
			}
			System.out.println("Mouse Click\tX: " + screenX + "\tY: " + screenY);
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) 
	{
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) 
	{
		return false;
	}

	@Override
	public boolean scrolled(int amount)
	{
		return false;
	}

	@Override
	public boolean keyDown(int keycode) 
	{
		if (keycode == Input.Keys.ESCAPE)
			Gdx.app.exit();
				
		return true;
	}
}

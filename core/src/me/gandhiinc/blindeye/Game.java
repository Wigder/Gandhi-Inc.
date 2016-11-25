package me.gandhiinc.blindeye;

import java.util.List;

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
	Texture img;
	
	List<Player> humanPlayers;
	List<AIPlayer> aiPlayers;
	
	public final int mapWidth = 6;
	public final int mapHeight = 5;
	
	Plot[] plots;
	
	public final int phase2Time = 2 * 60;
	public final int phase3Time = 2 * 60;
	
	float phaseTime;
	
	@Override
	public void create () 
	{
		phaseTime = phase2Time;
		batch = new SpriteBatch();
		img = new Texture("YorkUniMap.png");
		
		Gdx.input.setInputProcessor(this); //This handles all the processing input (The processor of the input is "this")
	}

	public void update(float deltaTime)
	{
		float timeBefore = phaseTime;
		phaseTime -= deltaTime;
		float timeAfter = phaseTime;
		if (((int) timeBefore - (int) timeAfter) == 1)
			System.out.println(phaseTime);
		if (phaseTime < 0 )
			Gdx.app.exit();
	}
	
	@Override
	public void render () 
	{
		update(Gdx.graphics.getDeltaTime());
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);	
		batch.end();
	}
	
	@Override
	public void dispose () 
	{
		batch.dispose();
		img.dispose();
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

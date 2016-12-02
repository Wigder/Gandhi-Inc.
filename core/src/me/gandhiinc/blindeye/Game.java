package me.gandhiinc.blindeye;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Game extends ApplicationAdapter
{
	private SpriteBatch batch;
	private ShapeRenderer shapeRenderer;
	private Texture mapImg;
	private Texture roboticonImg;
	
	private Stage stage;
	
	private GameEngine gameEngine;
	
	private int activePlotIndex = -1;
	
	@Override
	public void create () 
	{
		
		ArrayList<AIPlayer> players = new ArrayList<AIPlayer>();
		players.add(new AIPlayer("Player 1", 50, 50, 50));
		players.add(new AIPlayer("Player 2", 50, 50, 50));
		gameEngine = new GameEngine(null, players, 6, 5);
		gameEngine.start();
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		mapImg = new Texture("YorkUniMap.png");
		roboticonImg = new Texture("Graphics/Roboticon.png");
		
		stage = new Stage(){
	        @Override
	        public boolean keyDown(int keyCode) {
	            if (keyCode == Input.Keys.ESCAPE) {
	                activePlotIndex = -1;
	            }
	            return super.keyDown(keyCode);
	        }
		};
		
		Actor mapActor = new Actor();
		mapActor.setBounds(0, 0, mapImg.getWidth(), mapImg.getHeight());
		
		mapActor.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float screenX, float screenY, int pointer, int button)
			{
				if (button == Input.Buttons.LEFT)
				{
					int x = (int)(((float)screenX / mapImg.getWidth()) * gameEngine.getMapWidth());
					int y = (int)(((float)(mapImg.getHeight() - screenY) / mapImg.getHeight()) * gameEngine.getMapHeight());
					System.out.println("X: " + screenX + " Y: " + screenY + "Point: (" + x + ", " + y + ") = Index: " + (x + y * gameEngine.getMapWidth()));
					activePlotIndex = (x + y * gameEngine.getMapWidth());
				}
				return true;
			}
		});
		
		stage.addActor(mapActor);
		
		Gdx.input.setInputProcessor(stage); //This handles all the processing input (The processor of the input is "this")
	}
	
	@Override
	public void render () 
	{
		if (gameEngine.isRunning())
			gameEngine.updateTest();
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		
		batch.draw(mapImg, 0, 0);
		
		batch.end();
		
		Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		
		shapeRenderer.begin(ShapeType.Filled);
		
		for (int i = 0; i < gameEngine.getPlots().length; i++)
		{
			if (gameEngine.getPlots()[i].getPlayer() != null)
			{
				if (gameEngine.getAIPlayers().indexOf(gameEngine.getPlots()[i].getPlayer()) == 0)
				{
					shapeRenderer.setColor(1, 0, 0, 0.35f);
					shapeRenderer.rect((i % gameEngine.getMapWidth()) * 174, (gameEngine.getMapHeight() - (i / gameEngine.getMapWidth()) - 1) * 174, 174, 174);
				}
				else if (gameEngine.getAIPlayers().indexOf(gameEngine.getPlots()[i].getPlayer()) == 1)
				{
					shapeRenderer.setColor(0, 0, 1, 0.35f);
					shapeRenderer.rect((i % gameEngine.getMapWidth()) * 174, (gameEngine.getMapHeight() - (i / gameEngine.getMapWidth()) - 1) * 174, 174, 174);	
				}
			}
		}
		
		if (activePlotIndex >= 0 && activePlotIndex < gameEngine.getPlots().length)
		{
			shapeRenderer.setColor(1, 1, 1, 0.5f);
			shapeRenderer.rect((activePlotIndex % gameEngine.getMapWidth()) * 174, (gameEngine.getMapHeight() - (activePlotIndex / gameEngine.getMapWidth()) - 1) * 174, 173, 173);
		}
		
		shapeRenderer.end();
		
		Gdx.gl.glDisable(GL20.GL_BLEND);
		
		batch.begin();
		for (int i = 0; i < gameEngine.getPlots().length; i++)
		{
			if (gameEngine.getPlots()[i].getPlayer() != null)
			{
				if (gameEngine.getPlots()[i].hasRoboticon())
				{
					batch.draw(roboticonImg, (i % gameEngine.getMapWidth()) * 175, (gameEngine.getMapHeight() - (i / gameEngine.getMapWidth()) - 1) * 175);
				}
			}
		}
		batch.end();
		
		/*
		if (gameEngine.isRunning())	
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		*/
		
	}
	
	@Override
	public void dispose () 
	{
		batch.dispose();
		mapImg.dispose();
	}

}

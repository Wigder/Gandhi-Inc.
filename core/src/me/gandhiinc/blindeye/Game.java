package me.gandhiinc.blindeye;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
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
		//Setup the players for the game
		ArrayList<AIPlayer> players = new ArrayList<AIPlayer>();
		players.add(new AIPlayer("Player 1", 50, 50, 50));
		players.add(new AIPlayer("Player 2", 50, 50, 50));
		
		//Create the game and the then start the game
		gameEngine = new GameEngine(null, players, 6, 5);
		gameEngine.start();
		
		//Create the require renderers
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		
		//Setup the required assets
		mapImg = new Texture("YorkUniMap.png");
		roboticonImg = new Texture("Graphics/Roboticon.png");
		
		//Create a new stage object to implement the UI
		stage = new Stage(){
	        @Override
	        public boolean keyDown(int keyCode) {
	            if (keyCode == Input.Keys.ESCAPE) {
	                activePlotIndex = -1;
	            }
	            return super.keyDown(keyCode);
	        }
		};
		
		//Create a new empty map to get mouse click events on the map
		Actor mapActor = new Actor();
		//Set the bounds to where the map will exist on the screen
		mapActor.setBounds(0, 0, mapImg.getWidth(), mapImg.getHeight());
		//Add a new event listener to listen for click (called touch in this library) events on the map actor 
		mapActor.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float screenX, float screenY, int pointer, int button)
			{
				if (button == Input.Buttons.LEFT)
				{
					//Get the percentage of the width of where the click was and then multiply how many tiles are in the width to get the x position of the tile that was clicked on
					int x = (int)(((float)screenX / mapImg.getWidth()) * gameEngine.getMapWidth());
					//Since our 0 tile is in the top left and the 0 pixel is in the bottom left we have to reverse the value by doing Image height - Screen Click Y to align the values
					//Then a similar calculation to find the y position of the tile that was clicked
					int y = (int)(((float)(mapImg.getHeight() - screenY) / mapImg.getHeight()) * gameEngine.getMapHeight());
					//Logging to make sure the that the active plot index is set correctly
					System.out.println("X: " + screenX + " Y: " + screenY + "Point: (" + x + ", " + y + ") = Index: " + (x + y * gameEngine.getMapWidth()));
					activePlotIndex = (x + y * gameEngine.getMapWidth());
				}
				return true;
			}
		});
		
		//Add the map actor to the stage
		stage.addActor(mapActor);
		
		//This handles all the processing input (The processor of the input is "stage")
		Gdx.input.setInputProcessor(stage); //This handles all the processing input (The processor of the input is "this")
	}
	
	@Override
	public void render () 
	{
		//Only update if the game is running
		if (gameEngine.isRunning())
			gameEngine.updateTest();
		
		//Clear to the back ground to black
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//Draw the map to the screen (Happens first so that the map is on the bottom so all the UI appears above it)
		batch.begin();
		batch.draw(mapImg, 0, 0);
		batch.end();
		
		//Allows transparency of of the UI rectangles (Allows the merging of the fragment (Colour) shaders, simulating transparency)
		Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
		//Sets transparency on the alpha channel
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		
		shapeRenderer.begin(ShapeType.Filled);
		
		for (int i = 0; i < gameEngine.getPlots().length; i++)
		{
			//If a plot is owned by a player then draw a transparent rectangle over it to signify its ownership
			if (gameEngine.getPlots()[i].getPlayer() != null)
			{
				if (gameEngine.getAIPlayers().indexOf(gameEngine.getPlots()[i].getPlayer()) == 0)
				{
					//the setColor function is in the form of setColor(red, green, blue, alpha) all of them being floats
					shapeRenderer.setColor(1, 0, 0, 0.35f);
					//X position is the remainder of the index divided by the map width, then to get the pixel value we multiply the tile width
					//Y position is the index divided by the map width then decremented to 0 index, and then invert to align with the correct coordinate system
					//The rect function is in the form of rect(x, y, width, height) where x and y are the bottom left of the rectangle
					shapeRenderer.rect((i % gameEngine.getMapWidth()) * 174, (gameEngine.getMapHeight() - (i / gameEngine.getMapWidth()) - 1) * 174, 174, 174);
				}
				else if (gameEngine.getAIPlayers().indexOf(gameEngine.getPlots()[i].getPlayer()) == 1)
				{
					//the setColor function is in the form of setColor(red, green, blue, alpha) all of them being floats
					shapeRenderer.setColor(0, 0, 1, 0.35f);
					//X position is the remainder of the index divided by the map width, then to get the pixel value we multiply the tile width
					//Y position is the index divided by the map width then decremented to 0 index, and then invert to align with the correct coordinate system
					//The rect function is in the form of rect(x, y, width, height) where x and y are the bottom left of the rectangle
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
		
		//Stop using transparency for the rest of the graphics
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
				
		stage.act();
		
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
		shapeRenderer.dispose();
		batch.dispose();
		mapImg.dispose();
		stage.dispose();
	}

}

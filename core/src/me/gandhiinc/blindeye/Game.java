package me.gandhiinc.blindeye;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class Game extends ApplicationAdapter
{
	private SpriteBatch batch;
	private ShapeRenderer shapeRenderer;
	private Texture mapImg;
	private Texture roboticonImg;
	
	private Stage gameStage;
	private Label timeLabel;
	private Label nameLabel;
	private Label oreLabel;
	private Label energyLabel;
	private Label moneyLabel;
	private TextButton nextPhaseButton;
	
	private GameEngine gameEngine;
	
	FreeTypeFontGenerator font;
	
	@Override
	public void create () 
	{
		
		//Setup the players for the game
		ArrayList<AIPlayer> aiPlayers = new ArrayList<AIPlayer>();
		aiPlayers.add(new AIPlayer("Player 1", 50, 50, 50));
		
		ArrayList<Player> humanPlayers = new ArrayList<Player>();
		humanPlayers.add(new Player("Steven", 50, 50, 50));
		
		//Create the game and the then start the game
		gameEngine = new GameEngine(humanPlayers, aiPlayers, 6, 5);
		gameEngine.start();
		
		//Create the require renderers
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		
		//Setup the required assets
		mapImg = new Texture(Gdx.files.internal("YorkUniMap.png"));
		roboticonImg = new Texture(Gdx.files.internal("Graphics/Roboticon.png"));
		font = new FreeTypeFontGenerator(Gdx.files.internal("earthorbiter.ttf"));
		
		//Create a new stage object to implement the UI
		gameStage = new Stage(){
	        @Override
	        public boolean keyDown(int keyCode) {
	            if (keyCode == Input.Keys.ESCAPE) {
	                gameEngine.setActivePlot(null);
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
					gameEngine.setActivePlot(gameEngine.getPlots()[x + y * gameEngine.getMapWidth()]);
				}
				return true;
			}
		});
		
		LabelStyle timels = new LabelStyle();
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 40;
		timels.font = font.generateFont(parameter);
		timeLabel = new Label("Time:", timels);
		timeLabel.setPosition(mapImg.getWidth() + 10, mapImg.getHeight() - 40);
		
		LabelStyle namels = new LabelStyle();
		parameter.size = 25;
		namels.font = font.generateFont(parameter);
		nameLabel = new Label("Player: ", namels);
		nameLabel.setPosition(mapImg.getWidth() + 10, mapImg.getHeight() - 80);
		
		LabelStyle resource = new LabelStyle();
		parameter.size = 30;
		
		resource.font = font.generateFont(parameter);
		
		oreLabel = new Label("", resource);
		energyLabel = new Label("", resource);
		moneyLabel = new Label("", resource);
		
		oreLabel.setPosition(mapImg.getWidth() + 10, mapImg.getHeight() - 120);
		energyLabel.setPosition(mapImg.getWidth() + 10, mapImg.getHeight() - 160);
		moneyLabel.setPosition(mapImg.getWidth() + 10, mapImg.getHeight() - 200);
		
		nextPhaseButton = new TextButton(null, null);
		
		//Add the map actor to the stage
		gameStage.addActor(mapActor);
		gameStage.addActor(timeLabel);
		gameStage.addActor(nameLabel);
		gameStage.addActor(oreLabel);
		gameStage.addActor(energyLabel);
		gameStage.addActor(moneyLabel);
		
		//This handles all the processing input (The processor of the input is "stage")
		Gdx.input.setInputProcessor(gameStage); //This handles all the processing input (The processor of the input is "this")
	}
	
	public void renderMap()
	{
		//Draw the map to the screen (Happens first so that the map is on the bottom so all the UI appears above it)
		batch.begin();
		batch.draw(mapImg, 0, 0);
		batch.end();
	}
	
	public void renderTileHighlights()
	{
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
		shapeRenderer.end();
		
		//Stop using transparency for the rest of the graphics
		Gdx.gl.glDisable(GL20.GL_BLEND);
	}
	
	public void renderActiveTileHighlight()
	{
		//Allows transparency of of the UI rectangles (Allows the merging of the fragment (Colour) shaders, simulating transparency)
		Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
		//Sets transparency on the alpha channel
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		
		shapeRenderer.begin(ShapeType.Filled);
		
		int activePlotIndex = java.util.Arrays.asList(gameEngine.getPlots()).indexOf(gameEngine.getActivePlot());
		
		if (activePlotIndex >= 0 && activePlotIndex < gameEngine.getPlots().length)
		{
			shapeRenderer.setColor(1, 1, 1, 0.5f);
			shapeRenderer.rect((activePlotIndex % gameEngine.getMapWidth()) * 174, (gameEngine.getMapHeight() - (activePlotIndex / gameEngine.getMapWidth()) - 1) * 174, 173, 173);
		}
		
		shapeRenderer.end();
		
		//Stop using transparency for the rest of the graphics
		Gdx.gl.glDisable(GL20.GL_BLEND);
	}
	
	public void renderRoboticons()
	{
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
	}
	
	public void renderUI()
	{
		Integer time = new Integer((int)gameEngine.getPhaseTime());
		if (time < 0)
			timeLabel.setText("Time: n/a");
		else
			timeLabel.setText("Time: " + time.toString());
		
		nameLabel.setText("Name: " + gameEngine.getCurrentPlayer().getName());

		oreLabel.setText("Ore: " + gameEngine.getCurrentPlayer().getOre());
		energyLabel.setText("Energy: " + gameEngine.getCurrentPlayer().getEnergy());
		moneyLabel.setText("Money: " + gameEngine.getCurrentPlayer().getMoney());
		
		gameStage.draw();
	}
	
	@Override
	public void render () 
	{
		//Clear to the back ground to black
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if (gameEngine.isRunning())
			gameEngine.update(Gdx.graphics.getDeltaTime());
		
		renderMap();
		renderTileHighlights();
		renderActiveTileHighlight();
		renderRoboticons();
		renderUI();
		gameStage.act();
	}
	
	@Override
	public void dispose () 
	{
		shapeRenderer.dispose();
		batch.dispose();
		mapImg.dispose();
		gameStage.dispose();
	}

}

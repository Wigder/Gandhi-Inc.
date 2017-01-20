package me.gandhiinc.blindeye;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
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
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

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
	private Label marketStockLabel;
	private TextButton nextPhaseButton;
	private TextButton buyRoboticonButton;
	private TextButton specialiseRoboticonButton;
	private TextButton assignRoboticonButton;
	private TextButton oreSpecButton;
	private TextButton energySpecButton;
	private TextButton cancelSpecButton;
	private TextButton oreAddSellButton;
	private TextButton oreMinusSellButton;
	private TextButton oreAddBuyButton;
	private TextButton oreMinusBuyButton;
	private TextButton energyAddSellButton;
	private TextButton energyMinusSellButton;
	private TextButton energyAddBuyButton;
	private TextButton energyMinusBuyButton;
	private TextButton energyBuyButton;
	private TextButton energySellButton;
	private TextButton oreBuyButton;
	private TextButton oreSellButton;
	
	private int oreBuyAmount = 0;
	private int oreSellAmount = 0;
	private int energyBuyAmount = 0;
	private int energySellAmount = 0;
	
	private TextButtonStyle tbs;
	
	private GameEngine gameEngine;
	
	FreeTypeFontGenerator font;
	FreeTypeFontParameter parameter;
	
	@Override
	public void create () 
	{
		
		//Setup the players for the game
		ArrayList<AIPlayer> aiPlayers = new ArrayList<AIPlayer>();
		
		ArrayList<Player> humanPlayers = new ArrayList<Player>();
		humanPlayers.add(new Player("Player 1", 50, 50, 50));
		humanPlayers.add(new Player("Player 2", 50, 50, 50));
		
		//Create the game and the then start the game
		gameEngine = new GameEngine(humanPlayers, aiPlayers, 6, 5);
		gameEngine.start();
		
		//Create the require renderers
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		
		initAssests();
		initGameUI();
		
	}
	
	public void initAssests()
	{
		//Setup the required assets
		mapImg = new Texture(Gdx.files.internal("YorkUniMap.png"));
		roboticonImg = new Texture(Gdx.files.internal("Graphics/Roboticon.png"));
		font = new FreeTypeFontGenerator(Gdx.files.internal("earthorbiter.ttf"));
	}
	
	public void initGameUI()
	{
		//Create a new stage object to implement the UI
		gameStage = new Stage(){
	        @Override
	        public boolean keyDown(int keyCode) {
	            if (keyCode == Input.Keys.ESCAPE) {
	                gameEngine.setActivePlot(null);
	                nextPhaseButton.setDisabled(false);
	            }
	            return super.keyDown(keyCode);
	        }
		};
		
		initMap();
		initGameUIElements();
		
		gameStage.addActor(marketStockLabel);
		gameStage.addActor(energyBuyButton);
		gameStage.addActor(energySellButton);
		gameStage.addActor(oreBuyButton);
		gameStage.addActor(oreSellButton);
		gameStage.addActor(oreAddSellButton);
		gameStage.addActor(oreMinusSellButton);
		gameStage.addActor(oreAddBuyButton);
		gameStage.addActor(oreMinusBuyButton);
		gameStage.addActor(energyAddSellButton);
		gameStage.addActor(energyMinusSellButton);
		gameStage.addActor(energyAddBuyButton);
		gameStage.addActor(energyMinusBuyButton);
		
		gameStage.addActor(assignRoboticonButton);
		gameStage.addActor(specialiseRoboticonButton);
		gameStage.addActor(buyRoboticonButton);
		
		gameStage.addActor(timeLabel);
		gameStage.addActor(nameLabel);
		gameStage.addActor(oreLabel);
		gameStage.addActor(energyLabel);
		gameStage.addActor(moneyLabel);
		
		gameStage.addActor(nextPhaseButton);
		
		//This handles all the processing input (The processor of the input is "stage")
		Gdx.input.setInputProcessor(gameStage);
	}
	
	public void initGameUIElements()
	{
		parameter = new FreeTypeFontParameter();
		tbs = new TextButtonStyle();
		
		initNextPhaseButton();
		initPlayerStatsUI();
		initPhaseTwoUI();
		initPhaseThreeUI();
		
	}
	
	public void initNextPhaseButton()
	{
		Pixmap backgroundColor = new Pixmap(Gdx.graphics.getWidth() - mapImg.getWidth() - 20, 50, Pixmap.Format.RGB888);
		backgroundColor.setColor(Color.DARK_GRAY);
		backgroundColor.fill();
		tbs.up = new Image(new Texture(backgroundColor)).getDrawable();
		tbs.down = new Image(new Texture(backgroundColor)).getDrawable();
		backgroundColor.setColor(Color.LIGHT_GRAY);
		backgroundColor.fill();
		tbs.over = new Image(new Texture(backgroundColor)).getDrawable();
		parameter.size = 40;
		tbs.font = font.generateFont(parameter);
		
		nextPhaseButton = new TextButton("Skip", tbs);
		nextPhaseButton.setPosition(mapImg.getWidth() + 10,  mapImg.getHeight() - 300);
		nextPhaseButton.addListener(new ClickListener(){
			
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				onNextPhaseClick();
			}
		});
	}
	
	public void initPlayerStatsUI()
	{
		LabelStyle timels = new LabelStyle();
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
	}
	
	public void initPhaseTwoUI()
	{
		
		parameter.size = 20;
		tbs.font = font.generateFont(parameter);
		buyRoboticonButton = new TextButton("Buy Roboticon, " + new Integer(gameEngine.getMarket().getMarketRoboticonSellPrice()), tbs);
		buyRoboticonButton.setPosition(mapImg.getWidth() + 10,  mapImg.getHeight() - 360);
		
		buyRoboticonButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				try {
					gameEngine.getMarket().buyRoboticon(gameEngine.getCurrentPlayer(), 1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		parameter.size = 18;
		tbs.font = font.generateFont(parameter);
		specialiseRoboticonButton = new TextButton("Specialise Roboticon", tbs);
		specialiseRoboticonButton.setPosition(mapImg.getWidth() + 10,  mapImg.getHeight() - 420);
		
		specialiseRoboticonButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				roboticonSpecialiseMenu(tbs);
			}	
		});
		
		assignRoboticonButton = new TextButton("Assign Roboticon", tbs);
		assignRoboticonButton.setPosition(mapImg.getWidth() + 10,  mapImg.getHeight() - 480);
		
		assignRoboticonButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				Roboticon r = gameEngine.getCurrentPlayer().getUnassignedRoboticons().get(0);
				Plot p = gameEngine.getActivePlot();
				r.setPlot(p);
				p.addRoboticon(r);
			}	
		});
	}
	
	public void initPhaseThreeUI()
	{
		LabelStyle lbls = new LabelStyle();
		parameter.size = 18;
		lbls.font = font.generateFont(parameter);
		marketStockLabel = new Label("", lbls);
		marketStockLabel.setPosition(mapImg.getWidth() + 10, mapImg.getHeight() - 480);
		parameter.size = 30;
		tbs.font = font.generateFont(parameter);
		
		oreAddBuyButton = new TextButton("+", tbs);
		oreAddBuyButton.setWidth(30);
		oreAddBuyButton.setHeight(30);
		oreAddBuyButton.setPosition(Gdx.graphics.getWidth() - 40, mapImg.getHeight() - 505);
		oreAddBuyButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				oreBuyAmount += 5;
				if (oreBuyAmount > gameEngine.getMarket().getMarketOreStock())
				{
					oreBuyAmount = gameEngine.getMarket().getMarketOreStock();
				}
				if (oreBuyAmount * gameEngine.getMarket().getMarketOreBuyPrice() > gameEngine.getCurrentPlayer().getMoney())
				{
					oreBuyAmount = (int)((float)gameEngine.getCurrentPlayer().getMoney() / gameEngine.getMarket().getMarketOreBuyPrice());
				}
			}	
		});
		
		oreMinusBuyButton = new TextButton("-", tbs);
		oreMinusBuyButton.setWidth(30);
		oreMinusBuyButton.setHeight(30);
		oreMinusBuyButton.setPosition(mapImg.getWidth() + 10, mapImg.getHeight() - 505);
		oreMinusBuyButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				oreBuyAmount -= 5;
				if (oreBuyAmount < 0)
				{
					oreBuyAmount = 0;
				}
			}	
		});
		
		oreAddSellButton = new TextButton("+", tbs);
		oreAddSellButton.setWidth(30);
		oreAddSellButton.setHeight(30);
		oreAddSellButton.setPosition(Gdx.graphics.getWidth() - 40, mapImg.getHeight() - 560);
		oreAddSellButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				oreSellAmount += 5;
				if (oreSellAmount > gameEngine.getCurrentPlayer().getOre())
				{
					oreSellAmount = gameEngine.getCurrentPlayer().getOre();
				}
			}	
		});
		
		oreMinusSellButton = new TextButton("-", tbs);
		oreMinusSellButton.setWidth(30);
		oreMinusSellButton.setHeight(30);
		oreMinusSellButton.setPosition(mapImg.getWidth() + 10, mapImg.getHeight() - 560);
		oreMinusSellButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				oreSellAmount -= 5;
				if (oreSellAmount < 0)
				{
					oreSellAmount = 0;
				}
			}	
		});
		
		energyAddBuyButton = new TextButton("+", tbs);
		energyAddBuyButton.setWidth(30);
		energyAddBuyButton.setHeight(30);
		energyAddBuyButton.setPosition(Gdx.graphics.getWidth() - 40, mapImg.getHeight() - 630);
		energyAddBuyButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				energyBuyAmount += 5;
				if (energyBuyAmount > gameEngine.getMarket().getMarketEnergyStock())
				{
					energyBuyAmount = gameEngine.getMarket().getMarketEnergyStock();
				}
				if (energyBuyAmount * gameEngine.getMarket().getMarketEnergyBuyPrice() > gameEngine.getCurrentPlayer().getMoney())
				{
					energyBuyAmount = (int)((float)gameEngine.getCurrentPlayer().getMoney() / gameEngine.getMarket().getMarketEnergyBuyPrice());
				}
			}	
		});
		
		energyMinusBuyButton = new TextButton("-", tbs);
		energyMinusBuyButton.setWidth(30);
		energyMinusBuyButton.setHeight(30);
		energyMinusBuyButton.setPosition(mapImg.getWidth() + 10, mapImg.getHeight() - 630);
		energyMinusBuyButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				energyBuyAmount -= 5;
				if (energyBuyAmount < 0)
				{
					energyBuyAmount = 0;
				}
			}	
		});
		
		energyAddSellButton = new TextButton("+", tbs);
		energyAddSellButton.setWidth(30);
		energyAddSellButton.setHeight(30);
		energyAddSellButton.setPosition(Gdx.graphics.getWidth() - 40, mapImg.getHeight() - 685);
		energyAddSellButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				energySellAmount += 5;
				if (energySellAmount > gameEngine.getCurrentPlayer().getEnergy())
				{
					energySellAmount = gameEngine.getCurrentPlayer().getEnergy();
				}
			}	
		});
		
		energyMinusSellButton = new TextButton("-", tbs);
		energyMinusSellButton.setWidth(30);
		energyMinusSellButton.setHeight(30);
		energyMinusSellButton.setPosition(mapImg.getWidth() + 10, mapImg.getHeight() - 685);
		energyMinusSellButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				energySellAmount -= 5;
				if (energySellAmount < 0)
				{
					energySellAmount = 0;
				}
			}	
		});
		
		oreBuyButton = new TextButton("0", tbs);
		oreBuyButton.setWidth(100);
		oreBuyButton.setHeight(30);
		oreBuyButton.setPosition(mapImg.getWidth() + 65, mapImg.getHeight() - 505);
		oreBuyButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				try {
					gameEngine.getMarket().buyOre(gameEngine.getCurrentPlayer(), oreBuyAmount);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		oreSellButton = new TextButton("0", tbs);
		oreSellButton.setWidth(100);
		oreSellButton.setHeight(30);
		oreSellButton.setPosition(mapImg.getWidth() + 65, mapImg.getHeight() - 560);
		oreSellButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				try {
					gameEngine.getMarket().sellOre(gameEngine.getCurrentPlayer(), oreSellAmount);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		energyBuyButton = new TextButton("0", tbs);
		energyBuyButton.setWidth(100);
		energyBuyButton.setHeight(30);
		energyBuyButton.setPosition(mapImg.getWidth() + 65, mapImg.getHeight() - 630);
		energyBuyButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				try {
					gameEngine.getMarket().buyEnergy(gameEngine.getCurrentPlayer(), energyBuyAmount);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		energySellButton = new TextButton("0", tbs);
		energySellButton.setWidth(100);
		energySellButton.setHeight(30);
		energySellButton.setPosition(mapImg.getWidth() + 65, mapImg.getHeight() - 685);
		energySellButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				try {
					gameEngine.getMarket().sellEnergy(gameEngine.getCurrentPlayer(), energySellAmount);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	
	public void roboticonSpecialiseMenu(TextButtonStyle tbs)
	{
		parameter.size = 20;
		tbs.font = font.generateFont(parameter);
		oreSpecButton = new TextButton("Ore", tbs);
		energySpecButton = new TextButton("Energy", tbs);
		cancelSpecButton = new TextButton("Cancel", tbs);
		
		oreSpecButton.setWidth(100);
		energySpecButton.setWidth(100);
		cancelSpecButton.setWidth(100);
		
		oreSpecButton.setPosition(0, 0);
		energySpecButton.setPosition(110, 0);
		cancelSpecButton.setPosition(220, 0);
		
		oreSpecButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				gameEngine.getCurrentPlayer().getUnspecialisedRoboticons().get(0).setSpec(Resource.ORE);
				oreSpecButton.remove();
				energySpecButton.remove();
				cancelSpecButton.remove();
			}
		});
		
		energySpecButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				gameEngine.getCurrentPlayer().getUnspecialisedRoboticons().get(0).setSpec(Resource.ENERGY);
				oreSpecButton.remove();
				energySpecButton.remove();
				cancelSpecButton.remove();
			}
		});
		
		cancelSpecButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				oreSpecButton.remove();
				energySpecButton.remove();
				cancelSpecButton.remove();
			}
		});
		
		gameStage.addActor(oreSpecButton);
		gameStage.addActor(energySpecButton);
		gameStage.addActor(cancelSpecButton);
	}
	
	public void onNextPhaseClick()
	{
		if (gameEngine.getPhase() == 1)
		{
			if (gameEngine.getActivePlot() == null)
				gameEngine.setPhase(2);
			else
			{
				try {
					gameEngine.getCurrentPlayer().AcquirePlot(gameEngine.getActivePlot());
				} catch (Exception e) {
					e.printStackTrace();
				}
				gameEngine.setPhase(2);
			}
		}
		else
		{
			gameEngine.setPhaseTime(-1);
		}
	}
	
	public void initMap()
	{
		//Create a new empty map to get mouse click events on the map
		Actor mapActor = new Actor();
		//Set the bounds to where the map will exist on the screen
		mapActor.setBounds(0, 0, mapImg.getWidth(), mapImg.getHeight());
		//Add a new event listener to listen for click (called touch in this library) events on the map actor 
		mapActor.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float screenX, float screenY, int pointer, int button)
			{
				return onMapTouchDown(screenX, screenY, button);
			}
		});
		
		//Add the map actor to the stage
		gameStage.addActor(mapActor);
	}
	
	public boolean onMapTouchDown(float screenX, float screenY, int button)
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
		
		//Only 2 Players, will need tweaking to work for more than 2!
		int playerCount = 0;
		for (Iterator<Player> playerIterator = gameEngine.getHumanPlayers().iterator(); playerIterator.hasNext(); )
		{
			Player p = playerIterator.next();
			for (Iterator<Plot> plotIterator = p.getPlots().iterator(); plotIterator.hasNext(); )
			{
				Plot plot = plotIterator.next();
				int i = Arrays.asList(gameEngine.getPlots()).indexOf(plot);
				if (playerCount == 0)
				{
					//the setColor function is in the form of setColor(red, green, blue, alpha) all of them being floats
					shapeRenderer.setColor(1, 0, 0, 0.35f);
					//X position is the remainder of the index divided by the map width, then to get the pixel value we multiply the tile width
					//Y position is the index divided by the map width then decremented to 0 index, and then invert to align with the correct coordinate system
					//The rect function is in the form of rect(x, y, width, height) where x and y are the bottom left of the rectangle
					shapeRenderer.rect((i % gameEngine.getMapWidth()) * 174, (gameEngine.getMapHeight() - (i / gameEngine.getMapWidth()) - 1) * 174, 174, 174);
				}
				else
				{
					//the setColor function is in the form of setColor(red, green, blue, alpha) all of them being floats
					shapeRenderer.setColor(0, 0, 1, 0.35f);
					//X position is the remainder of the index divided by the map width, then to get the pixel value we multiply the tile width
					//Y position is the index divided by the map width then decremented to 0 index, and then invert to align with the correct coordinate system
					//The rect function is in the form of rect(x, y, width, height) where x and y are the bottom left of the rectangle
					shapeRenderer.rect((i % gameEngine.getMapWidth()) * 174, (gameEngine.getMapHeight() - (i / gameEngine.getMapWidth()) - 1) * 174, 174, 174);	
				}
			}
			playerCount++;
		}
		
		for (Iterator<AIPlayer> playerIterator = gameEngine.getAIPlayers().iterator(); playerIterator.hasNext(); )
		{
			AIPlayer p = playerIterator.next();
			for (Iterator<Plot> plotIterator = p.getPlots().iterator(); plotIterator.hasNext(); )
			{
				Plot plot = plotIterator.next();
				int i = Arrays.asList(gameEngine.getPlots()).indexOf(plot);
				if (playerCount == 0)
				{
					//the setColor function is in the form of setColor(red, green, blue, alpha) all of them being floats
					shapeRenderer.setColor(1, 0, 0, 0.35f);
					//X position is the remainder of the index divided by the map width, then to get the pixel value we multiply the tile width
					//Y position is the index divided by the map width then decremented to 0 index, and then invert to align with the correct coordinate system
					//The rect function is in the form of rect(x, y, width, height) where x and y are the bottom left of the rectangle
					shapeRenderer.rect((i % gameEngine.getMapWidth()) * 174, (gameEngine.getMapHeight() - (i / gameEngine.getMapWidth()) - 1) * 174, 174, 174);
				}
				else
				{
					//the setColor function is in the form of setColor(red, green, blue, alpha) all of them being floats
					shapeRenderer.setColor(0, 0, 1, 0.35f);
					//X position is the remainder of the index divided by the map width, then to get the pixel value we multiply the tile width
					//Y position is the index divided by the map width then decremented to 0 index, and then invert to align with the correct coordinate system
					//The rect function is in the form of rect(x, y, width, height) where x and y are the bottom left of the rectangle
					shapeRenderer.rect((i % gameEngine.getMapWidth()) * 174, (gameEngine.getMapHeight() - (i / gameEngine.getMapWidth()) - 1) * 174, 174, 174);	
				}
			}
			playerCount++;
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
		
		if (gameEngine.getActivePlot() != null)
		{
			if (gameEngine.getPhase() == 1)
			{
				if (gameEngine.getActivePlot().getPlayer() != null)
				{
					nextPhaseButton.setTouchable(Touchable.disabled);
					nextPhaseButton.setText("Owned!");
				}
				else
				{
					nextPhaseButton.setTouchable(Touchable.enabled);
					nextPhaseButton.setText("Buy Plot");
				}
			}
		}
		
		if (gameEngine.getPhase() == 1)
		{
			if (gameEngine.getActivePlot() == null)
			{
				nextPhaseButton.setText("Skip");
			}
			else if (gameEngine.getActivePlot().getPlayer() != null)
			{
				nextPhaseButton.setTouchable(Touchable.disabled);
				nextPhaseButton.setText("Owned!");
			}
			else
			{
				nextPhaseButton.setTouchable(Touchable.enabled);
				nextPhaseButton.setText("Buy Plot");
			}
		}
		else
		{
			nextPhaseButton.setText("Skip");
		}
		
		if (gameEngine.getPhase() == 2)
		{
			buyRoboticonButton.setVisible(true);
			specialiseRoboticonButton.setVisible(true);
			assignRoboticonButton.setVisible(true);
			System.out.println(gameEngine.getMarket().getMarketRoboticonStock());
			if (gameEngine.getMarket().getMarketRoboticonStock() < 1 || gameEngine.getCurrentPlayer().getMoney() < gameEngine.getMarket().getMarketRoboticonSellPrice())
				buyRoboticonButton.setTouchable(Touchable.disabled);
			else
				buyRoboticonButton.setTouchable(Touchable.enabled);
			
			if (gameEngine.getCurrentPlayer().getUnspecialisedRoboticons().size() == 0)
				specialiseRoboticonButton.setTouchable(Touchable.disabled);
			else
				specialiseRoboticonButton.setTouchable(Touchable.enabled);
			
			if (gameEngine.getActivePlot() == null)
				assignRoboticonButton.setTouchable(Touchable.disabled);
			else if (gameEngine.getActivePlot().getPlayer() != gameEngine.getCurrentPlayer())
				assignRoboticonButton.setTouchable(Touchable.disabled);
			else if (gameEngine.getCurrentPlayer().getUnassignedRoboticons().size() == 0)
				assignRoboticonButton.setTouchable(Touchable.disabled);
			else
				assignRoboticonButton.setTouchable(Touchable.enabled);
		}
		else
		{
			buyRoboticonButton.setVisible(false);
			specialiseRoboticonButton.setVisible(false);
			assignRoboticonButton.setVisible(false);
			if (gameStage.getActors().contains(oreSpecButton, true))
			{
				oreSpecButton.remove();
				energySpecButton.remove();
				cancelSpecButton.remove();
			}
		}
		
		if (gameEngine.getPhase() == 3)
		{
			marketStockLabel.setVisible(true);
			oreBuyButton.setVisible(true);
			oreSellButton.setVisible(true);
			energyBuyButton.setVisible(true);
			energySellButton.setVisible(true);
			oreAddBuyButton.setVisible(true);
			oreMinusBuyButton.setVisible(true);
			oreAddSellButton.setVisible(true);
			oreMinusSellButton.setVisible(true);
			energyAddBuyButton.setVisible(true);
			energyMinusBuyButton.setVisible(true);
			energyAddSellButton.setVisible(true);
			energyMinusSellButton.setVisible(true);
			marketStockLabel.setText("Market:\n"
					+ "Ore Stock: " + new Integer(gameEngine.getMarket().getMarketOreStock()).toString() + "\n"
					+ "Energy Stock: " + new Integer(gameEngine.getMarket().getMarketEnergyStock()).toString() + "\n"
					+ "Ore Buy Price: " + new Float(gameEngine.getMarket().getMarketOreBuyPrice()).toString() + "\n"
					+ "Ore Sell Price: " + new Float(gameEngine.getMarket().getMarketOreSellPrice()).toString() + "\n"
					+ "Energy Buy Price: " + new Float(gameEngine.getMarket().getMarketEnergyBuyPrice()).toString() + "\n"
					+ "Energy Sell Price: " + new Float(gameEngine.getMarket().getMarketEnergySellPrice()).toString() + "\n"
					+ "ORE: \n"
					+ "Buy: \n"
					+ "\n"
					+ "\n"
					+ "Sell: \n"
					+ "\n"
					+ "\n"
					+ "ENERGY: \n"
					+ "Buy: \n"
					+ "\n"
					+ "\n"
					+ "Sell: ");
			oreBuyButton.setText(new Integer(oreBuyAmount).toString());
			oreSellButton.setText(new Integer(oreSellAmount).toString());
			energyBuyButton.setText(new Integer(energyBuyAmount).toString());
			energySellButton.setText(new Integer(energySellAmount).toString());
		}
		else
		{
			marketStockLabel.setVisible(false);
			oreBuyButton.setVisible(false);
			oreSellButton.setVisible(false);
			energyBuyButton.setVisible(false);
			energySellButton.setVisible(false);
			oreAddBuyButton.setVisible(false);
			oreMinusBuyButton.setVisible(false);
			oreAddSellButton.setVisible(false);
			oreMinusSellButton.setVisible(false);
			energyAddBuyButton.setVisible(false);
			energyMinusBuyButton.setVisible(false);
			energyAddSellButton.setVisible(false);
			energyMinusSellButton.setVisible(false);
			oreBuyAmount = 0;
			oreSellAmount = 0;
			energyBuyAmount = 0;
			energySellAmount = 0;
		}
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

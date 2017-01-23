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

/**
 * The Class that runs the game engine and controls the UI, This is how the user interacts with the game.
 * 
 * @author Steven Tomlinson
 *
 */
public class Game extends ApplicationAdapter
{
	// The required renderers to properlly display the game
	private SpriteBatch batch;
	private ShapeRenderer shapeRenderer;
	
	// The required assets
	private Texture mapImg;
	private Texture roboticonImg;

	// All of the required interface controls
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

	// Variables to store the buy and sell amounts for the player
	private int oreBuyAmount = 0;
	private int oreSellAmount = 0;
	private int energyBuyAmount = 0;
	private int energySellAmount = 0;

	//Button Style
	private TextButtonStyle tbs;

	// Game Engine
	private GameEngine gameEngine;

	// Objects required to render fonts
	private FreeTypeFontGenerator font;
	private FreeTypeFontParameter parameter;

	@Override
	/**
	 * This is the functions that is called by the main function to set up all the relevant parts of the game
	 * 
	 * NOTE: At the moment the game is currently 2 human multiplayer
	 */
	public void create ()
	{

		// Initialises the aiPlayers List, but at the moment adds no AI players
		ArrayList<AIPlayer> aiPlayers = new ArrayList<AIPlayer>();

		//  Initialises the human players list and adds 2 human players so 2 people can play the game against each over
		ArrayList<Player> humanPlayers = new ArrayList<Player>();
		humanPlayers.add(new Player("Player 1", 50, 50, 50));
		humanPlayers.add(new Player("Player 2", 50, 50, 50));

		//Create the game and the then start the game
		gameEngine = new GameEngine(humanPlayers, aiPlayers, 6, 5);
		gameEngine.start();

		//Create the require renderers
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();

		// Inits the required assets and setups the UI
		initAssests();
		initGameUI();

	}

	/**
	 * Initialises all of the assets required for the game to qork
	 */
	public void initAssests()
	{
		//Setup the required assets
		mapImg = new Texture(Gdx.files.internal("YorkUniMap.png"));
		roboticonImg = new Texture(Gdx.files.internal("Graphics/Roboticon.png"));
		font = new FreeTypeFontGenerator(Gdx.files.internal("earthorbiter.ttf"));
	}

	/**
	 * Initialises the UI for the game
	 */
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

		// Initialises the map into a known state
		initMap();
		// Initialises the UI into a known state
		initGameUIElements();

		// Adds all of the required UI elements to the stage
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

	/**
	 * Initialises The different phases of the UI
	 */
	public void initGameUIElements()
	{
		// Setups the font and button style
		parameter = new FreeTypeFontParameter();
		tbs = new TextButtonStyle();

		// Initialises each part of the UI into a known state
		initNextPhaseButton();
		initPlayerStatsUI();
		initPhaseTwoUI();
		initPhaseThreeUI();

	}

	/**
	 * Initialises the next phase button so that is appears in the right place and performs the correct action
	 */
	public void initNextPhaseButton()
	{
		// Setups the button style so that it is correct for every button
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

		// Initialises the nextPhaseButton to a known state and setups its event listener
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

	/**
	 * Initialises the player stats so that they appear in the correct place and at the correct size
	 */
	public void initPlayerStatsUI()
	{
		// Setup label styles for each of the player stats like money ore etc. so that they are displayed correctly
		// Then sets the position of each label to the correct place on screen
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

	/**
	 * Initialises the phase 2 UI so that the different elements appear in the correct place and the buttons perform the correct function.
	 */
	public void initPhaseTwoUI()
	{
		// Setups the buyRoboticonButton up correctly and setups the event listener so that when the button is clicked it buys a roboticon for the current player
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

		// Similar to the buyRoboticonButton except for that the event Listener does the action of displaying a menu to specialise the roboticon
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

		// Setups the assignRoboticonButton which assigns the first roboticon in the list of unassigned roboticons for the current player to the currently active plot, i.e the one that is highlighted white in the UI
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

	/**
	 * Initialises the phase 3 UI so that the different elements appear in the correct place and the buttons perform the correct function.
	 */
	public void initPhaseThreeUI()
	{
		// Setups the label to display all of the market stock values and prices etc.
		LabelStyle lbls = new LabelStyle();
		parameter.size = 18;
		lbls.font = font.generateFont(parameter);
		marketStockLabel = new Label("", lbls);
		marketStockLabel.setPosition(mapImg.getWidth() + 10, mapImg.getHeight() - 480);
		parameter.size = 30;
		tbs.font = font.generateFont(parameter);

		// Setups all of the buttons for the player to be able to buy and sell to the UI
		// So for each resource they player is able to sell to the market and buy from the market.
		// And for each of those they have three buttons
		// A button to add 5 to the amount they want to buy and sell and a button to subtract 5 to the amount they want to buy or sell
		// And also a button which displays the current amount they want to buy or sell and which when clicked will do buy or sell the correct resource labelled
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
				if (oreBuyAmount * gameEngine.getMarket().getMarketOreSellPrice() > gameEngine.getCurrentPlayer().getMoney())
				{
					oreBuyAmount = (int)((float)gameEngine.getCurrentPlayer().getMoney() / gameEngine.getMarket().getMarketOreSellPrice());
				}
				if (oreBuyAmount < 0)
				{
					oreBuyAmount = 0;
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
				if (oreSellAmount < 0)
				{
					oreSellAmount = 0;
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
				if (energyBuyAmount * gameEngine.getMarket().getMarketEnergySellPrice() > gameEngine.getCurrentPlayer().getMoney())
				{
					energyBuyAmount = (int)((float)gameEngine.getCurrentPlayer().getMoney() / gameEngine.getMarket().getMarketEnergySellPrice());
				}
				if (oreSellAmount < 0)
				{
					oreSellAmount = 0;
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
				if (oreSellAmount < 0)
				{
					oreSellAmount = 0;
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
				oreBuyAmount = 0;
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
				oreSellAmount = 0;
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
				energyBuyAmount = 0;
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
				energySellAmount = 0;
			}
		});
	}

	/**
	 * The code to setup the specialise menu so that it is displayed correctly, this is called when the player clicks on the specialise roboticon button
	 * @param tbs the Text Buttons Style, so that the buttons appear to look like the other buttons so that they fit in with the rest of the UI
	 */
	public void roboticonSpecialiseMenu(TextButtonStyle tbs)
	{
		// Setups the buttons to specialise the roboticons, which consists of 3 buttons, one to specialise to ore, one to energy and one to cancel and quit out of the menu
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

	/**
	 * The functions that is performed when the player clicks on the nextPhaseButton
	 */
	public void onNextPhaseClick()
	{
		// If the game is on phase 1 (buy plot phase)
		if (gameEngine.getPhase() == 1)
		{
			// If no plot is selected then just go to the next phase
			if (gameEngine.getActivePlot() == null)
				gameEngine.setPhase(2);
			else
			{
				// Else attempt to buy the plot (Should always happen since they can only click the button if the current active plot is null (i.e no plot selected) or they are on an unowned plot)
				try {
					gameEngine.getCurrentPlayer().AcquirePlot(gameEngine.getActivePlot());
				} catch (Exception e) {
					e.printStackTrace();
				}
				// Then advance the phase since only one plot can be bought per turn
				gameEngine.setPhase(2);
			}
		}
		else
		{
			// Else set the timer to -1 which will automatically advance the phase to the next phase.
			gameEngine.setPhaseTime(-1);
		}
		// Reset all the buy and sell amounts
		oreBuyAmount = 0;
		oreSellAmount = 0;
		energyBuyAmount = 0;
		energySellAmount = 0;
	}

	/**
	 * this initialises the map so that it appears correctly and is able to take user mouse input
	 */
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

	
	/**
	 * the function that is performed when the player clicks on the map
	 * @param screenX The x location in pixels of where they clicked
	 * @param screenY The y location in pixels of where they clicked
	 * @param button What button they click the mouse with, i.e left mouse button
	 * @return returns whether the event has been handled by this function
	 */
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

	/**
	 * draws the map to the screen
	 */
	public void renderMap()
	{
		//Draw the map to the screen (Happens first so that the map is on the bottom so all the UI appears above it)
		batch.begin();
		batch.draw(mapImg, 0, 0);
		batch.end();
	}

	/**
	 * draws the tile highlights to the screen so the players knows which tiles are owned buy which player
	 */
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
			// get the next player
			Player p = playerIterator.next();
			// for each plot that the player owns highlight the plot the correct colour
			for (Iterator<Plot> plotIterator = p.getPlots().iterator(); plotIterator.hasNext(); )
			{
				Plot plot = plotIterator.next();
				int i = Arrays.asList(gameEngine.getPlots()).indexOf(plot);
				// this if statement only accounts for 2 players playing the game
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
			// When done increment the playerCounter so it knows what colour to highlight the plots for the next player
			playerCount++;
		}

		// Similar to the previous loop but for ai Players.
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

	/**
	 * renders the highlight for the current active tile so the player knows which tile they have selected
	 */
	public void renderActiveTileHighlight()
	{
		//Allows transparency of of the UI rectangles (Allows the merging of the fragment (Colour) shaders, simulating transparency)
		Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
		//Sets transparency on the alpha channel
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

		shapeRenderer.begin(ShapeType.Filled);

		// get the index of the current plot being selected so that the correct plot can be highlighted
		int activePlotIndex = java.util.Arrays.asList(gameEngine.getPlots()).indexOf(gameEngine.getActivePlot());

		if (activePlotIndex >= 0 && activePlotIndex < gameEngine.getPlots().length)
		{
			//the setColor function is in the form of setColor(red, green, blue, alpha) all of them being floats
			shapeRenderer.setColor(1, 1, 1, 0.5f);
			//X position is the remainder of the index divided by the map width, then to get the pixel value we multiply the tile width
			//Y position is the index divided by the map width then decremented to 0 index, and then invert to align with the correct coordinate system
			//The rect function is in the form of rect(x, y, width, height) where x and y are the bottom left of the rectangle
			shapeRenderer.rect((activePlotIndex % gameEngine.getMapWidth()) * 174, (gameEngine.getMapHeight() - (activePlotIndex / gameEngine.getMapWidth()) - 1) * 174, 173, 173);
		}

		shapeRenderer.end();

		//Stop using transparency for the rest of the graphics
		Gdx.gl.glDisable(GL20.GL_BLEND);
	}

	/**
	 * renders all of the roboticons to the screen
	 */
	public void renderRoboticons()
	{
		batch.begin();
		// For each plot if it has a roboticon then print display a roboticon at the current plot
		for (int i = 0; i < gameEngine.getPlots().length; i++)
		{
			if (gameEngine.getPlots()[i].hasRoboticon())
			{
				//X position is the remainder of the index divided by the map width, then to get the pixel value we multiply the tile width
				//Y position is the index divided by the map width then decremented to 0 index, and then invert to align with the correct coordinate system
				batch.draw(roboticonImg, (i % gameEngine.getMapWidth()) * 175, (gameEngine.getMapHeight() - (i / gameEngine.getMapWidth()) - 1) * 175);
			}

		}
		batch.end();
	}

	/**
	 * renders the UI to the screen correctly
	 */
	public void renderUI()
	{
		// Display the timer correctly, so since the only time the game isn't time bound is select plot then if the time is < 0 set the time to n/a display the current time left in the current phase
		Integer time = new Integer((int)gameEngine.getPhaseTime());
		if (time < 0)
			timeLabel.setText("Time: n/a");
		else
			timeLabel.setText("Time: " + time.toString());

		// Display the name of the current player along with the resource values
		nameLabel.setText("Name: " + gameEngine.getCurrentPlayer().getName());

		oreLabel.setText("Ore: " + gameEngine.getCurrentPlayer().getOre());
		energyLabel.setText("Energy: " + gameEngine.getCurrentPlayer().getEnergy());
		moneyLabel.setText("Money: " + gameEngine.getCurrentPlayer().getMoney());

		if (gameEngine.getPhase() == 1)
		{
			// if there is no plot selected then just let the text of the button let them skip the current phase
			if (gameEngine.getActivePlot() == null)
			{
				nextPhaseButton.setText("Skip");
			}
			// if the current plot selected is already owned then make the button not able to be clicked and tell the player that it is already owned
			// otherwise make the button clickable and have it say that the current plot is buyable.
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
			// if not on phase one then the button just acts as a skip phase button
			nextPhaseButton.setText("Skip");
		}

		if (gameEngine.getPhase() == 2)
		{
			// set all the required UI elements to be visible
			buyRoboticonButton.setVisible(true);
			specialiseRoboticonButton.setVisible(true);
			assignRoboticonButton.setVisible(true);
			
			// if the player is able to buy a roboticon then make it possible for them to do so, otherwise make it impossible
			if (gameEngine.getMarket().getMarketRoboticonStock() < 1 || gameEngine.getCurrentPlayer().getMoney() < gameEngine.getMarket().getMarketRoboticonSellPrice())
				buyRoboticonButton.setTouchable(Touchable.disabled);
			else
				buyRoboticonButton.setTouchable(Touchable.enabled);

			// Similar to the previous if statement just for the specialise roboticon
			if (gameEngine.getCurrentPlayer().getUnspecialisedRoboticons().size() == 0)
				specialiseRoboticonButton.setTouchable(Touchable.disabled);
			else
				specialiseRoboticonButton.setTouchable(Touchable.enabled);

			// if there is not plot selected then do not allow them to assign a roboticon
			if (gameEngine.getActivePlot() == null)
				assignRoboticonButton.setTouchable(Touchable.disabled);
			// if the current plot selected is not owned buy the player then do not allow them to assign a roboticon
			else if (gameEngine.getActivePlot().getPlayer() != gameEngine.getCurrentPlayer())
				assignRoboticonButton.setTouchable(Touchable.disabled);
			// if they do not have any unassigned roboticons then do not allow them to assign a roboticon
			else if (gameEngine.getCurrentPlayer().getUnassignedRoboticons().size() == 0)
				assignRoboticonButton.setTouchable(Touchable.disabled);
			// otherwise allow them to assign a roboticon
			else
				assignRoboticonButton.setTouchable(Touchable.enabled);
		}
		else
		{
			// if not phase 2 then remove all of the UI elements left over from phase 2
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
			// Show all of the relevant UI Elements
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
			
			// set the marketStockLavel text to display all of the require market information and into the correct format so that the informations is aligned in the correct place
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
			
			// Set the text on the buttons to the correct amount of the resource
			oreBuyButton.setText(new Integer(oreBuyAmount).toString());
			oreSellButton.setText(new Integer(oreSellAmount).toString());
			energyBuyButton.setText(new Integer(energyBuyAmount).toString());
			energySellButton.setText(new Integer(energySellAmount).toString());
		}
		else
		{
			//Remove all the UI elements
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
			// Reset the amounts
			oreBuyAmount = 0;
			oreSellAmount = 0;
			energyBuyAmount = 0;
			energySellAmount = 0;
		}
		
		// Draw the UI to the screen
		gameStage.draw();
	}

	@Override
	public void render ()
	{
		//Clear to the back ground to black
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// if the game is running then update the game
		if (gameEngine.isRunning())
			gameEngine.update(Gdx.graphics.getDeltaTime());

		// All the functions required to display the game correctly
		// NOTE: for the desired look of the game, at the functions current state they must go in this particular order.
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
		// Remove the graphics elements
		shapeRenderer.dispose();
		batch.dispose();
		mapImg.dispose();
		gameStage.dispose();
	}

}

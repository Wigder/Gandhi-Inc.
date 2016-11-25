package me.gandhiinc.blindeye;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends ApplicationAdapter implements InputProcessor
{
	SpriteBatch batch;
	Texture img;
	int width, height;
	float red, green, blue;
	boolean up;
	float speed = 0.01f;
	
	@Override
	public void create () 
	{
		batch = new SpriteBatch();
		img = new Texture("gandhi.png");
		
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		
		red = 0;
		green = 0;
		blue = 0;
		up = true;
		
		Gdx.input.setInputProcessor(this); //This handles all the processing input (The processor of the input is "this")
	}

	@Override
	public void render () 
	{
		if (up == true)
		{
			if (red >= 1.0f)
			{
				if (green >= 1.0f)
				{
					if (blue >= 1.0f)
					{
						up = false;
					}
					else
					{
						blue += speed;
					}
				}
				else
				{
					green += speed;
				}
			}
			else
			{
				red += speed;
			}
		}
		else
		{
			if (red <= 0)
			{
				if (green <= 0)
				{
					if (blue <= 0)
					{
						up = true;
					}
					else
					{
						blue -= speed;
					}
				}
				else
				{
					green -= speed;
				}
			}
			else
			{
				red -= speed;
			}
		}
		Gdx.gl.glClearColor(red, green, blue, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		for (int i = 0; i < (red + blue + green) * 100; i++)
		{
			batch.draw(img, red *10 * i, green * 10 * i);	
		}
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

package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import entidades.Jugador;
import ventanas.AbstractScreen;
import ventanas.MenuScreen;

public class AirDestructionGame extends Game{
	SpriteBatch batch;
	public AbstractScreen GameScreen;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		
		GameScreen = new ventanas.GameScreen(this);
		setScreen(GameScreen);
		/*MenuScreen MenuScreen = new ventanas.MenuScreen(this);
		setScreen(MenuScreen);*/
	}

	/*@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.draw(img2, jugador.getX(), jugador.getY(), jugador.getAltura(), jugador.getAnchura());
		batch.end();
	}*/
	
	@Override
	public void dispose () {
		batch.dispose();
	}

}

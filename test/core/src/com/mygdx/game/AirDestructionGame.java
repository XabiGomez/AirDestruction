package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import entidades.Jugador;
import ventanas.AbstractScreen;

public class AirDestructionGame extends Game{
	SpriteBatch batch;
	Texture img, img2;
	Jugador jugador = new Jugador(10, 10, 300, 300, 3, 3);;
	
	public AbstractScreen GameScreen;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		img2 = new Texture("snorlax.png");
		
		GameScreen = new ventanas.GameScreen(this);
		setScreen(GameScreen);
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
		img.dispose();
	}

}

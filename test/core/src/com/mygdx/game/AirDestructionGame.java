//main

package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import entidades.Jugador;

public class AirDestructionGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img, img2;
	Jugador jugador = new Jugador(10, 10, 300, 300, 3, 3);;
	
	@Override
	public void create () {
		//Jugador jugador = new Jugador(10, 10, 10, 10, 3, 3);
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		img2 = new Texture("snorlax.png");
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.draw(img2, jugador.getX(), jugador.getY(), jugador.getAltura(), jugador.getAnchura());
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
	
	//Comentario de prueba
}

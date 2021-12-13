package com.mygdx.game;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import com.badlogic.gdx.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import entidades.BaseDeDatos;
import entidades.Jugador;
import ventanas.AbstractScreen;
import ventanas.MenuScreen;

public class AirDestructionGame extends Game{
	SpriteBatch batch;
	public AbstractScreen GameScreen;
	
	//
	private Viewport viewport;
	public static final int DEFAULT_WIDTH = 600;
    public static final int DEFAULT_HEIGHT = 1000;
    private Skin skin;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		
		viewport = new FitViewport(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		skin = new Skin(Gdx.files.internal("widgets/uiskin.json"));
		
		/*GameScreen = new ventanas.GameScreen(this);
		setScreen(GameScreen);*/
		MenuScreen MenuScreen = new ventanas.MenuScreen(this);
		setScreen(MenuScreen);
		if (new File("ranking.db").exists()) {
			BaseDeDatos.conexionBd("ranking.db", false);
		}else {
			BaseDeDatos.conexionBd("ranking.db", true);
		}
		addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				BaseDeDatos.cierreBD();
			}
		});
	
	}

	private static void addWindowListener(WindowAdapter windowAdapter) {
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

	//
	public Viewport getViewport() {
		// TODO Auto-generated method stub
		return viewport;
	}

	public Skin getDefaultSkin() {
		// TODO Auto-generated method stub
		return skin;
	}

}

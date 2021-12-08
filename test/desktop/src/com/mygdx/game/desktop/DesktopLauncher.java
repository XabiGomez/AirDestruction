package com.mygdx.game.desktop;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.AirDestructionGame;

import entidades.BaseDeDatos;



public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration configuracion = new LwjglApplicationConfiguration();
		//Tamaño de la ventana
		configuracion.height = 1000;
		configuracion.width = 600;
		configuracion.title = "Air Destruction";
		new LwjglApplication(new AirDestructionGame(), configuracion);
		//new LwjglApplication(new MenuScreen(), configuracion);
		if (new File("ranking.bd").exists()) {
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
}
		


package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.AirDestructionGame;

import ventanas.MenuScreen;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration configuracion = new LwjglApplicationConfiguration();
		//Tamaño de la ventana
		configuracion.height = 1000;
		configuracion.width = 600;
		configuracion.title = "Air Destruction";
		new LwjglApplication(new AirDestructionGame(), configuracion);
		//new LwjglApplication(new MenuScreen(), configuracion);
	}
}

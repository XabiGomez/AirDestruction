package entidades;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import ventanas.GameScreen;

public class Enemigo3 extends Enemigo{
	private float posxJugador, posyJugador;
	/**
	 * Enemigo que se mueve hacia el personaje
	 * @param x				float Marca su posicion en x
	 * @param y				float Marca su posicion en y
	 * @param altura		float Marca la altura del sprite
	 * @param anchura		float Marca la anchura del sprite
	 * @param vida			int Dice la vida del enemigo
	 * @param velocidadX	float Marca la velocidad en x del enemigo
	 * @param velocidadY	float Marca la velocidad en y del enemigo
	 * @param textura		Textura Marca la velocidad en y del enemigo
	 * @param dano			int dano que hace el enemigo
	 * @param tipo			int tipo del enemigo
	 * 
	 */
	public Enemigo3(float x, float y , float altura, float anchura, int vida, int velocidadX, int velocidadY, Texture textura, int dano,int tipo) {
		super(x, y, altura, anchura, vida, velocidadX, velocidadY, textura, dano, tipo);
		posxJugador = GameScreen.getPosXJugador();
		posyJugador = GameScreen.getPosYJugador();
		control();
	}
	@Override
	public void mover() {
		
		if(GameScreen.fueradepantalla(this)) {
			setY(Gdx.graphics.getHeight());
			posxJugador = GameScreen.getPosXJugador();
			posyJugador = GameScreen.getPosYJugador();
			control();
		}
		setX(getX()-getVelocidadX()*3);
		setY(getY()-getVelocidadY()*3);
	}
	@Override
	public boolean intentadisparar(ArrayList<Entidad> disparoenemigo) {
		return false;
	}
	
	public void control() {
		float difx = getX() - posxJugador;
		float dify = getY() - posyJugador;
		float unitario = (float) Math.sqrt(difx * difx + dify * dify);
		setVelocidadX(difx/unitario);
		setVelocidadY(dify/unitario);
	}
}

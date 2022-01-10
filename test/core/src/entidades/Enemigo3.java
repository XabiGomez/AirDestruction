package entidades;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import ventanas.GameScreen;

public class Enemigo3 extends Enemigo{
	private float posxJugador, posyJugador;
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

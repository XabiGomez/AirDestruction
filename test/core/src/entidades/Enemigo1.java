package entidades;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import ventanas.GameScreen;

public class Enemigo1 extends Enemigo{
	private float enfriamientodisparo, disparot;
	public Enemigo1(float x, float y , float altura, float anchura, int vida, int velocidadX, int velocidadY, Texture textura, int dano,int tipo) {
		super(x, y, altura, anchura, vida, velocidadX, velocidadY, textura, dano, tipo);
		enfriamientodisparo = 2.0f;
		disparot = 0.0f;
		
	}
	float vel  = getVelocidadX();
	@Override
	public void mover() {
		
		if(getX()<0) {
			vel = getVelocidadX();
		}else if(getX()+getAnchura()>Gdx.graphics.getWidth()) {
			vel = -getVelocidadX();
		}
		if(GameScreen.fueradepantalla(this)) {
			setY(Gdx.graphics.getHeight());
		}
		setX(getX()+vel);
		setY(getY()-getVelocidadY());
	}
	@Override
	public boolean intentadisparar(ArrayList<Entidad> disparoenemigo) {
		disparot += Gdx.graphics.getDeltaTime();
		if(disparot>=enfriamientodisparo) {
			disparot=0.0f;
			int probabilidaddisp = 50;
			if(Math.random()*101 + 1<=probabilidaddisp) {
				float x = (float) getX()+(getAnchura()/2);
				float y = getY();
				float altura= 25;
				float anchura= 10;
				int velyd = -3;
				Texture texturadisparo1 = new Texture("snorlax.png");
				disparoenemigo.add(new Disparo(x,y,altura,anchura,0,0,velyd,texturadisparo1));
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	
}

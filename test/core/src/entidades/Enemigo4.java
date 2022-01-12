package entidades;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import ventanas.GameScreen;

public class Enemigo4 extends Enemigo{
	private float enfriamientodisparo, disparot;
	public Enemigo4(float x, float y , float altura, float anchura, int vida, int velocidadX, int velocidadY, Texture textura, int dano,int tipo) {
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
	}
	@Override
	public boolean intentadisparar(ArrayList<Entidad> disparoenemigo) {
		disparot += Gdx.graphics.getDeltaTime();
		if(disparot>=enfriamientodisparo) {
			disparot=0.0f;
			int probabilidaddisp = 50;
			double prob = Math.random()*101 + 1;
			if(prob<=probabilidaddisp) {
				float x = (float) getX()+(getAnchura()/2);
				float y = getY();
				float altura= 50;
				float anchura= 50;
				int velyd = -3;
				Texture texturadisparo1 = new Texture("snorlax.png");
				disparoenemigo.add(new Disparo(x,y,altura,anchura,0,0,velyd,texturadisparo1));
				return true;
			}else{
				float x = (float) getX()+(getAnchura()/2);
				float y = getY();
				float altura = 50;
				float anchura = 50;
				Texture texturadisparo1 = new Texture("snorlax.png");
				float difx = GameScreen.getPosXJugador() - (getX()+(getAnchura()/2));
				float dify = GameScreen.getPosYJugador() - getY();
				float unitario = (float) Math.sqrt(difx * difx + dify * dify);
				float velxd = 3*difx/unitario;
				float velyd = 3*dify/unitario;
				disparoenemigo.add(new Disparo(x,y,altura,anchura,0,velxd,velyd,texturadisparo1));				
				return true;
			}
		}else {
			return false;
		}
	}
	
}
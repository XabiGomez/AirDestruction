package entidades;

import com.badlogic.gdx.graphics.Texture;

public class Enemigo1 extends Enemigo{
	public Enemigo1(float x, float y , float altura, float anchura, int vida, int velocidadX, int velocidadY, Texture textura, int dano) {
		super(x, y, altura, anchura, vida, velocidadX, velocidadY, textura, dano);
	}
	int vel  = getVelocidadX();
	@Override
	public void mover() {
		
		if(getX()<0) {
			vel = getVelocidadX();
		}else if(getX()+getAnchura()>600) {
			vel = -getVelocidadX();
		}
		setX(getX()+vel);
	}
	
}

package entidades;

import com.badlogic.gdx.graphics.Texture;

public class Enemigo extends Entidad{
	int dano;
	public Enemigo(float x, float y , float altura, float anchura, int vida, int velocidadX, int velocidadY, Texture textura, int dano) {
		super(x, y, altura, anchura, vida, velocidadX, velocidadY, textura);
		this.dano = dano;
		
	}

	public int getDano() {
		return dano;
	}
	public void setDano(int dano) {
		this.dano = dano;
	}
	
	
	
	
}


package entidades;

import com.badlogic.gdx.graphics.Texture;

public class Enemigo extends Entidad{

	int dano;
	public Enemigo(float x, float y , int altura, int anchura, int vida, int velocidad, int dano, Texture textura) {
		super(y, x, altura, anchura, vida, velocidad, textura);
		this.dano = dano;
		
	}
	
	
	
	
}

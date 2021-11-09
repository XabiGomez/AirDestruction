package entidades;

import com.badlogic.gdx.graphics.Texture;

public class Disparo extends Entidad{
	
	public Disparo(float x, float y , float altura, int velocidadX, int velocidadY, Texture textura) {
		super(x, y, altura, velocidadX, velocidadY, velocidadY, velocidadY, textura);
	}
	
}

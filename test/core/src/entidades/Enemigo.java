package entidades;

import java.util.ArrayList;

public class Enemigo extends Entidad{

	int dano;
	public Enemigo(float x, float y , int altura, int anchura, int vida, int velocidad, int dano) {
		super(y, x, altura, anchura, vida, velocidad);
		this.dano = dano;
		
	}

	
}

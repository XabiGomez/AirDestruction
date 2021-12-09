package entidades;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;

public class Enemigo extends Entidad{
	protected int dano;
	protected int tipo;
	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public Enemigo(float x, float y , float altura, float anchura, int vida, int velocidadX, int velocidadY, Texture textura, int dano,int tipo) {
		super(x, y, altura, anchura, vida, velocidadX, velocidadY, textura);
		this.dano = dano;
		this.tipo = tipo;
	}

	public int getDano() {
		return dano;
	}
	public void setDano(int dano) {
		this.dano = dano;
	}

	public boolean intentadisparar(ArrayList<Entidad> disparoenemigo) {
		return false;
		
	}
	
	
	
}



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
	/**
	 * Clase padre de todos los enemigos
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
	/**
	 * El enemigo intenta disparar
	 * 
	 * @param disparoenemigo
	 * 
	 * @return True si puede disparar y False si no
	 */
	public boolean intentadisparar(ArrayList<Entidad> disparoenemigo) {
		return false;
		
	}
	
	
	
}



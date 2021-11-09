package entidades;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Disposable;

public class Entidad implements Disposable{

	private int vida, velocidad;
	protected Texture textura;
	protected Sprite sprite;
	
	public Entidad(float x, float y , float altura, float anchura, int vida, int velocidad,Texture textura) {
		this.vida = vida;
		this.velocidad = velocidad;
		this.textura=textura;
		this.sprite= new Sprite(textura);
		sprite.setX(x);
		sprite.setY(y);
		sprite.setSize(anchura, altura);
		sprite.setScale(anchura, altura);
		
	}

	public float getX() {
		return sprite.getX();
	}

	public void setX(float x) {
		sprite.setX(x);
	}

	public float getY() {
		return sprite.getY();
	}

	public void setY(float y) {
		sprite.setY(y);
	}

	public float getAltura() {
		return sprite.getHeight();
	}

	public void setAltura(float altura) {
		sprite.setScale(this.getAnchura(), altura);
	}

	public float getAnchura() {
		return sprite.getWidth();
	}

	public void setAnchura(float anchura) {
		sprite.setSize(anchura, this.getAltura());
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public int getVelocidad() {
		return velocidad;
	}
	
	
	
	public Texture getTextura() {
		return textura;
	}

	public void setTextura(Texture textura) {
		this.textura = textura;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	public String toString() {
		return "X: "+this.getX()+" Y: "+this.getY()+" altura: "+this.getAltura()+" anchura: "+this.getAnchura();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stubç
		textura.dispose();
	}
}


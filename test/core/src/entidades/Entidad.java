package entidades;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Disposable;

public class Entidad implements Disposable{

	private int vida, velocidadX, velocidadY;
	protected Texture textura;
	protected Sprite sprite;
	
	public Entidad(float x, float y , float altura, float anchura, int vida, int velocidadX, int velocidadY, Texture textura) {
		this.vida = vida;
		this.velocidadX = velocidadX;
		this.velocidadY = velocidadY;
		this.textura=textura;
		this.sprite= new Sprite(textura);
		sprite.setX(x);
		sprite.setY(y);
		sprite.setSize(anchura, altura);
		
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
	
	public int getVelocidadX() {
		return velocidadX;
	}

	public void setVelocidadX(int velocidadX) {
		this.velocidadX = velocidadX;
	}

	public int getVelocidadY() {
		return velocidadY;
	}

	public void setVelocidadY(int velocidadY) {
		this.velocidadY = velocidadY;
	}

	public String toString() {
		return "X: "+this.getX()+" Y: "+this.getY()+" altura: "+this.getAltura()+" anchura: "+this.getAnchura();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stubÃƒÂ§
		textura.dispose();
	}
}

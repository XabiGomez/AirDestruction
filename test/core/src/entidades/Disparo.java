package entidades;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

public class Disparo implements Disposable{
	private float x,y;
	private int tamaino,velX,velY;
	private Texture textura;
	public Texture getTextura() {
		return textura;
	}
	public void setTextura(Texture textura) {
		this.textura = textura;
	}
	public Disparo(float x, float y, int tamaino, int velX,int velY,Texture textura) {
		this.x=x;
		this.y=y;
		this.tamaino=tamaino;
		this.velX=velX;
		this.velY=velY;
		this.textura=textura;
		
	}
	public int getVelX() {
		return velX;
	}
	public void setVelX(int velX) {
		this.velX = velX;
	}
	public int getVelY() {
		return velY;
	}
	public void setVelY(int velY) {
		this.velY = velY;
	}
	public float getX() {
		return x;
	}
	public void setX(float f) {
		this.x = f;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public int gettamaino() {
		return tamaino;
	}
	public void settamaino(int tamaino) {
		this.tamaino = tamaino;
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		this.textura.dispose();
	}
	public String toString() {
		return this.getX()+" "+this.getY();
	}
}

package entidades;

public class Entidad {

	float x, y;
	int altura, anchura;
	int vida, velocidad;
	
	public Entidad(float x, float y , int altura, int anchura, int vida, int velocidad) {
		this.x = x;
		this.y = y;
		this.altura = altura;
		this.anchura = anchura;
		this.vida = vida;
		this.velocidad = velocidad;
		
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getAnchura() {
		return anchura;
	}

	public void setAnchura(int anchura) {
		this.anchura = anchura;
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

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public void move(float x, float y) {
		this.setX(x);
		this.setY(y);
	}
}

package entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import ventanas.AbstractScreen;

public class Boton {
	Texture nombreActivo;
	Texture nombreInactivo;
	int x;
	int y;
	int anchura;
	int altura;
		public Boton(Texture nombreActivo, Texture nombreInactivo, int x, int y, int anchura, int altura) {
		super();
		this.nombreActivo = nombreActivo;
		this.nombreInactivo = nombreInactivo;
		this.x = x;
		this.y = y;
		this.anchura = anchura;
		this.altura = altura;
	}
		public Texture getNombreActivo() {
			return nombreActivo;
		}
		public void setNombreActivo(Texture nombreActivo) {
			this.nombreActivo = nombreActivo;
		}
		public Texture getNombreInactivo() {
			return nombreInactivo;
		}
		public void setNombreInactivo(Texture nombreInactivo) {
			this.nombreInactivo = nombreInactivo;
		}
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		public int getAnchura() {
			return anchura;
		}
		public void setAnchura(int anchura) {
			this.anchura = anchura;
		}
		public int getAltura() {
			return altura;
		}
		public void setAltura(int altura) {
			this.altura = altura;
		}
		
		public boolean esTocado() {
			if (Gdx.input.getX() < x*AbstractScreen.anchura/600 + anchura*AbstractScreen.anchura/600 && Gdx.input.getX() > x*AbstractScreen.anchura/600 && AbstractScreen.altura - Gdx.input.getY() < y*AbstractScreen.altura/1000 + altura*AbstractScreen.altura/1000 && AbstractScreen.altura - Gdx.input.getY() > y*AbstractScreen.altura/1000) {
				return true;
			}else {
				return false;
			}	
		}
}

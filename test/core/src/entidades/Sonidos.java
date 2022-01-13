package entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Sonidos {

	private static Music morir = Gdx.audio.newMusic(Gdx.files.internal("morir.mp3"));;
	private static Music danyo = Gdx.audio.newMusic(Gdx.files.internal("dano.mp3"));;
	private static Music disparoSonido = Gdx.audio.newMusic(Gdx.files.internal("disparo.mp3"));;
	
	private static float volumenPred = 0.5f;
	
	
	public static Music getMorir() {
		return morir;
	}
	
	public static Music getDanyo() {
		return danyo;
	}
	
	public static Music getDisparoSonido() {
		return disparoSonido;
	}

	public static void setMorir(Music morir) {
		Sonidos.morir = morir;
	}
	
	public static void setDanyo(Music danyo) {
		Sonidos.danyo = danyo;
	}
	
	public static void setDisparoSonido(Music disparoSonido) {
		Sonidos.disparoSonido = disparoSonido;
	}
	
	public static void reproducirMorir() {
		morir.play();
	}
	
	public static void reproducirDanyo() {
		danyo.play();
	}
	
	public static void reproducirDisparoSonido() {
		disparoSonido.play();
	}	
	
	public static void pararSonidos() {
		morir.dispose();
		danyo.dispose();
		disparoSonido.dispose();
	}
	
	public static void apagarSonidos() {
		morir.setVolume(0.0f);
		danyo.setVolume(0.0f);
		disparoSonido.setVolume(0.0f);
	}
	
	public static void setVolumenPred(float f) {
		morir.setVolume(volumenPred);
		danyo.setVolume(volumenPred);
		disparoSonido.setVolume(volumenPred);
	}
	
	public static float getVolume() {
		return getVolume();
	}

}

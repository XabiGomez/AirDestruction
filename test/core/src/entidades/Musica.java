package entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.audio.Music;

public class Musica {

	private static Music music;
	private static float volumenPred = 0.1f;
	
	
	public static Music getMusic() {
		return music;
	}

	public static void setMusic(Music music) {
		Musica.music = music;
	}

	public static void reproducirMusica() {
		music = Gdx.audio.newMusic(Gdx.files.getFileHandle("musica.mp3", FileType.Internal));
		music.setVolume(volumenPred);
		music.play();
		music.setLooping(true);
	}
	
	public static void pararMusica() {
		music.dispose();
	}
	
	public static void apagarMusica() {
		music.setVolume(0.0f);
	}
	
	public static void setVolumePred(float f) {
		music.setVolume(volumenPred);
	}
	
	public static float getVolume() {
		return getVolume();
	}

}

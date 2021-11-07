package ventanas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.AirDestructionGame;

public class GameScreen extends AbstractScreen {
	private SpriteBatch batch;
	private Texture texture;
	
	public GameScreen(AirDestructionGame main) {
		super(main);
	}
	
	public void show() {
		batch = new SpriteBatch();
		texture = new Texture("GameFondo.jpg");
	}
	
	@Override
    public void render(float delta) { // Método que permite actualizar los valores del juego y dibujar el juego para que lo vea el usuario.
        //Gdx es una clase con la que podemos acceder a variables que hacen referencia a todos los subsitemas, como son graficos, audio, ficheros, entrada y aplicaciones
        // gl es una variable de tipo GL, nos permite acceder a metodos de GL10, GL11 y GL20
        //En este caso glClearColor es un bucle (game loop) que establecera el fondo de la pantalla negro (0,0,0) con transparencia 1
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Despues de la funcion anterior es necesario ejecutar esta, para que se lleve a cabo

        batch.begin(); // Aqui se comienza a dibujar
        batch.draw(texture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); // Pintamos el fondo de pantalla
        batch.end(); // Se termina de dibujar
    }

}

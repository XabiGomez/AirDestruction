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
    public void render(float delta) { 
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 

        batch.begin(); 
        batch.draw(texture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); 
        batch.end(); 
    }

}

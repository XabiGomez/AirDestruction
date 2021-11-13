package ventanas;

import com.badlogic.gdx.Screen;
import com.mygdx.game.AirDestructionGame;

public class AbstractScreen implements Screen{
	private AirDestructionGame main;
	public static int altura = 1000;
	public static int anchura = 600;
	
	public AbstractScreen(AirDestructionGame main) {
		this.main = main;
	}

	public AbstractScreen() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		altura = height;
		anchura = width;
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}

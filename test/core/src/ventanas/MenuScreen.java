package ventanas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.AirDestructionGame;

import entidades.Boton;



public class MenuScreen extends AbstractScreen{
	private SpriteBatch batch;
	private Texture texture;
	
	private AirDestructionGame game = new AirDestructionGame();
	public GameScreen GameScreen;
	
	private Texture playActivo;
	private Texture playInactivo;

	Boton boton1;
	
	public MenuScreen(AirDestructionGame main) {
		super(main);
		game = main;
		playActivo = new Texture("botones/playActivo.png");
		playInactivo = new Texture("botones/playInactivo.png");
		boton1 = new Boton(playActivo, playInactivo, 100, 100, 100, 100);
	}
	
	public void show() {
		batch = new SpriteBatch();
		texture = new Texture("GameFondo.jpg");

	}
	
	@Override
    public void render(float delta) { 
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 

        batch.begin();  
        
        
       if (boton1.esTocado()) {
        	 batch.draw(boton1.getNombreActivo(), boton1.getX(), boton1.getY(), boton1.getAltura(), boton1.getAnchura());
        	 if (Gdx.input.isTouched()) {
        		 System.out.println("Tocado");
        		 //this.dispose();
        		 GameScreen = new ventanas.GameScreen(game);
        		 game.setScreen(GameScreen);
        	 }
        }else {
        	batch.draw(boton1.getNombreInactivo(), boton1.getX(), boton1.getY(), boton1.getAltura(), boton1.getAnchura());
        }
       
        batch.end(); 
    }
	
	@Override
	public void dispose() {
		
		batch.dispose();
		
	}
	
}

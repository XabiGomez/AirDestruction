package ventanas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.AirDestructionGame;



public class MenuScreen extends AbstractScreen{
	private SpriteBatch batch;
	private Texture texture;
	
	private AirDestructionGame game = new AirDestructionGame();
	public GameScreen GameScreen;
	
	private Texture playActivo;
	private static final int playActivoAltura = 100;
	private static final int playActivoAnchura = 100;
	
	private Texture playInactivo;
	private static final int playInactivoAltura = 100;
	private static final int playInactivoAnchura = 100;

	public MenuScreen(AirDestructionGame main) {
		super(main);
		game = main;
		playActivo = new Texture("botones/playActivo.png");
		playInactivo = new Texture("botones/playInactivo.png");
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
        //batch.draw(texture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); 
        
        
        int x = 100;
        int y = 100;
        //hay que poner las width y height del launcher como constantes para utilizarlos
        int altura = 1000;
        int anchura = 600;
        if (Gdx.input.getX() < x + playActivoAnchura && Gdx.input.getX() > x && altura - Gdx.input.getY() < y + playActivoAltura && altura - Gdx.input.getY() > y) {
        	 batch.draw(playActivo, 100, 100, playActivoAltura, playActivoAnchura);
        	 if (Gdx.input.isTouched()) {
        		 System.out.println("Tocado");
        		 //this.dispose();
        		 GameScreen = new ventanas.GameScreen(game);
        		 game.setScreen(GameScreen);
        	 }
        }else {
        	batch.draw(playInactivo, 100, 100, playInactivoAltura, playInactivoAnchura);
        }
       
        batch.end(); 
    }
	
	@Override
	public void dispose() {
		
		batch.dispose();
		
	}
	
}

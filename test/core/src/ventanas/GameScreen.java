package ventanas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.AirDestructionGame;

import entidades.Jugador;

public class GameScreen extends AbstractScreen {
	private SpriteBatch batch;
	private Texture texture;
	private Texture jug;
	private Jugador player;
	int posyjugador = 100;
	
	public GameScreen(AirDestructionGame main) {
		super(main);
	}
	
	public void show() {
		batch = new SpriteBatch();
		texture = new Texture("GameFondo.jpg");
		jug = new Texture("snorlax.png");
		player = new Jugador(calcularmitadpantX(),posyjugador,100,100,0,5);
	}
	
	@Override
    public void render(float delta) { 
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 

        batch.begin(); 
        batch.draw(texture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); 
	batch.draw(jug, player.getX(),posyjugador,player.getAnchura(),player.getAltura());
        batch.end(); 
	entradadatos();
    }
	
    public void entradadatos() {
		boolean izquierdapulsada = Gdx.input.isKeyPressed(Input.Keys.A)||Gdx.input.isKeyPressed(Input.Keys.LEFT);
		boolean derechapulsada = Gdx.input.isKeyPressed(Input.Keys.D)||Gdx.input.isKeyPressed(Input.Keys.RIGHT);
		boolean intentadisparar = Gdx.input.isKeyPressed(Input.Keys.W)||Gdx.input.isKeyPressed(Input.Keys.UP)||Gdx.input.isKeyPressed(Input.Keys.SPACE);
		if(izquierdapulsada!=derechapulsada) {
			int nuevaX;
			if(izquierdapulsada) {
				nuevaX=(int) (player.getX()-player.getVelocidad());
				if(tocaborde(nuevaX,player)) {
					player.setX(0);
				}else {
					player.setX(nuevaX);
				}
			}else if(derechapulsada) {
				nuevaX=(int) (player.getX()+player.getVelocidad());
				if(tocaborde(nuevaX,player)) {
					player.setX(Gdx.graphics.getWidth()-player.getAnchura());
				}else {
					player.setX(nuevaX);
				}
			}
		}
		if(intentadisparar) {
			System.out.println("dispara");
		}
		
	}
	public int calcularmitadpantX() {
		return  (int) Gdx.graphics.getWidth()/2;
	}
	public boolean tocaborde(int nuevaX,Jugador player) {
		if(Gdx.graphics.getWidth()<(nuevaX+player.getAnchura())) {
			return true;
		}else if(nuevaX<0) {
			return true;
		}else {
			return false;
		}
	}

}

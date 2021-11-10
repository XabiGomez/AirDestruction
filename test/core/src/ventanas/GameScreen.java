package ventanas;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.AirDestructionGame;

import entidades.*;

public class GameScreen extends AbstractScreen {
	private SpriteBatch batch;
	private Texture texture;
	private Texture jug;
	private Jugador player;
	int posyjugador = 100;
	private int movimientoPantalla=0;
	private int tamainodisparoaliado=32,veldisparoaliado=10;
	float enfriamientodisparo= 0.1f;
	float tiempodisparo=5;
	ArrayList<Disparo> disparoaliado = new ArrayList<Disparo>();
	
	public GameScreen(AirDestructionGame main) {
		super(main);
	}
	
	public void show() {
		batch = new SpriteBatch();
		texture = new Texture("GameFondo.jpg");
		jug = new Texture("snorlax.png");
		player = new Jugador(calcularmitadpantX(),posyjugador,100,100,0,200,0,jug);

	}
	
	@Override
    public void render(float delta) { 
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 

        batch.begin(); 
        //Habra que cambiar la pantalla para que no se vea tan mal
        movimientoPantalla++;
        if(movimientoPantalla % Gdx.graphics.getHeight()==0) {
        	movimientoPantalla = 0;
        }
        batch.draw(texture, 0, -movimientoPantalla, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); 
        batch.draw(texture, 0, -movimientoPantalla+ Gdx.graphics.getHeight(), Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        if(disparoaliado!=null&&disparoaliado.size()!=0) {
        	for (int i = 0; i < disparoaliado.size(); ++i) {
        		batch.draw(disparoaliado.get(i).getTextura(), disparoaliado.get(i).getX(), disparoaliado.get(i).getY(), disparoaliado.get(i).getAltura(), disparoaliado.get(i).getAltura());
        	}
        }
        batch.draw(player.getTextura(), player.getX(), player.getY(), player.getAnchura(), player.getAltura());
        batch.end(); 
        entradadatos();
	
    }
	
    public void entradadatos() {
		boolean izquierdapulsada = Gdx.input.isKeyPressed(Input.Keys.A)||Gdx.input.isKeyPressed(Input.Keys.LEFT);
		boolean derechapulsada = Gdx.input.isKeyPressed(Input.Keys.D)||Gdx.input.isKeyPressed(Input.Keys.RIGHT);
		boolean intentadisparar = Gdx.input.isKeyPressed(Input.Keys.W)||Gdx.input.isKeyPressed(Input.Keys.UP)||Gdx.input.isKeyPressed(Input.Keys.SPACE);
		float delta=Gdx.graphics.getDeltaTime();
		if(izquierdapulsada!=derechapulsada) {
			if(izquierdapulsada) {
				if(player.getX()-player.getVelocidadX()*delta<0) {
					player.setX(0);
				}else {
					player.setX(player.getX()-player.getVelocidadX()*delta);
				}
			}else if(derechapulsada) {
				
				if(Gdx.graphics.getWidth()<(player.getX()+player.getVelocidadX()*delta+player.getAnchura())) {
					player.setX(Gdx.graphics.getWidth()-player.getAnchura());
				}else {
					player.setX(player.getX()+player.getVelocidadX()*delta);
				}
			}
		}
		gestiondisparosaliado(intentadisparar,delta);
		
	}
	public int calcularmitadpantX() {
		return  (int) Gdx.graphics.getWidth()/2;
	}
	public boolean tocaborde(Entidad entity) {
		float delta= Gdx.graphics.getDeltaTime();
		if(Gdx.graphics.getWidth()<(entity.getX()+entity.getVelocidadX()*delta+entity.getAnchura())) {
			return true;
		}else if(entity.getX()-entity.getVelocidadX()*delta<0) {
			return true;
		}else {
			return false;
		}
	}
	
	public void disparaaliado() {
		Texture textshoot = new Texture("badlogic.jpg");
		int veldisparoY = 0;
		Disparo shoot = new Disparo(player.getX()+player.getAnchura()/2-tamainodisparoaliado/2, player.getY(), tamainodisparoaliado, tamainodisparoaliado, 1, veldisparoY, veldisparoaliado, textshoot);
		disparoaliado.add(shoot);
	}
	public boolean fueradepantalla(Entidad entity) {
		float pantx = Gdx.graphics.getWidth();
		float panty = Gdx.graphics.getHeight();
		if(entity.getX()+entity.getAnchura()<0||entity.getX()>pantx) {
			return true;
		}else if(entity.getY()+entity.getAltura()<0||entity.getY()>panty) {
			return true;
		}else{
			return false;
		}
		
	}

	public void gestiondisparosaliado(boolean intentadisparar,float delta) {
		if(intentadisparar) {
			if(tiempodisparo>=enfriamientodisparo) {
				disparaaliado();
				tiempodisparo=0;
			}else {
				tiempodisparo+=delta;
			}
		}
		if(disparoaliado!=null&&disparoaliado.size()!=0) {
			for (int i = 0; i < disparoaliado.size(); ++i) {
				if(fueradepantalla(disparoaliado.get(i))) {
					disparoaliado.get(i).dispose();
					disparoaliado.remove(i);
				}else {
					disparoaliado.get(i).setX(disparoaliado.get(i).getX()+disparoaliado.get(i).getVelocidadX());
					disparoaliado.get(i).setY(disparoaliado.get(i).getY()+disparoaliado.get(i).getVelocidadY());
				}
			}
		}
		
		
	}
	
}

package ventanas;

import java.util.ArrayList;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.AirDestructionGame;

import entidades.*;

public class GameScreen extends AbstractScreen {
class KeyboardProcessor extends InputAdapter {
        
        @Override
		public boolean keyUp(int key) {
            switch (key) {
                case Keys.C:    game.dispose();
                                game.setScreen(new GameScreen(game));
                                break;

                default:        break;
            }

            return false;
        }
    }

	private AirDestructionGame game = new AirDestructionGame();
	private SpriteBatch batch;
	private Texture fondo;
	private Texture fondo2;
	private Texture jug,enem1,texturadisparo1;
	private Jugador player;
	private HealthBar healthBar;
	protected Stage stage;
	private int posyjugador = 100;
	private int movimientoPantalla=0;
	private int tamainodisparoaliado=32,veldisparoaliado=4;
	private int tiempospawnmin = 5, tiempospawnmax= 60;
	private float tiempospawn=0.0f,tiempoactualspawn=1.0f;
	private float enfriamientodisparo= 0.5f, tiempodisparo=5;
	ArrayList<Entidad> disparoaliado = new ArrayList<Entidad>();
	ArrayList<Entidad> enemigos = new ArrayList<Entidad>();
	ArrayList<Entidad> disparoenemigo = new ArrayList<Entidad>();
	int  score = 0;
	private BitmapFont font;
	int numOleada = 0;
	
	protected Skin skin;
	boolean parar = false;
	Table menu;

	
	public GameScreen(AirDestructionGame main) {
		super(main);
		game = main;
		stage = new Stage(game.getViewport());
	}
	
	public void show() {
		batch = new SpriteBatch();
		fondo = new Texture("GameFondo.jpg");
		fondo2 = new Texture("GameFondo2.jpg");
		jug = new Texture("NaveAliada.jpg");
		enem1=new Texture("Enemigo.jpg");
		player = new Jugador(0,posyjugador,100,100,5,200,0,jug);
		player.setX(calcularmitadpantX(player));
		texturadisparo1 = new Texture("snorlax.png");
		//provisional
		generarOleada();
		//crearenem(1);
		font = new BitmapFont();
		healthBar = new HealthBar(200, 20);
		healthBar.setPosition(Gdx.graphics.getWidth() - 220, 60);
		
		//boton parar
		skin = new Skin(Gdx.files.internal("widgets//uiskin.json"));
		TextButton botonStop = new TextButton("Stop", skin);
		botonStop.addListener(new ChangeListener(){
            public void changed (ChangeEvent event, Actor actor) {
            	System.out.println("parar");
            	parar = !parar;
            }
	     });
		menu = new Table();
		menu.setBounds(10, 850, 60, 60);
		menu.add(botonStop).minWidth(60).padBottom(60);
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(stage);
		multiplexer.addProcessor(new KeyboardProcessor());
		Gdx.input.setInputProcessor(multiplexer);
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
        	fondo=fondo2;
        }
        batch.draw(fondo, 0, -movimientoPantalla, Gdx.graphics.getWidth()*AbstractScreen.anchuraProporcion, Gdx.graphics.getHeight()*AbstractScreen.alturaProporcion); 
        batch.draw(fondo2, 0, -movimientoPantalla+ Gdx.graphics.getHeight(), Gdx.graphics.getWidth()*AbstractScreen.anchuraProporcion, Gdx.graphics.getHeight()*AbstractScreen.alturaProporcion);
        renderizarArrayList(disparoaliado);
        renderizarArrayList(enemigos);
        renderizarArrayList(disparoenemigo);
        batch.draw(player.getTextura(), player.getX()*AbstractScreen.anchuraProporcion, player.getY()*AbstractScreen.alturaProporcion, player.getAnchura(), player.getAltura());
        font.getData().setScale(2f);
        font.setColor(Color.RED);
        font.draw(batch, score+"", 50, 75);
        batch.end(); 
        if(!parar) {
        	entradadatos();
        	gestiondecolisionesymov();
        }

        esperar();
        //generadordeenemigos();
        
        stage.addActor(healthBar);
        stage.addActor(menu);
        

		stage.act();
	    stage.draw();
        
	
    }
	

	
	private void generarOleada() {
		//numOleada++;
		numOleada=1;
		switch (numOleada) {
		case 1:
			crearenem(1);
			crearenem(1);
			crearenem(1);
			crearenem(1);
			crearenem(1);
			break;

		default:
			Gdx.app.error("Error", "no existe ese numero de oleada"); 
			break;
		}
	}
	

	private void moverEnemigos() {
		for (Entidad i :enemigos) {
			i.mover();
		}	
	}

	public void renderizarArrayList(ArrayList<Entidad> disparoaliado2) {
		if(disparoaliado2!=null&&disparoaliado2.size()!=0) {
			for (int i = 0; i < disparoaliado2.size(); ++i) {
				batch.draw(disparoaliado2.get(i).getTextura(), disparoaliado2.get(i).getX()*AbstractScreen.anchuraProporcion, disparoaliado2.get(i).getY()*AbstractScreen.alturaProporcion, disparoaliado2.get(i).getAnchura(), disparoaliado2.get(i).getAltura());
			}
		}
	}
	
	public void crearenem(int i) {
		switch (i) {
		case 1:
			crearenem1();
			break;

		default:
			Gdx.app.error("Error", "no existe clase enemigo"); 
			break;
		}
	}
	public void crearenem1() {
		Enemigo1 enemprovisional;
		int tamXenempro = 70, tamYenempro = 50;
		float enemproY= Gdx.graphics.getHeight()-(tamYenempro+50);
		float enemproX= (float) (Math.random()*((Gdx.graphics.getWidth()-tamXenempro)*10));
		enemproX = enemproX/10;
		enemprovisional = new Enemigo1(enemproX,enemproY,tamXenempro,tamYenempro,1,1,1,enem1,1,1,texturadisparo1);
		enemigos.add(enemprovisional);
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
	public float calcularmitadpantX(Entidad entity) {
		return  Gdx.graphics.getWidth()/2-entity.getAnchura()/2;
	}
	public static boolean tocaborde(Entidad entity) {
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
	public static boolean fueradepantalla(Entidad entity) {
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
		tiempodisparo+=delta;
		if(intentadisparar) {
			if(tiempodisparo>=enfriamientodisparo) {
				disparaaliado();
				tiempodisparo=0;
			}
		}
		
	}
	public void moverdisparo(ArrayList<Entidad> a){
		if(a!=null&&a.size()!=0) {
			ArrayList<Entidad> temp = new ArrayList<Entidad>();
			for (int i = 0; i < a.size(); ++i) {
				if(fueradepantalla(a.get(i))) {
					temp.add(a.get(i));
				}else {
					a.get(i).setX(a.get(i).getX()+a.get(i).getVelocidadX());
					a.get(i).setY(a.get(i).getY()+a.get(i).getVelocidadY());
				}
			}
			if(!temp.isEmpty()||temp!=null) {
				for(Entidad i:temp) {
					a.remove(i);
				}
			}
		}
		if(a!=null&&a.size()!=0) {
			for (int i = 0; i < a.size(); ++i) {
				if(fueradepantalla(a.get(i))) {
					a.get(i).dispose();
					a.remove(i);
				}else {
					a.get(i).setX(a.get(i).getX()+a.get(i).getVelocidadX());
					a.get(i).setY(a.get(i).getY()+a.get(i).getVelocidadY());
				}
			}
		}
	}
	public void gestiondecolisionesymov() {
		moverdisparo(disparoaliado);
		moverEnemigos();
		gestiondecolisionesdisparoaliado();
		gestiondisenem();
		moverdisparo(disparoenemigo);
		gestiondecolisionesdisparoEnemigo();
	}
	private void gestiondecolisionesdisparoEnemigo() {
		if(disparoenemigo!=null&&disparoenemigo.size()>0) {
			ArrayList<Disparo> disparoenemigoelimin = new ArrayList<>();
			boolean overlap = false;
			for(Entidad i : disparoenemigo) {
				overlap = i.getSprite().getBoundingRectangle().overlaps(player.getSprite().getBoundingRectangle());
				if(overlap) {
					Gdx.app.log("Colision", "Personaje Tocado");
					player.perderVida(1);
					healthBar.setValue(healthBar.getValue() - 0.2f);
					if(player.getVida()<1) {
						GameScreen.this.dispose();
				    	GameScreen.this.game.setScreen(new MenuScreen(GameScreen.this.game));
				        Gdx.app.log("Jugar", "Fin de la partida");
					}
					disparoenemigoelimin.add((Disparo) i);
					overlap=false;
					break;
				}
			}
			if(disparoenemigoelimin!=null) {
				for(int i =0; i<disparoenemigoelimin.size();++i) {
					disparoenemigo.remove(disparoenemigoelimin.get(i));
				}
			}
		}
		
	}
	@Override
	public void dispose() {
		skin.dispose();
		batch.dispose();
		
	}

	public void gestiondecolisionesdisparoaliado() {
		if(disparoaliado!=null&&disparoaliado.size()>0&&enemigos!=null&&enemigos.size()>0) {
			ArrayList<Disparo> disparoaliadoelimin = new ArrayList<>();
			ArrayList<Enemigo> enemigoselimin = new ArrayList<>();
			boolean overlap=false;
			for(Entidad i : disparoaliado) {
				for(Entidad j :enemigos) {
					overlap = i.getSprite().getBoundingRectangle().overlaps(j.getSprite().getBoundingRectangle());
					if(overlap) {
						Gdx.app.log("Colision", "Enemigo Tocado");
						score += 1;
						System.out.println(score);
						disparoaliadoelimin.add((Disparo) i);
						enemigoselimin.add((Enemigo) j);
						overlap=false;
						break;
					}
				}
			}
			if(disparoaliadoelimin!=null) {
				for(int i =0; i<disparoaliadoelimin.size();++i) {
					disparoaliado.remove(disparoaliadoelimin.get(i));
				}
			}
			if(enemigoselimin!=null) {
				for(int i =0; i<enemigoselimin.size();++i) {
					enemigoselimin.get(i).perderVida(1);
					if(enemigoselimin.get(i).getVida()<1) {
						Gdx.app.log("Colision", "Enemigo Eliminado");
						enemigos.remove(enemigoselimin.get(i));
					}
					if (enemigos.isEmpty()) {
						//Espera medio segundo antes de generar la siguiente oleada
						Thread esperar =new Thread() {
							@Override
							public void run() {
								try {
									Thread.sleep(500);
								} catch (InterruptedException e) {
									Gdx.app.error("Error", "Error al esperar"); 
								}
								generarOleada();

							}
						};
						esperar.start();
					}
				}
			}
		}
	}
	public void gestiondisenem() {
		for(Entidad i: enemigos) {
			Enemigo b = (Enemigo) i;
			b.intentadisparar(disparoenemigo);
		}
	}
	public void esperar() {
		float x = 1/30;
		if(x>Gdx.graphics.getDeltaTime()) {
			try {
				Thread.sleep((long) (x-Gdx.graphics.getDeltaTime()));
			} catch (InterruptedException e) {
				Gdx.app.error("Error", "Error al esperar"); 
			}
		}
	}
	
	
	//no se usa
	public void generadordeenemigos() {
		if(tiempospawn<tiempoactualspawn) {
			tiempospawn= (float) ((Math.random()*tiempospawnmax + tiempospawnmin)/10);  // Valor entre M y N, ambos incluidos.
			tiempoactualspawn=0.0f;
			crearenem(1);
		}else {
			tiempoactualspawn+=Gdx.graphics.getDeltaTime();
		}
	}
	
	
}

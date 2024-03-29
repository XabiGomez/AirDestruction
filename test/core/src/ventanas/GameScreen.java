package ventanas;

import java.util.ArrayList;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.audio.Sound;
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
	private Texture jug,enem1;
	private static Jugador player;
	private HealthBar healthBar;
	protected Stage stage;
	private int posyjugador = 100;
	private int movimientoPantalla=0;
	private int tamainodisparoaliado=32,veldisparoaliado=4;
	private int tiempospawnmin = 5, tiempospawnmax= 60;
	private float tiempospawn=0.0f,tiempoactualspawn=1.0f;
	private float enfriamientodisparo= 0.5f, tiempodisparo=5;
	private float propenem1=50,propenem2=17,propenem3=30,propenem4=3;
	ArrayList<Entidad> disparoaliado = new ArrayList<Entidad>();
	ArrayList<Entidad> enemigos = new ArrayList<Entidad>();
	ArrayList<Entidad> disparoenemigo = new ArrayList<Entidad>();
	int score;
	private BitmapFont font;
	int numOleada = 10;
	
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
		jug = new Texture("NaveAliada.png");
		enem1=new Texture("Enemigo.png");
		player = new Jugador(0,posyjugador,100,100,5,200,0,jug);
		player.setX(calcularmitadpantX(player));
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
		if (numOleada%5==0 && numOleada!=10) {
			crearenem(5);
		}else {
			for(int i=0; i<numOleada; ++i) {
				double x = Math.random()*100 + 1;
				System.out.println(x);
				if(x<propenem1) {
					crearenem(1);
				}else if(x<(propenem2+propenem1)&&x>=propenem1) {
					crearenem(2);
				}else if(x<(propenem2+propenem1+propenem3)&&x>=(propenem1+propenem2)) {
					crearenem(3);
				}else if(x<(propenem2+propenem1+propenem3+propenem4)&&x>=(propenem1+propenem2+propenem3)) {
					crearenem(4);
				}
			}
		}
		numOleada=2+numOleada;
		
	}
	

	private void moverEnemigos() {
		for (int i=0;i<enemigos.size();++i) {
			enemigos.get(i).mover();
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
		case 2:
			crearenem2();
			break;
		case 3:
			crearenem3();
			break;
		case 4:
			crearenem4();
			break;
		case 5:
			crearenem5();
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
		float a = Gdx.graphics.getWidth()-tamXenempro;
		float enemproX= (float) (Math.random()*(a))*10;
		enemproX = enemproX/10;
		enemprovisional = new Enemigo1(enemproX,enemproY,tamXenempro,tamYenempro,1,1,1,enem1,1,1);
		enemigos.add(enemprovisional);
		System.out.println(enemprovisional);
	}
	public void crearenem2() {
		Enemigo2 enemigoprovisional;
		int tamXenempro = 70, tamYenempro = 80;
		float enemproY= Gdx.graphics.getHeight()-(tamYenempro+50);
		float a = Gdx.graphics.getWidth()-tamXenempro;
		float enemproX= (float) (Math.random()*(a));
		enemigoprovisional = new Enemigo2(enemproX,enemproY,tamYenempro,tamXenempro,3,1,1,enem1,1,1);
		enemigos.add(enemigoprovisional);
		System.out.println(enemigoprovisional);
		
	}
	public void crearenem3() {
		Enemigo3 enemigoprovisional;
		int tamXenempro = 70, tamYenempro = 80;
		float enemproY= Gdx.graphics.getHeight()-(tamYenempro+50);
		float a = Gdx.graphics.getWidth()-tamXenempro;
		float enemproX= (float) (Math.random()*(a));
		enemigoprovisional = new Enemigo3(enemproX,enemproY,tamYenempro,tamXenempro,2,1,1,enem1,1,1);
		enemigos.add(enemigoprovisional);
		System.out.println(enemigoprovisional);
		
	}
	public void crearenem4() {
		Enemigo4 enemigoprovisional;
		int tamXenempro = 300, tamYenempro = 80;
		float enemproY= Gdx.graphics.getHeight()-(tamYenempro+50);
		float a = Gdx.graphics.getWidth()-tamXenempro;
		float enemproX= (float) (Math.random()*(a));
		enemigoprovisional = new Enemigo4(enemproX,enemproY,tamYenempro,tamXenempro,10,1,1,enem1,1,1);
		enemigos.add(enemigoprovisional);
		System.out.println(enemigoprovisional);
		
	}
	public void crearenem5() {
		Enemigo5 enemigoprovisional;
		int tamXenempro = 300, tamYenempro = 80;
		float enemproY= Gdx.graphics.getHeight()-(tamYenempro+50);
		float a = Gdx.graphics.getWidth()-tamXenempro;
		float enemproX= (float) (Math.random()*(a));
		enemigoprovisional = new Enemigo5(enemproX,enemproY,tamYenempro,tamXenempro,20,1,1,enem1,1,1);
		enemigos.add(enemigoprovisional);
		System.out.println(enemigoprovisional);
		
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
		Texture textshoot = new Texture("laser aliado.png");
		int veldisparoY = 0;
		Disparo shoot = new Disparo(player.getX()+player.getAnchura()/2-tamainodisparoaliado/2, player.getY(), tamainodisparoaliado, tamainodisparoaliado, 1, veldisparoY, veldisparoaliado, textshoot);
		Sonidos.reproducirDisparoSonido();
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
		colisionesentredisparo();
		gestiondisenem();
		moverdisparo(disparoenemigo);
		gestiondecolisionesdisparoEnemigo();
		GestionDeColisionConEnemigo();
	}
	
	private void GestionDeColisionConEnemigo() {
		boolean overlap = false;
		for(Entidad i : enemigos) {
			overlap = i.getSprite().getBoundingRectangle().overlaps(player.getSprite().getBoundingRectangle());
			if(overlap) {
				Gdx.app.log("Colision", "Personaje Colisionado con Enemigo");
				perderVida(1);
				i.setY(Gdx.graphics.getHeight());
				if (i instanceof Enemigo3) {
					((Enemigo3) i).control();
				}
			}
		}

	}
	private void perderVida(int i) {
		player.perderVida(1);
		Sonidos.reproducirDanyo();
		healthBar.setValue(healthBar.getValue() - 0.2f*i);
		if(player.getVida()<1) {
			Sonidos.reproducirMorir();
			Player player = new Player(1, MenuScreen.getNombre(), score);
			BD.conexionBd("ranking.db", false);
			BD.insertarPlayer(player);
			BD.cierreBD();
			GameScreen.this.dispose();
			GameScreen.this.game.setScreen(new MuerteScreen(GameScreen.this.game, score));
	        Gdx.app.log("Jugar", "Fin de la partida");
		}
	}
	
	private void gestiondecolisionesdisparoEnemigo() {
		if(disparoenemigo!=null&&disparoenemigo.size()>0) {
			ArrayList<Disparo> disparoenemigoelimin = new ArrayList<>();
			boolean overlap = false;
			for(Entidad i : disparoenemigo) {
				overlap = i.getSprite().getBoundingRectangle().overlaps(player.getSprite().getBoundingRectangle());
				if(overlap) {
					Gdx.app.log("Colision", "Personaje Tocado");
					perderVida(1);
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
						score += 1;
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
	
	public void colisionesentredisparo() {
		if(disparoaliado!=null&&disparoaliado.size()>0&&disparoenemigo!=null&&disparoenemigo.size()>0) {
			ArrayList<Disparo> disenemigoelimin = new ArrayList<>();
			ArrayList<Disparo> disparoaliadoelimin = new ArrayList<>();
			boolean overlap=false;
			for(Entidad i : disparoaliado) {
				for(Entidad j :disparoenemigo) {
					overlap = i.getSprite().getBoundingRectangle().overlaps(j.getSprite().getBoundingRectangle());
					if(overlap) {
						Gdx.app.log("Colision", "Disparos Colisionan");
						disenemigoelimin.add((Disparo) j);
						disparoaliadoelimin.add((Disparo) i);
						overlap=false;
						break;
					}
				}
			}
			if(disenemigoelimin!=null&&!disenemigoelimin.isEmpty()) {
				for(int i =0; i<disenemigoelimin.size();++i) {
					disenemigoelimin.get(i).perderVida(1);
					if(disenemigoelimin.get(i).getVida()<1) {
						disparoenemigo.remove(disenemigoelimin.get(i));
					}
				}
			}
			if(disparoaliadoelimin!=null&&!disparoaliadoelimin.isEmpty()) {
				for(int i =0; i<disparoaliadoelimin.size();++i) {
					disparoaliadoelimin.get(i).perderVida(1);
					if(disparoaliadoelimin.get(i).getVida()<1) {
						disparoaliado.remove(disparoaliadoelimin.get(i));
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
	
	public static float getPosYJugador() {
		return player.getY();	
	}
	
	public static float getPosXJugador() {
		return player.getX();	
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

package ventanas;

import javax.swing.JOptionPane;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.AirDestructionGame;
import entidades.Musica;



public class MenuScreen extends AbstractScreen{

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
	
	private SpriteBatch batch = new SpriteBatch();;
	private Texture texture;
	
	private AirDestructionGame game = new AirDestructionGame();
	public GameScreen GameScreen;
	 
	protected Stage stage;
	protected Skin skin;
	
	public MenuScreen(AirDestructionGame main) {
		super(main);
		game = main;
    	skin = new Skin(Gdx.files.internal("widgets//uiskin.json"));
    	stage = new Stage(game.getViewport());

		
	}
	
	public void show() {
		//Imagen de fondo
		texture = new Texture("GameFondo.jpg");
		Image fondo = new Image(texture);
        stage.addActor(fondo);
		
        //reproducir musica
      	if (Musica.getMusic() == null) {
      		Musica.reproducirMusica();
      		Gdx.app.log("Musica", "Reproducciendo la musica");
  		}
      	else if (!Musica.getMusic().isPlaying()) {
      		Musica.reproducirMusica();
      		Gdx.app.log("Musica", "Reproducciendo la musica");
   		}
        
        //crear la tabla
		Table menu = new Table();
		menu.setFillParent(true);
		menu.center();
		
		//Boton jugar
		TextButton botonJugar = new TextButton("Jugar", skin);

		botonJugar.addListener(new ChangeListener(){
            public void changed (ChangeEvent event, Actor actor) {
            	MenuScreen.this.dispose();
            	MenuScreen.this.game.setScreen(new GameScreen(MenuScreen.this.game));    
            	Gdx.app.log("Jugar", "Entrando a la partida"); 
				String j = JOptionPane.showInputDialog(null, "Introduzca el nombre de jugador:", "Ejemplo: Maria");
				while (j==null){
					j = JOptionPane.showInputDialog(null, "Introduzca el nombre de jugador:", "Ejemplo: Maria");
				}
            }
        });
		
		//Boton opciones
		TextButton botonOpciones = new TextButton("Opciones", skin);

		botonOpciones.addListener(new ChangeListener(){
		    public void changed (ChangeEvent event, Actor actor) {
		    	MenuScreen.this.dispose();
		    	MenuScreen.this.game.setScreen(new OptionsScreen(MenuScreen.this.game));
		        Gdx.app.log("Opciones", "Entrando menu de opciones");     	           	
		               
		    }
		});
		
		//Boton salir
		TextButton botonSalir = new TextButton("Salir", skin);

		botonSalir.addListener(new ChangeListener(){
            public void changed (ChangeEvent event, Actor actor) {
            	Gdx.app.log("Salir", "Saliendo del juego"); 
            	Gdx.app.exit();    
            	Musica.pararMusica();
               
            }
        });	
		
		//Anyadir botones a la tabla
		menu.add(botonJugar).minWidth(200).padBottom(25);
        menu.row();
        menu.add(botonOpciones).minWidth(200).padBottom(25);
        menu.row();
        menu.add(botonSalir).minWidth(200);
        
        stage.addActor(menu);
        
        //multiplexer para que funcionen los botones
        InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(stage);
		multiplexer.addProcessor(new KeyboardProcessor());
		Gdx.input.setInputProcessor(multiplexer);
        
	}
	
	@Override
    public void render(float delta) { 
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 

        batch.begin();       
        batch.end(); 
        
        stage.act();
        stage.draw();
    }
	
	@Override
	public void dispose() {
		skin.dispose();
		batch.dispose();
		
	}
	
}
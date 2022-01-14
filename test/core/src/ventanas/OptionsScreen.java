package ventanas;


import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.AirDestructionGame;

import entidades.Musica;
import entidades.Sonidos;



public class OptionsScreen extends AbstractScreen{

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
	public GameScreen GameScreen;
	private SpriteBatch batch = new SpriteBatch();;
	
	protected Stage stage;
	protected Skin skin;
	
	
	public OptionsScreen(AirDestructionGame main) {
		super(main);
		game = main;
    	skin = new Skin(Gdx.files.internal("widgets//uiskin.json"));
    	stage = new Stage(game.getViewport());

		
	}
	
	public void show() {
		
		//Crear una tabla "Menu"
		Table menu = new Table();
		menu.setFillParent(true);
		menu.center();
		stage.addActor(menu);
		
		//slider
		final Slider slider = new Slider(0, 100, 0.1f, false, skin);
		slider.setValue(10);
		
		slider.addListener(new ChangeListener() {

	        @Override
	        public void changed(ChangeEvent event, Actor actor) {
	            if (slider.isDragging())
	                Musica.getMusic().setVolume(slider.getValue() / 100f);
		            Sonidos.getDanyo().setVolume(slider.getValue() / 100f);
	            	Sonidos.getDisparoSonido().setVolume(slider.getValue() / 100f);
	            	Sonidos.getMorir().setVolume(slider.getValue() / 100f);
	        }
	    });
		
		//Boton del ranking
		TextButton botonRanking = new TextButton("Ranking",skin);
		botonRanking.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				OptionsScreen.this.dispose();
                OptionsScreen.this.game.setScreen(new ScoreScreen(OptionsScreen.this.game));	
				Gdx.app.log("Ranking", "Abriendo Ranking");
		    }
		});

		
		//Boton salir
		TextButton botonSalir = new TextButton("Volver a la pantalla principal", skin);

		botonSalir.addListener(new ChangeListener(){
            public void changed (ChangeEvent event, Actor actor) {
            	OptionsScreen.this.dispose();
                OptionsScreen.this.game.setScreen(new MenuScreen(OptionsScreen.this.game));	
                Gdx.app.log("MenuSalir", "Volviendo al menu");
               
            }
        });	
		    
        //Boton silenciar 
        TextButton botonSonido = new TextButton("Silenciar", skin);
        
        botonSonido.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                Musica.apagarMusica();
                Sonidos.apagarSonidos();
                slider.setValue(0);
            }
        });
        
		//Anyadir botones a la tabla "Menu"
        menu.add(slider).padBottom(25);
		menu.row();
		menu.add(botonSonido).minWidth(200).padBottom(25);
		menu.row();
		menu.add(botonRanking).minWidth(200).padBottom(25);;
		menu.row();
		menu.add(botonSalir).minWidth(300);        
        InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(stage);
		multiplexer.addProcessor(new KeyboardProcessor());
		Gdx.input.setInputProcessor(multiplexer);

        
	}
	@Override
    public void render(float delta) { 
		Gdx.gl.glClearColor(95/255f, 216/255f, 233/255f, 1);
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

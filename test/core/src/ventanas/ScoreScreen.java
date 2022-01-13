package ventanas;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.mygdx.game.AirDestructionGame;

import entidades.BD;
import entidades.Musica;
import entidades.Player;

public class ScoreScreen extends AbstractScreen{
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
	private ArrayList<Player> ranking;
	private SpriteBatch batch = new SpriteBatch();;
	
	protected Stage stage;
	protected Skin skin;
	
	
	public ScoreScreen(AirDestructionGame main) {
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
		
		ArrayList<Player> list = BD.getPlayers();
		if (list.size()<15) {
			for (int i = 0 ; i<list.size(); i++) {
				Label frase = new Label(i+1+"º "+list.get(i).getNombre()+ " con "+ list.get(i).getScore()+ " puntos" , skin);
				menu.add(frase);
				menu.row();
			}
		}else {
			for (int i = 0 ; i<15; i++) {
				Label frase = new Label(i+"º "+list.get(i).getNombre()+ " con "+ list.get(i).getScore(), skin);
				menu.add(frase);
				menu.row();
			}
		}
			
	
		
		//Boton salir
		TextButton botonSalir = new TextButton("Volver a opciones", skin);

		botonSalir.addListener(new ChangeListener(){
            public void changed (ChangeEvent event, Actor actor) {
            	ScoreScreen.this.dispose();
            	ScoreScreen.this.game.setScreen(new OptionsScreen(ScoreScreen.this.game));	
                Gdx.app.log("MenuSalir", "Volviendo al menu");
               
            }
        });	
		    
        
		//Anyadir botones a la tabla "Menu"
		
		menu.row();
		menu.add(botonSalir).minWidth(300);
        
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
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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.AirDestructionGame;


public class MuerteScreen extends AbstractScreen{

class KeyboardProcessor extends InputAdapter {
        
       @Override
       public boolean keyUp(int key) {
           switch (key) {
                case Keys.C:   game.dispose();
                               game.setScreen(new GameScreen(game));
                               break;

                default:       break;
            }

            return false;
        }
    }

	private AirDestructionGame game = new AirDestructionGame();
	public GameScreen GameScreen;
	private SpriteBatch batch = new SpriteBatch();;
	
	protected Stage stage;
	protected Skin skin;
	int score2; 

	public MuerteScreen(AirDestructionGame main, int score) {
		super(main);
		game = main;
		score2 = score;
    	skin = new Skin(Gdx.files.internal("widgets//uiskin.json"));
    	stage = new Stage(game.getViewport());
	}
	
public void show() {
		
		//Crear una tabla "Menu"
		Table menu = new Table();
		menu.setFillParent(true);
		menu.center();
		stage.addActor(menu);
		
		Label gameover= new Label("Game Over", skin);
		gameover.setFontScale(4f, 4f);
		gameover.setColor(Color.RED);
		
		Label puntuacion= new Label("Tu puntuacion es " + score2, skin);
		puntuacion.setFontScale(2f, 2f);
		
		
		//Boton salir
		TextButton botonSalir = new TextButton("Volver a la pantalla principal", skin);

		botonSalir.addListener(new ChangeListener(){
            public void changed (ChangeEvent event, Actor actor) {
            	MuerteScreen.this.dispose();
            	MuerteScreen.this.game.setScreen(new MenuScreen(MuerteScreen.this.game));	
                Gdx.app.log("MenuSalir", "Volviendo al menu");
               
            }
        });	
		
		//Anyadir botones a la tabla "Menu"
		
		menu.add(gameover).padBottom(200);
		menu.row();
		menu.add(puntuacion).padBottom(100);
		menu.row();
		menu.add(botonSalir).minWidth(300);
        
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

package ventanas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.AirDestructionGame;

import ventanas.MenuScreen.KeyboardProcessor;

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
	private Texture texture;
	
	protected Stage stage;
	private Viewport viewport;
	protected Skin skin;
	
	
	public OptionsScreen(AirDestructionGame main) {
		super(main);
		game = main;
    	skin = new Skin(Gdx.files.internal("widgets//uiskin.json"));
    	stage = new Stage(game.getViewport());

		
	}
	
	public void show() {
		//Fondo (NO COMPLETADO)
		texture = new Texture("GameFondo.jpg");
		
		//Crear una tabla "Menu"
		Table menu = new Table();
		menu.setFillParent(true);
		menu.center();
		stage.addActor(menu);
		
		//Boton salir
		TextButton botonSalir = new TextButton("Volver a la pantalla principal", skin);

		botonSalir.addListener(new ChangeListener(){
            public void changed (ChangeEvent event, Actor actor) {
            	OptionsScreen.this.dispose();
                OptionsScreen.this.game.setScreen(new MenuScreen(OptionsScreen.this.game));	
                Gdx.app.log("MenuSalir", "Volviendo al menu");
               
            }
        });	
        
        //checkbox sonido (NO COMPLETADO)
        final CheckBox sonidoCheck = new CheckBox("Sonido", game.getDefaultSkin());
		menu.add(sonidoCheck).minWidth(200).padBottom(25);
        
		//Anyadir botones a la tabla "Menu"
		menu.add(sonidoCheck).minWidth(200).padBottom(25);
		menu.row();
		menu.add(botonSalir).minWidth(400);        
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

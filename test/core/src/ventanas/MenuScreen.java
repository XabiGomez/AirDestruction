package ventanas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.AirDestructionGame;

import entidades.Boton;



public class MenuScreen extends AbstractScreen{
	private SpriteBatch batch = new SpriteBatch();;
	private Texture texture;
	
	private AirDestructionGame game = new AirDestructionGame();
	public GameScreen GameScreen;
	
	private Texture playActivo;
	private Texture playInactivo;
	 
	protected Stage stage;
	private Viewport viewport;
	protected Skin skin;

	Boton boton1;
	
	public MenuScreen(AirDestructionGame main) {
		super(main);
		game = main;
		playActivo = new Texture("botones/playActivo.png");
		playInactivo = new Texture("botones/playInactivo.png");
		boton1 = new Boton(playActivo, playInactivo, 100, 100, 100, 100);
		
    	viewport = new FitViewport(600, 600);
    	viewport.apply();
    	skin = new Skin(Gdx.files.internal("widgets//uiskin.json"));
    	stage = new Stage(viewport,batch);

		
	}
	
	public void show() {
		texture = new Texture("GameFondo.jpg");
		
		Table menu = new Table();
		menu.setFillParent(true);
		menu.center();
		
		TextButton boton1 = new TextButton("Jugar", skin);

		boton1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	System.out.println("aaaaaa");
            	           	
            	//((Game)Gdx.app.getApplicationListener()).setScreen(new GameScreen());
            	//((Game)Gdx.app.getApplicationListener()).setScreen(new PlayScreen());
                //MainScreen.this.dispose();
               // MainScreen.this.game.setScreen(new GameScreen(MainScreen.this.game));
            }
        });
		
		menu.add(boton1).width(200);
        menu.row();
        
        stage.addActor(menu);
	}
	
	@Override
    public void render(float delta) { 
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 

        batch.begin();  
        
       /*
       if (boton1.esTocado()) {
        	 batch.draw(boton1.getNombreActivo(), boton1.getX(), boton1.getY(), boton1.getAltura(), boton1.getAnchura());
        	 if (Gdx.input.isTouched()) {
        		 System.out.println("Tocado");
        		 //this.dispose();
        		 GameScreen = new ventanas.GameScreen(game);
        		 game.setScreen(GameScreen);
        	 }
        }else {
        	batch.draw(boton1.getNombreInactivo(), boton1.getX(), boton1.getY(), boton1.getAltura(), boton1.getAnchura());
        }
        */
       
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
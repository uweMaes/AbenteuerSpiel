package com.mygdx.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.PositionComponent;
import com.mygdx.game.entities.EntityFactory;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.MainMenuScreen;
import com.mygdx.game.systems.Listener;
import com.mygdx.game.systems.MovementSystem;
import com.mygdx.game.systems.MyInputProcessor;
import com.mygdx.game.systems.RenderingSystem;
import com.mygdx.game.systems.WorldController;

public class GameMain extends Game {
public SpriteBatch batch;
private Engine engine;
private GameScreen gameScreen;
private EntityFactory entityFactory;
private MyInputProcessor myInputProcessor;
private WorldController worldController;
private PositionComponent positionComponent;
private Listener listener;

    @Override
    public void create() {
        this.engine = new Engine();
        this.entityFactory = new EntityFactory();        

        Entity player = entityFactory.createPlayer(engine); 
        positionComponent = new PositionComponent(200, 200, 32f, 32f);
        player.add(positionComponent);        
        this.worldController = new WorldController(player);
        player.add(worldController.bodyComponent);
        this.engine.addSystem(new MovementSystem(this.engine, worldController));
        worldController.createWorldBody();
        this.gameScreen = new GameScreen(this, engine, worldController);
        this.batch = new SpriteBatch();
        this.engine.addSystem(new RenderingSystem(batch));        
        myInputProcessor = new MyInputProcessor(player);
        listener = new Listener(worldController.world, player);
        worldController.world.setContactListener(listener);
        this.setScreen(new MainMenuScreen(this));
        Gdx.app.log("EntityFactory", "Player position component added at: " + positionComponent.position.toString()); 
        Gdx.app.log("GameMain", "[PositionComponent] Created at: " + positionComponent.position.x + ", " + positionComponent.position.y);

        // Log hinzufügen, um zu überprüfen, ob der Player-Body korrekt erstellt wurde
        Gdx.app.log("GameMain", "[WorldController] Player body created at: (" + worldController.playerBody.getPosition().x + "," + worldController.playerBody.getPosition().y + ")");
        Gdx.app.log("GameMain", "Listener ist hoffentlich dabei " + engine.getSystems() + worldController.world);
    }

    public void startGame() {
        Gdx.input.setInputProcessor(myInputProcessor);
        this.setScreen(gameScreen);
    }

    @Override
    public void render() {  
        super.render();      
    }

    @Override
    public void dispose() {     
        batch.dispose();        
    }
}

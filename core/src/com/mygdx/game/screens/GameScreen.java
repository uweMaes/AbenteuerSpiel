package com.mygdx.game.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameMain;
import com.mygdx.game.systems.WorldController;

public class GameScreen implements Screen {
    final GameMain game;
    private TiledMap map;
    private MapLayer collisionLayer;
    private OrthogonalTiledMapRenderer renderer;    
    private OrthographicCamera camera;
    private Viewport viewport;
    private int worldWidth = 512;
    private int worldHeight = 320;
    private Engine engine;
    private WorldController worldController;
    Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();

    public GameScreen(final GameMain game, Engine engine, WorldController worldController) {
        this.game = game;
        this.engine = engine;
        this.worldController = worldController;
        //map = new TmxMapLoader().load("TestTileMap192mal224.tmx");
        map = new TmxMapLoader().load("neueKarteTest.tmx");
        collisionLayer = map.getLayers().get("Objektebene 1");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
        viewport = new FitViewport(worldWidth, worldHeight, camera);
        camera.setToOrtho(false, worldWidth, worldHeight);
    }

    @Override
    public void render(float delta) {       
        
        ScreenUtils.clear(0, 0, 0, 1);
        float deltaTime = Gdx.graphics.getDeltaTime();        
        worldController.update(deltaTime);
        camera.update();
        viewport.apply();
        renderer.setView(camera);        
        renderer.render();
        engine.update(deltaTime);
        game.batch.setProjectionMatrix(camera.combined);
        debugRenderer.render(worldController.world, camera.combined); 
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(worldWidth / 2, worldHeight / 2, 0);
        camera.update();
    }
    
    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();        
        game.batch.dispose(); 
    }

    @Override
    public void show() { }
    
    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    public MapLayer getCollisionLayer() {
        return collisionLayer;
    }
}

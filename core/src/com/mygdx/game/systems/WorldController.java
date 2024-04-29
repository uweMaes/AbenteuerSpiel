package com.mygdx.game.systems;

import java.util.ArrayList;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mygdx.game.components.BodyComponent;
import com.mygdx.game.components.PositionComponent;

public class WorldController {
    public World world;
    public Box2DDebugRenderer box2DDebugRenderer;
    public Entity player;
    public TiledMap map;
    public MapLayer collisionLayer;
    public ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    public MovementSystem movementSystem;
    public Body playerBody;
    public BodyComponent bodyComponent;
    public PositionComponent positionComponent;
    private ArrayList<Body> worldBodies;
    

    public WorldController(Entity player) {
        this.player = player;
        this.positionComponent = pm.get(player);
        world = new World(new Vector2(0, 0), true);
        this.playerBody = createPlayerBody(player);
        bodyComponent = new BodyComponent(playerBody);
        this.worldBodies = createWorldBody();
    }

    public void update(float deltaTime) {
        world.step(1/60f, 6, 2);
        handlePlayerMovement(deltaTime);
    }

    public ArrayList<Body> createWorldBody(){

        map = new TmxMapLoader().load("neueKarteTest.tmx");
        collisionLayer = map.getLayers().get("Objektebene 1");
        ArrayList<Body> worldBodies = new ArrayList<Body>();

        for (MapObject object : collisionLayer.getObjects()) {
            if (object instanceof RectangleMapObject) {
                Rectangle rect = ((RectangleMapObject) object).getRectangle();
                BodyDef worldBodyDef = new BodyDef();
                worldBodyDef.type = BodyType.StaticBody;
                worldBodyDef.position.set(rect.getX(), rect.getY());
                Body worldBody = world.createBody(worldBodyDef);
                PolygonShape shape = new PolygonShape();
                shape.setAsBox(rect.getHeight(), rect.getWidth());
                worldBody.createFixture(shape, 1f);
                worldBodies.add(worldBody);
                shape.dispose();
            }
        }
        return worldBodies;
        
    }

    public Body createPlayerBody(Entity player){
        
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DynamicBody;
        bodyDef.position.set(positionComponent.position);
        Body playerBody = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(16f, 16f); 
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        playerBody.createFixture(fixtureDef);
        shape.dispose();
        Gdx.app.log("WorldController", "Player body created at: " + playerBody.getPosition().toString());
        return playerBody;
    }

    public void handlePlayerMovement(float deltaTime) {
        if (playerBody != null && positionComponent != null) {
            playerBody.setTransform(positionComponent.position, playerBody.getAngle());
        }
    }

    public void setPlayer(Entity player) {
        this.player = player;
        world.setContactListener(new Listener(world, player));
    }
}

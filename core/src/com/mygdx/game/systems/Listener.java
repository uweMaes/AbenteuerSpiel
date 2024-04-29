package com.mygdx.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldManifold;
import com.mygdx.game.components.BodyComponent;
import com.mygdx.game.components.PositionComponent;
import com.mygdx.game.components.VelocityComponent;

public class Listener implements ContactListener{

    private World world;
    private Entity player;
    private ComponentMapper <BodyComponent> bodyMapper = ComponentMapper.getFor(BodyComponent.class);
    private ComponentMapper <PositionComponent> positionMapper = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper <VelocityComponent> veloMapper = ComponentMapper.getFor(VelocityComponent.class);

    public Listener(World world, Entity player){
        this.world = world;
        this.player = player;
    }    

    @Override
    public void beginContact(Contact contact) {

        WorldManifold coll = contact.getWorldManifold();
        Vector2 collisionPosition = coll.getPoints()[0];
        BodyComponent body = bodyMapper.get(player);
        Vector2 playerVelocity = veloMapper.get(player).velocity;
        PositionComponent posi = positionMapper.get(player);
        Vector2 playerPosition = posi.position;
        Gdx.app.log("Listener", "body " + body);
        Gdx.app.log("Listener", "player? " + body.playerBody);
        Gdx.app.log("Listener", "bodyPosition " + body.playerBody.getPosition());
        Gdx.app.log("Listener", "linearvelocity" + playerVelocity);
        Gdx.app.log("Listener", "MapperPosition" + playerPosition);
        Gdx.app.log("Listener", "MapperVelo" + veloMapper.get(player).velocity);

        if (playerVelocity.x > 0 && collisionPosition.x > playerPosition.x) {
            veloMapper.get(player).setVelocity(new Vector2(0f, playerVelocity.y));
        } else if (playerVelocity.x < 0 && collisionPosition.x < playerPosition.x) {
            veloMapper.get(player).setVelocity(new Vector2(0f, playerVelocity.y));
        } else if (playerVelocity.y > 0 && collisionPosition.y > playerPosition.y){
            veloMapper.get(player).setVelocity(new Vector2( playerVelocity.x, 0f));
        } else if (playerVelocity.y < 0 && collisionPosition.y < playerPosition.y){
            veloMapper.get(player).setVelocity(new Vector2(playerVelocity.x, 0f));
        }

        Gdx.app.log("Listener", "Kontakt");
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }    
}

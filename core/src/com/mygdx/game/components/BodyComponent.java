package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;

public class BodyComponent implements Component{

    public Body playerBody;
    public Body[] worldBodies;

    public BodyComponent(Body body){
        this.playerBody = body;
    }

    public Body getPlayerBody(){
        return playerBody;
    }

    public void setWorldBodies(Body[] worldBodies){
        this.worldBodies = worldBodies;
    }

    public Body getBody(Entity objectEntity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBody'");
    }
    
}

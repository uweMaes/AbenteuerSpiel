package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;


public class VelocityComponent implements Component {
    public Vector2 velocity = new Vector2();
    public boolean bewegung = false;
    public Direction direction = Direction.DOWN;
   
    public VelocityComponent(float x, float y, boolean bewegung) {
        this.velocity.set(x, y);
        this.bewegung = bewegung;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }
}

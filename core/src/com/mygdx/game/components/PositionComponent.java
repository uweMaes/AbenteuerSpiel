package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class PositionComponent implements Component {
    public Vector2 position = new Vector2();
    public float hx, hy;
    public PositionComponent positionComponent;

    public PositionComponent(float x, float y, float hx, float hy) {
        this.position.set(x, y);
        this.hx = hx;
        this.hy = hy;
        Gdx.app.log("PositionComponent", "Created at: " + x + ", " + y);
    }

    public Vector2 getPosition() {
        return position;
    }
    
    public void setPosition(float x, float y) {
        this.position.set(x, y);
        Gdx.app.log("PositionComponent", "Position set to: " + x + ", " + y);
    }
}

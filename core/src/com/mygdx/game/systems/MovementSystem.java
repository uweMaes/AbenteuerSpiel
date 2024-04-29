package com.mygdx.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.mygdx.game.components.PositionComponent;
import com.mygdx.game.components.VelocityComponent;

public class MovementSystem extends IteratingSystem{

    private Engine engine;
    private WorldController worldController;

    public MovementSystem(Engine engine, WorldController worldController) {
        super(Family.all(PositionComponent.class, VelocityComponent.class).get());
        this.engine = engine;
        this.worldController = worldController;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        PositionComponent position = ComponentMapper.getFor(PositionComponent.class).get(entity);
        VelocityComponent velocity = ComponentMapper.getFor(VelocityComponent.class).get(entity);
        
        position.position.x += velocity.velocity.x * deltaTime;
        position.position.y += velocity.velocity.y * deltaTime;
    }
}

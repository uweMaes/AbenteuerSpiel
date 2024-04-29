package com.mygdx.game.systems;


import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.components.PositionComponent;
import com.mygdx.game.components.TextureComponent;
import com.mygdx.game.components.VelocityComponent;

public class RenderingSystem extends IteratingSystem {

    private SpriteBatch batch;
    private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<TextureComponent> tm = ComponentMapper.getFor(TextureComponent.class);
    private ComponentMapper<VelocityComponent> vm = ComponentMapper.getFor(VelocityComponent.class);
    private float stateTime = 0f;

    public RenderingSystem(SpriteBatch batch) {
        // Hier geben wir an, dass dieses System an allen Entitäten interessiert ist,
        // die sowohl eine PositionComponent als auch eine TextureComponent haben
        super(Family.all(PositionComponent.class, TextureComponent.class).get());

        this.batch = batch;
    }

    @Override
    public void update(float deltaTime) {
        stateTime += deltaTime;        
        batch.begin();        
        super.update(deltaTime);        
        batch.end();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = pm.get(entity);
        TextureComponent texture = tm.get(entity);
        VelocityComponent velocity = vm.get(entity);    
 
         // Texturen basierend auf der Bewegungsrichtung aktualisieren
         TextureRegion currentFrame;
         if (velocity.bewegung) {
             switch (velocity.direction) {
                 case LEFT:
                     currentFrame = texture.linksAnimation.getKeyFrame(stateTime, true);
                     break;
                 case RIGHT:
                     currentFrame = texture.rechtsAnimation.getKeyFrame(stateTime, true);
                     break;
                 case UP:
                     currentFrame = texture.wegAnimation.getKeyFrame(stateTime, true);
                     break;
                 case DOWN:
                 default:
                     currentFrame = texture.animation.getKeyFrame(stateTime, true);
                     break;
             }
         } else {
             switch (velocity.direction) {
                 case LEFT:
                     currentFrame = new TextureRegion(texture.regionlinks);
                     break;
                 case RIGHT:
                     currentFrame = new TextureRegion(texture.regionrechts);
                     break;
                 case UP:
                     currentFrame = new TextureRegion(texture.regionweg);
                     break;
                 case DOWN:
                 default:
                     currentFrame = new TextureRegion(texture.region);
                     break;
             }
         }
         batch.draw(currentFrame, position.position.x, position.position.y);
    }
}
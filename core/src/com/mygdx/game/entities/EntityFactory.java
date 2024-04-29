package com.mygdx.game.entities;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.components.PlayerComponent;
import com.mygdx.game.components.TextureComponent;
import com.mygdx.game.components.VelocityComponent;

public class EntityFactory {    
    private Animation<TextureRegion> walkAnimation;
    private Animation<TextureRegion> walkWegAnimation;
    private Animation<TextureRegion> walkLinksgAnimation;
    private Animation<TextureRegion> walkRechtsAnimation;

    public EntityFactory() {
    }

    public Entity createPlayer(Engine engine) {
        Entity player = new Entity();

        TextureAtlas atlas = new TextureAtlas("assets/zuTest.atlas");
        TextureAtlas atlasweg = new TextureAtlas("assets/wegTest.atlas");
        TextureAtlas atlaslinks = new TextureAtlas("assets/linksTest.atlas");
        TextureAtlas atlasrechts = new TextureAtlas("assets/rechtsTest.atlas");

        TextureRegion regionzu = atlas.findRegion("18");
        TextureRegion regionlinks = atlaslinks.findRegion("09");
        TextureRegion regionrechts = atlasrechts.findRegion("27");
        TextureRegion regionweg = atlasweg.findRegion("00");

        walkAnimation = new Animation<>(0.1f, atlas.getRegions(), Animation.PlayMode.LOOP);
        walkWegAnimation = new Animation<>(0.1f, atlasweg.getRegions(), Animation.PlayMode.LOOP);
        walkLinksgAnimation = new Animation<>(0.1f, atlaslinks.getRegions(), Animation.PlayMode.LOOP);
        walkRechtsAnimation = new Animation<>(0.1f, atlasrechts.getRegions(), Animation.PlayMode.LOOP);

        player.add(new VelocityComponent(0, 0, false));
        player.add(new TextureComponent(regionzu, walkAnimation, walkWegAnimation, walkLinksgAnimation, walkRechtsAnimation, regionlinks, regionrechts, regionweg));
        player.add(new PlayerComponent());
        engine.addEntity(player);
               
        return player;
    }
}

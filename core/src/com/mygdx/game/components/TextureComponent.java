package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureComponent implements Component{
    public TextureRegion region = null;
    public TextureRegion regionlinks = null;
    public TextureRegion regionrechts = null;
    public TextureRegion regionweg = null;
    public Animation<TextureRegion> animation = null;
    public Animation<TextureRegion> wegAnimation =null;
    public Animation<TextureRegion> linksAnimation = null;
    public Animation<TextureRegion> rechtsAnimation =null;
    public TextureComponent() {}
    
    public TextureComponent(TextureRegion region, Animation<TextureRegion> animation, Animation wegAnimation, Animation linksAnimation, Animation rechtsAnimation, TextureRegion regionlinks, TextureRegion regionrechts, TextureRegion regionweg) {
        this.region = region;
        this.animation = animation;
        this.wegAnimation = wegAnimation;
        this.linksAnimation = linksAnimation;
        this.rechtsAnimation = rechtsAnimation;
        this.regionlinks = regionlinks;
        this.regionrechts = regionrechts;
        this.regionweg = regionweg;
    }
}

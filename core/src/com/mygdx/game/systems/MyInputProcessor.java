package com.mygdx.game.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Input.Keys;
import com.mygdx.game.components.Direction;
import com.mygdx.game.components.VelocityComponent;

public class MyInputProcessor implements InputProcessor {
    
    private ComponentMapper<VelocityComponent> vm = ComponentMapper.getFor(VelocityComponent.class);
    private Entity player;
    private final float speed = 50f;

    public MyInputProcessor(Entity player) {
        this.player = player;
    }

    @Override
    public boolean keyDown(int keycode) {
        VelocityComponent velocityComponent = vm.get(player);
        velocityComponent.bewegung = true;
       
        switch (keycode) {
            case Keys.LEFT, Keys.A:
                velocityComponent.velocity.x = -speed;
                velocityComponent.direction = Direction.LEFT;
                break;
            case Keys.RIGHT, Keys.D:
                velocityComponent.velocity.x = speed;
                velocityComponent.direction = Direction.RIGHT;
                break;
            case Keys.UP, Keys.W:
                velocityComponent.velocity.y = speed;
                velocityComponent.direction = Direction.UP;
                break;
            case Keys.DOWN, Keys.S:
                velocityComponent.velocity.y = -speed;
                velocityComponent.direction = Direction.DOWN;
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        VelocityComponent velocityComponent = vm.get(player);
    boolean noDirectionKeyPressed = !Gdx.input.isKeyPressed(Keys.LEFT) &&
                                    !Gdx.input.isKeyPressed(Keys.RIGHT) &&
                                    !Gdx.input.isKeyPressed(Keys.UP) &&
                                    !Gdx.input.isKeyPressed(Keys.DOWN) &&
                                    !Gdx.input.isKeyPressed(Keys.A) &&
                                    !Gdx.input.isKeyPressed(Keys.D) &&
                                    !Gdx.input.isKeyPressed(Keys.W) &&
                                    !Gdx.input.isKeyPressed(Keys.S);

    if (noDirectionKeyPressed) {
        velocityComponent.bewegung = false;
        velocityComponent.velocity.set(0, 0);
    } else {
        velocityComponent.velocity.x = 0;
        velocityComponent.velocity.y = 0;
        if (Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A)) {
            velocityComponent.velocity.x = -speed;
        }
        if (Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D)) {
            velocityComponent.velocity.x = speed;
        }
        if (Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.W)) {
            velocityComponent.velocity.y = speed;
        }
        if (Gdx.input.isKeyPressed(Keys.DOWN) || Gdx.input.isKeyPressed(Keys.S)) {
            velocityComponent.velocity.y = -speed;
        }
    }
    return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int x, int y) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }
}

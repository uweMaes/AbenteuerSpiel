package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.GameMain;

public class MainMenuScreen implements Screen {
    final GameMain game;
    private Stage stage;

    public MainMenuScreen(GameMain game) {
        this.game = game;
        stage = new Stage(new ScreenViewport()); // Verwendet den gesamten Bildschirm
        Gdx.input.setInputProcessor(stage); // Akzeptiert Eingaben für die Bühne

        Table table = new Table();
    	table.setFillParent(true); // Lässt den Table den gesamten Bildschirm ausfüllen
    	table.center(); // Zentriert die Inhalte des Tables
		
		Skin skin = new Skin(Gdx.files.internal("uiskin.json")); // Lädt den UI-Skin
        		
		TextButton startButton = new TextButton("Start Game", skin);
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.startGame(); // Wechselt zum Spielbildschirm bei Klick
            }
        });

		table.add(startButton).fillX().uniformX();
		stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Löscht den Bildschirm
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw(); // Zeichnet die Bühne
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() {
        stage.dispose();
    }
}

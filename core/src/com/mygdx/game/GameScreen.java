/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.entity_inheritance.Player;
import com.mygdx.game.systems.RenderSystem;
import com.mygdx.game.systems.WorldSystem;

/**
 *
 * @author koriwizz
 */
public class GameScreen implements Screen {

    PooledEngine engine;
    TheGame game;
    OrthographicCamera camera;
    Viewport viewport;

    public GameScreen(TheGame _game) {
        game = _game;

        engine = new PooledEngine();
        camera = new OrthographicCamera();
        viewport = new FitViewport(300, 250, camera);

        engine.addSystem(new RenderSystem(camera, game.batch));
        engine.addSystem(new WorldSystem());
        
        Entity player = engine.createEntity();
        Player.apply(player);
        engine.addEntity(player);
    }

    @Override
    public void render(float time) {
        engine.update(time);
    }

    @Override
    public void show() {
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }

}

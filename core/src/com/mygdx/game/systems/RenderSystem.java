/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.components.TransformComponent;
import com.mygdx.game.components.VisualComponent;
import com.mygdx.game.utility.Components;

/**
 *
 * @author koriwizz
 */
public class RenderSystem extends EntitySystem {

    ImmutableArray<Entity> entities;
    SpriteBatch batch;
    Camera camera;
    TiledMap tiledMap;
    OrthogonalTiledMapRenderer tiledMapRenderer;

    public RenderSystem(TiledMap _tiledMap, Camera _camera, SpriteBatch _batch) {
        tiledMap = _tiledMap;
        camera = _camera;
        batch = _batch;
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 1f, batch);
        
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(TransformComponent.class, VisualComponent.class).get());
    }

    @Override
    public void removedFromEngine(Engine engine) {
        entities = null;
    }

    @Override
    public void update(float time) {
        Gdx.graphics.getGL20().glClearColor(1, 0, 0, 1);
        Gdx.graphics.getGL20().glClear(GL20.GL_COLOR_BUFFER_BIT);
        TransformComponent transform;
        VisualComponent visual;
        camera.update();

        
        
        tiledMapRenderer.render();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();    
        for (Entity e : entities) {
            transform = Components.transform.get(e);
            visual = Components.visual.get(e);

            batch.draw(visual.texture, transform.position.x, transform.position.y);
        }
        batch.end();
    }

}

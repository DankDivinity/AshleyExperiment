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
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.Components;
import com.mygdx.game.components.TransformComponent;
import com.mygdx.game.components.VisualComponent;

/**
 *
 * @author koriwizz
 */
public class RenderSystem extends EntitySystem {

    ImmutableArray<Entity> entities;
    SpriteBatch batch;
    Camera camera;

    public RenderSystem(Camera _camera, SpriteBatch _batch) {
        camera = _camera;
        batch = _batch;
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
        TransformComponent transform;
        VisualComponent visual;
        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for (Entity e : entities) {
            transform = Components.transform.get(e);
            visual = Components.visual.get(e);

            batch.draw(visual.texture, transform.x, transform.y);
        }
        batch.end();
    }

}

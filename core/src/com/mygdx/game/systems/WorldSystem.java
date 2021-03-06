/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.components.BodyInfoComponent;
import com.mygdx.game.components.MovementComponent;
import com.mygdx.game.components.TransformComponent;
import com.mygdx.game.components.VisualComponent;
import com.mygdx.game.utility.Components;

/**
 *
 * @author koriwizz
 */
public class WorldSystem extends IteratingSystem {

    World world;
    TiledMap tiledMap;
    final float PIXELS_TO_METERS = 100f;

    public WorldSystem(World _world, TiledMap _tiledMap) {
        super(Family.all(TransformComponent.class, MovementComponent.class, VisualComponent.class, BodyInfoComponent.class).get());
        world = _world;
        tiledMap = _tiledMap;
        
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        engine.addEntityListener(getFamily(), new EntityListener() {

            @Override
            public void entityAdded(Entity entity) {

                TransformComponent transform = Components.transform.get(entity);
                VisualComponent visual = Components.visual.get(entity);
                BodyInfoComponent bodyInfo = Components.bodyInfo.get(entity);

                Texture texture = visual.texture;
                bodyInfo.bodyDef = new BodyDef();
                BodyDef bodyDef = bodyInfo.bodyDef;

                bodyDef.type = BodyDef.BodyType.DynamicBody;
                bodyDef.position.set((transform.position.x + texture.getWidth() / 2) / PIXELS_TO_METERS,
                        (transform.position.y + texture.getHeight() / 2) / PIXELS_TO_METERS);

                bodyInfo.body = world.createBody(bodyDef);

                bodyInfo.shape = new PolygonShape();
                PolygonShape shape = bodyInfo.shape;

                shape.setAsBox(texture.getWidth() / 2 / PIXELS_TO_METERS,
                        texture.getHeight() / 2 / PIXELS_TO_METERS);

                bodyInfo.fixtureDef = new FixtureDef();
                FixtureDef fixtureDef = bodyInfo.fixtureDef;
                fixtureDef.shape = shape;
                fixtureDef.density = 0.1f;

                bodyInfo.body.createFixture(fixtureDef);
                shape.dispose();

                System.out.println("heyy");

            }

            @Override
            public void entityRemoved(Entity entity) {

            }
        });
    }

    @Override
    public void update(float time) {
        super.update(time);
        world.step(1f / 160f, 6, 2);

    }

    @Override
    protected void processEntity(Entity entity, float f) {

        TransformComponent transform = Components.transform.get(entity);
        MovementComponent movement = Components.movement.get(entity);
        VisualComponent visual = Components.visual.get(entity);
        Texture texture = visual.texture;
        BodyInfoComponent bodyInfo = Components.bodyInfo.get(entity);
        Body body = bodyInfo.body;
        transform.position.x = (body.getPosition().x * PIXELS_TO_METERS) - texture.getWidth() / 2;
        transform.position.y = (body.getPosition().y * PIXELS_TO_METERS) - texture.getHeight() / 2;

        //should previousVelocity == newVelocity then this would cancel and
        //the body will keeps it current velocity
        //should pv != nv this would take away any velocity added by pv and add the new velocity nv
        //basically: setV(currentV - preV + newV)
        //ex: going from 5v to -5v
        //    setV(5 - 5 + (-5))
        //    v is now -5
        bodyInfo.body.setLinearVelocity(bodyInfo.body.getLinearVelocity().sub(movement.previousVelocity).add(movement.velocity));
    }

}

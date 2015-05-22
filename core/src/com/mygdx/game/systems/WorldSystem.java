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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.OrderedMap;
import com.mygdx.game.components.Components;
import com.mygdx.game.components.MovementComponent;
import com.mygdx.game.components.TransformComponent;
import com.mygdx.game.components.VisualComponent;

/**
 *
 * @author koriwizz
 */
public class WorldSystem extends IteratingSystem {

    static Family family = Family.all(TransformComponent.class, MovementComponent.class, VisualComponent.class).get();
    World world;
    final float PIXELS_TO_METERS = 100f;

    OrderedMap<Entity, Body> bodies;

    public WorldSystem() {
        super(family);
        world = new World(new Vector2(0, -9.8f), true);
        bodies = new OrderedMap<Entity, Body>();

    }

    @Override
    public void addedToEngine(Engine engine) {

        engine.addEntityListener(family, new EntityListener() {

            @Override
            public void entityAdded(Entity entity) {
                Body body;

                TransformComponent transform = Components.transform.get(entity);
                VisualComponent visual = Components.visual.get(entity);
                Texture texture = visual.texture;
                BodyDef bodyDef = new BodyDef();
                bodyDef.type = BodyDef.BodyType.DynamicBody;
                bodyDef.position.set((transform.x + texture.getWidth() / 2) / PIXELS_TO_METERS,
                        (transform.y + texture.getHeight() / 2) / PIXELS_TO_METERS);

                body = world.createBody(bodyDef);
                PolygonShape shape = new PolygonShape();
                shape.setAsBox(texture.getWidth() / 2 / PIXELS_TO_METERS,
                        texture.getHeight() / 2 / PIXELS_TO_METERS);

                FixtureDef fixtureDef = new FixtureDef();
                fixtureDef.shape = shape;
                fixtureDef.density = 0.1f;

                body.createFixture(fixtureDef);
                shape.dispose();

                bodies.put(entity, body);
                System.out.println("heyy");
            }

            @Override
            public void entityRemoved(Entity entity) {
                bodies.remove(entity);
            }
        });
    }

    @Override
    public void update(float time) {
        super.update(time);
        //world.step(1f / 160f, 6, 2);
        
    }

    @Override
    protected void processEntity(Entity entity, float f) {
        Body body = bodies.get(entity);
        if(body == null)
            System.out.println("wrongo");
        TransformComponent transform = Components.transform.get(entity);
        VisualComponent visual = Components.visual.get(entity);
        Texture texture = visual.texture;

        transform.x = (body.getPosition().x * PIXELS_TO_METERS) - texture.getWidth() / 2;
        transform.y = (body.getPosition().y * PIXELS_TO_METERS) - texture.getHeight() / 2;
    }

}

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
import com.mygdx.game.components.BodyInfoComponent;
import com.mygdx.game.components.Components;
import com.mygdx.game.components.MovementComponent;
import com.mygdx.game.components.TransformComponent;
import com.mygdx.game.components.VisualComponent;

/**
 *
 * @author koriwizz
 */
public class WorldSystem extends IteratingSystem {

    static Family family = Family.all(TransformComponent.class, MovementComponent.class, VisualComponent.class, BodyInfoComponent.class).get();
    World world;
    final float PIXELS_TO_METERS = 100f;


    public WorldSystem() {
        super(family);
        world = new World(new Vector2(0, -9.8f), true);

    }

    @Override
    public void addedToEngine(Engine engine) {

        engine.addEntityListener(family, new EntityListener() {

            @Override
            public void entityAdded(Entity entity) {

                TransformComponent transform = Components.transform.get(entity);
                VisualComponent visual = Components.visual.get(entity);
                BodyInfoComponent bodyInfo = Components.bodyInfo.get(entity);

                Texture texture = visual.texture;
                bodyInfo.bodyDef = new BodyDef();
                BodyDef bodyDef = bodyInfo.bodyDef;
                
                bodyDef.type = BodyDef.BodyType.DynamicBody;
                bodyDef.position.set((transform.x + texture.getWidth() / 2) / PIXELS_TO_METERS,
                        (transform.y + texture.getHeight() / 2) / PIXELS_TO_METERS);

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
        //world.step(1f / 160f, 6, 2);

    }

    @Override
    protected void processEntity(Entity entity, float f) {
        
        
        TransformComponent transform = Components.transform.get(entity);
        VisualComponent visual = Components.visual.get(entity);
        Texture texture = visual.texture;
        BodyInfoComponent bodyInfo = Components.bodyInfo.get(entity);
        Body body = bodyInfo.body;

        transform.x = (body.getPosition().x * PIXELS_TO_METERS) - texture.getWidth() / 2;
        transform.y = (body.getPosition().y * PIXELS_TO_METERS) - texture.getHeight() / 2;
    }

}

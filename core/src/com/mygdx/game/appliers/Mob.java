/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.appliers;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.components.BodyInfoComponent;
import com.mygdx.game.components.MobInfoComponent;
import com.mygdx.game.components.MovementComponent;
import com.mygdx.game.components.TransformComponent;
import com.mygdx.game.components.VisualComponent;

/**
 *
 * @author koriwizz
 */
public abstract class Mob extends Applier {
    
    @Override
    public void apply(Entity entity) {
        super.apply(entity);
        entity.add(new MobInfoComponent());
        entity.add(new TransformComponent(new Vector2(0, 0)));
        entity.add(new VisualComponent(null));
        entity.add(new MovementComponent());
        entity.add(new BodyInfoComponent());
    }

}

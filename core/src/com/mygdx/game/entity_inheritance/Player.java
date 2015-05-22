/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.entity_inheritance;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.components.BodyInfoComponent;
import com.mygdx.game.components.MovementComponent;
import com.mygdx.game.components.TransformComponent;
import com.mygdx.game.components.VisualComponent;

/**
 *
 * @author koriwizz
 */
public class Player {

    public static void apply(Entity entity) {
        Texture t = new Texture(Gdx.files.internal("farmer.png"));
        entity.add(new TransformComponent(0, 0));
        entity.add(new VisualComponent(t));
        entity.add(new MovementComponent(0,0));
        entity.add(new BodyInfoComponent());
        //t.dispose();??
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.entity_inheritance;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.components.PlayerInfoComponent;
import com.mygdx.game.components.VisualComponent;

/**
 *
 * @author koriwizz
 */
public class Player extends Mob {

    @Override
    public void apply(Entity entity) {
        super.apply(entity);
        entity.getComponent(VisualComponent.class).texture = new Texture(Gdx.files.internal("farmer.png"));
        entity.add(new PlayerInfoComponent(Keys.UP, Keys.DOWN, Keys.LEFT, Keys.RIGHT));
    }

}

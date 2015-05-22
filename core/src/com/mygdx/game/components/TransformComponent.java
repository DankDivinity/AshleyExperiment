/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool.Poolable;

/**
 *
 * @author koriwizz
 */
public class TransformComponent extends Component implements Poolable {

    public Vector2 position;

    public TransformComponent(Vector2 p) {
        position = p;
    }

    @Override
    public void reset() {
        position= null;
    }

}

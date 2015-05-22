/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool.Poolable;

/**
 *
 * @author koriwizz
 */
public class TransformComponent extends Component implements Poolable {

    public float x = 0f;
    public float y = 0f;

    public TransformComponent(float _x, float _y) {
        x = _x;
        y = _y;
    }

    @Override
    public void reset() {
        x = 0f;
        y = 0f;
    }

}

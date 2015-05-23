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
public class MovementComponent extends Component implements Poolable {

    public Vector2 velocity;
    public Vector2 previousVelocity;
    
    public MovementComponent() {
        velocity = new Vector2(0, 0);
        previousVelocity = new Vector2(velocity);
    }

    @Override
    public void reset() {
        velocity = null;
    }

}

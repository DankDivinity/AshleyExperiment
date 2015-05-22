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
public class MovementComponent extends Component implements Poolable {

    public float velocityX = 0f;
    public float velocityY = 0f;
    
    public MovementComponent(float vx, float vy){
        velocityX = vx;
        velocityY = vy;
    }
    
    @Override
    public void reset() {
        velocityX = 0f;
        velocityY = 0f;
    }

}

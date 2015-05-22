/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Pool.Poolable;

/**
 *
 * @author koriwizz
 */
public class VisualComponent extends Component implements Poolable {

    public Texture texture = null;

    public VisualComponent(Texture t) {
        texture = t;
    }

    @Override
    public void reset() {
        texture = null;
    }

}

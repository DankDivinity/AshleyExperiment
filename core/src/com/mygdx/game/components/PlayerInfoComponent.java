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
public class PlayerInfoComponent extends Component implements Poolable {

    public int up;
    public int down;
    public int left;
    public int right;

    public PlayerInfoComponent(int _up, int _down, int _left, int _right) {
        up = _up;
        down = _down;
        left = _left;
        right = _right;
    }

    @Override
    public void reset() {
        up = 0;
        down = 0;
        left = 0;
        right = 0;
    }

}

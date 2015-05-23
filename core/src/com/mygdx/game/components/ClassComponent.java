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
public class ClassComponent extends Component implements Poolable {

    public Class appliedClass = null;

    @Override
    public void reset() {
        appliedClass = null;
    }

}

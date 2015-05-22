/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Pool.Poolable;

/**
 *
 * @author koriwizz
 */
public class BodyInfoComponent extends Component implements Poolable{
    public Body body;
    public BodyDef bodyDef;
    public PolygonShape shape;
    public FixtureDef fixtureDef;

    @Override
    public void reset() {
        body = null;
        bodyDef = null;
        shape = null;
        fixtureDef = null;
    }
    
}

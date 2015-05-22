/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.components;

import com.badlogic.ashley.core.ComponentMapper;

/**
 *
 * @author koriwizz
 */
public class Components {

    public static ComponentMapper<TransformComponent> transform = ComponentMapper.getFor(TransformComponent.class);
    public static ComponentMapper<VisualComponent> visual = ComponentMapper.getFor(VisualComponent.class);
}

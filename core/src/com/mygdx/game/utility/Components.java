/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.utility;

import com.badlogic.ashley.core.ComponentMapper;
import com.mygdx.game.components.BodyInfoComponent;
import com.mygdx.game.components.MovementComponent;
import com.mygdx.game.components.PlayerInfoComponent;
import com.mygdx.game.components.TransformComponent;
import com.mygdx.game.components.VisualComponent;

/**
 *
 * @author koriwizz
 */
public class Components {

    public static ComponentMapper<TransformComponent> transform = ComponentMapper.getFor(TransformComponent.class);
    public static ComponentMapper<VisualComponent> visual = ComponentMapper.getFor(VisualComponent.class);
    public static ComponentMapper<MovementComponent> movement = ComponentMapper.getFor(MovementComponent.class);
    public static ComponentMapper<BodyInfoComponent> bodyInfo = ComponentMapper.getFor(BodyInfoComponent.class);
    public static ComponentMapper<PlayerInfoComponent> playerInfo = ComponentMapper.getFor(PlayerInfoComponent.class);
}

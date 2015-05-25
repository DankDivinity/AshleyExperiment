/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.appliers;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.mygdx.game.components.ClassComponent;
import com.mygdx.game.utility.Components;

/**
 *
 * @author koriwizz
 */
public abstract class Applier {
    
    public void apply(Entity entity) {
        entity.add(new ClassComponent());
        ClassComponent applierClass = Components.applierClass.get(entity);
        applierClass.appliedClass = getClass();
    }

}

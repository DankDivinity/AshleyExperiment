/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.utility;

import com.badlogic.ashley.core.Entity;
import com.mygdx.game.entity_inheritance.Applier;
import com.mygdx.game.entity_inheritance.Player;

/**
 *
 * @author koriwizz
 */
public class Appliers {

    public static void apply(Entity entity, Class<? extends Applier> applierClass) {
        Applier applier;
        try {
            applier = applierClass.newInstance();
        } catch (InstantiationException ex) {
            return;
        } catch (IllegalAccessException ex) {
            return;
        }
        System.out.println(applier instanceof Player);
        applier.apply(entity);

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.utility;

import com.badlogic.ashley.core.Entity;
import com.mygdx.game.entity_inheritance.Applier;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        applier.apply(entity);
    }

    public static boolean isInstance(Entity entity, Class<?> someClass) {
        try {
            Object obj;
            obj = Components.applierClass.get(entity).appliedClass.newInstance();
            return someClass.isInstance(obj);
        } catch (InstantiationException ex) {
            Logger.getLogger(Applier.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Applier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}

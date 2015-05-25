/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.SnapshotArray;
import com.mygdx.game.components.MobInfoComponent;
import com.mygdx.game.components.MovementComponent;
import com.mygdx.game.components.PlayerInfoComponent;
import com.mygdx.game.utility.Components;

/**
 *
 * @author koriwizz
 */
public class PlayerSystem extends IteratingSystem implements InputProcessor {

    SnapshotArray<Boolean> keys;
    //so if there are a bunch of players input processor wont change keys before
    //the next player
    Boolean[] processKeys;

    public PlayerSystem() {
        super(Family.all(PlayerInfoComponent.class).get());
        keys = new SnapshotArray<Boolean>(new Boolean[128]);
        for (int i = 0; i < keys.size; i++) {
            keys.set(i, Boolean.FALSE);
        }
    }

    @Override
    public void update(float priority) {
        processKeys = keys.begin();
        super.update(priority);
        keys.end();
    }

    @Override
    protected void processEntity(Entity entity, float f) {
        PlayerInfoComponent playerInfo = Components.playerInfo.get(entity);
        MovementComponent movement = Components.movement.get(entity);
        movement.previousVelocity.x = movement.velocity.x;
        movement.previousVelocity.y = movement.velocity.y;

        MobInfoComponent mobInfo = Components.mobInfo.get(entity);
        float dy, dx;
        if (processKeys[playerInfo.up]) {
            dy = mobInfo.speed;
        } else if (processKeys[playerInfo.down]) {
            dy = -mobInfo.speed;
        } else {
            dy = 0;
        }

        if (processKeys[playerInfo.left]) {
            dx = -mobInfo.speed;
        } else if (processKeys[playerInfo.right]) {
            dx = mobInfo.speed;
        } else {
            dx = 0;
        }

        movement.velocity.x = dx;
        movement.velocity.y = dy;

    }

    @Override
    public boolean keyDown(int keycode) {
        keys.set(keycode, Boolean.TRUE);
        System.out.println("pressed");
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        keys.set(keycode, Boolean.FALSE);
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}

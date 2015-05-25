/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.appliers.Player;
import com.mygdx.game.systems.PlayerSystem;
import com.mygdx.game.systems.RenderSystem;
import com.mygdx.game.systems.WorldSystem;
import com.mygdx.game.utility.Appliers;

/**
 *
 * @author koriwizz
 */
public class GameScreen implements Screen {

    PooledEngine engine;
    TheGame game;
    OrthographicCamera camera;
    Viewport viewport;
    World world;
    TiledMap tiledMap;

    public GameScreen(TheGame _game) {
        game = _game;

        engine = new PooledEngine();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 5, 5);
        viewport = new FitViewport(125, 125, camera);

        world = new World(new Vector2(0, 0), true);

        tiledMap = new TiledMap();
        setUpLevel();

        engine.addSystem(new RenderSystem(tiledMap, camera, game.batch));
        engine.addSystem(new WorldSystem(world, tiledMap));
        engine.addSystem(new PlayerSystem());

        InputMultiplexer inputs = new InputMultiplexer();
        inputs.addProcessor(engine.getSystem(PlayerSystem.class));
        Gdx.input.setInputProcessor(inputs);

        Entity player = engine.createEntity();
        Appliers.apply(player, Player.class);
        engine.addEntity(player);
    }

    private void setUpLevel() {
        Texture texture = new Texture(Gdx.files.internal("tiles.png"));
        TextureRegion[][] tileTextures = TextureRegion.split(texture, 32, 32);

        TiledMapTileSet tileSet = new TiledMapTileSet();
        TiledMapTile tile = new StaticTiledMapTile(tileTextures[0][0]);
        tileSet.putTile(0, tile);

        TiledMapTileSets tileSets = tiledMap.getTileSets();
        tileSets.addTileSet(tileSet);

        TiledMapTileLayer tileLayer = new TiledMapTileLayer(10, 10, 32, 32);

        Cell cell = new Cell();
        cell.setTile(tile);

        Cell cellB = new Cell();
        cellB.setTile(tile);
        tileLayer.setCell(0, 1, cell);
        //something wont let me change x values..if i do it doesnt render
        tileLayer.setCell(1, 0, cellB);
        
        tiledMap.getLayers().add(tileLayer);
    }

    @Override
    public void render(float time) {
        engine.update(time);
    }

    @Override
    public void show() {
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }

}

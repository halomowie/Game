package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.math.MathUtils;

class GameScreen implements Screen {

    //temp & testing


    //screen
    private Camera camera;
    private Viewport viewport;

    //graphic

    private SpriteBatch batch;
    private Texture background;



    //world parameters
    private final int WORLD_WIDTH = 1240;
    private final int WORLD_HEIGHT = 640;

    //map
    Map gameMap;





    GameScreen() {

        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

        //background
        background = new Texture("background.jpg");


        batch = new SpriteBatch();

        gameMap = new Map();
    }

    @Override
    public void render(float delta) {
        batch.begin();

        batch.draw(background,0,0,WORLD_WIDTH,WORLD_HEIGHT);

        gameMap.mouseOnMap();
        gameMap.drawMap(batch);

        batch.end();


    }



    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);
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
    public void show() {

    }

    @Override
    public void dispose() {

    }

}

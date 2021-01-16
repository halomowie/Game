package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.math.MathUtils;

class GameScreen implements Screen {

    //temp & testing

    private float countDown;

    private int randomNumber;

    int randomNum;

    //screen
    private Camera camera;
    private Viewport viewport;

    //graphic

    private SpriteBatch batch;
    private Texture background;
    private Texture[][] Tiles;
    //timing

    //world parameters
    private final int WORLD_WIDTH = 600;
    private final int WORLD_HEIGHT = 600;

    GameScreen() {

        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

        //background
        background = new Texture("background.jpg");

        //tiles
        Tiles = new Texture[8][8];
        Tiles[0][0] = new Texture("medievalTile_46.png");
        Tiles[1][0] = new Texture("medievalTile_58.png");

        batch = new SpriteBatch();


    }

    @Override
    public void render(float delta) {
        batch.begin();

        batch.draw(background,0,0,WORLD_WIDTH,WORLD_HEIGHT);

        for(int x = 0; x < 8; x++) {
            for(int y = 0; y < 8; y++) {

                batch.draw(Tiles[1][0], 20 + 70 * x, 20 + 70 * y, 60, 60);

            }
        }

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

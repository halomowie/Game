package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;


class Map extends Game {

    public Map() {
        create();
    }
    //hexagon
    Texture hexagon;
    Texture hexagonRed;

    //map
    private int xSize = 10;
    private int ySize = 19;

    private Sprite[][] Tiles = new Sprite[xSize][ySize];

    private float xHexSize = 60;
    private float yHexSize = xHexSize/2*(float)Math.sqrt(3);







    @Override
    public void create() {
        hexagon = new Texture("hexagonGray.png");
        hexagonRed = new Texture("HexagonRed.png");

        initTextures();
    }

    public void drawGrid(SpriteBatch spriteBatch){
        //gridCalcSprites(spriteBatch);
    }

    void initTextures(){
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                Tiles[x][y] = new Sprite(hexagon, 0, 0, 189, 161);
                Tiles[x][y].setSize(xHexSize, yHexSize);
            }
        }
    }


    void drawMap (SpriteBatch spriteBatch) {
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {

                if (y % 2 == 0) {

                    Tiles[x][y].setPosition(15 + Tiles[x][y].getWidth()/2*3 * x, 20 + Tiles[x][y].getHeight()/2 * y);
                    //Tiles[x][y].setPosition(0,0);

                } else if (y % 2 == 1) {

                    Tiles[x][y].setPosition(15 + Tiles[x][y].getWidth()/2*3 * x + Tiles[x][y].getWidth()/4*3, 20 + Tiles[x][y].getHeight()/2 * y);

                }

                Tiles[x][y].draw(spriteBatch);
                //Tiles[x][y].setBounds(Tiles[x][y].getX()+xHexSize/4,Tiles[x][y].getY(),xHexSize,yHexSize);
            }
        }
    }

    public void mouseOnMap() {
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            //Tiles[1][1].setTexture(hexagonRed);
            for (int x = 0; x < xSize; x++) {
                for (int y = 0; y < ySize; y++) {
                    if (Gdx.input.getX() >= Tiles[x][y].getBoundingRectangle().getX() &&
                            Gdx.input.getX() <= Tiles[x][y].getBoundingRectangle().getX() + Tiles[x][y].getBoundingRectangle().getWidth() &&
                            640-Gdx.input.getY() >= Tiles[x][y].getBoundingRectangle().getY() &&
                            640-Gdx.input.getY() <= Tiles[x][y].getBoundingRectangle().getY() + Tiles[x][y].getBoundingRectangle().getHeight()) {
                        Tiles[x][y].setTexture(hexagonRed);
                    }
                }
            }


        }


    }




}




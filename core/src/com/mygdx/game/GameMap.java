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


class GameMap {

    public GameMap() {
        InitMapObj();
        gameBuildings = new Buildings();
    }

    //hexagon textures
    Texture hexagon;
    Texture hexagonRed;

    //map Size
    private int xSize = 10;
    private int ySize = 19;


    //Finding hex
    float centerX;
    float centerY;
    float circleR;

    //Hex Size
    private float xHexSize = 60;
    private float yHexSize = xHexSize/2*(float)Math.sqrt(3);

    //2d Array of Sprites
    private Sprite[][] Tiles = new Sprite[xSize][ySize];


    //Buildings
    Buildings gameBuildings;


    //initialization of Textures
    public void InitMapObj() {
        hexagon = new Texture("hexagonGray.png");
        hexagonRed = new Texture("HexagonRed.png");

        initTextures();

        //Circle Bounding radious
        circleR=yHexSize/2;
    }

    //Get position(WORLD UNITS) of specific Hex
    public Vector2 getHexPosition(Vector2 hexCords){
        Vector2 Pos;

        float x;
        float y;

        x = Tiles[(int)hexCords.x][(int)hexCords.y].getBoundingRectangle().x;
        y = Tiles[(int)hexCords.x][(int)hexCords.y].getBoundingRectangle().y + Tiles[(int)hexCords.x][(int)hexCords.y].getBoundingRectangle().getHeight()/3;

        Pos = new Vector2(x,y);
        return Pos;
    }



    //initialization of textures
    void initTextures(){
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                Tiles[x][y] = new Sprite(hexagon, 0, 0, 190, 162);
                Tiles[x][y].setSize(xHexSize, yHexSize);
            }
        }
    }


    //Draw whole map with Buildings and Units
    void drawMap (SpriteBatch spriteBatch) {

        drawHexes(spriteBatch);
        gameBuildings.setCastle(new Vector2(0,0),0,spriteBatch, this);
        gameBuildings.setCastle(new Vector2(xSize-1,ySize-1),0,spriteBatch,this);

    }

    //Draw hexes on board
    void drawHexes(SpriteBatch spriteBatch){
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {

                if (y % 2 == 0) {
                    Tiles[x][y].setPosition(15 + Tiles[x][y].getWidth()/2*3 * x, 20 + Tiles[x][y].getHeight()/2 * y);

                } else if (y % 2 == 1) {
                    Tiles[x][y].setPosition(15 + Tiles[x][y].getWidth()/2*3 * x + Tiles[x][y].getWidth()/4*3, 20 + Tiles[x][y].getHeight()/2 * y);

                }
                Tiles[x][y].draw(spriteBatch);
            }
        }
    }


    //Returns Vector with X and Y values to identify specific Hex
    //based on circle bounding inside of a hex
    public Vector2 getHexCord(int mouseX, int mouseY){

        Vector2 hexID;
        mouseY = 640 - mouseY;
        double equa;
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                centerX = Tiles[x][y].getBoundingRectangle().getX() + Tiles[x][y].getBoundingRectangle().getWidth()/2;
                centerY = Tiles[x][y].getBoundingRectangle().getY() + Tiles[x][y].getBoundingRectangle().getHeight()/2;

                equa = Math.sqrt((mouseX-centerX)*(mouseX-centerX) + (mouseY-centerY)*(mouseY-centerY));

                if( equa < circleR){
                    hexID = new Vector2(x,y);
                    return hexID;
                }
            }
        }
       return null;
    }


    //Function for testing
    //Doing actions on specific Hex
    public void doActionOnHex(Vector2 hexCoordinates){
        if(hexCoordinates!=null) {
            Tiles[(int) hexCoordinates.x][(int) hexCoordinates.y].setTexture(hexagonRed);
        }
    }


    //Getters
    public int getxSize() {
        return xSize;
    }

    public int getySize() {
        return ySize;
    }

    public float getCenterX() {
        return centerX;
    }

    public float getxHexSize() {
        return xHexSize;
    }

    public float getyHexSize() {
        return yHexSize;
    }
}




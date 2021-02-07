package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Buildings  {

    public Buildings(){
        castle = new Texture("Castle.png");

        castleSprite = new Sprite(castle, 0,0,190,162);
    }


    Texture castle;
    Texture farm;

    private Sprite castleSprite;
    private Sprite farmSprite;



    void setCastle(Vector2 hexCon,  int team, SpriteBatch spriteBatch, GameMap gameMap){
        castleSprite.setPosition(gameMap.getHexPosition(hexCon).x,gameMap.getHexPosition(hexCon).y);
        castleSprite.setSize(gameMap.getxHexSize(),gameMap.getyHexSize());
        castleSprite.draw(spriteBatch);

    }
}

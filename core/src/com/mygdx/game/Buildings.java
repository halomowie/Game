package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Buildings  {

    public Buildings(GameMap gameMap){
        castle = new Texture("Castle.png");


        hexStatus = new HexStatus(gameMap);
        InitiateTextures();

    }


    Texture castle;
    Texture farm;

    private Sprite[] castleSprite = new Sprite[5];
    private Sprite farmSprite;

    HexStatus hexStatus;



    //drawing buildings
    public void drawBuilding(SpriteBatch spriteBatch){

            castleSprite[1].draw(spriteBatch);
            castleSprite[2].draw(spriteBatch);

    }

    public void InitiateTextures(){
        for(int i = 0; i<4; i++) {
            castleSprite[i] = new Sprite(castle, 0, 0, 190, 162);
        }
    }
    public void setCastle(Vector2 hexCon,  int team,  GameMap gameMap, HexStatus hexStat){
        castleSprite[team].setPosition(gameMap.getHexPosition(hexCon).x,gameMap.getHexPosition(hexCon).y);
        castleSprite[team].setSize(gameMap.getxHexSize(),gameMap.getyHexSize());

        gameMap.setHexColor( hexCon, team);

        hexStat.changeTeamNumber(hexCon,team);
        hexStat.changeIsAreaTaken(hexCon, true);
        hexStat.changeIsBuildUp(hexCon, true);
        hexStat.changeIsOccupiedByUnit(hexCon, false);
        hexStat.changeIsCastle(hexCon, true);

        }

    }


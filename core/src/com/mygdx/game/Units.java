package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Units {


    public Units(GameMap gameMap2){

        xSize = gameMap2.getxHexBoardSize();
        ySize = gameMap2.getyHexBoardSize();

        initArr();
        initTextures();
        setSpriteSize(gameMap2);


    }

    private int teamNumber;
    public int[][] level;
    public boolean[][] isReady;
    public boolean[][] isAlive;

    Texture LVL1;
    Texture LVL2;
    Texture LVL3;

    Texture LVL1sleep;
    Texture LVL2sleep;
    Texture LVL3sleep;

    private Sprite[][] unitsSprite;


    HexStatus hexStatus;

    private int xSize;
    private int ySize;


    public void initTextures(){
        LVL1 = new Texture("LVL1.png");
        LVL2 = new Texture("LVL2.png");
        LVL3 = new Texture("LVL3.png");

        LVL1sleep = new Texture("LVL1sleep.png");
        LVL2sleep = new Texture("LVL2sleep.png");
        LVL3sleep = new Texture("LVL3sleep.png");


        for (int x = 0; x<xSize; x++){
            for (int y = 0; y<ySize; y++){
                unitsSprite[x][y] = new Sprite(LVL1,0,0,190,162);
            }
        }
    }

    public void setSpriteSize(GameMap gameMap){

        for (int x = 0; x<xSize; x++){
            for (int y = 0; y<ySize; y++){
                unitsSprite[x][y].setSize(gameMap.getxHexSize(),gameMap.getxHexSize());
            }
        }
    }

    public void initArr(){
        unitsSprite = new Sprite[xSize][ySize];
        isReady = new boolean[xSize][ySize];
        isAlive = new boolean[xSize][ySize];
        level = new int[xSize][ySize];
    }

    public void assignTextureToUnit(Vector2 hexCor, int level, GameMap gameMap){
        int x = (int)hexCor.x;
        int y = (int)hexCor.y;

        if(level==1) {
            unitsSprite[x][y].setTexture(LVL1);
        }
        else if (level==2){
            unitsSprite[x][y].setTexture(LVL2);
        }
        else if (level==3){
            unitsSprite[x][y].setTexture(LVL3);
        }

        unitsSprite[x][y].setPosition(gameMap.getHexPosition(hexCor).x,gameMap.getHexPosition(hexCor).y);
    }

    public void setUnitSpritePosition(Vector2 hexCor,int unitLVL, GameMap gMap){
        //unitsSprite[(int)hexCor.x][(int)hexCor.y].setTexture();
        //unitsSprite[(int)hexCor.x][(int)hexCor.y].setPosition(gMap.getHexPosition(hexCor).x,gMap.getHexPosition(hexCor).y);
        assignTextureToUnit(hexCor, unitLVL, gMap);
    }

    public void placeUnit(Vector2 hexCor , int teamNum, int lvl, HexStatus hexStatus, GameMap gameMap, Units gUnits, GameInfo gInfo){
        assignTextureToUnit(hexCor,lvl, gameMap);
        int x;
        int y;
        x = (int)hexCor.x;
        y = (int)hexCor.y;


        if(teamNum==hexStatus.getTeamNumber(hexCor) &&
                !hexStatus.getIsBuildUp(hexCor) &&
                !hexStatus.getIsOccupiedByUnit(hexCor) &&
                gInfo.isMoneyEfficient(lvl,teamNum)){
            gUnits.isAlive[x][y] = true;
            gUnits.isReady[x][y] = true;
            gUnits.level[x][y]= lvl;
            hexStatus.changeIsOccupiedByUnit(hexCor,true);
            gInfo.moneyPay(lvl,teamNum,gameMap);

        }
    }

    public void drawUnitsOnBoard(SpriteBatch batch, HexStatus hexstat){
        for (int x = 0; x<xSize; x++){
            for (int y = 0; y<ySize; y++){
                /*
                if(isAlive[x][y]) {
                    unitsSprite[x][y].draw(batch);
                }
                */

                if(isAlive[x][y] && isReady[x][y]) {
                    if(level[x][y]==1){
                        unitsSprite[x][y].setTexture(LVL1);
                    }
                    if(level[x][y]==2){
                        unitsSprite[x][y].setTexture(LVL2);
                    }
                    if(level[x][y]==3){
                        unitsSprite[x][y].setTexture(LVL3);
                    }


                }
                if(isAlive[x][y] && !isReady[x][y]){
                    if(level[x][y]==1){
                        unitsSprite[x][y].setTexture(LVL1sleep);
                    }
                    if(level[x][y]==2){
                        unitsSprite[x][y].setTexture(LVL2sleep);
                    }
                    if(level[x][y]==3){
                        unitsSprite[x][y].setTexture(LVL3sleep);
                    }
                }
                if(hexstat.getIsOccupiedByUnit(new Vector2(x,y))){
                    unitsSprite[x][y].draw(batch);
                }
            }
        }

    }



    public int getLevel(Vector2 hexCor){
        return level[(int)hexCor.x][(int)hexCor.y];
    }

    public boolean getIsAlive(Vector2 hexCor){
        return isAlive[(int)hexCor.x][(int)hexCor.y];
    }

    public boolean getIsReady(Vector2 hexCor){
        return isReady[(int)hexCor.x][(int)hexCor.y];
    }

}

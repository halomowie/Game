package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GameInfo {

    public GameInfo(GameMap gMap){
        hexStatus = new HexStatus(gMap);
        initTextures();
        startingStatus();
        //incomeOfCoins(1,hexStatus,gMap);

    }
    int[] coins = new int[4];
    int[] income = new int[4];
    private int coinsMultiplier = 2;

    Vector2 sideBarStart;

    private BitmapFont font;

    HexStatus hexStatus;

    public void initTextures(){
        font = new BitmapFont();
        font.setColor(Color.BLACK);
    }

    public void drawFont(SpriteBatch batch, int value){
        font.draw(batch, String.valueOf(coins[2]), 1000,600);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(3);
    }

    public void startingStatus(){
        for(int i=1; i<3; i++ ){
            coins[i]=10;
        }
    }

    public void incomeOfCoins(int teamNum, HexStatus hexStat, GameMap gMap){
        income[teamNum] = hexStat.getNumOfClaimedHexes(teamNum,gMap) * coinsMultiplier;
    }

    public int getCoinsValue(int teamNum){
        return coins[teamNum];
    }


}

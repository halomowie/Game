package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;

public class GameInfo {

    public GameInfo(GameMap gMap){
        unitPrice[1] = 10;
        unitPrice[2] = 20;
        unitPrice[3] = 40;

        hexStatus = new HexStatus(gMap);

        initTextures();
        startingStatus();
        initSprites();
        shopSprites();


    }
    int[] coins = new int[4];
    int[] income = new int[4];
    private int coinsMultiplier = 2;
    int xPosEndTurn = 950;
    int yPosEndTurn = 100;

    Vector2 sideBarStart;

    //private BitmapFont font;

    private FreeTypeFontGenerator fontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
    private FreeTypeFontGenerator.FreeTypeFontParameter teamFontParameter;
    private BitmapFont coinsStatusText;
    private BitmapFont endTurnText;
    private BitmapFont currentTeamText;

    private Sprite[] unitsShop;
    private Sprite farm;

    Texture farmTxt;
    Texture LVL1Txt;
    Texture LVL2Txt;
    Texture LVL3Txt;

    private int[] unitPrice = new int[4];
    private int farmPrice;



    HexStatus hexStatus;

    public void initTextures(){
        coinsStatusText = new BitmapFont();
        endTurnText = new BitmapFont();
        currentTeamText = new BitmapFont();

        coinsStatusText.setColor(Color.BLACK);
        endTurnText.setColor(Color.BLACK);



        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Pixeled.ttf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        teamFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        teamFontParameter.size = 25;
        fontParameter.size = 20;

        teamFontParameter.borderWidth = 4;
        fontParameter.borderWidth = 4;

        teamFontParameter.borderColor = Color.BLACK;
        fontParameter.borderColor = Color.BLACK;


        teamFontParameter.color = Color.WHITE;
        fontParameter.color = Color.WHITE;


        coinsStatusText = fontGenerator.generateFont(fontParameter);
        endTurnText = fontGenerator.generateFont(fontParameter);
        currentTeamText = fontGenerator.generateFont(teamFontParameter);

        fontGenerator.dispose();

    }

    public void drawFont(SpriteBatch batch, int team){
        coinsStatusText.draw(batch,"COINS: "+coins[team] +"\nINCOME: +"+income[team], 950, 530 );

        endTurnText.draw(batch,"END TURN",xPosEndTurn,yPosEndTurn);


        currentTeamText.draw(batch,setCurrentTeamTextColor(team) +" TURN",950,600);
    }

    public void initSprites(){
        farmTxt = new Texture("Farm.png");
        LVL1Txt = new Texture("LVL1.png");
        LVL2Txt = new Texture("LVL2.png");
        LVL3Txt = new Texture("LVL3.png");

        unitsShop = new Sprite[4];
        farm = new Sprite(farmTxt,0,0,190,162);
        for(int i=1; i<4; i++){
            if(i==1) {
                unitsShop[i] = new Sprite(LVL1Txt, 0, 0, 190, 162);
            }
            else if(i==2){
                unitsShop[i] = new Sprite(LVL2Txt, 0, 0, 190, 162);
            }
            else if(i==3){
                unitsShop[i] = new Sprite(LVL3Txt, 0, 0, 190, 162);
            }
        }
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

    public void updateCoinsValue(int teamNum){
        coins[teamNum] = coins[teamNum] + income[teamNum];
    }

    public String setCurrentTeamTextColor(int team){
        String text;
        text = "";
        if(team==1) {
            text = "RED";
        }
        else if(team==2){
           text = "BLUE";
        }
        return text;
    }

    public void shopSprites(){
        int xPos = 950;
        int yPos = 120;
        for(int i=1; i<4; i++){
            unitsShop[i].setSize(120,102);
            unitsShop[i].setPosition(xPos,yPos+102*(i-1)+10);
        }
    }

    public void drawShop(SpriteBatch batch){
        for(int i=1; i<4; i++){
            unitsShop[i].draw(batch);
        }
    }

    public void shopButtons(int xMouse, int yMouse, GameMap gMap){
        yMouse = 640 - yMouse;
        for(int i=1; i<4; i++) {
            if (unitsShop[i].getBoundingRectangle().getX() < xMouse &&
                    unitsShop[i].getBoundingRectangle().getX() + unitsShop[i].getWidth() > xMouse &&
                    unitsShop[i].getBoundingRectangle().getY() < yMouse &&
                    unitsShop[i].getBoundingRectangle().getY() + unitsShop[i].getHeight() > yMouse) {

                gMap.isBuying = true;

                if(i==1){
                    gMap.boughtUnitLvl = 1;
                }
                else if(i==2){
                    gMap.boughtUnitLvl = 2;
                }
                else if(i==3){
                    gMap.boughtUnitLvl = 3;
                }
            }
        }
    }

    public void moneyPay(int boughtUnitLevel, int playerTeam,GameMap gameMap){

            if (boughtUnitLevel == 1 && coins[playerTeam] - 10 >= 0) {
                coins[playerTeam] = coins[playerTeam] - 10;
            }
            else if (boughtUnitLevel == 2 && coins[playerTeam] - 20 >= 0) {
                coins[playerTeam] = coins[playerTeam] - 20;
            }
            else if (boughtUnitLevel == 3 && coins[playerTeam] - 40 >= 0) {
                coins[playerTeam] = coins[playerTeam] - 40;
            }
    }

    public boolean isMoneyEfficient(int boughtUnitLevel, int playerTeam){
        if (boughtUnitLevel == 1 && coins[playerTeam] - 10 >= 0) {
            return true;
        }
        else if (boughtUnitLevel == 2 && coins[playerTeam] - 20 >= 0) {
            return true;
        }
        else if (boughtUnitLevel == 3 && coins[playerTeam] - 40 >= 0) {
            return true;
        }
        return false;
    }

    public void buttonEndTurn(int xMouse, int yMouse, GameMap gMap){
        yMouse=640-yMouse;
        if(xMouse>xPosEndTurn &&
                xMouse<xPosEndTurn+180 &&
                yMouse<yPosEndTurn &&
                yMouse>yPosEndTurn-30){
            gMap.turnEnd(gMap.playerTeam);
            if(gMap.playerTeam==1){
                gMap.playerTeam=2;

            }
            else if(gMap.playerTeam==2){
                gMap.playerTeam=1;
            }
        }
    }

}

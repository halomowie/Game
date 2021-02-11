package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;


class GameMap {

    public GameMap() {
        InitMapObj();
        gameBuildings = new Buildings(this);
        hexStatus = new HexStatus(this);
        gameUnits = new Units(this);
        gInfo = new GameInfo(this);


        Map1(hexStatus);


        //claimOneHex(new Vector2(1,7),2,hexStatus);
        //gameUnits.placeUnit(new Vector2(1,7),1,1,hexStatus,this,gameUnits);

        //startRegionClaims(hexStatus);

        coinsUpdate(2);


        //hexStatus.getNumOfClaimedHexes(1,this);
    }

    //hexagon textures
    Texture hexagon;
    Texture hexagonRed;
    Texture hexagonBlue;

    //map Size
    final private int xHexBoardSize = 10;
    final private int yHexBoardSize = 19;


    //Finding hex
    float centerX;
    float centerY;
    float circleR;

    //Hex Size
    final private float xHexSize = 60;
    final private float yHexSize = xHexSize/2*(float)Math.sqrt(3);

    //2d Array of Sprites
    private Sprite[][] Tiles = new Sprite[xHexBoardSize][yHexBoardSize];



    public int playerTeam = 1;
    public boolean isBuying;
    public boolean isPlaced;
    public boolean isPaid;

    public boolean isUnitSelected;
    public Vector2 previousUnitLocation;
    public int boughtUnitLvl;



    Buildings gameBuildings;

    HexStatus hexStatus;

    Units gameUnits;

    GameInfo gInfo;

    public void placeBoughtUnit(Vector2 hexCord){
        gameUnits.placeUnit(hexCord, playerTeam, boughtUnitLvl, hexStatus, this, gameUnits, gInfo);

        isBuying=false;
    }

    public void selectUnit(Vector2 hexCord, int playerTeam){

    }


    public void buttons(int xMouse, int yMouse, GameMap gMap){
        //turnEnd(playerTeam);
        gInfo.buttonEndTurn(xMouse,yMouse,gMap);
        gInfo.shopButtons(xMouse,yMouse,gMap);
    }

    public void coinsUpdate(int numberOfTeams){
        for(int x=1; x<numberOfTeams+1; x++){
            gInfo.incomeOfCoins(x,hexStatus,this);
            gInfo.getCoinsValue(x);
        }
    }

    public void turnEnd(int playerTeam){
        gInfo.updateCoinsValue(playerTeam);
    }


    //initialization of Textures and Hexes Position
    public void InitMapObj() {
        hexagon = new Texture("hexagonGray.png");
        hexagonRed = new Texture("HexagonRed.png");
        hexagonBlue = new Texture("HexagonBlue.png");

        initTextures();
        setHexesPosition();

        //Circle Bounding radious
        circleR=yHexSize/2;
    }

    //Claims single hex by his Coordinates
    //Changes Color of Hex
    //Changes HexStatus:
    // - Area Taken
    // - Team Number
    public void claimOneHex(Vector2 hexCor, int team, HexStatus hexStat){

        if(!hexStat.getIsDisabled(hexCor)) {
            if (team == 1) {
                Tiles[(int) hexCor.x][(int) hexCor.y].setTexture(hexagonRed);
            } else if (team == 2) {
                Tiles[(int) hexCor.x][(int) hexCor.y].setTexture(hexagonBlue);
            }

            hexStat.changeIsAreaTaken(hexCor, true);
            hexStat.changeTeamNumber(hexCor, team);
            coinsUpdate(2);
        }
    }





    public void createCastleWithStartRegion(Vector2 castleCords, int teamNumber, HexStatus hexStat, GameMap gMap){
        gameBuildings.setCastle(castleCords,teamNumber,gMap,hexStat);
        claimStartingHexes(castleCords,teamNumber,hexStat);
    }


    public void claimStartingHexes(Vector2 castleCords, int teamNumber, HexStatus hexStat){
        int x = (int)castleCords.x;
        int y = (int)castleCords.y;

        if(y%2==0) {
            //Hex Upper
            if (y + 2 < yHexBoardSize) {
                claimOneHex(new Vector2(x, y + 2), teamNumber, hexStat);
            }
            //Hex Upper Right
            if (y + 1 < yHexBoardSize) {
                claimOneHex(new Vector2(x, y + 1), teamNumber, hexStat);
            }
            //Hex Bottom Right
            if (y - 1 >= 0) {
                claimOneHex(new Vector2(x, y - 1), teamNumber, hexStat);
            }
            //Hex Bottom
            if (y - 2 >= 0) {
                claimOneHex(new Vector2(x, y - 2), teamNumber, hexStat);
            }
            //Hex Bottom Left
            if (x - 1 >= 0 && y - 1 >= 0) {
                claimOneHex(new Vector2(x - 1, y - 1), teamNumber, hexStat);
            }
            //Hex Upper Left
            if (x - 1 >= 0 && y + 1 < yHexBoardSize) {
                claimOneHex(new Vector2(x - 1, y + 1), teamNumber, hexStat);
            }
        }
        else if(y%2==1){
            //Hex Upper
            if (y + 2 < yHexBoardSize) {
                claimOneHex(new Vector2(x, y + 2), teamNumber, hexStat);
            }
            //Hex Upper Left
            if (y + 1 < yHexBoardSize) {
                claimOneHex(new Vector2(x, y + 1), teamNumber, hexStat);
            }
            //Hex Bottom Left
            if (y - 1 >= 0) {
                claimOneHex(new Vector2(x, y - 1), teamNumber, hexStat);
            }
            //Hex Bottom
            if (y - 2 >= 0) {
                claimOneHex(new Vector2(x, y - 2), teamNumber, hexStat);
            }
            //Hex Bottom Right
            if (x + 1 < xHexBoardSize && y - 1 >= 0) {
                claimOneHex(new Vector2(x + 1, y - 1), teamNumber, hexStat);
            }
            //Hex Upper Right
            if (x + 1 < xHexBoardSize && y + 1 < yHexBoardSize) {
                claimOneHex(new Vector2(x + 1, y + 1), teamNumber, hexStat);
            }
        }
    }



    //Dropped from use!
    //Claims all hexes that surround castle
    public void startRegionClaims(HexStatus hexStat){
        int xCor, yCor;

        for (int x = 0; x < xHexBoardSize; x++) {
            for (int y = 0; y < yHexBoardSize; y++) {
                if(hexStat.getIsCastle(new Vector2(x,y))) {
                    for(int teamNumber=1; teamNumber<=2; teamNumber++){
                    if(hexStat.getTeamNumber(new Vector2(x,y))==teamNumber) {
                        if(y%2==0) {
                            //Hex Upper
                            if (y + 2 < yHexBoardSize) {
                                claimOneHex(new Vector2(x, y + 2), teamNumber, hexStat);
                            }
                            //Hex Upper Right
                            if (y + 1 < yHexBoardSize) {
                                claimOneHex(new Vector2(x, y + 1), teamNumber, hexStat);
                            }
                            //Hex Bottom Right
                            if (y - 1 >= 0) {
                                claimOneHex(new Vector2(x, y - 1), teamNumber, hexStat);
                            }
                            //Hex Bottom
                            if (y - 2 >= 0) {
                                claimOneHex(new Vector2(x, y - 2), teamNumber, hexStat);
                            }
                            //Hex Bottom Left
                            if (x - 1 >= 0 && y - 1 >= 0) {
                                claimOneHex(new Vector2(x - 1, y - 1), teamNumber, hexStat);
                            }
                            //Hex Upper Left
                            if (x - 1 >= 0 && y + 1 < yHexBoardSize) {
                                claimOneHex(new Vector2(x - 1, y + 1), teamNumber, hexStat);
                            }
                        }
                        else if(y%2==1){
                            //Hex Upper
                            if (y + 2 < yHexBoardSize) {
                                claimOneHex(new Vector2(x, y + 2), teamNumber, hexStat);
                            }
                            //Hex Upper Left
                            if (y + 1 < yHexBoardSize) {
                                claimOneHex(new Vector2(x, y + 1), teamNumber, hexStat);
                            }
                            //Hex Bottom Left
                            if (y - 1 >= 0) {
                                claimOneHex(new Vector2(x, y - 1), teamNumber, hexStat);
                            }
                            //Hex Bottom
                            if (y - 2 >= 0) {
                                claimOneHex(new Vector2(x, y - 2), teamNumber, hexStat);
                            }
                            //Hex Bottom Right
                            if (x + 1 < xHexBoardSize && y - 1 >= 0) {
                                claimOneHex(new Vector2(x + 1, y - 1), teamNumber, hexStat);
                            }
                            //Hex Upper Right
                            if (x + 1 < xHexBoardSize && y + 1 < yHexBoardSize) {
                                claimOneHex(new Vector2(x + 1, y + 1), teamNumber, hexStat);
                            }
                        }
                        }
                    }
                }
            }
        }
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
        for (int x = 0; x < xHexBoardSize; x++) {
            for (int y = 0; y < yHexBoardSize; y++) {
                Tiles[x][y] = new Sprite(hexagon, 0, 0, 190, 162);
                Tiles[x][y].setSize(xHexSize, yHexSize);
            }
        }
    }


    //Draw whole map with Buildings and Units
    void drawMap (SpriteBatch spriteBatch) {
        drawHexes(spriteBatch);
        gameBuildings.drawBuilding(spriteBatch);
        gameUnits.drawUnitsOnBoard(spriteBatch);
        gInfo.drawFont(spriteBatch,playerTeam);
        gInfo.drawShop(spriteBatch);
    }


    //Set hexes position
    void setHexesPosition(){
        for (int x = 0; x < xHexBoardSize; x++) {
            for (int y = 0; y < yHexBoardSize; y++) {

                if (y % 2 == 0) {
                    Tiles[x][y].setPosition(15 + Tiles[x][y].getWidth()/2*3 * x, 20 + Tiles[x][y].getHeight()/2 * y);
                }
                else if (y % 2 == 1) {
                    Tiles[x][y].setPosition(15 + Tiles[x][y].getWidth()/2*3 * x + Tiles[x][y].getWidth()/4*3, 20 + Tiles[x][y].getHeight()/2 * y);
                }
            }
        }
    }

    //Draw hexes on board
    void drawHexes(SpriteBatch spriteBatch){
        for (int x = 0; x < xHexBoardSize; x++) {
            for (int y = 0; y < yHexBoardSize; y++) {
                if(!hexStatus.getIsDisabled(new Vector2(x,y))) {
                    Tiles[x][y].draw(spriteBatch);
                }
            }
        }
    }


    //Returns Vector with X and Y values to identify specific Hex
    //based on circle bounding inside of a hex
    public Vector2 getHexCord(int mouseX, int mouseY){

        Vector2 hexID;
        mouseY = 640 - mouseY;
        double equa;
        for (int x = 0; x < xHexBoardSize; x++) {
            for (int y = 0; y < yHexBoardSize; y++) {
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
            //Tiles[(int) hexCoordinates.x][(int) hexCoordinates.y].setTexture(hexagonBlue);
            //claimOneHex(hexCoordinates,playerTeam,hexStatus);
            //hexStatus.getHexFullInfo(hexCoordinates,gameUnits);

            if(checkIfHasUnitOfCurrentPlayer(hexCoordinates)){
                previousUnitLocation = hexCoordinates;
                isUnitSelected=true;
            }
        }
    }

    public void moveUnit(Vector2 hexCords){

    }

    public boolean checkIfHexesAreNeighbours(Vector2 hex1Cords, Vector2 hex2Cords){

        int x1 = (int)hex1Cords.x;
        int y1 = (int)hex1Cords.y;

        int x2 = (int)hex2Cords.x;
        int y2 = (int)hex1Cords.y;

        if(y1%2==0){
            if(y1+1==y2 && x1==x2){
                return true;
            }
        }

        return false;
    }

    public boolean checkIfHasUnitOfCurrentPlayer(Vector2 hexCord){
        return hexStatus.getIsOccupiedByUnit(hexCord) && hexStatus.getTeamNumber(hexCord) == playerTeam;
    }

    public void setHexColor(Vector2 hexCoordinates, int team){
        if(team == 1) {
            Tiles[(int) hexCoordinates.x][(int) hexCoordinates.y].setTexture(hexagonRed);
        }
        else if(team == 2){
            Tiles[(int) hexCoordinates.x][(int) hexCoordinates.y].setTexture(hexagonBlue);
        }
    }


    public void Map1(HexStatus hexStat){
        createCastleWithStartRegion(new Vector2(1,4),1,hexStat,this);
        createCastleWithStartRegion(new Vector2(8,15),2,hexStat,this);


        hexStat.setIsDisabled(new Vector2(5,9));
        hexStat.setIsDisabled(new Vector2(5,11));
        hexStat.setIsDisabled(new Vector2(5,10));
        hexStat.setIsDisabled(new Vector2(5,8));
        hexStat.setIsDisabled(new Vector2(0,18));
        hexStat.setIsDisabled(new Vector2(0,17));
        hexStat.setIsDisabled(new Vector2(0,16));
        hexStat.setIsDisabled(new Vector2(9,9));
        hexStat.setIsDisabled(new Vector2(9,7));
        hexStat.setIsDisabled(new Vector2(9,8));
        hexStat.setIsDisabled(new Vector2(9,6));
        hexStat.setIsDisabled(new Vector2(9,5));
        hexStat.setIsDisabled(new Vector2(9,4));
        hexStat.setIsDisabled(new Vector2(1,7));
        hexStat.setIsDisabled(new Vector2(7,13));
        hexStat.setIsDisabled(new Vector2(8,12));
        hexStat.setIsDisabled(new Vector2(2,4));
        hexStat.setIsDisabled(new Vector2(3,17));
        hexStat.setIsDisabled(new Vector2(4,16));
        hexStat.setIsDisabled(new Vector2(4,18));
        hexStat.setIsDisabled(new Vector2(4,17));
        hexStat.setIsDisabled(new Vector2(3,12));
        hexStat.setIsDisabled(new Vector2(3,11));
        hexStat.setIsDisabled(new Vector2(6,3));
        hexStat.setIsDisabled(new Vector2(7,4));
        hexStat.setIsDisabled(new Vector2(0,12));
        hexStat.setIsDisabled(new Vector2(0,11));
        hexStat.setIsDisabled(new Vector2(0,10));
        hexStat.setIsDisabled(new Vector2(3,1));
        hexStat.setIsDisabled(new Vector2(4,2));
        hexStat.setIsDisabled(new Vector2(4,1));
        hexStat.setIsDisabled(new Vector2(4,0));
        hexStat.setIsDisabled(new Vector2(2,6));
    }


    public int getxHexBoardSize() {
        return xHexBoardSize;
    }

    public int getyHexBoardSize() {
        return yHexBoardSize;
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




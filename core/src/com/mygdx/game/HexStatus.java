package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class HexStatus {


    public HexStatus(GameMap gameMap) {
        gameUnits = new Units(gameMap);

        int firstArraySize = gameMap.getxHexBoardSize();
        int secondArraySize = gameMap.getyHexBoardSize();
        TeamNumber = new int[firstArraySize][secondArraySize];
        isAreaTaken = new boolean[firstArraySize][secondArraySize];
        isOccupiedByUnit = new boolean[firstArraySize][secondArraySize];
        isBuildUp = new boolean[firstArraySize][secondArraySize];
        isCastle = new boolean[firstArraySize][secondArraySize];
        isDisabled = new boolean[firstArraySize][secondArraySize];

        //getIsCastle(new Vector2(0, 0));


    }

    private boolean[][] isAreaTaken;
    private boolean[][] isOccupiedByUnit;
    private boolean[][] isBuildUp;
    private boolean[][] isCastle;
    private boolean[][] isDisabled;
    private int[][] TeamNumber;

    private GameMap gameMap;
    Units gameUnits;


    public void changeIsAreaTaken(Vector2 hexCords, boolean status) {
        isAreaTaken[(int) hexCords.x][(int) hexCords.y] = status;
    }

    public void changeIsOccupiedByUnit(Vector2 hexCords, boolean status) {
        isOccupiedByUnit[(int) hexCords.x][(int) hexCords.y] = status;
    }

    public void changeIsBuildUp(Vector2 hexCords, boolean status) {
        isBuildUp[(int) hexCords.x][(int) hexCords.y] = status;
    }

    public void changeTeamNumber(Vector2 hexCords, int teamNumber) {
        TeamNumber[(int) hexCords.x][(int) hexCords.y] = teamNumber;
    }

    public void changeIsCastle(Vector2 hexCords, boolean status) {
        isCastle[(int) hexCords.x][(int) hexCords.y] = status;
    }


    public boolean getIsCastle(Vector2 hexCords) {
        return isCastle[(int) hexCords.x][(int) hexCords.y];
    }

    public boolean getIsAreaTaken(Vector2 hexCords) {
        return isAreaTaken[(int) hexCords.x][(int) hexCords.y];
    }

    public boolean getIsOccupiedByUnit(Vector2 hexCords) {
        return isOccupiedByUnit[(int) hexCords.x][(int) hexCords.y];
    }

    public boolean getIsBuildUp(Vector2 hexCords) {
        return isBuildUp[(int) hexCords.x][(int) hexCords.y];
    }

    public int getTeamNumber(Vector2 hexCords) {
        return TeamNumber[(int) hexCords.x][(int) hexCords.y];
    }

    public boolean getIsDisabled(Vector2 hexCords) {
        return isDisabled[(int) hexCords.x][(int) hexCords.y];
    }

    public void getHexFullInfo(Vector2 hexCor, Units gUnits) {
        System.out.printf("Info about hex [%d,%d] \n", (int) hexCor.x, (int) hexCor.y);
        System.out.printf("Is build up? %b \n", getIsBuildUp(hexCor));
        System.out.printf("Is Occupied by unit? %b\n", getIsOccupiedByUnit(hexCor));
        System.out.printf("Team number %d \n", getTeamNumber(hexCor));
        System.out.printf("Is this a Castle? %b \n", getIsCastle(hexCor));
        System.out.printf("Is area taken? %b \n\n", getIsAreaTaken(hexCor));
        if (getIsOccupiedByUnit(hexCor)) {
            System.out.printf("Unit Level: %d \n", gUnits.getLevel(hexCor));
            System.out.printf("Is Unit Alive? %b \n", gUnits.getIsAlive(hexCor));
            System.out.printf("Is Unit Ready? %b \n\n", gUnits.getIsReady(hexCor));
        }
    }

    public int getNumOfClaimedHexes(int teamNum, GameMap gmMap) {
        int claimedAreas;
        claimedAreas = 0;
        for (int x = 0; x < gmMap.getxHexBoardSize(); x++) {
            for (int y = 0; y < gmMap.getyHexBoardSize(); y++) {
                if(isAreaTaken[x][y] && TeamNumber[x][y]==teamNum){
                    claimedAreas++;
                }
            }
        }
        return claimedAreas;
    }

    public void setIsDisabled(Vector2 hexCor){
        isDisabled[(int)hexCor.x][(int)hexCor.y]=true;
    }


}

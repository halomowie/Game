package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class HexStatus {


    public HexStatus(GameMap gameMap){
        int firstArraySize = gameMap.getxSize();
        int secondArraySize = gameMap.getySize();
        TeamNumber = new int[firstArraySize][secondArraySize];
        isAreaTaken = new boolean[firstArraySize][secondArraySize];
        isOccupiedByUnit = new boolean[firstArraySize][secondArraySize];
        isBuildUp = new boolean[firstArraySize][secondArraySize];
        isCastle = new boolean[firstArraySize][secondArraySize];

        getIsCastle(new Vector2(0,0));
    }

    private boolean[][] isAreaTaken;
    private boolean[][] isOccupiedByUnit;
    private boolean[][] isBuildUp;
    private boolean[][] isCastle;
    private int[][] TeamNumber;

    private GameMap gameMap;


    public void changeIsAreaTaken(Vector2 hexCords, boolean status){
        isAreaTaken[(int) hexCords.x][(int) hexCords.y] = status;
    }

    public void changeIsOccupiedByUnit(Vector2 hexCords, boolean status){
        isOccupiedByUnit[(int) hexCords.x][(int) hexCords.y] = status;
    }

    public void changeIsBuildUp(Vector2 hexCords, boolean status){
        isBuildUp[(int) hexCords.x][(int) hexCords.y] = status;
    }

    public void changeTeamNumber(Vector2 hexCords, int teamNumber){
        TeamNumber[(int) hexCords.x][(int) hexCords.y] = teamNumber;
    }

    public void  changeIsCastle(Vector2 hexCords, boolean status){
        isCastle[(int) hexCords.x][(int) hexCords.y] = status;
    }



    public boolean getIsAreaTaken(){
        return true;
    }

    public boolean getIsCastle(Vector2 hexCords) {
        return isCastle[(int)hexCords.x][(int)hexCords.y];
    }

    public boolean getIsAreaTaken(Vector2 hexCords) {
        return isAreaTaken[(int)hexCords.x][(int)hexCords.y];
    }

    public boolean getIsOccupiedByUnit(Vector2 hexCords) {
        return isOccupiedByUnit[(int)hexCords.x][(int)hexCords.y];
    }

    public boolean getIsBuildUp(Vector2 hexCords) {
        return isBuildUp[(int)hexCords.x][(int)hexCords.y];
    }

    public int getTeamNumber(Vector2 hexCords){
        return TeamNumber[(int)hexCords.x][(int)hexCords.y];
    }


}

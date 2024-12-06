package methods;

import world.Tile;

public class AStar {

    private Tile tile;
    private int sumG = 0;
    private double h = 0;
    private AStar parent = null;

    public AStar (Tile cell, int sumG, double h, AStar parent) {
        this.tile = cell;
        this.sumG = sumG;
        this.h = h;
        this.parent = parent;
    }

    public double getF() {
        return sumG+h;
    }

    public AStar getParent() {
        return parent;
    }

    public int getSumG() {
        return sumG;
    }

    public double getH() {
        return h;
    }

    public Tile getTile() {
        return tile;
    }



}

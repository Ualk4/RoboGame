package entities;

import methods.AStarSearch;
import program.Control;
import world.Path;
import world.Tile;

import java.util.ArrayList;

public class Enemy extends Entity implements Runnable {

    boolean movedToFoodTile = false;
    public Enemy() {
        this.setPngName("images/enemy.png");
        this.type = "Enemy";
    }
    public Enemy(int pos_x, int pos_y) {
        this.setPngName("images/enemy.png");
        this.cords.setX(pos_x);
        this.cords.setY(pos_y);
        this.type = "Enemy";
    }

    public void eat() {
        Control.map.removeItem(this.item);
        Control.map.addItem_Eaten(this.item);
        Control.map.getTiles()[this.cords.getX()][this.cords.getY()].removeItem(this.item);
        this.item = null;
        Control.setCurrentState(Control.getCurrentCharacterNumber() + Control.getCurrentCharacter() + " eaten food at: " + this.cords.getX() + " x " + this.cords.getY());
        Control.logger.addToLog(Control.getCurrentState());
    }

    @Override
    public void run() {
        this.setPngName("images/enemy.png");
        Control.mapPanel.repaint();
        AStarSearch AStar = new AStarSearch();
        ArrayList<Tile> otherEnemies_Aim = new ArrayList<>();
        if (Control.map.isThereFood()) {
            if (getClosestFoodTileForEnemy() != null) {
                Path pathToTile = AStar.search(getClosestFoodTileForEnemy(), Control.map.getTiles()[this.cords.getX()][this.cords.getY()], -1);
                if (this.getAim() == null) {
                    pathToTile = AStar.search(getClosestFoodTileForEnemy(), Control.map.getTiles()[this.cords.getX()][this.cords.getY()], -1);
                    for (Enemy otherEnemy : Control.map.getEnemyCharacters()) {
                        if ((!otherEnemy.equals(this)) && (otherEnemy.getAim() != null)) {
                            if (otherEnemy.getAim() == (pathToTile.getLastTile())) {
                                otherEnemies_Aim.add(otherEnemy.getAim());
                            }
                        }
                    }
                    if (getClosestFoodTileForEnemyWithout(otherEnemies_Aim) != null) {
                        pathToTile = AStar.search(getClosestFoodTileForEnemyWithout(otherEnemies_Aim), Control.map.getTiles()[this.cords.getX()][this.cords.getY()], -1);
                    }
                }
                if (pathToTile != null) {
                    this.setAim(pathToTile.getLastTile());
                    if (this.item != null) {
                        this.eat();
                        this.setAim(null);
                    } else if (this.movedToFoodTile) {
                        this.pickUp();
                        this.movedToFoodTile = false;
                    } else if ((Control.map.getTiles()[this.cords.getX()][this.cords.getY()].constainsItem(this.getAim().getItems().get(0))) && (!this.movedToFoodTile)) {
                        this.movedToFoodTile = true;
                    } else if ((pathToTile.getFirstTile().getCords().getX() == this.cords.getX()) && (pathToTile.getFirstTile().getCords().getY() - 1 == this.cords.getY())) {
                        this.moveDown();
                    } else if ((pathToTile.getFirstTile().getCords().getX() == this.cords.getX()) && (pathToTile.getFirstTile().getCords().getY() + 1 == this.cords.getY())) {
                        this.moveUp();
                    } else if ((pathToTile.getFirstTile().getCords().getX() + 1 == this.cords.getX()) && (pathToTile.getFirstTile().getCords().getY() == this.cords.getY())) {
                        this.moveLeft();
                    } else if ((pathToTile.getFirstTile().getCords().getX() - 1 == this.cords.getX()) && (pathToTile.getFirstTile().getCords().getY() == this.cords.getY())) {
                        this.moveRight();
                    }
                }
            }
        }
        Control.setMoving(false);
    }
    public Tile getClosestFoodTileForEnemy() {
        ArrayList<Tile> item_Tiles = new ArrayList<>();
        for (Tile[] tiles : Control.map.getTiles()) {
            for (Tile tile : tiles) {
                if (!(tile.getItems().isEmpty())) {
                    for (Item item : tile.getItems()) {
                        if ((!tile.isBarrel()) && (!item.isInHand()) && (item.getType().equals("FOOD"))) {
                            item_Tiles.add(tile);
                        }
                    }
                }
            }
        }

        double heuristic = 100;
        Tile heuristic_tile = null;
        for(Tile tile : item_Tiles) {
            if (heuristic > heuristics(tile, Control.map.getTiles()[this.cords.getX()][this.cords.getY()])) {
                heuristic = heuristics(tile, Control.map.getTiles()[this.cords.getX()][this.cords.getY()]);
                heuristic_tile = tile;
            }
        }
        return heuristic_tile;
    }

    public Tile getClosestFoodTileForEnemyWithout(ArrayList<Tile> without_Items) {
        ArrayList<Tile> item_Tiles = new ArrayList<>();
        for (Tile[] tiles : Control.map.getTiles()) {
            for (Tile tile : tiles) {
                if (!(tile.getItems().isEmpty())) {
                    for (Item item : tile.getItems()) {
                        if ((!tile.isBarrel()) && (!item.isInHand()) && (item.getType().equals("FOOD")) && (!without_Items.contains(tile))) {
                            item_Tiles.add(tile);
                        }
                    }
                }
            }
        }

        if(item_Tiles.isEmpty())
            return null;

        double heuristic = 100;
        Tile heuristic_tile = null;
        for(Tile tile : item_Tiles) {
            if (heuristic > heuristics(tile, Control.map.getTiles()[this.cords.getX()][this.cords.getY()])) {
                heuristic = heuristics(tile, Control.map.getTiles()[this.cords.getX()][this.cords.getY()]);
                heuristic_tile = tile;
            }
        }
        return heuristic_tile;
    }

    public double heuristics(Tile start_tile, Tile end_tile) {
        return Math.sqrt(Math.pow(start_tile.getCords().getX()-end_tile.getCords().getX(),2)+Math.pow(start_tile.getCords().getY()-end_tile.getCords().getY(),2));
    }
}
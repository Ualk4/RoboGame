package entities;

import fileManagers.ScriptLoader;
import program.Control;
import world.Tile;

import java.util.ArrayList;
import java.util.Arrays;

public class MrMitten extends Entity implements Runnable {


    private int infection_rate = 0;
    public MrMitten(){
        this.setPngName("images/MrMitten/basic.png");
        this.type = "MrMitten";
        this.aim = null;
    }
    public MrMitten(int pos_x, int pos_y){
        this.setPngName("images/MrMitten/basic.png");
        this.cords.setX(pos_x);
        this.cords.setY(pos_y);
        this.type = "MrMitten";
        this.aim = null;
    }
    boolean automated = false;



    public boolean isAutomated() {
        return automated;
    }

    public void setIsAutomated(boolean isAutomated) {
        this.automated = isAutomated;
    }

    public void setInfection_rate(int infection_rate) {
        this.infection_rate = infection_rate;
    }

    public boolean isInfected() {
        return (infection_rate>0);
    }


    public void auto(){
        automated = true;
    }

    ScriptLoader scriptLoader = new ScriptLoader();

    public void moveUp() {
        super.moveUp();
        if (this.cords.getX() - 2 >= 0)
            Control.map.getTiles()[this.cords.getX() - 2][this.cords.getY()].setSeeable(true);
        if (this.cords.getX() + 2 < Control.map.getTiles().length)
            Control.map.getTiles()[this.cords.getX() + 2][this.cords.getY()].setSeeable(true);
        if (this.cords.getY() - 2 >= 0)
            Control.map.getTiles()[this.cords.getX()][this.cords.getY() - 2].setSeeable(true);
        if (this.cords.getY() - 1 >= 0) {
            if (this.cords.getX() - 1 >= 0)
                Control.map.getTiles()[this.cords.getX() - 1][this.cords.getY() - 1].setSeeable(true);
            if (this.cords.getX() + 1 < Control.map.getTiles().length)
                Control.map.getTiles()[this.cords.getX() + 1][this.cords.getY() - 1].setSeeable(true);
        }
    }

    public void moveDown() {
        super.moveDown();
        if (this.cords.getX() - 2 >= 0)
            Control.map.getTiles()[this.cords.getX() - 2][this.cords.getY()].setSeeable(true);
        if (this.cords.getX() + 2 < Control.map.getTiles().length)
            Control.map.getTiles()[this.cords.getX() + 2][this.cords.getY()].setSeeable(true);
        if (this.cords.getY()+ 2 < Control.map.getTiles()[0].length)
            Control.map.getTiles()[this.cords.getX()][this.cords.getY() + 2].setSeeable(true);
        if (this.cords.getY() + 1 < Control.map.getTiles()[0].length) {
            if (this.cords.getX() + 1 < Control.map.getTiles().length)
                Control.map.getTiles()[this.cords.getX() + 1][this.cords.getY() + 1].setSeeable(true);
            if (this.cords.getX() - 1 >= 0)
                Control.map.getTiles()[this.cords.getX() - 1][this.cords.getY() + 1].setSeeable(true);
        }
    }

    public void moveRight() {
        super.moveRight();
        if (this.cords.getX() + 2 < Control.map.getTiles().length)
            Control.map.getTiles()[this.cords.getX() + 2][this.cords.getY()].setSeeable(true);
        if (this.cords.getY() - 2 >= 0)
            Control.map.getTiles()[this.cords.getX()][this.cords.getY() - 2].setSeeable(true);
        if (this.cords.getY() + 2 < Control.map.getTiles()[0].length)
            Control.map.getTiles()[this.cords.getX()][this.cords.getY() + 2].setSeeable(true);
        if (this.cords.getX() + 1 < Control.map.getTiles().length) {
            if (this.cords.getY() - 1 >= 0)
                Control.map.getTiles()[this.cords.getX() + 1][this.cords.getY() - 1].setSeeable(true);
            if (this.cords.getY() + 1 < Control.map.getTiles()[0].length)
                Control.map.getTiles()[this.cords.getX() + 1][this.cords.getY() + 1].setSeeable(true);
        }
    }


    public void moveLeft() {
        super.moveLeft();
        if (this.cords.getX() - 2 >= 0)
            Control.map.getTiles()[this.cords.getX() - 2][this.cords.getY()].setSeeable(true);
        if (this.cords.getY() - 2 >= 0)
            Control.map.getTiles()[this.cords.getX()][this.cords.getY() - 2].setSeeable(true);
        if (this.cords.getY() + 2 < Control.map.getTiles()[0].length)
            Control.map.getTiles()[this.cords.getX()][this.cords.getY() + 2].setSeeable(true);
        if (this.cords.getX() - 1 >= 0) {
            if (this.cords.getY() - 1 >= 0)
                Control.map.getTiles()[this.cords.getX() - 1][this.cords.getY() - 1].setSeeable(true);
            if (this.cords.getY() + 1 < Control.map.getTiles()[0].length)
                Control.map.getTiles()[this.cords.getX() - 1][this.cords.getY() + 1].setSeeable(true);
        }
    }

    @Override
    public void run() {
        if (infection_rate != 0)
            infection_rate--;
        if (!Control.map.getEnemyCharacters().isEmpty()) {
            for (Enemy enemy : Control.map.getEnemyCharacters()) {
                if ((enemy.cords.getX() == this.cords.getX()) && (enemy.cords.getY() == this.cords.getY())) {
                    infection_rate = 2;
                    break;
                }
            }
            switch (infection_rate) {
                case 2: {
                    this.setPngName("images/MrMitten/cat_sick_two.png");
                    break;
                }
                case 1: {
                    this.setPngName("images/MrMitten/cat_sick_one.png");
                    break;
                }
                case 0 : {
                    this.setPngName("images/MrMitten/basic.png");
                    break;
                }
            }
        }

        if (Control.isFreestyleOn()) {
            Control.addKeyBindingsToFrame();
        } else {
            if (Control.scriptType == null) {
                scriptLoader.loadScriptFile(Control.scriptFileNames.getSelectedItem().toString());
            } else {
                scriptLoader.freeStyleScript(Control.scriptTxt.getText(), Control.scriptType);
            }
        }
        Control.setMoving(false);
    }

    public Tile getClosestKitchenEntrance() {
        ArrayList<Tile> kitchenEntrance_Tiles = new ArrayList<>();
        for(Tile[] tiles : Control.map.getTiles()) {
            for (Tile tile : tiles) {
                if (tile.isKitchen()) {
                    if (( (tile.getCords().getX() - 1 >= 0) && (!Control.map.getTiles()[tile.getCords().getX() - 1][tile.getCords().getY()].isKitchen()) && (!tile.hasLeft_wall())) ||
                            ((tile.getCords().getX() + 1 < Control.map.getTiles().length) && (!Control.map.getTiles()[tile.getCords().getX() + 1][tile.getCords().getY()].isKitchen()) && (!tile.hasRight_wall())) ||
                            ((tile.getCords().getY() - 1 >= 0) && (!Control.map.getTiles()[tile.getCords().getX()][tile.getCords().getY() - 1].isKitchen()) && (!tile.hasTop_wall())) ||
                            ((tile.getCords().getY() + 1 < Control.map.getTiles()[0].length) && (!Control.map.getTiles()[tile.getCords().getX()][tile.getCords().getY() + 1].isKitchen()) && (!tile.hasDown_wall())))
                        kitchenEntrance_Tiles.add(tile);
                }
            }
        }

        double heuristic = 100;
        Tile heuristic_tile = null;
        for(Tile tile : kitchenEntrance_Tiles){
            if(heuristic > heuristics(tile,Control.map.getTiles()[this.cords.getX()][this.cords.getY()])){
                heuristic = heuristics(tile,Control.map.getTiles()[this.cords.getX()][this.cords.getY()]);
                heuristic_tile = tile;
            }
        }
        return heuristic_tile;
    }
    public Tile getClosestItemTile() {
        ArrayList<Tile> item_Tiles = new ArrayList<>();
        for (Tile[] tiles : Control.map.getTiles()) {
            for (Tile tile : tiles) {
                if (!(tile.getItems().isEmpty())) {
                    for (Item item : tile.getItems()) {
                        if ((!tile.isBarrel()) && (!item.isInHand()) && (item.isMovable()) && (tile.isSeeable())) {
                            item_Tiles.add(tile);
                        }
                    }
                }
            }
        }

        double heuristic = 100;
        Tile heuristic_tile = null;
        for(Tile tile : item_Tiles) {
            if(heuristic > heuristics(tile,Control.map.getTiles()[this.cords.getX()][this.cords.getY()])){
                heuristic = heuristics(tile,Control.map.getTiles()[this.cords.getX()][this.cords.getY()]);
                heuristic_tile = tile;
            }
        }

        return heuristic_tile;
    }

    public Tile getClosestUnknownTile() {
        ArrayList<Tile> unseen_Tiles = new ArrayList<>();
        for (Tile[] tiles : Control.map.getTiles()) {
            for (Tile tile : tiles) {
                if (!tile.isSeeable()) {
                    unseen_Tiles.add(tile);
                }
            }
        }

        double heuristic = 100;
        Tile heuristic_tile = null;
        for(Tile tile : unseen_Tiles) {
            if(heuristic > heuristics(tile,Control.map.getTiles()[this.cords.getX()][this.cords.getY()])){
                heuristic = heuristics(tile,Control.map.getTiles()[this.cords.getX()][this.cords.getY()]);
                heuristic_tile = tile;
            }
        }

        return heuristic_tile;
    }

    public Tile getClosestItemTileWithout(ArrayList<Tile> without_Items) {
        ArrayList<Tile> item_Tiles = new ArrayList<>();
        for (Tile[] tiles : Control.map.getTiles()) {
            for (Tile tile : tiles) {
                if (!(tile.getItems().isEmpty()) && !(without_Items.contains(tile))) {
                    for (Item item : tile.getItems()) {
                        if ((!tile.isBarrel()) && (item.isMovable()) && (!item.isInHand()) && (tile.isSeeable())) {
                            item_Tiles.add(tile);
                        }
                    }
                }
            }
        }

        double heuristic = 100;
        Tile heuristic_tile = null;
        for(Tile tile : item_Tiles) {
            if(heuristic > heuristics(tile,Control.map.getTiles()[this.cords.getX()][this.cords.getY()])){
                heuristic = heuristics(tile,Control.map.getTiles()[this.cords.getX()][this.cords.getY()]);
                heuristic_tile = tile;
            }
        }

        return heuristic_tile;
    }

    public Tile getClosestItemTileWithoutArray(Tile[] without_tiles) {
        ArrayList<Tile> item_Tiles = new ArrayList<>();
        ArrayList<Tile> withouth_items = new ArrayList<>(Arrays.asList(without_tiles));
        for (Tile[] tiles : Control.map.getTiles()) {
            for (Tile tile : tiles) {
                if (!(tile.isBarrel()) && !(tile.getItems().isEmpty()) && !(withouth_items.contains(tile)) && (tile.isSeeable())) {
                    for (Item item : tile.getItems()) {
                        if ((item.isMovable()) && (!item.isInHand()) ) {
                            item_Tiles.add(tile);
                        }
                    }
                }
            }
        }

        double heuristic = 100;
        Tile heuristic_tile = null;
        for(Tile tile : item_Tiles) {
            if(heuristic > heuristics(tile,Control.map.getTiles()[this.cords.getX()][this.cords.getY()])){
                heuristic = heuristics(tile,Control.map.getTiles()[this.cords.getX()][this.cords.getY()]);
                heuristic_tile = tile;
            }
        }

        return heuristic_tile;
    }

    public double heuristics(Tile start_tile, Tile end_tile) {
        return Math.sqrt(Math.pow(start_tile.getCords().getX()-end_tile.getCords().getX(),2)+Math.pow(start_tile.getCords().getY()-end_tile.getCords().getY(),2));
    }

}
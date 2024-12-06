package entities;

import program.Control;
import world.Cords;
import world.Tile;

public class Entity implements Runnable{

    private Tile tile;

    protected Cords cords = new Cords();

    protected String pngName;

    protected Item item = null;

    protected String type = null;

    protected Tile aim = null;

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public void setAim(Tile aim) {
        this.aim = aim;
    }

    public Tile getAim() {
        return aim;
    }

    public void setPngName(String name) {
        this.pngName = name;
    }

    public String getPngName() {
        return pngName;
    }

    public Cords getCords() {
        return cords;
    }

    public Item getItem() {
        return item;
    }

    public String getTypeMovable() {
        return this.type;
    }


    @Override
    public void run() {}


    public void moveUp() {
        MrMitten mr = null;
        if (this.type.equals("MrMitten"))
            mr = (MrMitten) this;
        if (((mr != null) && (!mr.isInfected())) || this.type.equals("Enemy")) {
            if (((this.cords.getY() - 1) >= 0) && !(Control.map.getTiles()[this.cords.getX()][this.cords.getY()].hasTop_wall()) && !(Control.map.getTiles()[this.cords.getX()][this.cords.getY() - 1].isBarrel())) {
                Control.map.getTiles()[this.cords.getX()][this.cords.getY()].removeEntity(this);
                this.cords.decreaseY();
                Control.map.getTiles()[this.cords.getX()][this.cords.getY()].addEntity(this);
                if (this.item != null) {
                    Control.map.getTiles()[this.item.getCords().getX()][this.item.getCords().getY()].removeItem(this.item);
                    this.item.getCords().decreaseY();
                    Control.map.getTiles()[this.item.getCords().getX()][this.item.getCords().getY()].addItem(this.item);
                }
                this.tile = Control.map.getTiles()[this.cords.getX()][this.cords.getY()];
                Control.setCurrentState(Control.getCurrentCharacterNumber() + Control.getCurrentCharacter() + "moved UP to: " + this.cords.getX() + " x " + this.cords.getY());
                Control.logger.addToLog(Control.getCurrentState());
            }
        }
    }

    public void moveDown() {
        MrMitten mr = null;
        if (this.type.equals("MrMitten"))
            mr = (MrMitten) this;
        if (((mr != null) && (!mr.isInfected())) || this.type.equals("Enemy")) {
            if (((this.cords.getY() + 1) < Control.map.getTiles()[0].length) && !(Control.map.getTiles()[this.cords.getX()][this.cords.getY()].hasDown_wall()) && !(Control.map.getTiles()[this.cords.getX()][this.cords.getY() + 1].isBarrel())) {
                Control.map.getTiles()[this.cords.getX()][this.cords.getY()].removeEntity(this);
                this.cords.increaseY();
                Control.map.getTiles()[this.cords.getX()][this.cords.getY()].addEntity(this);
                if (this.item != null) {
                    Control.map.getTiles()[this.item.getCords().getX()][this.item.getCords().getY()].removeItem(this.item);
                    this.item.getCords().increaseY();
                    Control.map.getTiles()[this.item.getCords().getX()][this.item.getCords().getY()].addItem(this.item);
                }
                this.tile = Control.map.getTiles()[this.cords.getX()][this.cords.getY()];
                Control.setCurrentState(Control.getCurrentCharacterNumber() + Control.getCurrentCharacter() + "moved DOWN to: " + this.cords.getX() + " x " + this.cords.getY());
                Control.logger.addToLog(Control.getCurrentState());
            }
        }
    }

    public void moveRight() {
        MrMitten mr = null;
        if (this.type.equals("MrMitten"))
            mr = (MrMitten) this;
        if (((mr != null) && (!mr.isInfected())) || (this.type.equals("Enemy"))) {
            if (((this.cords.getX() + 1) < Control.map.getTiles().length) && !(Control.map.getTiles()[this.cords.getX()][this.cords.getY()].hasRight_wall()) && !(Control.map.getTiles()[this.cords.getX() + 1][this.cords.getY()].isBarrel())) {
                Control.map.getTiles()[this.cords.getX()][this.cords.getY()].removeEntity(this);
                this.cords.increaseX();
                Control.map.getTiles()[this.cords.getX()][this.cords.getY()].addEntity(this);
                if (this.item != null) {
                    Control.map.getTiles()[this.item.getCords().getX()][this.item.getCords().getY()].removeItem(this.item);
                    this.item.getCords().increaseX();
                    Control.map.getTiles()[this.item.getCords().getX()][this.item.getCords().getY()].addItem(this.item);
                }
                this.tile = Control.map.getTiles()[this.cords.getX()][this.cords.getY()];
                Control.setCurrentState(Control.getCurrentCharacterNumber() + Control.getCurrentCharacter() + "moved RIGHT to: " + this.cords.getX() + " x " + this.cords.getY());
                Control.logger.addToLog(Control.getCurrentState());
            }
        }
    }

    public void moveLeft() {
        MrMitten mr = null;
        if (this.type.equals("MrMitten"))
            mr = (MrMitten) this;
        if (((mr != null) && (!mr.isInfected())) || (this.type.equals("Enemy"))) {
            if (((this.cords.getX() - 1) >= 0) && !(Control.map.getTiles()[this.cords.getX()][this.cords.getY()].hasLeft_wall()) && !(Control.map.getTiles()[this.cords.getX() - 1][this.cords.getY()].isBarrel())) {
                Control.map.getTiles()[this.cords.getX()][this.cords.getY()].removeEntity(this);
                this.cords.decreaseX();
                Control.map.getTiles()[this.cords.getX()][this.cords.getY()].addEntity(this);
                if (this.item != null) {
                    Control.map.getTiles()[this.item.getCords().getX()][this.item.getCords().getY()].removeItem(this.item);
                    this.item.getCords().decreaseX();
                    Control.map.getTiles()[this.item.getCords().getX()][this.item.getCords().getY()].addItem(this.item);
                }
                this.tile = Control.map.getTiles()[this.cords.getX()][this.cords.getY()];
                Control.setCurrentState(Control.getCurrentCharacterNumber() + Control.getCurrentCharacter() + "moved LEFT to: " + this.cords.getX() + " x " + this.cords.getY());
                Control.logger.addToLog(Control.getCurrentState());
            }
        }
    }

    public void pickUp() {
        Item pickedItem = null;
        if (this.item == null) {
            for (Item item : Control.map.getTiles()[this.cords.getX()][this.cords.getY()].getItems()) {
                if (item.isMovable()) {
                    pickedItem = item;
                    break;
                }
            }
        }
        if(pickedItem != null) {
            this.item = pickedItem;
            this.item.setInHand(true);
            Control.map.getTiles()[this.cords.getX()][this.cords.getY()].removeItem(this.item);
            String name = (this.item.getPngName().split("/")[3]).split("\\.")[0];
            Control.setCurrentState(Control.getCurrentCharacterNumber() + Control.getCurrentCharacter() + "PICKED UP: " + name + ", at " + this.cords.getX() + " x " + this.cords.getY());
            Control.logger.addToLog(Control.getCurrentState());
        }
    }

    public void putDown() {
        if (this.item != null) {
            if (((Control.map.getTiles()[this.cords.getX()][this.cords.getY()].isTrashCan()) || (Control.map.getTiles()[this.cords.getX()][this.cords.getY()].isKitchen())  && (Control.map.getTiles()[this.cords.getX()][this.cords.getY()].getItems().size() == 2)) || (Control.map.getTiles()[this.cords.getX()][this.cords.getY()].getItems().size() == 1)) {
                this.item.getCords().setX(this.cords.getX());
                this.item.getCords().setY(this.cords.getY());
                Control.map.getTiles()[this.cords.getX()][this.cords.getY()].addItem(this.item);
                this.item.setInHand(false);
                String name = (this.item.getPngName().split("/")[3]).split("\\.")[0];
                this.item = null;
                this.aim = null;
                Control.setCurrentState(Control.getCurrentCharacterNumber() + Control.getCurrentCharacter() + "PUT DOWN: " + name + ", at " + this.cords.getX() + " x " + this.cords.getY());
                Control.logger.addToLog(Control.getCurrentState());
            }
        }
    }

}
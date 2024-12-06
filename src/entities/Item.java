package entities;

import program.Control;
import world.Cords;

import java.io.IOException;

public class Item {

    protected String pngName = null;
    protected Cords cords = new Cords();

    protected boolean movable = true;

    protected boolean inHand = false;

    protected String type;

    public void setPngName(String name){
        this.pngName = name;
    }
    public String getPngName() {
        return pngName;
    }

    public Cords getCords() {
        return cords;
    }

    public void setInHand(boolean inHand) {
        this.inHand = inHand;
        if(!inHand) {
            if((Control.map.getTiles()[this.cords.getX()][this.cords.getY()].isTrashCan()) && (this.getType().equals("FOOD"))) {
                Food helper = (Food) this;
                if(helper.getHealth() == 0){
                    this.movable = false;
                    Control.map.addItem_Thrown(this);
                    Control.map.removeItem(this);
                    Control.map.getTiles()[this.cords.getX()][this.cords.getY()].removeItem(this);
                    String logLine = Control.getCurrentCharacterNumber() + Control.getCurrentCharacter() + " thrown out item!";
                    Control.addToCurrentState(logLine);
                    Control.logger.addToLog(Control.getCurrentState() + logLine);
                    try {
                        Control.logger.saveLog();
                    } catch (IOException ignored) {}
                }
            }
            if(Control.map.getTiles()[this.cords.getX()][this.cords.getY()].isKitchen()){
                if(this.getType().equals("FOOD")){
                    Food helper = (Food) this;
                    if(helper.getHealth() > 0){
                        this.movable = false;
                        Control.map.addItem_Saved(this);
                        Control.map.removeItem(this);
                        Control.map.getTiles()[this.cords.getX()][this.cords.getY()].removeItem(this);
                        String logLine = (Control.getCurrentCharacterNumber() + Control.getCurrentCharacter() + "saved an item!");
                        Control.addToCurrentState(logLine);
                        Control.logger.addToLog(Control.getCurrentState() + logLine);
                        try {
                            Control.logger.saveLog();
                        } catch (IOException ignored) {}
                    }
                }
            }
        }
    }

    public boolean isInHand() {
        return inHand;
    }

    public boolean isMovable() {
        return movable;
    }

    public String getType() {
        return type;
    }
}
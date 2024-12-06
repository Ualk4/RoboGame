package entities;

import program.Control;

import java.util.Random;

public class Food extends Item{

    private int health;

    public Food() {}
    public Food(boolean isRand){
        Random rand = new Random();
        String[] pngName = {"bread","burger","cake","catnip","chicken-bucket","chili","coffee-beans","cookie","corn-dog",
                            "cucumber","fried-chicken","fries","honey","ice-cream","jam","pie","pizza","snack","sushi-roll","watermelon"};
        this.health = rand.nextInt(101);
        if(health == 0)
             this.pngName += "images/pantry/food/spoiled_ " + pngName[rand.nextInt(20)] + ".png";
        else
            this.pngName = "images/pantry/food/" + pngName[rand.nextInt(20)];
        this.cords.setX(rand.nextInt(Control.map.getTiles().length));
        this.cords.setY(rand.nextInt(Control.map.getTiles()[0].length));
        this.type = "FOOD";

        Control.map.getTiles()[this.cords.getX()][this.cords.getY()].addItem(this);
    }

    public Food(String pngName, int pos_x, int pos_y, int health, String type){
        this.pngName = "images/pantry/food/" + pngName + ".png";
        this.cords.setX(pos_x);
        this.cords.setY(pos_y);
        this.health = health;
        this.type = type;
    }
    public int getHealth() {
        return health;
    }

    public float getHealthRatio() {
        return (float)health/100F;
    }

    public void setHealth(int health) {
        this.health = health;
        if(this.health == 0) {
            String[] name = (this.pngName.split("/"));
            this.pngName = name[0] + "/" + name[1] + "/" + name[2] + "/" + "spoiled_" + name[3];
        }
    }


    public void spoil(int spoilRate){
        health -= spoilRate;
        if(this.health == 0) {
            String[] name = (this.pngName.split("/"));
            this.pngName = name[0] + "/" + name[1] + "/" + name[2] + "/" + "spoiled_" + name[3];

        }
    }

}

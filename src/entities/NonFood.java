package entities;

import program.Control;

import java.util.Random;

public class NonFood extends Item {


    public NonFood() {}
    public NonFood(boolean isRand){
        Random rand = new Random();
        String[] pngName = {"bag","blender","bulb","camera","chess-pieces","coffee-grinder","fish","gift",
                            "guitar","hair-comb","hat","med-kit","scissor","soccer-ball","teapot","trophy","weight"};
        this.setPngName("images/pantry/nonfood/" + pngName[rand.nextInt(17)] + ".png");
        this.cords.setX(rand.nextInt(Control.map.getTiles().length));
        this.cords.setY(rand.nextInt(Control.map.getTiles()[0].length));
        this.movable = rand.nextInt(2) == 0;
        this.type = "NONFOOD";
    }

    public NonFood(String pngName, int pos_x, int pos_y, boolean movable, String type){
        this.pngName = "images/pantry/nonfood/" + pngName + ".png";
        this.cords.setX(pos_x);
        this.cords.setY(pos_y);
        this.movable = movable;
        this.type = type;
    }

}
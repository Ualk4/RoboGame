package world;

import entities.Entity;
import entities.Item;

import java.util.ArrayList;

public class Tile {
    private  ArrayList<Entity> entities = new ArrayList<>();

    private  ArrayList<Item> items = new ArrayList<>();

    private Cords cords;

    private boolean isBarrel = false;

    private boolean isKitchen = false;
    private boolean isTrashCan = false;

    private boolean top_wall = false, left_wall = false, right_wall = false, down_wall = false;

    private boolean isSeeable = false;

    public ArrayList<Entity> getEntities() {
        return this.entities;
    }

    public Entity[] getEntitiesAsArray() {
        Entity[] entitiesAsArray = new Entity[this.entities.size()];
        for(int i = 0; i < this.entities.size(); i ++){
            entitiesAsArray[i] = entities.get(i);
        }
        return entitiesAsArray;
    }
    public ArrayList<Item> getItems() {

        return this.items;
    }
    public Item[] getItemsAsArray() {
        Item[] itemsAsArray = new Item[this.items.size()];
        for(int i = 0; i < this.items.size();i ++){
            itemsAsArray[i] = this.items.get(i);
        }
        return  itemsAsArray;
    }

    public boolean constainsItem(Item item){
        for(Item items : this.items){
            if(items == item)
                return true;
        }
        return false;
    }

    public void addEntity(Entity entity) {
        this.entities.add(entity);
    }
    public void removeEntity(Entity entity) {
        this.entities.remove(entity);
    }

    public Cords getCords() {
        return this.cords;
    }

    public void setCords(Cords cords) {
        this.cords = cords;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void removeItem(Item item){
        this.items.remove(item);
    }

    public void setTop_wall(boolean top_wall) {
        this.top_wall = top_wall;
    }

    public void setDown_wall(boolean down_wall) {
        this.down_wall = down_wall;
    }

    public void setRight_wall(boolean right_wall) {
        this.right_wall = right_wall;
    }

    public void setLeft_wall(boolean left_wall) {
        this.left_wall = left_wall;
    }

    public boolean isSeeable() {
        return isSeeable;
    }

    public boolean isKitchen() {
        return isKitchen;
    }

    public boolean isBarrel() {
        return isBarrel;
    }

    public boolean isTrashCan() {
        return isTrashCan;
    }

    public void setKitchen(boolean kitchen) {
        isKitchen = kitchen;
    }

    public void setBarrel(boolean barrel) {
        isBarrel = barrel;
    }

    public void setTrashCan(boolean trashCan) {
        isTrashCan = trashCan;
    }

    public void setSeeable(boolean seeable) {
        isSeeable = seeable;
    }

    public boolean hasDown_wall() {
        return down_wall;
    }
    public boolean hasTop_wall() {
        return top_wall;
    }

    public boolean hasRight_wall() {
        return right_wall;
    }

    public boolean hasLeft_wall() {
        return left_wall;
    }
}
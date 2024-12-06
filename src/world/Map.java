package world;

import entities.*;
import program.Control;

import java.util.ArrayList;

public class Map {
    private final Tile[][] tiles;
    private ArrayList<MrMitten> characters = new ArrayList<>();
    private ArrayList<Enemy> enemyCharacters = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Item> items_saved = new ArrayList<>();
    private ArrayList<Item> items_thrown = new ArrayList<>();
    private ArrayList<Item> items_eaten = new ArrayList<>();
    private MrMitten currentCharacter = null;

    public Map(int x, int y) {
        tiles = new Tile[x][y];
        for (int i = 0; i < x; i++)
            for (int j = 0; j < y; j++)
                tiles[i][j] = new Tile();
    }

    public void addCharacter(MrMitten player) {
        this.characters.add(player);
    }

    public void addEnemy(Enemy enemy) {
        this.enemyCharacters.add(enemy);
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void addItem_Saved(Item item) {
        this.items_saved.add(item);
    }

    public void addItem_Thrown(Item item) {
        this.items_thrown.add(item);
    }

    public void addItem_Eaten(Item item) {
        this.items_eaten.add(item);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<Item> getItems_saved() {
        return items_saved;
    }

    public ArrayList<Item> getItems_thrown() {
        return items_thrown;
    }

    public ArrayList<Item> getItems_eaten() {
        return items_eaten;
    }


    public void removeItem(Item item) {
        this.items.remove(item);
    }

    public void setCharacters() {
        for (MrMitten player : characters) {
            int x = player.getCords().getX();
            int y = player.getCords().getY();
            tiles[x][y].addEntity(player);
            player.setTile(tiles[x][y]);
        }
    }

    public void setEnemyCharacters() {
        for (Enemy enemy : enemyCharacters) {
            int x = enemy.getCords().getX();
            int y = enemy.getCords().getY();
            tiles[x][y].addEntity(enemy);
        }
    }

    public void setItems() {
        this.items_saved = new ArrayList<>();
        this.items_thrown = new ArrayList<>();
        this.items_eaten = new ArrayList<>();
        for (Item item : items) {
            int x = item.getCords().getX();
            int y = item.getCords().getY();
            tiles[x][y].addItem(item);
        }
    }

    public void stepTime() {
        for (Item item : this.items) {
            if ((item.getType().equals("FOOD")) && (!item.isInHand())) {
                if (((Food) item).getHealth() > 0) {
                    ((Food) item).spoil(1);
                } else if(Control.map.getTiles()[item.getCords().getX()][item.getCords().getY()].isTrashCan()){
                    item.setInHand(false);
                }
            }
        }
    }

    public boolean isThereFood(){
        for(Item item : this.items){
            if (item.getType().equals("FOOD")) {
                return true;
            }
        }
        return false;
    }
    public boolean isThereSavable(){
        for(Item item : this.items)
            if (item.isMovable()) {
                return true;
            }
        return false;
    }

    public boolean isLastOne() {
        int count = 0;
        for(Item item : this.items)
            if (item.isMovable()) {
                count++;
            }
        return (count == 0);
    }

    public void setNull(){
        characters = new ArrayList<>();
        enemyCharacters = new ArrayList<>();
        items = new ArrayList<>();
        items_saved = new ArrayList<>();
        items_thrown = new ArrayList<>();
        items_eaten = new ArrayList<>();
    }

    public ArrayList<MrMitten> getCharacters() {
        return characters;
    }

    public ArrayList<Enemy> getEnemyCharacters() {
        return this.enemyCharacters;
    }

    public Entity[] getCharactersAsArray() {
        Entity[] entitiesAsArray = new Entity[this.characters.size()];
        for(int i = 0; i < this.characters.size(); i ++){
            entitiesAsArray[i] = this.characters.get(i);
        }
        return entitiesAsArray;
    }

    public ArrayList<Entity> getAllCharacters(){
        ArrayList<Entity> moveAbles = new ArrayList<>();
        moveAbles.addAll(characters);
        moveAbles.addAll(enemyCharacters);
        return moveAbles;
    }


    public Tile[][] getTiles() {
        return tiles;
    }

    public MrMitten getCurrentCharacter() {
        return currentCharacter;
    }

    public void setCurrentCharacter(MrMitten currentCharacter) {
        this.currentCharacter = currentCharacter;
    }
}
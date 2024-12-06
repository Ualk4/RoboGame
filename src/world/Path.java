package world;

import java.util.ArrayList;

public class Path {
    ArrayList<Tile> path = new ArrayList<>();

    public ArrayList<Tile> getPath() {
        return this.path;
    }

    public void addTileToPath(Tile tile){
        this.path.add(tile);
    }

    public void removeTileToPath(Tile tile){
        this.path.remove(tile);
    }

    public Tile getFirstTile(){
        if (this.path.size()>1)
            return this.path.get(1);
        return null;
    }

    public Tile getStartTile() {
        if (!this.path.isEmpty()) return this.path.get(0);
        else return null;
    }

    public Tile getLastTile(){
        return this.path.get(this.path.size()-1);
    }

    public int getLength(){
        return this.path.size();
    }
}
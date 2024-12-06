package world;

public class Cords {

    private int x, y;

    public Cords(){}
    public Cords(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void increaseX(){
        this.x += 1;
    }

    public void increaseY(){
        this.y += 1;
    }
    public void decreaseX(){
        this.x -= 1;
    }

    public void decreaseY(){
        this.y -= 1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
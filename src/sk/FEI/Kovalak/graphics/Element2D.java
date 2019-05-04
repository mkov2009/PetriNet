package sk.FEI.Kovalak.graphics;


import java.awt.*;


public abstract class Element2D {
    private int x;
    private int y;
    private long id;

    public abstract void draw(Graphics g);

    public Element2D(int x, int y, long id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }


    public Element2D(){

    }

    public long onClick(int x, int y){
        if(contains(x,y)){
            return this.getId();
        }
        return -1;
    }

    public boolean contains(int x,int y){
        if(x<this.getX()+50 && x>this.getX()){
            if(y<this.getY()+50 && y>this.getY()){
                return true;
            }
        }
        return false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public long getId() {
        return id;
    }
}

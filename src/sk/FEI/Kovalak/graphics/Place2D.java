package sk.FEI.Kovalak.graphics;

import java.awt.*;

public class Place2D extends Element2D {

    public Place2D(int x, int y,int id) {
        super(x, y,id);
    }

    public Place2D() {
    }

    @Override
    public void draw(Graphics g) {

        g.drawOval(this.getX(),this.getY(),50,50);
    }

}

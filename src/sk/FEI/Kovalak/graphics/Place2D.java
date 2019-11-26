package sk.FEI.Kovalak.graphics;

import java.awt.*;

public class Place2D extends Element2D {
    private boolean clicked;

    public Place2D(int x, int y,int id) {
        super(x, y,id);
    }

    public Place2D(int x, int y,int id, boolean clicked) {
        super(x, y, id);
        this.clicked = clicked;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(2));
        if(clicked){
            g.setColor(new Color(0x0023FF));
        }
        g.drawOval(this.getX(),this.getY(),50,50);
        g.setColor(new Color(0x000000));
    }

}

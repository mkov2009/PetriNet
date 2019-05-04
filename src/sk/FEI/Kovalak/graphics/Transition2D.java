package sk.FEI.Kovalak.graphics;

import java.awt.*;

public class Transition2D extends Element2D {

    private boolean runnable;

    public Transition2D(int x, int y, boolean runnable,int id) {
        super(x, y,id);
        this.runnable = runnable;
    }

    public Transition2D() {
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawRect(this.getX(),this.getY(),50,50);
        if(runnable){
            g2.setColor(new Color(0x00FF00));
            g2.fillRect(this.getX(),this.getY(),50,50);
            g2.setColor(new Color(0));
        }

    }

}

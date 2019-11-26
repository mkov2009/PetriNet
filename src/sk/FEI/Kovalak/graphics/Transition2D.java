package sk.FEI.Kovalak.graphics;

import java.awt.*;

public class Transition2D extends Element2D {

    private boolean runnable;
    private boolean stateRun;
    private boolean clicked;

    public Transition2D(int x, int y, boolean runnable,int id, boolean stateRun) {
        super(x, y,id);
        this.runnable = runnable;
        this.stateRun = stateRun;
    }

    public Transition2D(int x, int y, boolean runnable,int id, boolean stateRun,boolean clicked) {
        super(x, y,id);
        this.runnable = runnable;
        this.stateRun = stateRun;
        this.clicked = clicked;
    }

    public Transition2D() {
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        if(clicked){
            g2.setColor(new Color(0x0023FF));
        }
        g2.drawRect(this.getX(),this.getY(),50,50);
        g2.setColor(new Color(0));
        if(runnable && stateRun){
            g2.setColor(new Color(0x00FF00));
            g2.fillRect(this.getX(),this.getY(),50,50);
            g2.setColor(new Color(0));
        }

    }

}

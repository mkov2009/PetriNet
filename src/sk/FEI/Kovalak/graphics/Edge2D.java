package sk.FEI.Kovalak.graphics;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

public class Edge2D extends Element2D {

    private int x2,y2;
    private int nX1,nY1,nX2,nY2;
    private boolean reset;
    private String multiplicity;
    public Edge2D(long id, int x, int y, int x2, int y2, boolean reset, String multiplicity) {
        super(x, y, id);
        this.x2 = x2;
        this.y2 = y2;
        this.reset = reset;
        this.multiplicity = multiplicity;
    }

    public Edge2D() {
    }

    @Override
    public void draw(Graphics g) {
        drawArrow(g,this.getX(),this.getY(),x2,y2,0);
        if(reset){
            drawArrow(g,this.getX(),this.getY(),x2,y2,5);
        }
    }

    private void drawArrow(Graphics g1, int x1, int y1, int x2, int y2,int shorter)
    {
        int up=0, left=0;
//
        final int ARR_SIZE = 6;
        Graphics2D g = (Graphics2D) g1.create();

        double dx = x2 - x1, dy = y2 - y1;
        double angle = Math.atan2(dy, dx);

        double degrees = Math.toDegrees(angle);
        if(degrees <= 0){
            degrees+=360;
        }

        if(degrees < 45){
            x1 += 50;
            y1 += 25;
            y2 += 25;
            up = 20;
        }
        else if(degrees < 135){
            x1 += 25;
            y1 += 50;
            x2 += 25;
            left = 10;

        }
        else if (degrees < 225){
            y1 += 25;
            x2 += 50;
            y2 += 25;
            up = 10;
        }
        else if(degrees < 315){
            x1+=25;
            x2 += 25;
            y2 += 50;
            left = 10;
        }
        else {
            x1 += 50;
            y1 += 25;
            y2 += 25;
            up = 10;

        }
        this.nX1 = x1;
        this.nY1 = y1;
        this.nX2 = x2;
        this.nY2 = y2;

        g1.drawString(multiplicity, ((x1 + x2) / 2) - left, ((y1 + y2) / 2) - up);

        int len = (int) Math.sqrt(dx*dx + dy*dy)-50-shorter;
        AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g.transform(at);
        g.drawLine(0, 0, len, 0);
        g.fillPolygon(new int[] {len, len-ARR_SIZE, len-ARR_SIZE, len},
                new int[] {0, -ARR_SIZE, ARR_SIZE, 0}, 4);
    }

    public long onClick(int x, int y){
        if(contains(x,y)){
            return this.getId();
        }
        return -1;
    }

    public boolean contains(int x,int y){
        if(new Line2D.Double(nX1,nY1,nX2,nY2).intersects(x-7,y-7,15,15)){
            return true;
        }
        return false;
    }


}

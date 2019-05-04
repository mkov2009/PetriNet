package sk.FEI.Kovalak.graphics;

import java.awt.*;


public class Text2D extends Element2D {
    private String text;

    public Text2D(int x, int y,String text) {
        super(x, y, 0);
        this.text = text;
    }

    public Text2D() {
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        FontMetrics metrics =  g2.getFontMetrics();
        int x = this.getX() + (50 - metrics.stringWidth(text))/2;
        int y = this.getY() + (50 - metrics.getHeight())/2;

        g2.drawString(text,x,y);
    }


}

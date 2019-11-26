package sk.FEI.Kovalak.graphics;

import java.awt.Canvas;
import java.awt.Graphics;

import java.util.ArrayList;
import java.util.List;

public class MyCanvas extends Canvas {
    private List<Element2D> elements2D;

    public MyCanvas() {
        super();
        this.elements2D = new ArrayList<>();
    }

    public void paint(Graphics graphics){
        for(Element2D element2D : elements2D){
            element2D.draw(graphics);
        }
    }

    public List<Element2D> getElements2D() {
        return elements2D;
    }

    public void addElement2D(Element2D element2D) {
        this.elements2D.add(element2D);
    }


    public void clearList(){
        this.elements2D.clear();
    }

}

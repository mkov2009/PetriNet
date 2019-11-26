package sk.FEI.Kovalak.graphics.mouseListeners;

import sk.FEI.Kovalak.graphics.MyCanvas;
import sk.FEI.Kovalak.graphics.MyFrame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class MouseListenerComponent implements MouseListener {

    protected MyFrame frame;

    public MouseListenerComponent(MyFrame frame) {
        this.frame = frame;
    }

    public MyFrame getFrame() {
        return frame;
    }



    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

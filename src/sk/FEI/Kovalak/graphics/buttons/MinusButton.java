package sk.FEI.Kovalak.graphics.buttons;

import sk.FEI.Kovalak.graphics.MyFrame;
import sk.FEI.Kovalak.graphics.mouseListeners.MinusML;

import java.awt.*;
import java.awt.event.ActionEvent;


public class MinusButton extends BaseButton {

    public MinusButton(MyFrame frame, String label, int x, int y) throws HeadlessException {
        super(frame, label, x, y);
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.setRunState(false);
        frame.draw();
        frame.removeMouseListners();
        frame._addMouseListener(new MinusML(frame));
    }
}

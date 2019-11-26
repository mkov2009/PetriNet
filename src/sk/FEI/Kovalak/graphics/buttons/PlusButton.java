package sk.FEI.Kovalak.graphics.buttons;

import sk.FEI.Kovalak.graphics.MyFrame;
import sk.FEI.Kovalak.graphics.mouseListeners.PlusML;

import java.awt.*;
import java.awt.event.ActionEvent;

public class PlusButton extends BaseButton {

    public PlusButton(MyFrame frame, String label, int x, int y) throws HeadlessException {
        super(frame, label, x, y);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.setRunState(false);
        frame.draw();
        frame.removeMouseListners();
        frame._addMouseListener(new PlusML(frame));
    }
}

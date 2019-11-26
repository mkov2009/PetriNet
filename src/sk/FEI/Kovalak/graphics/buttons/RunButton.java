package sk.FEI.Kovalak.graphics.buttons;

import sk.FEI.Kovalak.graphics.MyFrame;
import sk.FEI.Kovalak.graphics.mouseListeners.RunML;

import java.awt.*;
import java.awt.event.ActionEvent;

public class RunButton extends BaseButton {

    public RunButton(MyFrame frame, String label, int x, int y) throws HeadlessException {
        super(frame, label, x, y);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.setRunState(true);
        frame.draw();
        frame.removeMouseListners();
        frame._addMouseListener(new RunML(frame));
    }
}

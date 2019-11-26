package sk.FEI.Kovalak.graphics.buttons;

import sk.FEI.Kovalak.graphics.MyFrame;
import sk.FEI.Kovalak.graphics.mouseListeners.AddResetEdgeML;

import java.awt.*;
import java.awt.event.ActionEvent;


public class AddResetEdgeButton extends BaseButton {

    public AddResetEdgeButton(MyFrame frame, String label, int x, int y) throws HeadlessException {
        super(frame, label, x, y);
        this.setBounds(x,y,100,30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.setRunState(false);
        frame.draw();
        frame.removeMouseListners();
        frame._addMouseListener(new AddResetEdgeML(frame));
    }
}

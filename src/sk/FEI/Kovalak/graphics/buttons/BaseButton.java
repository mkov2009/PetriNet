package sk.FEI.Kovalak.graphics.buttons;

import sk.FEI.Kovalak.graphics.MyFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class BaseButton extends Button implements ActionListener {

    protected MyFrame frame;

    public BaseButton(MyFrame frame, String label, int x, int y) throws HeadlessException {
        super(label);
        this.frame = frame;
        this.setBounds(x,y,90,30);
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

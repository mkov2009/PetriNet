package sk.FEI.Kovalak.graphics.mouseListeners;

import sk.FEI.Kovalak.graphics.IdGenerator;
import sk.FEI.Kovalak.graphics.MyFrame;
import sk.FEI.Kovalak.graphics.mouseListeners.MouseListenerComponent;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AddTransitionML extends MouseListenerComponent {
    public AddTransitionML(MyFrame frame) {
        super(frame);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        frame.getPetriNet().addTransition(new IdGenerator(frame.getPetriNet()).getId(),"",e.getX()-25,e.getY()-25);
        frame.draw();

    }
}

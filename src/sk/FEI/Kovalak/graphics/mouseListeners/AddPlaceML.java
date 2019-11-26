package sk.FEI.Kovalak.graphics.mouseListeners;

import sk.FEI.Kovalak.graphics.IdGenerator;
import sk.FEI.Kovalak.graphics.MyCanvas;
import sk.FEI.Kovalak.graphics.MyFrame;

import java.awt.event.MouseEvent;

public class AddPlaceML extends MouseListenerComponent {


    public AddPlaceML(MyFrame frame) {
        super(frame);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        frame.getPetriNet().addPlace(new IdGenerator(frame.getPetriNet()).getId(),"",0,e.getX()-25,e.getY()-25);
        frame.draw();

    }
}

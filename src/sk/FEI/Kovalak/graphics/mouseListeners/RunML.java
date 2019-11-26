package sk.FEI.Kovalak.graphics.mouseListeners;

import sk.FEI.Kovalak.graphics.Element2D;
import sk.FEI.Kovalak.graphics.MyFrame;

import java.awt.event.MouseEvent;
import java.util.ConcurrentModificationException;
import java.util.List;

public class RunML extends MouseListenerComponent{
    public RunML(MyFrame frame) {
        super(frame);
    }


    //to do
    @Override
    public void mouseClicked(MouseEvent e) {
        List<Element2D> element2DS = frame.getCanvas().getElements2D();
        int x = e.getX();
        int y = e.getY();
        try{
            for(Element2D element2D: element2DS) {
                long id = element2D.onClick(x,y);
                if(id!=-1){
                    if(frame.getPetriNet().getTransition(id)!=null){
                        frame.getPetriNet().runTransition(id);
                        frame.draw();
                    }
                }
            }
        }
        catch (ConcurrentModificationException ex){
            ex.getMessage();
        }
    }
}

package sk.FEI.Kovalak.graphics.mouseListeners;

import sk.FEI.Kovalak.exceptions.SameTypeElementException;
import sk.FEI.Kovalak.exceptions.WrongTypeElementException;
import sk.FEI.Kovalak.graphics.Element2D;
import sk.FEI.Kovalak.graphics.IdGenerator;
import sk.FEI.Kovalak.graphics.MyFrame;
import sk.FEI.Kovalak.petrinet.Place;
import sk.FEI.Kovalak.petrinet.Transition;

import java.awt.event.MouseEvent;
import java.util.ConcurrentModificationException;
import java.util.List;

public class AddResetEdgeML extends MouseListenerComponent {

    private Transition transition;
    private Place place;
    private boolean firstClick;
    private boolean secondClick;
    private boolean placeFirst;

    public AddResetEdgeML(MyFrame frame) {
        super(frame);
        this.firstClick  = false;
        this.secondClick = false;
        this.placeFirst = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        List<Element2D> element2DS = frame.getCanvas().getElements2D();
        int x = e.getX();
        int y = e.getY();
        long id = -1;
        try {
            for (Element2D element2D : element2DS) {
                id = element2D.onClick(x, y);
                if (id != -1) {
                    break;
                }
            }
            if(id!=-1){
                if(!firstClick){
                    if(frame.getPetriNet().getPlace(id)!=null){
                        place = frame.getPetriNet().getPlace(id);
                        firstClick = true;
                        frame.setIdClicked(id);
                        frame.draw();
                    }
                    if(frame.getPetriNet().getTransition(id)!=null){
                        throw new WrongTypeElementException("Reset Edge can't start from Transition.");
                    }
                }
                else {
                    if(frame.getPetriNet().getTransition(id)!=null){
                        transition = frame.getPetriNet().getTransition(id);
                        secondClick = true;
                    }
                    if(frame.getPetriNet().getPlace(id)!=null){
                        firstClick = false;
                        throw new SameTypeElementException("Can't be Place to Place Edge.");
                    }
                    frame.setIdClicked(-1);
                }
                if(firstClick && secondClick){
                    frame.getPetriNet().addEdgeReset(new IdGenerator(frame.getPetriNet()).getId(),place.getId(),transition.getId());
                    firstClick = false;
                    secondClick = false;
                }
            }
        }
        catch (ConcurrentModificationException ex){
             ex.getMessage();
            }
        catch (SameTypeElementException ex){
            System.out.println(ex.getErrorMessage());
        }
        catch (WrongTypeElementException ex){
            System.out.println(ex.getErrorMessage());
        }
        frame.draw();
    }
}

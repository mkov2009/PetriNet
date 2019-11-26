package sk.FEI.Kovalak.graphics.mouseListeners;

import sk.FEI.Kovalak.graphics.Element2D;
import sk.FEI.Kovalak.graphics.MyFrame;
import sk.FEI.Kovalak.petrinet.EdgePlaceToTransition;
import sk.FEI.Kovalak.petrinet.EdgeTransitionToPlace;
import sk.FEI.Kovalak.petrinet.Place;

import java.awt.event.MouseEvent;
import java.util.ConcurrentModificationException;
import java.util.List;

public class MinusML extends MouseListenerComponent {
    public MinusML(MyFrame frame) {
        super(frame);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        List<Element2D> element2DS = frame.getCanvas().getElements2D();
        int x = e.getX();
        int y = e.getY();
        long id = -1;
        try{
            for(Element2D element2D: element2DS) {
                id = element2D.onClick(x, y);
                if(id!=-1){break;}
            }
            if(id!=-1){
                if(frame.getPetriNet().getPlace(id)!=null){
                    Place place = frame.getPetriNet().getPlace(id);
                    if(place.getMarking()>0) {
                        place.setMarking(place.getMarking() - 1);
                    }
                }
                else if(frame.getPetriNet().getEdgePlaceToTransition(id)!=null){
                    EdgePlaceToTransition edgePlaceToTransition = frame.getPetriNet().getEdgePlaceToTransition(id);
                    if(edgePlaceToTransition.getWeight()>1) {
                        edgePlaceToTransition.setWeight(edgePlaceToTransition.getWeight() - 1);
                    }
                }

                else if(frame.getPetriNet().getEdgeTransitionToPlace(id)!=null){
                    EdgeTransitionToPlace edgeTransitionToPlace = frame.getPetriNet().getEdgeTransitionToPlace(id);
                    if(edgeTransitionToPlace.getWeight()>1) {
                        edgeTransitionToPlace.setWeight(edgeTransitionToPlace.getWeight() - 1);
                    }
                }
            }
        }
        catch (ConcurrentModificationException ex){
            ex.getMessage();
        }
        frame.draw();
    }
}

package sk.FEI.Kovalak.graphics.mouseListeners;

import sk.FEI.Kovalak.graphics.Element2D;
import sk.FEI.Kovalak.graphics.MyFrame;
import sk.FEI.Kovalak.petrinet.*;

import java.awt.event.MouseEvent;
import java.util.ConcurrentModificationException;
import java.util.List;

public class DeleteML extends MouseListenerComponent {
    public DeleteML(MyFrame frame) {
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
                    for(Place place : frame.getPetriNet().getPlaces()){
                        if(place.getId() == id){
                            frame.getPetriNet().getPlaces().remove(place);

                            //Place to Transition Edge
                            /*for(EdgePlaceToTransition edgePlaceToTransition : frame.getPetriNet().getEdgesPlaceToTransition()){
                                if(edgePlaceToTransition.getIdOfPlace() == id){
                                    frame.getPetriNet().getEdgesPlaceToTransition().remove(edgePlaceToTransition);
                                }
                            }*/
                            for(int i = 0;i<frame.getPetriNet().getEdgesPlaceToTransition().size();i++){
                                EdgePlaceToTransition edgePlaceToTransition = frame.getPetriNet().getEdgesPlaceToTransition().get(i);
                                if(edgePlaceToTransition.getIdOfPlace() == id){
                                    frame.getPetriNet().getEdgesPlaceToTransition().remove(edgePlaceToTransition);
                                }
                            }
                            //Transition to Place Edge

                            /*for(EdgeTransitionToPlace edgeTransitionToPlace : frame.getPetriNet().getEdgesTransitionToPlace()){
                                if(edgeTransitionToPlace.getIdOfPlace() == id){
                                    frame.getPetriNet().getEdgesTransitionToPlace().remove(edgeTransitionToPlace);
                                }
                            }*/

                            for(int j = 0; j<frame.getPetriNet().getEdgesTransitionToPlace().size();j++){
                                EdgeTransitionToPlace edgeTransitionToPlace = frame.getPetriNet().getEdgesTransitionToPlace().get(j);
                                if(edgeTransitionToPlace.getIdOfPlace() == id){
                                    frame.getPetriNet().getEdgesTransitionToPlace().remove(edgeTransitionToPlace);
                                }
                            }

                            //Reset Edge

                            for(EdgeReset edgeReset : frame.getPetriNet().getEdgesReset()){
                                if(edgeReset.getIdOfPlace() == id){
                                    frame.getPetriNet().getEdgesReset().remove(edgeReset);
                                }
                            }
                            frame.draw();
                        }
                    }

                }
                if(frame.getPetriNet().getTransition(id)!=null) {
                    for (Transition transition : frame.getPetriNet().getTransitions()) {
                        if (transition.getId() == id) {
                            frame.getPetriNet().getTransitions().remove(transition);
                            //Place to Transition Edge
                           /* for (EdgePlaceToTransition edgePlaceToTransition : frame.getPetriNet().getEdgesPlaceToTransition()) {
                                if (edgePlaceToTransition.getIdOfTransition() == id) {
                                    frame.getPetriNet().getEdgesPlaceToTransition().remove(edgePlaceToTransition);
                                }
                            }*/

                            for(int i = 0;i<frame.getPetriNet().getEdgesPlaceToTransition().size();i++){
                                EdgePlaceToTransition edgePlaceToTransition = frame.getPetriNet().getEdgesPlaceToTransition().get(i);
                                if(edgePlaceToTransition.getIdOfTransition() == id){
                                    frame.getPetriNet().getEdgesPlaceToTransition().remove(edgePlaceToTransition);
                                }
                            }

                            //Transition to Place Edge

                            /*for (EdgeTransitionToPlace edgeTransitionToPlace : frame.getPetriNet().getEdgesTransitionToPlace()) {
                                if (edgeTransitionToPlace.getIdOfTransition() == id) {
                                    frame.getPetriNet().getEdgesTransitionToPlace().remove(edgeTransitionToPlace);
                                }
                            }*/

                            for(int j = 0; j<frame.getPetriNet().getEdgesTransitionToPlace().size();j++){
                                EdgeTransitionToPlace edgeTransitionToPlace = frame.getPetriNet().getEdgesTransitionToPlace().get(j);
                                if(edgeTransitionToPlace.getIdOfTransition() == id){
                                    frame.getPetriNet().getEdgesTransitionToPlace().remove(edgeTransitionToPlace);
                                }
                            }

                            //Reset Edge
                            for (EdgeReset edgeReset : frame.getPetriNet().getEdgesReset()) {
                                if (edgeReset.getIdOfTransition() == id) {
                                    frame.getPetriNet().getEdgesReset().remove(edgeReset);
                                }
                            }
                            frame.draw();
                        }
                    }
                }

                if(frame.getPetriNet().getEdgePlaceToTransition(id)!=null){
                    frame.getPetriNet().getEdgesPlaceToTransition().remove(frame.getPetriNet().getEdgePlaceToTransition(id));
                }

                if(frame.getPetriNet().getEdgeTransitionToPlace(id)!=null){
                    frame.getPetriNet().getEdgesTransitionToPlace().remove(frame.getPetriNet().getEdgeTransitionToPlace(id));
                }
                if(frame.getPetriNet().getEdgeReset(id)!=null){
                    frame.getPetriNet().getEdgesReset().remove(frame.getPetriNet().getEdgeReset(id));
                }

            }
        }
        catch (ConcurrentModificationException ex){
            ex.getMessage();
        }
        frame.draw();

    }
}

package sk.FEI.Kovalak.generated;
import sk.FEI.Kovalak.petrinet.PetriNet;

import java.util.List;

public class Transformer {
    private Importer imp;
    private PetriNet net;

    public Transformer(Importer imp) {
        this.imp = imp;
        this.net = new PetriNet();
    }

    public PetriNet Transformation(){
        Document document = imp.importFromXML(imp.getPath());

        for(Place place : document.place){
            net.addPlace(place.id,place.label,place.tokens,place.x,place.y);
        }

        for(Transition transition : document.transition){
            net.addTransition(transition.id,transition.label,transition.x,transition.y);
        }

        List<Arc> arcs = document.getArc();
        for(Arc arc : arcs){
            ArcType arcType = arc.type;
            if(arcType.getValue() == "regular"){
                if(net.getPlace(arc.getSourceId())!= null){
                    net.addEdgePlaceToTransition(arc.multiplicity,arc.sourceId,arc.destinationId);
                }
                else if(net.getTransition(arc.getSourceId())!=null){
                    net.addEdgeTransitionToPlace(arc.multiplicity,arc.destinationId,arc.sourceId);
                }
            }
            else if(arcType.getValue() == "reset"){
                net.addEdgeReset(arc.sourceId,arc.destinationId);
            }

        }

        return net;
    }


}

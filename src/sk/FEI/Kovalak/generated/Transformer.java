package sk.FEI.Kovalak.generated;
import sk.FEI.Kovalak.petrinet.*;

import java.util.ArrayList;
import java.util.List;

public class Transformer {
    private Importer imp;
    private PetriNet net;
    private Document doc;

    public Transformer(Importer imp) {
        this.imp = imp;
        this.net = new PetriNet();
    }

    public Transformer(PetriNet net){
        this.net = net;
        this.doc = new Document();
    }

    public PetriNet transformationToPetriNet(){
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
                    net.addEdgePlaceToTransition(arc.multiplicity,arc.id,arc.sourceId,arc.destinationId);
                }
                else if(net.getTransition(arc.getSourceId())!=null){
                    net.addEdgeTransitionToPlace(arc.multiplicity,arc.id,arc.destinationId,arc.sourceId);
                }
            }
            else if(arcType.getValue() == "reset"){
                net.addEdgeReset(arc.id,arc.sourceId,arc.destinationId);
            }

        }

        return net;
    }

    public Document transformationToDocument(){
        ObjectFactory factory = new ObjectFactory();

        for(sk.FEI.Kovalak.petrinet.Place petriPlace: net.getPlaces()){
            Place place = factory.createPlace();
            place.setId((int)petriPlace.getId());
            place.setLabel(petriPlace.getName());
            place.setTokens((int)petriPlace.getMarking());
            place.setX(petriPlace.getX());
            place.setY(petriPlace.getY());
            doc.getPlace().add(place);
        }

        for(sk.FEI.Kovalak.petrinet.Transition petriTransition : net.getTransitions()){
            Transition transition = factory.createTransition();
            transition.setId((int)petriTransition.getId());
            transition.setLabel(petriTransition.getName());
            transition.setX(petriTransition.getX());
            transition.setY(petriTransition.getY());
            doc.getTransition().add(transition);
        }

        for(EdgeTransitionToPlace eTtP : net.getEdgesTransitionToPlace()){
            Arc arc = factory.createArc();
            arc.setId((int)eTtP.getId());
            arc.setSourceId((int)eTtP.getIdOfTransition());
            arc.setDestinationId((int)eTtP.getIdOfPlace());
            arc.setMultiplicity((int)eTtP.getWeight());
            arc.setType(ArcType.REGULAR);
            doc.getArc().add(arc);
        }

        for(EdgePlaceToTransition ePtT: net.getEdgesPlaceToTransition()){
            Arc arc = factory.createArc();
            arc.setId((int)ePtT.getId());
            arc.setSourceId((int)ePtT.getIdOfPlace());
            arc.setDestinationId((int)ePtT.getIdOfTransition());
            arc.setMultiplicity((int)ePtT.getWeight());
            arc.setType(ArcType.REGULAR);
            doc.getArc().add(arc);
        }

        for(EdgeReset reset : net.getEdgesReset()){
            Arc arc = factory.createArc();
            arc.setId((int)reset.getId());
            arc.setSourceId((int)reset.getIdOfPlace());
            arc.setDestinationId((int)reset.getIdOfTransition());
            arc.setType(ArcType.REGULAR);
            doc.getArc().add(arc);
        }

        return doc;
    }


}

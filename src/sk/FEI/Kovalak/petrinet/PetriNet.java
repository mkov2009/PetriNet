package sk.FEI.Kovalak.petrinet;
import sk.FEI.Kovalak.exceptions.*;

import java.util.ArrayList;
import java.util.List;

public class PetriNet {
    private List<Transition> transitions = new ArrayList<>();
    private List<Place> places = new ArrayList<>();
    private List<EdgePlaceToTransition> edgesPlaceToTransition = new ArrayList<>();
    private List<EdgeTransitionToPlace> edgesTransitionToPlace = new ArrayList<>();
    private List<EdgeReset> edgesReset = new ArrayList<>();


    public void runnability(long idOfTransition){
        Transition transition = getTransition(idOfTransition);
        boolean runnability = true;

        for(EdgePlaceToTransition edgePlaceToTransition : edgesPlaceToTransition){
            if(edgePlaceToTransition.getIdOfTransition() == idOfTransition){
                Place place = getPlace(edgePlaceToTransition.getIdOfPlace());
                if(place!=null) {
                    if (edgePlaceToTransition.getWeight() > place.getMarking()) {
                        runnability = false;
                    }
                }
            }
        }

        for(EdgeReset edgeReset : edgesReset){
            if(edgeReset.getIdOfTransition() == idOfTransition){
                Place place = getPlace(edgeReset.getIdOfPlace());
                if(place!=null) {
                    if (place.getMarking() == 0 && place != null) {
                        runnability = false;
                    }
                }
            }
        }
        transition.setRunnability(runnability);

    }

    public void runTransition(long idOfTransition){
        runnability(idOfTransition);

        Transition transition = getTransition(idOfTransition);
        if(transition.isRunnable()){
            //taking marks from places
            //place to transition edge
            for(EdgePlaceToTransition edgePlaceToTransition : edgesPlaceToTransition){
                if(edgePlaceToTransition.getIdOfTransition() == idOfTransition){
                    Place place = getPlace(edgePlaceToTransition.getIdOfPlace());
                    if(place!=null) {
                        long marking = place.getMarking();
                        place.setMarking(marking - edgePlaceToTransition.getWeight());
                    }
                }
            }
            //reset edge
            for(EdgeReset edgeReset : edgesReset) {
                if (edgeReset.getIdOfTransition() == idOfTransition){
                    Place place = getPlace(edgeReset.getIdOfPlace());
                    if(place!=null) {
                        place.setMarking(0);
                    }
                }
            }
            //giving marks to places
            for(EdgeTransitionToPlace edgeTransitionToPlace : edgesTransitionToPlace){
                if(edgeTransitionToPlace.getIdOfTransition() == idOfTransition){
                    Place place = getPlace(edgeTransitionToPlace.getIdOfPlace());
                    if(place!=null) {
                        long marking = place.getMarking();
                        place.setMarking(marking + edgeTransitionToPlace.getWeight());
                    }
                }
            }
        }
        else {
            try {
                throw new TransitionNotRunnableException("Transition " + transition.getName() + " is not runnable.");
            } catch (TransitionNotRunnableException exception) {
                System.out.println(exception.getErrorMessage());
            }
        }
    }

    private void testMissingElement(long idOfPlace, long idOfTransition) throws MissingElementException {
        if(getPlace(idOfPlace)==null) {
            throw new MissingElementException("Place ID: " + idOfPlace + " does not exist.");
        }
        if(getTransition(idOfTransition)==null) {
            throw new MissingElementException("Transition2D ID: " + idOfTransition + " does not exist.");
        }

    }

    private void testEdgeAlreadyCreated(long idOfPlace, long idOfTransition, int type) throws ObjectAlreadyExistsException {
        //types:
        //1: Place to Transition Edge
        //2: Transition to Place Edge
        //3: Reset Edge

        switch (type){
            case 1: {
                for(EdgePlaceToTransition edgePlaceToTransition : edgesPlaceToTransition){
                    if(edgePlaceToTransition.getIdOfPlace() == idOfPlace && edgePlaceToTransition.getIdOfTransition() == idOfTransition){
                        throw new ObjectAlreadyExistsException("This Edge already exists.");
                    }
                }
                break;
            }
            case 2: {
                for(EdgeTransitionToPlace edgeTransitionToPlace : edgesTransitionToPlace){
                    if(edgeTransitionToPlace.getIdOfPlace() == idOfPlace && edgeTransitionToPlace.getIdOfTransition() == idOfTransition){
                        throw new ObjectAlreadyExistsException("This Edge already exists.");
                    }
                }
                break;
            }
            case 3: {
                for(EdgeReset edgeReset : edgesReset){
                    if(edgeReset.getIdOfPlace() == idOfPlace && edgeReset.getIdOfTransition() == idOfTransition){
                        throw new ObjectAlreadyExistsException("This Edge already exists.");
                    }
                }
                break;
            }
            default:
                System.out.println("Wrong Type");

        }
    }

    public void checkId(long id) throws IdAlreadyUsedException {
        if(getPlace(id)!=null || getTransition(id)!=null || getEdgePlaceToTransition(id)!=null || getEdgeTransitionToPlace(id)!=null || getEdgeReset(id)!=null){
            throw new IdAlreadyUsedException("Id: " + id + " already exist.") ;
        }

    }

    public void printInfo(){
        System.out.println("Places: ");
        for(Place place : places){
            System.out.println("Name: "+ place.name + " | ID: " + place.id + " | Marking: " + place.getMarking());
        }
        System.out.println("Transitions: ");
        for (Transition transition : transitions){
            runnability(transition.getId());
            System.out.println("Name: " + transition.name + " | ID: " + transition.id + " | Runnable: " + transition.isRunnable());
        }
        System.out.println();

    }

    //Adders
    public void addTransition(long id,String name, int x, int y){
        try{
            checkId(id);
            transitions.add(new Transition(id,name,x,y));
        }
        catch (IdAlreadyUsedException exception){
            System.out.println(exception.getErrorMessage());
            //System.exit(1);
        }
    }

    public void addPlace(long id, String name, long marking, int x, int y){
        try {
            checkId(id);
            places.add(new Place(id, name, marking, x, y));
        }
        catch (WrongMarkingException exception){
            System.out.println(exception.getErrorMessage());
            //System.exit(1);
        }
        catch (IdAlreadyUsedException exception){
            System.out.println(exception.getErrorMessage());
            //System.exit(1);
        }

    }

    public void addEdgePlaceToTransition(long weight, long id, long idOfPlace, long idOfTransition){
       try {
           checkId(id);
           testEdgeAlreadyCreated(idOfPlace,idOfTransition,1);
           testMissingElement(idOfPlace,idOfTransition);
           edgesPlaceToTransition.add(new EdgePlaceToTransition(weight, id, idOfPlace, idOfTransition));
       }
       catch(WrongMarkingException exception){
           System.out.println(exception.getErrorMessage());
          // System.exit(1);
       }
       catch(SameTypeElementException exception){
           System.out.println(exception.getErrorMessage());
           //System.exit(1);
       }
       catch (MissingElementException exception){
           System.out.println(exception.getErrorMessage());
          // System.exit(1);
       }
       catch (IdAlreadyUsedException exception){
           System.out.println(exception.getErrorMessage());
       }
       catch (ObjectAlreadyExistsException exception){
           System.out.println(exception.getErrorMessage());
       }

    }

    public void addEdgeTransitionToPlace(long weight, long id, long idOfPlace, long idOfTransition){
        try {
            checkId(id);
            testEdgeAlreadyCreated(idOfPlace,idOfTransition,2);
            testMissingElement(idOfPlace,idOfTransition);
            edgesTransitionToPlace.add(new EdgeTransitionToPlace(weight, id, idOfPlace, idOfTransition));
        }
        catch(WrongMarkingException exception){
            System.out.println(exception.getErrorMessage());
            //System.exit(1);
        }
        catch(SameTypeElementException exception){
            System.out.println(exception.getErrorMessage());
            //System.exit(1);
        }
        catch (MissingElementException exception){
            System.out.println(exception.getErrorMessage());
            //System.exit(1);
        }
        catch (IdAlreadyUsedException exception){
            System.out.println(exception.getErrorMessage());
        }
        catch (ObjectAlreadyExistsException exception){
            System.out.println(exception.getErrorMessage());
        }
    }

    public void addEdgeReset(long id, long idOfPlace, long idOfTransition){
        try {
            checkId(id);
            testEdgeAlreadyCreated(idOfPlace,idOfTransition,3);
            testMissingElement(idOfPlace, idOfTransition);
            edgesReset.add(new EdgeReset(id, idOfPlace, idOfTransition));
        }
        catch (MissingElementException exception) {
            System.out.println(exception.getErrorMessage());
           // System.exit(1);
        }
        catch(SameTypeElementException exception){
            System.out.println(exception.getErrorMessage());
           // System.exit(1);
        }
        catch (IdAlreadyUsedException exception){
            System.out.println(exception.getErrorMessage());
        }
        catch (ObjectAlreadyExistsException exception){
            System.out.println(exception.getErrorMessage());
        }

    }

    //Getters
    public Transition getTransition(long id){
        for (Transition transition : transitions){
            if(transition.getId() == id){
                return transition;
            }
        }
        return null;
    }

    public Place getPlace(long id){
        for(Place place : places){
            if(place.getId() == id){
                return place;
            }
        }
        return null;
    }

    public EdgePlaceToTransition getEdgePlaceToTransition(long idOfPlace, long idOfTransition) {
        for(EdgePlaceToTransition  edgePlaceToTransition : edgesPlaceToTransition){
            if(edgePlaceToTransition.getIdOfPlace() == idOfPlace && edgePlaceToTransition.getIdOfTransition()==idOfTransition){
                return edgePlaceToTransition;
            }
        }
        return null;
    }

    public EdgeTransitionToPlace getEdgeTransitionToPlace(long idOfPlace, long idOfTransition) {
        for(EdgeTransitionToPlace edgeTransitionToPlace : edgesTransitionToPlace){
            if(edgeTransitionToPlace.getIdOfPlace() == idOfPlace && edgeTransitionToPlace.getIdOfTransition() == idOfTransition){
                return edgeTransitionToPlace;
            }
        }
        return null;
    }

    public EdgeReset getEdgeReset(long idOfPlace,long idOfTransition){
        for(EdgeReset edgeReset : edgesReset){
            if(edgeReset.getIdOfPlace() == idOfPlace && edgeReset.getIdOfTransition() == idOfTransition){
                return edgeReset;
            }
        }
        return null;
    }

    public EdgePlaceToTransition getEdgePlaceToTransition(long id){
        for (EdgePlaceToTransition edgePlaceToTransition : edgesPlaceToTransition){
            if(edgePlaceToTransition.getId() == id){
                return edgePlaceToTransition;
            }
        }
        return null;
    }

    public EdgeTransitionToPlace getEdgeTransitionToPlace(long id){
        for (EdgeTransitionToPlace edgeTransitionToPlace : edgesTransitionToPlace){
            if(edgeTransitionToPlace.getId() == id){
                return edgeTransitionToPlace;
            }
        }
        return null;
    }

    public EdgeReset getEdgeReset(long id){
        for(EdgeReset edgeReset : edgesReset){
            if(edgeReset.getId() == id){
                return edgeReset;
            }
        }
        return null;
    }


    public List<Place> getPlaces(){
        return places;
    }

    public List<Transition> getTransitions(){return transitions;}

    public List<EdgePlaceToTransition> getEdgesPlaceToTransition(){return edgesPlaceToTransition;}

    public List<EdgeTransitionToPlace> getEdgesTransitionToPlace(){return edgesTransitionToPlace;}

    public List<EdgeReset> getEdgesReset(){return edgesReset;}

}

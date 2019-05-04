package sk.FEI.Kovalak.petrinet;
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
                if(edgePlaceToTransition.getWeight() > place.getMarking()){
                    runnability = false;
                }
            }
        }

        for(EdgeReset edgeReset : edgesReset){
            if(edgeReset.getIdOfTransition() == idOfTransition){
                Place place = getPlace(edgeReset.getIdOfPlace());
                if(place.getMarking() == 0){
                    runnability = false;
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
                    long marking = place.getMarking();
                    place.setMarking(marking-edgePlaceToTransition.getWeight());
                }
            }
            //reset edge
            for(EdgeReset edgeReset : edgesReset) {
                if (edgeReset.getIdOfTransition() == idOfTransition){
                    Place place = getPlace(edgeReset.getIdOfPlace());
                    place.setMarking(0);
                }
            }
            //giving marks to places
            for(EdgeTransitionToPlace edgeTransitionToPlace : edgesTransitionToPlace){
                if(edgeTransitionToPlace.getIdOfTransition() == idOfTransition){
                    Place place = getPlace(edgeTransitionToPlace.getIdOfPlace());
                    long marking = place.getMarking();
                    place.setMarking(marking+edgeTransitionToPlace.getWeight());
                }
            }
        }
        else {
            try {
                throw new TransitionNotRunnableException("Transition " + transition.getName() + " is not runnable." + "\n");
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

    public void checkId(long id) throws IdAlreadyUsedException {
        if(getPlace(id)!=null || getTransition(id)!=null){
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
        }
        catch (IdAlreadyUsedException exception){
            System.out.println(exception.getErrorMessage());
            System.exit(1);
        }
        transitions.add(new Transition(id,name,x,y));
    }

    public void addPlace(long id, String name, long marking, int x, int y){
        try {
            checkId(id);
            places.add(new Place(id, name, marking, x, y));
        }
        catch (WrongMarkingException exception){
            System.out.println(exception.getErrorMessage());
            System.exit(1);
        }
        catch (IdAlreadyUsedException exception){
            System.out.println(exception.getErrorMessage());
            System.exit(1);
        }

    }

    public void addEdgePlaceToTransition(long weight, long idOfPlace, long idOfTransition){
       try {
           testMissingElement(idOfPlace,idOfTransition);
           edgesPlaceToTransition.add(new EdgePlaceToTransition(weight, idOfPlace, idOfTransition));
       }
       catch(WrongMarkingException exception){
           System.out.println(exception.getErrorMessage());
           System.exit(1);
       }
       catch(SameTypeElementException exception){
           System.out.println(exception.getErrorMessage());
           System.exit(1);
       }
       catch (MissingElementException exception){
           System.out.println(exception.getErrorMessage());
           System.exit(1);
       }

    }

    public void addEdgeTransitionToPlace(long weight, long idOfPlace, long idOfTransition){
        try {
            testMissingElement(idOfPlace,idOfTransition);
            edgesTransitionToPlace.add(new EdgeTransitionToPlace(weight, idOfPlace, idOfTransition));
        }
        catch(WrongMarkingException exception){
            System.out.println(exception.getErrorMessage());
            System.exit(1);
        }
        catch(SameTypeElementException exception){
            System.out.println(exception.getErrorMessage());
            System.exit(1);
        }
        catch (MissingElementException exception){
            System.out.println(exception.getErrorMessage());
            System.exit(1);
        }
    }

    public void addEdgeReset(long idOfPlace, long idOfTransition){
        try {
            testMissingElement(idOfPlace, idOfTransition);
            edgesReset.add(new EdgeReset(idOfPlace, idOfTransition));
        }
        catch (MissingElementException exception) {
            System.out.println(exception.getErrorMessage());
            System.exit(1);
        }
        catch(SameTypeElementException exception){
            System.out.println(exception.getErrorMessage());
            System.exit(1);
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

    public List<Place> getPlaces(){
        return places;
    }

    public List<Transition> getTransitions(){return transitions;}

    public List<EdgePlaceToTransition> getEdgesPlaceToTransition(){return edgesPlaceToTransition;}

    public List<EdgeTransitionToPlace> getEdgesTransitionToPlace(){return edgesTransitionToPlace;}

    public List<EdgeReset> getEdgesReset(){return edgesReset;}

}

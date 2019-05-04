package sk.FEI.Kovalak.petrinet;

public class EdgeTransitionToPlace extends EdgeWeight {

    public EdgeTransitionToPlace(long weight, long idOfPlace, long idOfTransition) throws SameTypeElementException, WrongMarkingException {
        super(weight,idOfPlace, idOfTransition);
    }

}

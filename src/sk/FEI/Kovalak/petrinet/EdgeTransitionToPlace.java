package sk.FEI.Kovalak.petrinet;

import sk.FEI.Kovalak.exceptions.SameTypeElementException;
import sk.FEI.Kovalak.exceptions.WrongMarkingException;

public class EdgeTransitionToPlace extends EdgeWeight {

    public EdgeTransitionToPlace(long weight,long id, long idOfPlace, long idOfTransition) throws SameTypeElementException, WrongMarkingException {
        super(weight, id, idOfPlace, idOfTransition);
    }

}

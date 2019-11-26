package sk.FEI.Kovalak.petrinet;

import sk.FEI.Kovalak.exceptions.SameTypeElementException;
import sk.FEI.Kovalak.exceptions.WrongMarkingException;

public class EdgePlaceToTransition extends EdgeWeight {

    public EdgePlaceToTransition(long weight,long id, long idOfPlace, long idOfTransition) throws SameTypeElementException, WrongMarkingException {
        super(weight, id, idOfPlace, idOfTransition);
    }
}

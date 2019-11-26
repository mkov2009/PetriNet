package sk.FEI.Kovalak.petrinet;

import sk.FEI.Kovalak.exceptions.SameTypeElementException;

public class EdgeReset extends Edge{
    public EdgeReset(long id, long idOfPlace, long idOfTransition) throws SameTypeElementException {
        super(id,idOfPlace, idOfTransition);
    }


}

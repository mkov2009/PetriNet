package sk.FEI.Kovalak.petrinet;

public class EdgePlaceToTransition extends EdgeWeight {

    public EdgePlaceToTransition(long weight, long idOfPlace, long idOfTransition) throws SameTypeElementException, WrongMarkingException {
        super(weight, idOfPlace, idOfTransition);
    }
}

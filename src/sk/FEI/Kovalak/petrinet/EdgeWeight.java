package sk.FEI.Kovalak.petrinet;

import sk.FEI.Kovalak.exceptions.SameTypeElementException;
import sk.FEI.Kovalak.exceptions.WrongMarkingException;

public class EdgeWeight extends Edge {
    private long weight;

    public EdgeWeight(long weight, long id, long idOfPlace, long idOfTransition) throws WrongMarkingException, SameTypeElementException {
        super(id,idOfPlace, idOfTransition);
        if(weight<0){
            throw new WrongMarkingException("Weight of Edge must be greater than 0.");
        }
        this.weight = weight;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }
}

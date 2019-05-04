package sk.FEI.Kovalak.petrinet;

public class EdgeWeight extends Edge {
    private long weight;

    public EdgeWeight(long weight, long idOfPlace, long idOfTransition) throws WrongMarkingException, SameTypeElementException {
        super(idOfPlace, idOfTransition);
        if(weight<0){
            throw new WrongMarkingException("Weight of Edge must be greater than 0.");
        }
        this.weight = weight;
    }

    public long getWeight() {
        return weight;
    }
}

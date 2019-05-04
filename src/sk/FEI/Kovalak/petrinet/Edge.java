package sk.FEI.Kovalak.petrinet;

public abstract class Edge {
    private long idOfPlace;
    private long idOfTransition;

    public Edge(long idOfPlace, long idOfTransition) throws SameTypeElementException {
        if(idOfPlace==idOfTransition){
            throw new SameTypeElementException("Begin and end of Edge are the same.");
        }
        this.idOfPlace = idOfPlace;
        this.idOfTransition = idOfTransition;
    }


    //getters & setters
    public long getIdOfPlace() {
        return idOfPlace;
    }

    public void setIdOfPlace(long idOfPlace) {
        this.idOfPlace = idOfPlace;
    }

    public long getIdOfTransition() {
        return idOfTransition;
    }

    public void setIdOfTransition(long idOfTransition) {
        this.idOfTransition = idOfTransition;
    }
}

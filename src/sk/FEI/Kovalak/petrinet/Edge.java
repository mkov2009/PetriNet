package sk.FEI.Kovalak.petrinet;

import sk.FEI.Kovalak.exceptions.SameTypeElementException;

public abstract class Edge {
    private long idOfPlace;
    private long idOfTransition;
    private long id;

    public Edge(long id, long idOfPlace, long idOfTransition) throws SameTypeElementException {
        if(idOfPlace==idOfTransition){
            throw new SameTypeElementException("Begin and end of Edge are the same.");
        }
        this.idOfPlace = idOfPlace;
        this.idOfTransition = idOfTransition;
        this.id = id;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

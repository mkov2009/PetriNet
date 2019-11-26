package sk.FEI.Kovalak.graphics;

import sk.FEI.Kovalak.exceptions.IdAlreadyUsedException;
import sk.FEI.Kovalak.petrinet.PetriNet;

public class IdGenerator {
    private long id = 1;
    private PetriNet net;

    public IdGenerator(PetriNet net) {
        this.net = net;
    }

    public long getId(){
        long x;
        try {
            net.checkId(id);
        }
        catch (IdAlreadyUsedException exception){
           // System.out.println(exception.getErrorMessage());
            id++;
            x= getId();
        }
        return id;

    }
}

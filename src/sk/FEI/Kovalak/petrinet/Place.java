package sk.FEI.Kovalak.petrinet;

public class Place extends Base{
    private long marking;

    public Place(long id, String name, long marking, int x, int y) throws WrongMarkingException {
        super(id, name, x, y);
        if(marking<0){
            throw new WrongMarkingException("Marking must be at least 0.");
        }
        else {
            setMarking(marking);
        }
    }

    //getters & setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMarking() {
        return marking;
    }

    public void setMarking(long marking){
        this.marking = marking;
    }
}

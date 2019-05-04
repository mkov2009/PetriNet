package sk.FEI.Kovalak.petrinet;

public class Transition extends Base{

    private boolean runnability;

    public Transition(long id, String name,int x, int y) {
        super(id, name, x, y);
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

    public boolean isRunnable() {
        return runnability;
    }

    public void setRunnability(boolean runnability) {
        this.runnability = runnability;
    }
}

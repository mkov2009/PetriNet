package sk.FEI.Kovalak.graphics;

import sk.FEI.Kovalak.graphics.buttons.*;
import sk.FEI.Kovalak.petrinet.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyFrame extends JFrame{


    private PetriNet petriNet;
    private MyCanvas canvas;
    private JFrame window;

    private long idClicked;

    private boolean runState=false;

    public MyFrame(){
        this.petriNet = new PetriNet();
        this.canvas = new MyCanvas();
        this.window = new JFrame();
        this.idClicked = -1;
    }

    public void frame(){

        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Button importButton = new ImportButton(this,"Import",0,0);
        Button exportButton = new ExportButton(this,"Export",90,0);
        Button addPlaceButton = new AddPlaceButton(this,"Add Place",180,0);
        Button addTransitionButton = new AddTransitionButton(this,"Add Transition", 270,0);
        Button addEdgeButton = new AddEdgeButton(this, "Add Edge",360,0);
        Button addResetEdgeButton = new AddResetEdgeButton(this,"Add Reset Edge",450,0);
        Button plusTokensButton = new PlusButton(this,"Plus",550,0);
        Button minusTokensButton = new MinusButton(this,"Minus",640,0);
        Button deleteButton = new DeleteButton(this,"Delete",730,0);
        Button runButton = new RunButton(this,"Run",820,0);

        window.add(importButton);
        window.add(exportButton);
        window.add(addPlaceButton);
        window.add(addTransitionButton);
        window.add(addEdgeButton);
        window.add(addResetEdgeButton);
        window.add(plusTokensButton);
        window.add(minusTokensButton);
        window.add(deleteButton);
        window.add(runButton);

        window.setSize(920,640);
        window.setTitle("Petrinet");

        window.add(canvas);


        window.setVisible(true);
    }


    public void _addMouseListener(MouseListener a){
        canvas.addMouseListener(a);
        window.add(canvas);

    }

    public void removeMouseListners(){
        for(MouseListener ml : canvas.getMouseListeners())
        {
            canvas.removeMouseListener(ml);
        }
    }

    public void draw(){
        canvas.clearList();
        boolean click = false;

        for(Place place : petriNet.getPlaces()){
            if(place.getId() == idClicked){click = true;}
            else {click = false;}

            Place2D place2D = new Place2D(place.getX(),place.getY(),(int)place.getId(),click);
            canvas.addElement2D(place2D);

            Text2D text2D = new Text2D((place.getX()),place.getY()+50,place.getName());
            canvas.addElement2D(text2D);

            Text2D tokens = new Text2D(place.getX(),place.getY()+15,""+place.getMarking());
            canvas.addElement2D(tokens);

        }

        for(Transition transition :  petriNet.getTransitions()){
            if(transition.getId() == idClicked){click = true;}
            else {click = false;}
            petriNet.runnability(transition.getId());

            Transition2D transition2D = new Transition2D(transition.getX(), transition.getY(),transition.isRunnable(),(int)transition.getId(),runState,click);
            canvas.addElement2D(transition2D);


            Text2D text2D = new Text2D((transition.getX()), transition.getY()+50,transition.getName());
            canvas.addElement2D(text2D);
        }

        for(EdgePlaceToTransition edgePlaceToTransition : petriNet.getEdgesPlaceToTransition()){
            Place place = petriNet.getPlace(edgePlaceToTransition.getIdOfPlace());
            Transition transition = petriNet.getTransition(edgePlaceToTransition.getIdOfTransition());
            if(place!=null && transition!=null) {
                Edge2D edge2D = new Edge2D(edgePlaceToTransition.getId(),place.getX(), place.getY(), transition.getX(), transition.getY(), false, "" + edgePlaceToTransition.getWeight());
                canvas.addElement2D(edge2D);
            }
        }

        for(EdgeTransitionToPlace edgeTransitionToPlace : petriNet.getEdgesTransitionToPlace()){
            Place place = petriNet.getPlace(edgeTransitionToPlace.getIdOfPlace());
            Transition transition = petriNet.getTransition(edgeTransitionToPlace.getIdOfTransition());

            if(place!=null && transition!=null) {
                Edge2D edge2D = new Edge2D(edgeTransitionToPlace.getId(), transition.getX(), transition.getY(), place.getX(), place.getY(), false, "" + edgeTransitionToPlace.getWeight());
                canvas.addElement2D(edge2D);
            }
        }

        for(EdgeReset edgeReset : petriNet.getEdgesReset()){
            Place place = petriNet.getPlace(edgeReset.getIdOfPlace());
            Transition transition = petriNet.getTransition(edgeReset.getIdOfTransition());
            if(place!=null && transition!=null) {
                Edge2D edge2D = new Edge2D(edgeReset.getId(), place.getX(), place.getY(), transition.getX(), transition.getY(), true, "");
                canvas.addElement2D(edge2D);
            }
        }

        window.add(canvas);
        window.setVisible(true);

    }

    public boolean isRunState() {
        return runState;
    }

    public void setRunState(boolean runState) {
        this.runState = runState;
    }


    public MyCanvas getCanvas() {
        return canvas;
    }

    public PetriNet getPetriNet() {
        return petriNet;
    }

    public void setPetriNet(PetriNet petriNet) {
        this.petriNet = petriNet;
    }

    public void setIdClicked(long idClicked) {
        this.idClicked = idClicked;
    }
}

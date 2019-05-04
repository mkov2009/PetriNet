package sk.FEI.Kovalak.graphics;

import sk.FEI.Kovalak.generated.Importer;
import sk.FEI.Kovalak.generated.Transformer;
import sk.FEI.Kovalak.petrinet.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ConcurrentModificationException;
import java.util.List;
import javax.swing.*;

public class MyFrame extends JFrame implements ActionListener, MouseListener{


    private PetriNet petriNet = new PetriNet();
    private MyCanvas canvas = new MyCanvas();
    private JFrame window = new JFrame();

    public void frame(){

        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Button importButton = new Button("Import");
        importButton.setBounds(0,0,80,30);


        importButton.addActionListener(this);

        window.add(importButton);

        window.setSize(920,640);
        window.setTitle("Petrinet");

        canvas.addMouseListener(this);

        window.add(canvas);


        window.setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        try {
            FileChooser fileChooser = new FileChooser();
            Importer importer = new Importer(fileChooser.fileChooser());
            Transformer transformer = new Transformer(importer);
            petriNet = transformer.Transformation();
        }catch (NullPointerException ex){

        }
        draw();
    }


    public void draw(){
        canvas.clearList();

        for(Place place : petriNet.getPlaces()){
            Place2D place2D = new Place2D(place.getX(),place.getY(),(int)place.getId());
            canvas.addElement2D(place2D);

            Text2D text2D = new Text2D((place.getX()),place.getY()+50,place.getName());
            canvas.addElement2D(text2D);

            Text2D tokens = new Text2D(place.getX(),place.getY()+15,""+place.getMarking());
            canvas.addElement2D(tokens);

        }

        for(Transition transition :  petriNet.getTransitions()){
            petriNet.runnability(transition.getId());

            Transition2D transition2D = new Transition2D(transition.getX(), transition.getY(),transition.isRunnable(),(int)transition.getId());
            canvas.addElement2D(transition2D);


            Text2D text2D = new Text2D((transition.getX()), transition.getY()+50,transition.getName());
            canvas.addElement2D(text2D);
        }

        for(EdgePlaceToTransition edgePlaceToTransition : petriNet.getEdgesPlaceToTransition()){
            Place place = petriNet.getPlace(edgePlaceToTransition.getIdOfPlace());
            Transition transition = petriNet.getTransition(edgePlaceToTransition.getIdOfTransition());

            Edge2D edge2D = new Edge2D(place.getX(),place.getY(),transition.getX(),transition.getY(),false,""+edgePlaceToTransition.getWeight());
            canvas.addElement2D(edge2D);
        }

        for(EdgeTransitionToPlace edgeTransitionToPlace : petriNet.getEdgesTransitionToPlace()){
            Place place = petriNet.getPlace(edgeTransitionToPlace.getIdOfPlace());
            Transition transition = petriNet.getTransition(edgeTransitionToPlace.getIdOfTransition());


            Edge2D edge2D = new Edge2D(transition.getX(),transition.getY(),place.getX(),place.getY(),false,""+edgeTransitionToPlace.getWeight());
            canvas.addElement2D(edge2D);
        }

        for(EdgeReset edgeReset : petriNet.getEdgesReset()){
            Place place = petriNet.getPlace(edgeReset.getIdOfPlace());
            Transition transition = petriNet.getTransition(edgeReset.getIdOfTransition());

            Edge2D edge2D = new Edge2D(place.getX(),place.getY(),transition.getX(),transition.getY(),true,"");
            canvas.addElement2D(edge2D);
        }

        window.add(canvas);
        window.setVisible(true);

    }

    public void mouseClicked(MouseEvent e){
        List<Element2D> element2DS = canvas.getElements2D();
        int x = e.getX();
        int y = e.getY();
        try{
        for(Element2D element2D: element2DS) {
            long id = element2D.onClick(x,y);
            if(id!=-1){
                if(petriNet.getTransition(id)!=null){
                    petriNet.runTransition(id);
                    draw();
                }
            }
        }
        }
        catch (ConcurrentModificationException ex){
            ex.getMessage();
        }
    }

    public void mousePressed(MouseEvent e) {

    }
    public void mouseReleased(MouseEvent e) {

    }
    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

}

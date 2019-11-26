package sk.FEI.Kovalak.graphics.buttons;

import sk.FEI.Kovalak.graphics.MyFrame;

import java.awt.*;
import java.awt.event.ActionEvent;

import sk.FEI.Kovalak.generated.Importer;
import sk.FEI.Kovalak.generated.Transformer;
import sk.FEI.Kovalak.graphics.FileChooser;

public class ImportButton extends BaseButton {

    public ImportButton(MyFrame frame, String label, int x, int y) throws HeadlessException {
        super(frame, label, x, y);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            FileChooser fileChooser = new FileChooser();
            Importer importer = new Importer(fileChooser.fileChooser());
            Transformer transformer = new Transformer(importer);
            frame.setPetriNet(transformer.transformationToPetriNet());
        }catch (NullPointerException ex){

        }
        frame.draw();
    }
}

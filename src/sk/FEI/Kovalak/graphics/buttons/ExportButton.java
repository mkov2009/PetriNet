package sk.FEI.Kovalak.graphics.buttons;

import sk.FEI.Kovalak.generated.Exporter;
import sk.FEI.Kovalak.graphics.FileChooser;
import sk.FEI.Kovalak.graphics.MyFrame;

import java.awt.*;
import java.awt.event.ActionEvent;


public class ExportButton extends BaseButton {

    public ExportButton(MyFrame frame, String label, int x, int y) throws HeadlessException {
        super(frame, label, x, y);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        Exporter exporter = new Exporter(frame.getPetriNet());
        exporter.exportToXml(fileChooser.choosePathtoDirectory());
    }
}

package sk.FEI.Kovalak.graphics;


import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser {

    public FileChooser() {
    }

    public String fileChooser() {
    JButton open = new JButton();
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Choose XML");

    fileChooser.setAcceptAllFileFilterUsed(false);
    FileNameExtensionFilter filter = new FileNameExtensionFilter("XML", "xml");
    fileChooser.addChoosableFileFilter(filter);

    if(fileChooser.showOpenDialog(open) == JFileChooser.APPROVE_OPTION){
    }


    String fileName = fileChooser.getSelectedFile().getAbsolutePath();
    return fileName;
    }

}


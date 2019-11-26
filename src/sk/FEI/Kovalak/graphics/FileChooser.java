package sk.FEI.Kovalak.graphics;


import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class FileChooser {

    private JFileChooser fileChooser;

    public FileChooser(){
        fileChooser = new JFileChooser();
    }

    public String fileChooser() {
        JButton open = new JButton();
        fileChooser.setDialogTitle("Choose XML");

        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("XML", "xml");
        fileChooser.addChoosableFileFilter(filter);

        if(fileChooser.showOpenDialog(open) == JFileChooser.APPROVE_OPTION){
        }


        String fileName = fileChooser.getSelectedFile().getAbsolutePath();
        return fileName;
    }

    public String choosePathtoDirectory(){
        JButton confirm = new JButton();
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.setDialogTitle("Save file.");

        fileChooser.setAcceptAllFileFilterUsed(false);

        fileChooser.setApproveButtonText("Save");
        if (fileChooser.showOpenDialog(confirm) == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getAbsolutePath();
        }
        return  null;
    }



}


package sk.FEI.Kovalak.generated;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Importer {
    private Document document;
    private String path;

    public Importer(String path) {
        this.path = path;
    }

    public Document importFromXML(String path){
        try {
            InputStream resource = new FileInputStream(path);
            JAXBContext context = JAXBContext.newInstance(Document.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            document = (Document) unmarshaller.unmarshal(resource);

        } catch (JAXBException e) {
            e.getMessage();
        } catch (FileNotFoundException exp){
            exp.getMessage();
        }

        return document;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

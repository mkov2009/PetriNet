package sk.FEI.Kovalak.generated;

import sk.FEI.Kovalak.petrinet.PetriNet;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class Exporter {

    private String path;
    private PetriNet net;

    public Exporter(PetriNet net) {
        this.net = net;
    }

    public void exportToXml(String path){
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(Document.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            Document document = new Transformer(net).transformationToDocument();

            File file = new File(path+ ".xml");

            marshaller.marshal(document,file);
        } catch (JAXBException e) {
            System.out.println(e.getCause());
        }
    }
}

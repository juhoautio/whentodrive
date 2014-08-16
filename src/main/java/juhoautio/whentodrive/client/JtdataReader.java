package juhoautio.whentodrive.client;

import com.arstraffic.ftt.schemas.locationdata.Jtdata;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;

/**
 * @author Juho Autio
 */
public class JtdataReader {

    private static Unmarshaller unmarshaller;

    public JtdataReader() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Jtdata.class);
            unmarshaller = jaxbContext.createUnmarshaller();
        } catch (JAXBException e) {
            throw new RuntimeException("Couldn't create unmarshaller for Jtdata");
        }
    }

    public Jtdata read(InputStream is) {
        try {
            return (Jtdata) unmarshaller.unmarshal(is);
        } catch (JAXBException e) {
            throw new RuntimeException("Couldn't unmarshall Jtdata object", e);
        }
    }
}

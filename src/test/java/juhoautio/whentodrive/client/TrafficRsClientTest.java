package juhoautio.whentodrive.client;

import juhoautio.whentodrive.configuration.Configuration;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

/**
 * @author Juho Autio
 */
public class TrafficRsClientTest {

    private static final Logger LOG = LoggerFactory.getLogger(TrafficRsClientTest.class);

    private TrafficRsClient client;

    @Before
    public void setUp() throws Exception {
        client = new TrafficRsClient(true, Configuration.read().getClientUser());
    }

    @Test
    public void getFluencyWsdl() throws Exception {
        String wsdl = client.getFluencyWsdl();
        try {
            FileUtils.writeStringToFile(new File("target/trafficFluency.wsdl"), wsdl, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException("Couldn't write to file", e);
        }
        LOG.info("Got WSDL:\n{}", wsdl);
    }

    @Test
    public void getFluency() throws Exception {
        String data = client.getFluency();
        LOG.info("Got data:\n{}", data);
        Document document = parse(data);
        NodeList nodes = select("/Envelope/Body/TrafficFluencyResponse/linkdynamicdata/linkstat",
                document, XPathConstants.NODESET);
        // check that we got a list with more than one link stat entries
        assertThat(nodes.getLength(), is(greaterThan(1)));
        // further work:
        // response could also be read with XmlMapper by using the generated JAX-B classes?
        // any way the main point of this client is to be independent of code generation.
    }

    private Document parse(String data) throws SAXException, IOException, ParserConfigurationException {
        return DocumentBuilderFactory.newInstance().newDocumentBuilder()
                .parse(IOUtils.toInputStream(data));
    }

    @SuppressWarnings("unchecked")
    private <T> T select(String xPath, Document document, QName type) throws XPathExpressionException {
        return (T) XPathFactory.newInstance().newXPath()
                    .evaluate(xPath, document.getDocumentElement(), type);
    }

}

package juhoautio.whentodrive.client;

import com.gofore.sujuvuus.schemas.ObstimeType;
import juhoautio.whentodrive.configuration.Configuration;
import juhoautio.whentodrive.json.JsonUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

/**
 * @author Juho Autio
 */
public class TrafficWsClientTest {

    private static final Logger LOG = LoggerFactory.getLogger(TrafficWsClientTest.class);

    private TrafficWsClient client;

    @Before
    public void setUp() throws Exception {
        client = new TrafficWsClient(true, Configuration.read().getClientUser());
    }

    @Test
    public void getFluency() throws Exception {
        FluencyResponse response = client.getFluency();
        LOG.debug("Timestamp: {}", response.getTimestamp());
        LOG.debug("LastStaticDataupdate: {}", response.getLastStaticDataupdate());
        LOG.debug("Total {} link stats", response.getLinkStats().size());
        LOG.debug("First link stat: {}", JsonUtils.pretty(response.getLinkStats().get(0)));
//        for (LinkStatType linkStat : response.getLinkStats()) {
//            LOG.debug("Got: {}", JsonUtils.pretty(linkStat));
//        }
        assertThat(response.getLinkStats().size(), is(greaterThan(0)));
    }

    // possible test data generators below

    private static ObstimeType createObstime() {
        ObstimeType type = new ObstimeType();
        type.setUtc(now());
        return type;
    }

    private static XMLGregorianCalendar now() {
        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(
                    new DateTime(DateTimeZone.UTC).toGregorianCalendar());
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

}

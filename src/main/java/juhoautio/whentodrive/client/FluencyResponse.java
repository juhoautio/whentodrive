package juhoautio.whentodrive.client;

import com.gofore.sujuvuus.schemas.LinkStatType;
import com.gofore.sujuvuus.schemas.ObstimeType;
import com.gofore.sujuvuus.schemas.TrafficFluencyResponse;
import org.joda.time.DateTime;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.List;

/**
 * A java coder readable response wrapper that circumvents the Holders.
 *
 * @author Juho Autio
 */
public class FluencyResponse {

    private final DateTime timestamp;
    private final DateTime lastStaticDataupdate;
    private final List<LinkStatType> linkStats;

    public FluencyResponse(ObstimeType timestamp, XMLGregorianCalendar lastStaticDataupdate,
                           TrafficFluencyResponse.Linkdynamicdata linkDynamicData) {
        this.timestamp = new DateTime(timestamp.getUtc().toGregorianCalendar());
        this.lastStaticDataupdate = new DateTime(lastStaticDataupdate.toGregorianCalendar());
        this.linkStats = linkDynamicData.getLinkstat();
    }

    public DateTime getTimestamp() {
        return timestamp;
    }

    public DateTime getLastStaticDataupdate() {
        return lastStaticDataupdate;
    }

    public List<LinkStatType> getLinkStats() {
        return linkStats;
    }

}

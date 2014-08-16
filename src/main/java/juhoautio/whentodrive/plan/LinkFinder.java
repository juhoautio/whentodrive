package juhoautio.whentodrive.plan;

import com.arstraffic.ftt.schemas.locationdata.Jtdata;
import com.arstraffic.ftt.schemas.locationdata.SiteType;
import com.arstraffic.ftt.schemas.locationdata.Wgs84CoordinatesType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.AbstractMap;

/**
 * @author Juho Autio
 */
public class LinkFinder {

    private static final Logger LOG = LoggerFactory.getLogger(LinkFinder.class);

    private final static double AVERAGE_RADIUS_OF_EARTH = 6371;
    private final Jtdata data;

    public LinkFinder(Jtdata data) {
        this.data = data;
    }

    /**
     * Nearest neighbour search for two points. Could be replaced with PostGIS or some in-memory spatial java index to
     * scale. For example http://www.vividsolutions.com/jts/jtshome.htm (maven:
     * http://mvnrepository.com/artifact/com.vividsolutions/jts/1.13 ).
     *
     * @throws Exception
     */
    public AbstractMap.SimpleEntry<SiteType, SiteType> findNearestSites(Wgs84CoordinatesType point1, Wgs84CoordinatesType point2) throws Exception {
        long start = System.currentTimeMillis();

        double smallestDistance1 = Double.MAX_VALUE;
        double smallestDistance2 = Double.MAX_VALUE;
        SiteType site1 = null;
        SiteType site2 = null;
        for (SiteType site : data.getSitelist().getSite()) {
            double distanceTo1 = distance(site.getWGS84(), point1);
            if (distanceTo1 < smallestDistance1) {
                smallestDistance1 = distanceTo1;
                site1 = site;
            }
            double distanceTo2 = distance(site.getWGS84(), point2);
            if (distanceTo2 < smallestDistance2) {
                smallestDistance2 = distanceTo2;
                site2 = site;
            }
        }

        long end = System.currentTimeMillis();
        // seems to stay < 0.1s
        LOG.debug("search time = {}ms", end - start);

        return new AbstractMap.SimpleEntry<>(site1, site2);
    }

    /**
     * Calculates distance with Haversine formula. Does not account for the Earth being a spheroid,
     * so only works well for relatively short distances.
     *
     * @return Distance in km.
     */
    private static double distance(Wgs84CoordinatesType point1, Wgs84CoordinatesType point2) {

        double lat1 = point1.getLat().doubleValue();
        double lat2 = point2.getLat().doubleValue();

        double latDistance = Math.toRadians(lat1 - lat2);
        double lngDistance = Math.toRadians(point1.getLon().doubleValue() - point2.getLon().doubleValue());

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return AVERAGE_RADIUS_OF_EARTH * c;
    }

}

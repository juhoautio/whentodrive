package juhoautio.whentodrive.plan;

import com.arstraffic.ftt.schemas.locationdata.Jtdata;
import com.arstraffic.ftt.schemas.locationdata.SiteType;
import com.arstraffic.ftt.schemas.locationdata.Wgs84CoordinatesType;
import juhoautio.whentodrive.client.JtdataReaderTest;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.AbstractMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LinkFinderTest {

    private static final Wgs84CoordinatesType HOME = latLon(60.1979986,24.7862059);
    private static final Wgs84CoordinatesType WORK = latLon(60.1787456,24.8360538);

    private LinkFinder fixture;

    @Before
    public void setUp() throws Exception {
        Jtdata data = JtdataReaderTest.readFile();
        fixture = new LinkFinder(data);
    }

    @Test
    public void findNearestSites() throws Exception {
        AbstractMap.SimpleEntry<SiteType, SiteType> nearest = fixture.findNearestSites(HOME, WORK);
        assertSite(nearest.getKey(), "Kehä I");
        assertSite(nearest.getValue(), "Keilaniemi");
    }

    private void assertSite(SiteType site, String expectedName) {
        System.out.println("site: " + siteToString(site));
        assertThat(resolveName(site), is(expectedName));
    }

    private static Wgs84CoordinatesType latLon(double lat, double lon) {
        Wgs84CoordinatesType coordinates = new Wgs84CoordinatesType();
        coordinates.setLat(new BigDecimal(lat));
        coordinates.setLon(new BigDecimal(lon));
        return coordinates;
    }

    private static String siteToString(SiteType site) {
        return resolveName(site) + " @ " +
                site.getWGS84().getLat().doubleValue() + "," +
                site.getWGS84().getLon().doubleValue();
    }

    /**
     * Get Finnish name, otherwise the first specified name, if no name -> "unknown".
     */
    private static String resolveName(SiteType site) {
        String name = "unknown";
        if (!site.getName().isEmpty()) {
            // default to first entry
            name = site.getName().get(0).getValue();
        }
        for (SiteType.Name localizedName : site.getName()) {
            if ("fi".equals(localizedName.getLanguage())) {
                name = localizedName.getValue();
            }
        }
        return name;
    }

}
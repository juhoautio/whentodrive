package juhoautio.whentodrive.client;

import com.arstraffic.ftt.schemas.locationdata.Jtdata;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

public class JtdataReaderTest {

    private JtdataReader reader;

    @Before
    public void setUp() throws Exception {
        reader = new JtdataReader();
    }

    @Test
    public void read() throws Exception {
        InputStream is = ClassLoader.class.getResourceAsStream("/linkdata.xml");
        Jtdata data = reader.read(is);
        assertThat(data, is(notNullValue()));
        assertThat(data.getLinklist().getLink().size(), greaterThan(0));
    }

}
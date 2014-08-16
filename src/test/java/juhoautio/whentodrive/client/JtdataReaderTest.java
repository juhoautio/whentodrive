package juhoautio.whentodrive.client;

import com.arstraffic.ftt.schemas.locationdata.Jtdata;
import org.junit.Test;

import java.io.InputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

public class JtdataReaderTest {

    private static JtdataReader READER = new JtdataReader();

    @Test
    public void read() throws Exception {
        Jtdata data = readFile();
        assertThat(data, is(notNullValue()));
        assertThat(data.getLinklist().getLink().size(), greaterThan(0));
    }

    public static Jtdata readFile() {
        InputStream is = ClassLoader.class.getResourceAsStream("/linkdata.xml");
        return READER.read(is);
    }

}
package juhoautio.whentodrive.configuration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import juhoautio.whentodrive.json.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Jackson-annotated class for parsing the configuration.
 *
 * @author Juho Autio
 */
public class Configuration {

    public static final String DT_USER_AGENT = "DT-User-Agent";
    public static final String DT_CONTACT_INFO = "DT-Contact-Info";

    private static final Logger LOG = LoggerFactory.getLogger(Configuration.class);

    public static Configuration read() {
        InputStream conf = Configuration.class.getResourceAsStream("/conf.json");
        if (conf == null) {
            File templateFile = new File("src/main/resources/conf-template.json");
            String msg = "No conf.json found. Writing a template to " + templateFile.getAbsolutePath() +
                    "\nAdd the missing values and rename to conf.json";
            LOG.error(msg);
            HashMap<String, String> userMap = new HashMap<>();
            userMap.put(DT_USER_AGENT, null);
            userMap.put(DT_CONTACT_INFO, null);
            Configuration template = new Configuration(userMap);
            try {
                JsonUtils.PRETTY_PRINTER.writeValue(templateFile, template);
            } catch (IOException e) {
                throw new RuntimeException("Writing conf.json template failed", e);
            }
            throw new IllegalArgumentException(msg);
        }
        try {
            return JsonUtils.PRETTY_PRINTER.readValue(conf, Configuration.class);
        } catch (IOException e) {
            throw new RuntimeException("Unable to read configuration", e);
        }
    }

    private final Map<String, String> clientUser;

    @JsonCreator
    public Configuration(@JsonProperty("clientUser") Map<String, String> clientUser) {
        this.clientUser = clientUser;
    }

    public Map<String, String> getClientUser() {
        return clientUser;
    }

}

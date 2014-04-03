package juhoautio.whentodrive.configuration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import juhoautio.whentodrive.json.JsonUtils;
import org.apache.commons.io.FileUtils;
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

    public static final String PLACEHOLDER_USER_AGENT = "TODO: appname-version";
    public static final String PLACEHOLDER_CONTACT_INFO = "TODO: name@example.com";

    public static final String CONF_PATH = "src/main/resources/conf.json";

    private static final Logger LOG = LoggerFactory.getLogger(Configuration.class);

    private final Map<String, String> clientUser;

    @JsonCreator
    public Configuration(@JsonProperty("clientUser") Map<String, String> clientUser) {
        this.clientUser = clientUser;
    }

    public Map<String, String> getClientUser() {
        return clientUser;
    }

    /**
     * Reads configuration from conf.json in classpath.
     *
     * @return configuration
     */
    public static Configuration read() {
        InputStream conf = Configuration.class.getResourceAsStream("/conf.json");
        if (conf == null) {
            return generateConfigurationFile();
        }
        Configuration read;
        try {
            read = JsonUtils.PRETTY_PRINTER.readValue(conf, Configuration.class);
        } catch (IOException e) {
            throw new RuntimeException("Unable to read configuration", e);
        }
        validate(read);
        return read;
    }

    private static void validate(Configuration read) {
        checkPlaceHolderReplaced(read, DT_USER_AGENT, PLACEHOLDER_USER_AGENT);
        checkPlaceHolderReplaced(read, DT_CONTACT_INFO, PLACEHOLDER_CONTACT_INFO);
    }

    private static void checkPlaceHolderReplaced(Configuration read, String key, String placeholderValue) {
        String value = read.getClientUser().get(key);
        if (placeholderValue.equals(value)) {
            throw new IllegalArgumentException("Placeholder value (" + placeholderValue + ") still left for '" + key
                    + "' - fix this in " + CONF_PATH);
        }
    }

    // write conf.json with all required configuration
    private static Configuration generateConfigurationFile() {
        File templateFile = new File(CONF_PATH);
        if (templateFile.exists()) {
            throw new RuntimeException("conf.json exists, but it cannot be found in the classpath - fix your " +
                    "classpath. Building the project again might help.");
        }
        String msg = "No conf.json found." +
                "\nWriting a template to " + templateFile.getAbsolutePath() +
                "\nAdd the missing values!\n";
        LOG.error(msg);
        HashMap<String, String> userMap = new HashMap<>();
        userMap.put(DT_USER_AGENT, PLACEHOLDER_USER_AGENT);
        userMap.put(DT_CONTACT_INFO, PLACEHOLDER_CONTACT_INFO);
        Configuration template = new Configuration(userMap);
        try {
            FileUtils.forceMkdir(templateFile.getParentFile());
            JsonUtils.PRETTY_PRINTER.writeValue(templateFile, template);
        } catch (IOException e) {
            throw new RuntimeException("Writing conf.json template failed", e);
        }
        throw new IllegalArgumentException(msg);
    }

}

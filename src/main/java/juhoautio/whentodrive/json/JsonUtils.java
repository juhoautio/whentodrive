package juhoautio.whentodrive.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Utilities for JSON processing.
 *
 * @author Juho Autio
 */
public class JsonUtils {

    public static final ObjectMapper PRETTY_PRINTER = new ObjectMapper();
    static {
        // pretty print JSON
        PRETTY_PRINTER.enable(SerializationFeature.INDENT_OUTPUT);
    }

    /**
     * Pretty prints any object as JSON.
     *
     * @param o object to serialize
     * @return JSON as a String
     */
    public static String pretty(Object o) {
        if (o == null) {
            return null;
        }
        try {
            return PRETTY_PRINTER.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Writing object failed", e);
        }
    }

}

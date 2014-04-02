package juhoautio.whentodrive.client;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import java.util.Map;

/**
 * HTTP Client with ability to call the DigiTraffic API.
 *
 * @author Juho Autio
 */
public class TrafficRsClient {

    public static final String FLUENCY_PAYLOAD = "<S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
            "  <S:Body>\n" +
            "    <TrafficFluency xmlns=\"http://www.gofore.com/sujuvuus/schemas\"/>\n" +
            "  </S:Body>\n" +
            "</S:Envelope>\n";

    private final Client client;
    private final Map<String, String> userHeaders;

    public TrafficRsClient(boolean logHeaders, Map<String, String> userHeaders) {
        this.userHeaders = userHeaders;
        ClientConfig config = new ClientConfig();
        if (logHeaders) {
            // log request & response headers
            config.register(LoggingFilter.class);
        }
        client = ClientBuilder.newClient(config);
    }

    /**
     * Downloads WSDL.
     *
     * @return Response body
     */
    public String getFluencyWsdl() {
        return client.target("http://open.digitraffic.fi")
                .path("services")
                .path("trafficFluency")
                .queryParam("wsdl", "")
                .request().get(String.class);
    }

    /**
     * Get fluency data as XML
     *
     * @return Response body
     */
    public String getFluency() {
        Invocation.Builder request = client.target("http://open.digitraffic.fi")
                .path("services")
                .path("trafficFluency")
                .request();
        request = addUserHeaders(request);
        return request.post(Entity.xml(FLUENCY_PAYLOAD), String.class);
    }

    private Invocation.Builder addUserHeaders(Invocation.Builder request) {
        for (Map.Entry<String, String> header : userHeaders.entrySet()) {
            request = request.header(header.getKey(), header.getValue());
        }
        return request;
    }

}

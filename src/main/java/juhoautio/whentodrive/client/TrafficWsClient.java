package juhoautio.whentodrive.client;

import com.arstraffic.ftt.schemas.locationdata.Jtdata;
import com.gofore.sujuvuus.schemas.ObstimeType;
import com.gofore.sujuvuus.schemas.TrafficFluencyPort;
import com.gofore.sujuvuus.schemas.TrafficFluencyResponse;
import com.gofore.sujuvuus.schemas.TrafficFluencyService;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Multimaps;
import juhoautio.whentodrive.configuration.Configuration;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.MessageContext;
import java.util.List;
import java.util.Map;

/**
 * @author Juho Autio
 */
public class TrafficWsClient {

    private static final Logger LOG = LoggerFactory.getLogger(TrafficWsClient.class);

    private final TrafficFluencyService service;
    private final boolean logSoapMessages;
    private final Map<String, List<String>> dtUserHeaders;
    private final Configuration conf;
    private final TrafficRsClient restClient;

    public TrafficWsClient(boolean logSoapMessages, Configuration conf) {
        this.logSoapMessages = logSoapMessages;
        ClientConfig config = new ClientConfig();
        config.register(LoggingFilter.class);
        service = new TrafficFluencyService();
        // convert to a multimap
        dtUserHeaders = Multimaps.asMap(ImmutableListMultimap.copyOf(Multimaps
                .forMap(conf.getClientUser())));
        this.conf = conf;
        this.restClient = new TrafficRsClient(false, conf);
        LOG.debug("Read headers: {}", dtUserHeaders);
    }

    public FluencyResponse getFluency() {
        TrafficFluencyPort port = service.getTrafficFluencyPort();
        setDtUserHeaders(port);
        if (logSoapMessages) {
            enableLogging(port);
        }

        Holder<ObstimeType> timestamp = new Holder<>();
        Holder<XMLGregorianCalendar> lastStaticDataupdate = new Holder<>();
        Holder<TrafficFluencyResponse.Linkdynamicdata> linkDynamicData = new Holder<>();

        port.trafficFluency(timestamp, lastStaticDataupdate, linkDynamicData);

        // map response
        return new FluencyResponse(timestamp.value, lastStaticDataupdate.value, linkDynamicData.value);
    }

    public Jtdata getLinkData() {
        return restClient.getLinkData();
    }

    private void enableLogging(TrafficFluencyPort port) {
        Binding binding = ((BindingProvider) port).getBinding();
        List<Handler> handlers = binding.getHandlerChain();
        handlers.add(new LoggingHandler(TrafficWsClient.class.getSimpleName()));
        binding.setHandlerChain(handlers);
    }

    private void setDtUserHeaders(TrafficFluencyPort port) {
        ((BindingProvider) port).getRequestContext().put(MessageContext.HTTP_REQUEST_HEADERS, dtUserHeaders);
    }

}

package juhoautio.whentodrive.client;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Set;

/**
 * SOAP Handler that logs the in & out bound messages.
 *
 * @author Juho Autio
 */
public class LoggingHandler implements SOAPHandler<SOAPMessageContext> {

    private final Logger LOG;

    public LoggingHandler(String owner) {
        LOG = LoggerFactory.getLogger(LoggingHandler.class.getName() + "-" + owner);
    }

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        boolean outbound = ((Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY));
        String prefix = outbound ? ">>" : "<<";
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            context.getMessage().writeTo(out);
        } catch (SOAPException e) {
            LOG.error("Couldn't write message to log", e);
            return true;
        } catch (IOException e) {
            LOG.error("Couldn't write message to log", e);
            return true;
        }
        String msg;
        try {
            msg = IOUtils.toString(out.toByteArray(), "UTF-8");
        } catch (IOException e) {
            LOG.error("Couldn't read message to log", e);
            return true;
        }
        LOG.debug("{} {}", prefix, msg);
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }

    @Override
    public void close(MessageContext context) {
    }

}

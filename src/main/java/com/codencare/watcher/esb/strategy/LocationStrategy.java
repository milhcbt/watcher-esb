package com.codencare.watcher.esb.strategy;

import com.codencare.message.EnrichAlarm;
import com.codencare.message.IMessage;
import com.codencare.message.NettyMessageGeneric;
import com.codencare.watcher.esb.util.DataConverter;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Map;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author abah
 */
public class LocationStrategy implements AggregationStrategy {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnrichAlarm.class);

    public Exchange aggregate(Exchange original, Exchange resource) {
//        try {
//            String template = "INSERT INTO `device`(`id`,`customer_id`) VALUES (%d,0)";
//            String mergedResult = null;
//            Object originalBody = original.getIn().getBody();
//            ArrayList<Map<String, Object>> resourceResponse = resource.getIn().getBody(ArrayList.class);
//            if (resourceResponse.size() < 1) {
//                NettyMessageGeneric dm = new NettyMessageGeneric(original.getIn());
//                long id = DataConverter.bytesToLong(dm.getSrcAddress().getAddress());
//                mergedResult = String.format(template, id);
//                
//                if (original.getPattern().isOutCapable()) {
//                    original.getOut().setBody(mergedResult);
//                } else {
//                    original.getIn().setBody(mergedResult);
//                }
//            }else{
//                Map<String, Object> firstE = resourceResponse.get(0);
//                original.getIn().getHeaders().put(IMessage.X, firstE.get(IMessage.X));
//                original.getIn().getHeaders().put(IMessage.Y, firstE.get(IMessage.X));
//            }
//        } catch (UnknownHostException ex) {
//            LOGGER.error(ex.toString());
//        }
        return original;
    }
}

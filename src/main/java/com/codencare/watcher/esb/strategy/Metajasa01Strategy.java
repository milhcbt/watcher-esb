package com.codencare.watcher.esb.strategy;

import com.codencare.message.EnrichAlarm;
import com.codencare.message.IMessage;
import com.codencare.message.Metajasa01;
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
public class Metajasa01Strategy implements AggregationStrategy {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnrichAlarm.class);

    public Exchange aggregate(Exchange original, Exchange resource) {
//        try {
//            IMessage mergedResult;
//            Object originalBody = original.getIn().getBody();
//            ArrayList<Map<String, Object>> resourceResponse = resource.getIn().getBody(ArrayList.class);
//            if (resourceResponse.size() > 0) {
//                Map<String, Object> firstE = resourceResponse.get(0);
//                mergedResult = new Metajasa01(original.getIn(),
//                        (Integer) firstE.get(IMessage.X),
//                        (Integer) firstE.get(IMessage.X));
//                for (Map<String, Object> m : resourceResponse) {
//                    for (Map.Entry<String, Object> e : m.entrySet()) {
//                        LOGGER.info(e.getKey() + ":" + e.getValue());
//                    }
//                }
//
//                if (original.getPattern().isOutCapable()) {
//                    original.getOut().setBody(mergedResult);
//                } else {
//                    original.getIn().setBody(mergedResult);
//                }
//            }
//            
//
//        } catch (UnknownHostException ex) {
//            LOGGER.error(ex.toString());
//        }
        return original;
    }
}

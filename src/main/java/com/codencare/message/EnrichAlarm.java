/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codencare.message;

import java.io.ByteArrayOutputStream;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Map;
import javax.json.Json;
import javax.json.stream.JsonGenerator;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.jboss.netty.buffer.ChannelBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author abah
 */
public class EnrichAlarm implements AggregationStrategy {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnrichAlarm.class);

    @Override
    public Exchange aggregate(Exchange original, Exchange resource) {
        LOGGER.debug("----properties-----");
        for (Map.Entry<String, Object> entry : original.getProperties().entrySet()) {
            LOGGER.debug("Key : " + entry.getKey() + " Value : "
                    + entry.getValue());
        }
        /*
         Key : CamelNettyMessageEvent Value : [id: 0xb631f37f, /192.168.101.5:4231 :> /192.168.101.102:5000] RECEIVED: BigEndianHeapChannelBuffer(ridx=0, widx=1, cap=1)
         Key : CamelNettyChannelHandlerContext Value : org.jboss.netty.channel.DefaultChannelPipeline$DefaultChannelHandlerContext@48c05ee2
         Key : CamelNettyLocalAddress Value : /192.168.101.102:5000
         Key : breadcrumbId Value : ID-user-THINK-30102-1376712884691-0-1
         Key : CamelNettyRemoteAddress Value : /192.168.101.5:4231
         ori:BigEndianHeapChannelBuffer(ridx=0, widx=1, cap=1)res:[{id=1, name=milh, email=milh_cbt@yahoo.com, password=pass}]
         */
        LOGGER.debug("----headers-----");
        for (Map.Entry<String, Object> entry : original.getIn().getHeaders().entrySet()) {
            LOGGER.debug("Key : " + entry.getKey() + " Value : "
                    + entry.getValue());
        }
        ChannelBuffer originalBody = (ChannelBuffer) original.getIn().getBody();
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resourceResponse = (List<Map<String, Object>>) resource.getIn().getBody();
        int size = originalBody.readableBytes();
        byte[] bytes = new byte[size];
        originalBody.readBytes(bytes);
        LOGGER.debug("number row:" + resourceResponse.size());

        Map<String, Object> mergeResult = getSingleValue(resourceResponse);
        mergeResult.put("message", new String(bytes).trim());// combine original body and resource response
        ByteArrayOutputStream jsonResult = new ByteArrayOutputStream();
        JsonGenerator jg = Json.createGenerator(jsonResult);
        jg.writeStartObject();
        for (Map.Entry<String, Object> e : mergeResult.entrySet()) {
            jg.write(e.getKey(), e.getValue().toString());
        }
        jg.writeEnd().close();

        if (original.getPattern().isOutCapable()) {
            original.getOut().setBody(jsonResult.toString());
        } else {
            original.getIn().setBody(jsonResult.toString());
        }
        LOGGER.debug("return:" + jsonResult.toString());
        return original;
    }

    private Map<String, Object> getSingleValue(List<Map<String, Object>> table) throws InvalidParameterException {
        if (table.size() != 1) {
            throw new InvalidParameterException("Table should only have "
                    + "one row, found:" + table.size() + " row(s)");
        }
        return (Map<String, Object>) table.get(0);

//        LOGGER.debug( "value_pair:" + value_pair.size());
//        if (value_pair.size() != 2) {
//            throw new InvalidParameterException("Table should only have "
//                    + "one row, found:" + table.size() + " row(s)");
//        }
//        return value_pair.values().toArray()[0];
//         
//        for (Object t : table) {
//            LinkedHashMap omap = (LinkedHashMap) t;
//            for (Object e : omap.entrySet()) {
//                Map.Entry<Object, Object> entry = (Map.Entry<Object, Object>) e;
//                LOGGER.debug("Key : " + entry.getKey() + "(" + entry.getKey().getClass().getCanonicalName() + ")"
//                        + " Value : " + entry.getValue() + "(" + entry.getValue().getClass().getCanonicalName() + ")");
//            }
//        }
    }
}

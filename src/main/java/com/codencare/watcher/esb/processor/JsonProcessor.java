/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codencare.watcher.esb.processor;

import java.io.StringWriter;
import java.util.Map;
import java.util.Map.Entry;
import javax.json.Json;
import javax.json.stream.JsonGenerator;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author abah
 */
public class JsonProcessor implements Processor {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonProcessor.class);

    public void process(Exchange exchange) throws Exception {
        Message in = exchange.getIn();
        Map<String, Object> map = in.getBody(Map.class);
//        ByteArrayOutputStream jsonResult = new ByteArrayOutputStream();
        StringWriter jsonResult = new StringWriter();
        JsonGenerator jg = Json.createGenerator(jsonResult);
        jg.writeStartObject();
        for (Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() instanceof String) {
                jg.write(entry.getKey(), entry.getValue().toString());
            } else if (entry.getValue() instanceof Integer) {
                jg.write(entry.getKey(), (Integer) entry.getValue());
            } else if (entry.getValue() instanceof Long) {
                jg.write(entry.getKey(), (Long) entry.getValue());
            } else if (entry.getValue() instanceof Boolean) {
                jg.write(entry.getKey(), (Boolean) entry.getValue());
            } else if (entry.getValue() instanceof Double) {
                jg.write(entry.getKey(), (Double) entry.getValue());
            } else {
                jg.write(entry.getKey(), entry.getValue().toString());
            }
        }
        jg.writeEnd().close();
        LOGGER.info(jsonResult.toString());
        exchange.getIn().setBody(jsonResult.toString());
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codencare.watcher.esb.processor;

import com.codencare.message.MessageLabel;
import java.util.Map;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author abah
 */
public class AlarmSqlProcessor implements Processor {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlarmSqlProcessor.class);

    public void process(Exchange exchng) throws Exception {
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf
                = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String currentTime = sdf.format(dt);
        String template = "UPDATE `device` SET `%s`=%d,`resolve`=%d WHERE id = %d;";
        String sql = "";
        Map<String, Object> dm = exchng.getIn().getBody(Map.class);
        String source = dm.get(MessageLabel.MESSAGE_SOURCE).toString();
        int value = (Integer) dm.get(MessageLabel.MESSAGE_VALUE);
        long id = (Long) dm.get(MessageLabel.ID);
        int resolve = 0;
        if (source.equals(MessageLabel.DIGITAL1) && value == MessageLabel.VALUE_HIGH) {
            resolve = -1;
        } else if (source.equals(MessageLabel.DIGITAL1) && value == MessageLabel.VALUE_LOW) {
            resolve = 1;
        }
        sql = String.format(template, source, value, resolve, id);
        LOGGER.info(sql);
        exchng.getIn().setBody(sql);
    }
}

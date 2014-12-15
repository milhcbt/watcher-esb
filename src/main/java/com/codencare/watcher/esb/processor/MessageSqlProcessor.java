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
public class MessageSqlProcessor implements Processor {

    private static final Logger LOG = LoggerFactory.getLogger(MessageSqlProcessor.class);

    public void process(Exchange exchng) throws Exception {
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf
                = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String currentTime = sdf.format(dt);
        String template = "INSERT INTO `message`(`message`, `device_id`, `msg_type`) VALUES ('%s',%d,%d);";
        String sql = "";
        Map<String, Object> dm = exchng.getIn().getBody(Map.class);
        String message = dm.get(MessageLabel.RAW_MESSAGE).toString();
        long id = (Long) dm.get(MessageLabel.ID);

        sql = String.format(template, message, id, MessageLabel.MESSAGE_IN);
        exchng.getIn().setBody(sql);
    }
}

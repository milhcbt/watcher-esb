/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codencare.watcher.esb.processor;

import com.codencare.message.NettyMessageGeneric;
import com.codencare.watcher.esb.util.DataConverter;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author abah
 */
public class LocationProcessor implements Processor {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocationProcessor.class);

    public void process(Exchange exch) throws Exception {
        String template = "SELECT id,locX,locY FROM `device` WHERE id = %d;";
        NettyMessageGeneric dm = new NettyMessageGeneric(exch.getIn());
        long id = DataConverter.bytesToLong(dm.getSrcAddress().getAddress());
        String sql = String.format(template, id);
        LOGGER.info(sql);
        exch.getIn().setBody(sql);
    }

}

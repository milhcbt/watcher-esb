/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codencare.message;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import org.apache.camel.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author abah
 */
public abstract class MessageBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(NettyMessageGeneric.class);
    public static final Properties defaultProps = new Properties();

    static {
        try {
            InputStream is = MessageBuilder.class.getResourceAsStream("/watcher.esb.properties");
            defaultProps.load(is);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    private MessageBuilder() {
    }

    public abstract ArrayList<Map> createMessage(String rawMessage, String MessageSource);
}

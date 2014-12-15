/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codencare.message;

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
public class DeviceA implements AggregationStrategy {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceA.class);

    public Exchange aggregate(Exchange original, Exchange resource) {
        Object originalBody = original.getIn().getBody();
        ArrayList<Map> resourceResponse = resource.getIn().getBody(ArrayList.class);
        Map mergeResult = resourceResponse.get(0);
        mergeResult.put(MessageLabel.RAW_MESSAGE, originalBody.toString());
        if (originalBody.toString().trim().matches(".*i$")) {
            original.getIn().setHeader(MessageLabel.URGENT, true);
            mergeResult.put(MessageLabel.MESSAGE_SOURCE, MessageLabel.DIGITAL1);
            mergeResult.put(MessageLabel.MESSAGE_VALUE, MessageLabel.VALUE_LOW);
        } else if (originalBody.toString().trim().matches(".*I$")) {
            original.getIn().setHeader(MessageLabel.URGENT, true);
            mergeResult.put(MessageLabel.MESSAGE_SOURCE, MessageLabel.DIGITAL1);
            mergeResult.put(MessageLabel.MESSAGE_VALUE, MessageLabel.VALUE_HIGH);
        } else if (originalBody.toString().trim().matches(".*j$")) {
            mergeResult.put(MessageLabel.MESSAGE_SOURCE, MessageLabel.DIGITAL2);
            mergeResult.put(MessageLabel.MESSAGE_VALUE, MessageLabel.VALUE_LOW);
        } else if (originalBody.toString().trim().matches(".*J$")) {
            mergeResult.put(MessageLabel.MESSAGE_SOURCE, MessageLabel.DIGITAL2);
            mergeResult.put(MessageLabel.MESSAGE_VALUE, MessageLabel.VALUE_HIGH);
        } else if (originalBody.toString().trim().matches(".*k$")) {
            mergeResult.put(MessageLabel.MESSAGE_SOURCE, MessageLabel.DIGITAL3);
            mergeResult.put(MessageLabel.MESSAGE_VALUE, MessageLabel.VALUE_LOW);
        } else if (originalBody.toString().trim().matches(".*K$")) {
            mergeResult.put(MessageLabel.MESSAGE_SOURCE, MessageLabel.DIGITAL3);
            mergeResult.put(MessageLabel.MESSAGE_VALUE, MessageLabel.VALUE_HIGH);
        } else if (originalBody.toString().trim().matches(".*l$")) {
            mergeResult.put(MessageLabel.MESSAGE_SOURCE, MessageLabel.DIGITAL4);
            mergeResult.put(MessageLabel.MESSAGE_VALUE, MessageLabel.VALUE_LOW);
        } else if (originalBody.toString().trim().matches(".*L$")) {
            mergeResult.put(MessageLabel.MESSAGE_SOURCE, MessageLabel.DIGITAL4);
            mergeResult.put(MessageLabel.MESSAGE_VALUE, MessageLabel.VALUE_HIGH);
        } else if (originalBody.toString().trim().matches("M\\d{1,4}$")) {
            mergeResult.put(MessageLabel.MESSAGE_SOURCE, MessageLabel.ANALOG1);
            int value = Integer.parseInt(originalBody.toString().trim().substring(originalBody.toString().trim().lastIndexOf("M") + 1));
            mergeResult.put(MessageLabel.MESSAGE_VALUE, value);
        } else if (originalBody.toString().trim().matches("N\\d{1,4}$")) {
            mergeResult.put(MessageLabel.MESSAGE_SOURCE, MessageLabel.ANALOG2);
            int value = Integer.parseInt(originalBody.toString().trim().substring(originalBody.toString().trim().lastIndexOf("N") + 1));
            mergeResult.put(MessageLabel.MESSAGE_VALUE, value);
        } else if (originalBody.toString().trim().matches("O\\d{1,4}$")) {
            mergeResult.put(MessageLabel.MESSAGE_SOURCE, MessageLabel.ANALOG3);
            int value = Integer.parseInt(originalBody.toString().trim().substring(originalBody.toString().trim().lastIndexOf("O") + 1));
            mergeResult.put(MessageLabel.MESSAGE_VALUE, value);
        } else if (originalBody.toString().trim().matches("P\\d{1,4}$")) {
            mergeResult.put(MessageLabel.MESSAGE_SOURCE, MessageLabel.ANALOG4);
            int value = Integer.parseInt(originalBody.toString().trim().substring(originalBody.toString().trim().lastIndexOf("P") + 1));
            mergeResult.put(MessageLabel.MESSAGE_VALUE, value);
        }

        LOGGER.info(mergeResult.toString());

        if (original.getPattern().isOutCapable()) {
            original.getOut().setBody(mergeResult);
        } else {
            original.getIn().setBody(mergeResult);
        }
        return original;
    }

}

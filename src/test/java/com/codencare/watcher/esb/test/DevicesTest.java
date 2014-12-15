/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codencare.watcher.esb.test;

import com.codencare.message.MessageBuilder;
import java.io.IOException;
import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author abah
 */
public class DevicesTest extends CamelTestSupport {

    @EndpointInject(uri = "mock:result")
    protected MockEndpoint resultEndpoint;

    @Produce(uri = "direct:start")
    protected ProducerTemplate template;

    @Override
    protected RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {
            public void configure() {
                from("direct:start").to("mock:result");
            }
        };
    }

    @Test
    public void testSendMatchingMessage() throws Exception {
        template.sendBodyAndHeader("L", "CamelNettyLocalAddress", "127.0.0.1");

    }
}

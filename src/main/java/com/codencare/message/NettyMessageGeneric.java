/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codencare.message;

import com.codencare.watcher.esb.util.DataConverter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.apache.camel.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author abah
 */
public class NettyMessageGeneric {

    private static final Logger LOGGER = LoggerFactory.getLogger(NettyMessageGeneric.class);
    public static final String CAMEL_NETTY_LOCAL_ADDRESS = "CamelNettyLocalAddress";
    public static final String CAMEL_NETTY_REMOTE_ADDRESS = "CamelNettyRemoteAddress";
    public static final String IP_PORT_SEPARATOR = ":";
    
    private final InetAddress srcAddress;
    private final int srcPort;
    private final InetAddress destAddress;
    private final int destPort;
    private final String body;
    //    private final Map<String,Object> fields;

    public NettyMessageGeneric(Message msg) throws UnknownHostException {

        String src[] = msg.getHeader(CAMEL_NETTY_LOCAL_ADDRESS, String.class).substring(1).split(IP_PORT_SEPARATOR);
        String dest[] = msg.getHeader(CAMEL_NETTY_REMOTE_ADDRESS, String.class).substring(1).split(IP_PORT_SEPARATOR);
        body = msg.getBody(String.class);
        this.srcAddress = InetAddress.getByName(src[0]);
        this.destAddress = InetAddress.getByName(dest[0]);

        srcPort = Integer.parseInt(src[1]);
        destPort = Integer.parseInt(dest[1]);
    }

    public InetAddress getSrcAddress() {
        return srcAddress;
    }

    public int getSrcPort() {
        return srcPort;
    }

    public InetAddress getDestAddress() {
        return destAddress;
    }

    public int getDestPort() {
        return destPort;
    }

    public String getBody() {
        return body;
    }

    public long ipSrcLong() {
        return DataConverter.bytesToLong(srcAddress.getAddress());
    }

    public long ipDestLong() {
        return DataConverter.bytesToLong(destAddress.getAddress());
    }

    @Override
    public String toString() {
        return "DeviceMessageGeneric{" + "srcAddress=" + srcAddress + ", srcPort=" + srcPort + ", destAddress=" + destAddress + ", destPort=" + destPort + ", body=" + body + '}';
    }

}

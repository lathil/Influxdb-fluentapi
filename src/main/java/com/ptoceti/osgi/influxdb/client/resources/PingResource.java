package com.ptoceti.osgi.influxdb.client.resources;

import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;


public interface PingResource {

    static String  path = "ping";
    
    public void ping();
}

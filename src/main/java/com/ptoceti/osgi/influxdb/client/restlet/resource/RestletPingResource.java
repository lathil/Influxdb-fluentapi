package com.ptoceti.osgi.influxdb.client.restlet.resource;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import com.ptoceti.osgi.influxdb.client.resources.PingResource;

public class RestletPingResource implements PingResource {

    ClientResource clientResource;
    
    public RestletPingResource(ClientResource clientResource){
	this.clientResource = clientResource;
    }
    
    @Override
    public void ping() {
	  clientResource.get();
    }
}

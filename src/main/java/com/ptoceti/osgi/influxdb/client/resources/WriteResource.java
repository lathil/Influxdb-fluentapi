package com.ptoceti.osgi.influxdb.client.resources;

import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;

import com.ptoceti.osgi.influxdb.Batch;
import com.ptoceti.osgi.influxdb.Point;
import com.ptoceti.osgi.influxdb.client.exception.InfluxDbApiBadrequestException;
import com.ptoceti.osgi.influxdb.client.exception.InfluxDbApiNotFoundException;


public interface WriteResource {
    
    static String  path = "write";
    
    @Post()
    void write(Point point) throws InfluxDbApiNotFoundException, InfluxDbApiBadrequestException;
    
    @Post()
    void write(Batch batch) throws InfluxDbApiNotFoundException, InfluxDbApiBadrequestException;

}

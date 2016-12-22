package com.ptoceti.osgi.influxdb.client.resources;

import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;

import com.ptoceti.osgi.influxdb.QueryResults;
import com.ptoceti.osgi.influxdb.client.exception.InfluxDbApiBadrequestException;
import com.ptoceti.osgi.influxdb.client.exception.InfluxDbApiNotFoundException;
import com.ptoceti.osgi.influxdb.ql.Query;

public interface QueryResource {

    static String  path = "query";
    
    @Get
    QueryResults get() throws InfluxDbApiNotFoundException, InfluxDbApiBadrequestException;
    
    @Post
    QueryResults post(Query query) throws InfluxDbApiNotFoundException, InfluxDbApiBadrequestException;
}

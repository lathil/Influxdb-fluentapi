package com.ptoceti.osgi.influxdb.client.restlet.resource;

import java.io.IOException;

import org.restlet.Response;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import com.ptoceti.osgi.influxdb.QueryResults;
import com.ptoceti.osgi.influxdb.client.exception.InfluxDbApiBadrequestException;
import com.ptoceti.osgi.influxdb.client.exception.InfluxDbApiNotFoundException;
import com.ptoceti.osgi.influxdb.client.exception.InfluxDbTransportException;
import com.ptoceti.osgi.influxdb.client.resources.QueryResource;
import com.ptoceti.osgi.influxdb.ql.Query;

public class RestletQueryResource implements QueryResource {

    ClientResource clientResource;

    public RestletQueryResource(ClientResource clientResource) {
	this.clientResource = clientResource;

    }

    @Override
    public QueryResults get() throws InfluxDbApiNotFoundException, InfluxDbApiBadrequestException {
	QueryResults result = null;

	try {
	    result = clientResource.get(QueryResults.class);
	} catch (ResourceException ex) {

	    Response response = clientResource.getResponse();
	    if( response.isEntityAvailable()){
		if( ex.getStatus().getCode() == 404){
		    InfluxDbApiNotFoundException t = clientResource.toObject(
                        response.getEntity(), InfluxDbApiNotFoundException.class);
		    throw t;
		} else if(ex.getStatus().getCode() == 400){
		    InfluxDbApiBadrequestException t = clientResource.toObject(
	                        response.getEntity(), InfluxDbApiBadrequestException.class);
		    throw t;
		}
	    }
	    throw ex;
	}

	return result;
    }

    @Override
    public QueryResults post(Query query) throws InfluxDbApiNotFoundException, InfluxDbApiBadrequestException {

	QueryResults result = null;
	try {
	    result = clientResource.post(clientResource.toRepresentation(query), QueryResults.class);
	} catch (ResourceException ex) {
	    Response response = clientResource.getResponse();
	    if( response.isEntityAvailable()){
		if( ex.getStatus().getCode() == 404){
		    InfluxDbApiNotFoundException t = clientResource.toObject(
                        response.getEntity(), InfluxDbApiNotFoundException.class);
		    throw t;
		} else if(ex.getStatus().getCode() == 400){
		    InfluxDbApiBadrequestException t = clientResource.toObject(
	                        response.getEntity(), InfluxDbApiBadrequestException.class);
		    throw t;
		}
	    }
	    throw ex;
	} catch (IOException e) {
	    throw new InfluxDbTransportException("Error building message representation for influxdb server.", e);
	}

	return result;

    }

}

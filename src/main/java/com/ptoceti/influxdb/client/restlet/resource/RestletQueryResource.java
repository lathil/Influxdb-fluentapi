package com.ptoceti.influxdb.client.restlet.resource;

import java.io.IOException;

import org.restlet.Response;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import com.ptoceti.influxdb.QueryResults;
import com.ptoceti.influxdb.client.exception.InfluxDbApiBadrequestException;
import com.ptoceti.influxdb.client.exception.InfluxDbApiNotFoundException;
import com.ptoceti.influxdb.client.exception.InfluxDbTransportException;
import com.ptoceti.influxdb.client.resources.QueryResource;
import com.ptoceti.influxdb.ql.Query;

/**
 * A query resource implemented via Restlet ClientResource
 * 
 * @author LATHIL
 *
 */
public class RestletQueryResource implements QueryResource {

    ClientResource clientResource;

    /**
     * build the resource by wrapping restlet ClientResource
     * 
     * @param clientResource he underlying ClientResource
     */
    public RestletQueryResource(ClientResource clientResource) {
	this.clientResource = clientResource;

    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    public QueryResults post(Query query) throws InfluxDbApiNotFoundException, InfluxDbApiBadrequestException, InfluxDbTransportException, InfluxDbTransportException {

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
    
    /**
     * {@inheritDoc}
     * 
     * Note: this will stop the underlying Client connector
     */
    public void close() {
	this.clientResource.release();
	
    }

}

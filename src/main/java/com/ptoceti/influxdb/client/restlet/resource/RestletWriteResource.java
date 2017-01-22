package com.ptoceti.influxdb.client.restlet.resource;

import java.io.IOException;

import org.restlet.Response;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import com.ptoceti.influxdb.Batch;
import com.ptoceti.influxdb.Point;
import com.ptoceti.influxdb.client.exception.InfluxDbApiBadrequestException;
import com.ptoceti.influxdb.client.exception.InfluxDbApiNotFoundException;
import com.ptoceti.influxdb.client.exception.InfluxDbTransportException;
import com.ptoceti.influxdb.client.resources.WriteResource;

/**
 * A write resource implemented via Restlet ClientResource
 * 
 * @author LATHIL
 *
 */
public class RestletWriteResource implements WriteResource {

    ClientResource clientResource;

    /**
     * build the resource by wrapping restlet ClientResource
     * 
     * @param clientResource he underlying ClientResource
     */
    public RestletWriteResource(ClientResource clientResource) {
	this.clientResource = clientResource;
    }

    /**
     * {@inheritDoc}
     */
    public void write(Point point) throws InfluxDbApiNotFoundException, InfluxDbApiBadrequestException, InfluxDbTransportException {
	try {
	    clientResource.post(clientResource.toRepresentation(point));
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
	} catch (IOException ex) {
	    throw new InfluxDbTransportException("Error building message representation for influxdb server.", ex);
	}
    }

    /**
     * {@inheritDoc}
     */
    public void write(Batch batch) throws InfluxDbApiNotFoundException, InfluxDbApiBadrequestException, InfluxDbTransportException {
	try {
	    clientResource.post(clientResource.toRepresentation(batch));
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
	} catch (IOException ex) {
	    throw new InfluxDbTransportException("Error building message representation for influxdb server.", ex);
	}
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

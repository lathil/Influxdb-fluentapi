package com.ptoceti.osgi.influxdb.client.restlet.resource;

import java.io.IOException;

import org.restlet.Response;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import com.ptoceti.osgi.influxdb.Batch;
import com.ptoceti.osgi.influxdb.Point;
import com.ptoceti.osgi.influxdb.client.exception.InfluxDbApiBadrequestException;
import com.ptoceti.osgi.influxdb.client.exception.InfluxDbApiNotFoundException;
import com.ptoceti.osgi.influxdb.client.exception.InfluxDbTransportException;
import com.ptoceti.osgi.influxdb.client.resources.WriteResource;

public class RestletWriteResource implements WriteResource {

    ClientResource clientResource;

    public RestletWriteResource(ClientResource clientResource) {
	this.clientResource = clientResource;
    }

    @Override
    public void write(Point point) throws InfluxDbApiNotFoundException, InfluxDbApiBadrequestException {
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

    @Override
    public void write(Batch batch) throws InfluxDbApiNotFoundException, InfluxDbApiBadrequestException {
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

}

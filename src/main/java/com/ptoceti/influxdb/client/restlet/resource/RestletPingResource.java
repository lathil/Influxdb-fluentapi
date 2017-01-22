package com.ptoceti.influxdb.client.restlet.resource;

import org.restlet.Response;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import com.ptoceti.influxdb.client.exception.InfluxDbApiBadrequestException;
import com.ptoceti.influxdb.client.exception.InfluxDbApiNotFoundException;
import com.ptoceti.influxdb.client.resources.PingResource;

/**
 * A ping resource implemented via Restlet ClientResource
 * 
 * @author LATHIL
 *
 */
public class RestletPingResource implements PingResource {

    ClientResource clientResource;

    /**
     * build the resource by wrapping restlet ClientResource
     * 
     * @param clientResource the underlying ClientResource
     */
    public RestletPingResource(ClientResource clientResource) {
	this.clientResource = clientResource;
    }

    /**
     * {@inheritDoc}
     */
    public boolean ping() throws InfluxDbApiNotFoundException, InfluxDbApiBadrequestException {

	boolean result = false;
	try {
	    clientResource.get();
	    org.restlet.data.Status status = clientResource.getResponse().getStatus();

	    if (status.getCode() == 204 || status.getCode() == 200) {
		result = true;
	    }

	} catch (ResourceException ex) {

	    Response response = clientResource.getResponse();
	    if (response.isEntityAvailable()) {
		if (ex.getStatus().getCode() == 404) {
		    InfluxDbApiNotFoundException t = clientResource.toObject(response.getEntity(),
			    InfluxDbApiNotFoundException.class);
		    throw t;
		} else if (ex.getStatus().getCode() == 400) {
		    InfluxDbApiBadrequestException t = clientResource.toObject(response.getEntity(),
			    InfluxDbApiBadrequestException.class);
		    throw t;
		}
	    }
	    throw ex;
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

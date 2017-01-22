package com.ptoceti.influxdb.client.restlet.resource;

/*
 * #%L
 * InfluxDb-FluentApi
 * %%
 * Copyright (C) 2016 - 2017 Ptoceti
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


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

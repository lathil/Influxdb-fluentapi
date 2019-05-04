package com.ptoceti.influxdb.client.jersey.resource;

/*
 * #%L
 * InfluxDb-FluentApi
 * %%
 * Copyright (C) 2016 - 2019 Ptoceti
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

import com.ptoceti.influxdb.client.exception.InfluxDbApiBadrequestException;
import com.ptoceti.influxdb.client.exception.InfluxDbApiNotFoundException;
import com.ptoceti.influxdb.client.resources.PingResource;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.core.Response;

public class JerseyPingResource implements PingResource {

    protected Invocation.Builder builder;

    public JerseyPingResource( Invocation.Builder builder) {
        this.builder = builder;
    }

    @Override
    public boolean ping() throws InfluxDbApiNotFoundException, InfluxDbApiBadrequestException {

        boolean result = false;

        Response response;

        try {
            response = builder.get();

            if ( response.getStatus() == Response.Status.OK.getStatusCode() ||  response.getStatus() == Response.Status.NO_CONTENT.getStatusCode()) {
                result = true;
            } else {
                if (response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
                    InfluxDbApiNotFoundException t = new InfluxDbApiNotFoundException();
                    throw t;
                } else if (response.getStatus() == Response.Status.BAD_REQUEST.getStatusCode()) {
                    InfluxDbApiBadrequestException t = new  InfluxDbApiBadrequestException();
                    throw t;
                }
            }

        } catch (ResponseProcessingException ex) {
            throw ex;
        } catch (ProcessingException ex) {
            throw ex;
        } catch (WebApplicationException ex) {
            throw ex;
        }

        return result;
    }

    @Override
    public void close() {
    }
}

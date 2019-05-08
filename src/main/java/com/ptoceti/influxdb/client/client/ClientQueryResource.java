package com.ptoceti.influxdb.client.client;

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

import com.ptoceti.influxdb.QueryResults;
import com.ptoceti.influxdb.client.exception.InfluxDbApiBadrequestException;
import com.ptoceti.influxdb.client.exception.InfluxDbApiNotFoundException;
import com.ptoceti.influxdb.client.exception.InfluxDbTransportException;
import com.ptoceti.influxdb.client.jackson.ObjectMapperFactory;
import com.ptoceti.influxdb.client.resources.QueryResource;
import com.ptoceti.influxdb.ql.Query;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientQueryResource implements QueryResource {

    URI resourceUri;
    HttpClient httpClient;

    public ClientQueryResource(HttpClient client, URI uri){
        httpClient = client;
        resourceUri = uri;
    }


    @Override
    public QueryResults get() throws InfluxDbApiNotFoundException, InfluxDbApiBadrequestException, InfluxDbTransportException {

        HttpRequest request = HttpRequest.newBuilder().uri(resourceUri).header("Content-Type", "application/json")
                .GET().build();

        HttpResponse<String> response;

        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            if( statusCode == 404){
                // not found
                InfluxDbApiNotFoundException t = new InfluxDbApiNotFoundException();
                throw t;
            } else if( statusCode == 400) {
                // bad request
                InfluxDbApiBadrequestException t = new  InfluxDbApiBadrequestException();
                throw t;
            }

            return ObjectMapperFactory.configure().readValue(response.body(), QueryResults.class);

        } catch (IOException ex) {
            throw new InfluxDbTransportException("Write: IOException: " , ex);
        } catch (InterruptedException ex) {
            throw new InfluxDbTransportException("Write: InterruptedException: " , ex);
        }


    }

    @Override
    public QueryResults post(Query query) throws InfluxDbApiNotFoundException, InfluxDbApiBadrequestException, InfluxDbTransportException {

        HttpRequest request = HttpRequest.newBuilder().uri(resourceUri).header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString( "q=" + query.toQL())).build();

        HttpResponse<String> response;

        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            if( statusCode == 404){
                // not found
                InfluxDbApiNotFoundException t = new InfluxDbApiNotFoundException();
                throw t;
            } else if( statusCode == 400) {
                // bad request
                InfluxDbApiBadrequestException t = new  InfluxDbApiBadrequestException();
                throw t;
            }

            return ObjectMapperFactory.configure().readValue(response.body(), QueryResults.class);

        } catch (IOException ex) {
            throw new InfluxDbTransportException("Write: IOException: " , ex);
        } catch (InterruptedException ex) {
            throw new InfluxDbTransportException("Write: InterruptedException: " , ex);
        }



    }

    @Override
    public void close() {

    }
}

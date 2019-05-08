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

import com.ptoceti.influxdb.client.exception.InfluxDbApiBadrequestException;
import com.ptoceti.influxdb.client.exception.InfluxDbApiNotFoundException;
import com.ptoceti.influxdb.client.exception.InfluxDbTransportException;
import com.ptoceti.influxdb.client.resources.PingResource;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientPingResource implements PingResource {

    URI resourceUri;
    HttpClient httpClient;

    public ClientPingResource(HttpClient client, URI uri){
        httpClient = client;
        resourceUri = uri;
    }

    @Override
    public boolean ping() throws InfluxDbApiNotFoundException, InfluxDbApiBadrequestException, InfluxDbTransportException {

        boolean result = false;

        HttpRequest request = HttpRequest.newBuilder().uri(resourceUri).GET().build();

        HttpResponse<String> response;

        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (IOException ex) {
            throw new InfluxDbTransportException("Ping: IOException: " , ex);
        } catch (InterruptedException ex) {
            throw new InfluxDbTransportException("Ping: InterruptedException: " , ex);
        }

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

        if( statusCode == 200 || statusCode == 201 || statusCode == 204){
            result = true;
        }

        return result;
    }

    @Override
    public void close() {

    }
}

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

import com.ptoceti.influxdb.Batch;
import com.ptoceti.influxdb.Point;
import com.ptoceti.influxdb.client.exception.InfluxDbApiBadrequestException;
import com.ptoceti.influxdb.client.exception.InfluxDbApiNotFoundException;
import com.ptoceti.influxdb.client.exception.InfluxDbTransportException;
import com.ptoceti.influxdb.client.resources.WriteResource;
import com.ptoceti.influxdb.converter.LineProtocol;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;

public class ClientWriteResource implements WriteResource {

    URI resourceUri;
    HttpClient httpClient;
    private LineProtocol lineProtocol;

    public ClientWriteResource(HttpClient client, URI uri) {
        httpClient = client;
        resourceUri = uri;
        lineProtocol = new LineProtocol();
    }

    @Override
    public void write(Point point) throws InfluxDbApiNotFoundException, InfluxDbApiBadrequestException, InfluxDbTransportException {

        HttpRequest request = HttpRequest.newBuilder().uri(resourceUri).header("Content-Type", "application/octet-stream")
                .POST( HttpRequest.BodyPublishers.ofByteArray(lineProtocol.toLine(point).getBytes(Charset.forName("UTF-8")))).build();

        HttpResponse<String> response;

        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (IOException ex) {
            throw new InfluxDbTransportException("Write: IOException: " , ex);
        } catch (InterruptedException ex) {
            throw new InfluxDbTransportException("Write: InterruptedException: " , ex);
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
    }

    @Override
    public void write(Batch batch) throws InfluxDbApiNotFoundException, InfluxDbApiBadrequestException, InfluxDbTransportException {
        HttpRequest request = HttpRequest.newBuilder().uri(resourceUri).header("Content-Type", "application/octet-stream")
                .POST( HttpRequest.BodyPublishers.ofByteArray(lineProtocol.toLine(batch).getBytes(Charset.forName("UTF-8")))).build();

        HttpResponse<String> response;

        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (IOException ex) {
            throw new InfluxDbTransportException("Write: IOException: " , ex);
        } catch (InterruptedException ex) {
            throw new InfluxDbTransportException("Write: InterruptedException: " , ex);
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
    }

    @Override
    public void close() {

    }
}

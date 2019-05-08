package com.ptoceti.influxdb.factory;

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

import com.ptoceti.influxdb.Consistency;
import com.ptoceti.influxdb.Epoch;
import com.ptoceti.influxdb.Precision;
import com.ptoceti.influxdb.client.client.ClientPingResource;
import com.ptoceti.influxdb.client.client.ClientQueryResource;
import com.ptoceti.influxdb.client.client.ClientWriteResource;
import com.ptoceti.influxdb.client.exception.InfluxDbResourceException;
import com.ptoceti.influxdb.client.resources.PingResource;
import com.ptoceti.influxdb.client.resources.QueryResource;
import com.ptoceti.influxdb.client.resources.WriteResource;
import com.ptoceti.influxdb.ql.Query;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;

public class ResourceFactory implements InfluxDbResourceFactory {

    protected URL target;

    protected String dbName;

    protected String username;

    protected String password;

    protected Boolean pretty = false;

    protected HttpClient client;

    public ResourceFactory(URL target) {


        client = HttpClient.newHttpClient();

        this.target = target;
    }

    @Override
    public WriteResource getWriteResource(Consistency consistency, String database, Precision precision, String retentionPolicy)  throws InfluxDbResourceException{

        ClientWriteResource wResource;
        StringBuffer query = new StringBuffer();

        if (consistency != null ) {
            query = query.append("&").append(CONSISTENCYPARAMNAME).append(consistency.getValue());
        }

        if (database != null && !database.isEmpty()) {
            query = query.append("&").append(WRITEDBNAMEPARAM).append("=").append(database);
        } else if (dbName != null) {
            query = query.append("&").append(WRITEDBNAMEPARAM).append("=").append(dbName);
        }

        if (password != null && !password.isEmpty()) {
            query = query.append("&").append(PASSWORDPARAMNAME).append("=").append(password);
        }

        if (precision != null ) {
            query =  query.append("&").append(PRECISONPARAMNAME).append("=").append(precision.getValue());
        }

        if (retentionPolicy != null && !retentionPolicy.isEmpty()) {
            query =  query.append("&").append(RETENTIONPOLICYPARAMNAME).append("=").append(retentionPolicy);
        }

        if (username != null && !username.isEmpty()) {
            query =  query.append("&").append(USERPARAMNAME).append("=").append(username);
        }

        String sQuery = query.toString();
        if( sQuery.startsWith("&")){
            sQuery = sQuery.substring(1);
        }

        try {
            URI uri = new URI(this.target.getProtocol(), this.target.getUserInfo(), this.target.getHost(), this.target.getPort(), WriteResource.path.startsWith("/") ? WriteResource.path : "/" + WriteResource.path, sQuery, null);
            wResource = new ClientWriteResource(client, uri);
        } catch ( URISyntaxException ex ) {
            throw new InfluxDbResourceException( "Error creating WriteResource", ex);
        }


        return wResource;
    }

    @Override
    public WriteResource getWriteResource() throws InfluxDbResourceException {
        return getWriteResource(null, null, null, null);
    }

    @Override
    public QueryResource getQueryResource() throws InfluxDbResourceException{

        ClientQueryResource qResource;
        StringBuffer query = new StringBuffer();

        if (pretty) {
            query = query.append("&").append(PRETTY).append("=true");
        }

        String sQuery = query.toString();
        if( sQuery.startsWith("&")){
            sQuery = sQuery.substring(1);
        }

        try {
            URI uri = new URI( this.target.getProtocol(), this.target.getUserInfo(), this.target.getHost(), this.target.getPort(), QueryResource.path.startsWith("/") ? QueryResource.path : "/" + QueryResource.path, sQuery.isEmpty() ? null : sQuery, null);
            qResource = new ClientQueryResource(client, uri);
        } catch ( URISyntaxException ex ) {
            throw new InfluxDbResourceException( "Error creating QueryResource", ex);
        }
        //Invocation.Builder builder = wtarget.request(MediaType.APPLICATION_JSON);

        return qResource;

    }

    @Override
    public QueryResource getQueryResource(Query query) throws InfluxDbResourceException {
        return getQueryResource(query, null, null);
    }

    @Override
    public QueryResource getQueryResource(Query influxdbquery, Epoch epoch, String retentionPolicy) throws InfluxDbResourceException{

        ClientQueryResource qResource;
        StringBuffer query = new StringBuffer();

        if (pretty) {
            query = query.append("&").append(PRETTY).append("=true");
        }

        query = query.append("&").append(WRITEDBNAMEPARAM).append("=").append(getDbName());

        if (epoch != null ) {
            query = query.append("&").append(EPOCH).append("=").append(epoch.getValue());
        }

        if (retentionPolicy != null && !retentionPolicy.isEmpty()) {
            query = query.append("&").append(RETENTIONPOLICYPARAMNAME).append("=").append(retentionPolicy);
        }

        query = query.append("&").append(QUERYPARAMNAME).append("=").append(influxdbquery.toQL());


        String sQuery = query.toString();
        if( sQuery.startsWith("&")){
            sQuery = sQuery.substring(1);
        }

        try {
            URI uri = new URI( this.target.getProtocol(), this.target.getUserInfo(), this.target.getHost(), this.target.getPort(), QueryResource.path.startsWith("/") ? QueryResource.path : "/" + QueryResource.path, sQuery, null);
            qResource = new ClientQueryResource(client, uri);
        } catch ( URISyntaxException ex ) {
            throw new InfluxDbResourceException( "Error creating QueryResource", ex);
        }
        //Invocation.Builder builder = wtarget.request(MediaType.APPLICATION_JSON);



        return qResource;

    }

    @Override
    public PingResource getPingResource() throws InfluxDbResourceException{

        ClientPingResource pResource;
        StringBuffer query = new StringBuffer(this.target.toExternalForm());
        try {
            URI uri = new URI( this.target.getProtocol(), this.target.getUserInfo(), this.target.getHost(), this.target.getPort(), PingResource.path.startsWith("/") ? PingResource.path : "/" + PingResource.path, null, null);
            pResource = new ClientPingResource(client, uri);
        } catch ( URISyntaxException ex ) {
            throw new InfluxDbResourceException( "Error creating PingResource", ex);
        }

        return pResource;
    }

    /**
     *
     * {@inheritDoc}
     */
    public String getDbName() {
        return dbName;
    }

    /**
     * {@inheritDoc}
     */
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    /**
     * {@inheritDoc}
     */
    public String getUsername() {
        return username;
    }

    /**
     * {@inheritDoc}
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * {@inheritDoc}
     */
    public String getPassword() {
        return password;
    }

    /**
     * {@inheritDoc}
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * {@inheritDoc}
     */
    public Boolean getPretty() {
        return pretty;
    }

    /**
     * {@inheritDoc}
     */
    public void setPretty(Boolean pretty) {
        this.pretty = pretty;
    }
}

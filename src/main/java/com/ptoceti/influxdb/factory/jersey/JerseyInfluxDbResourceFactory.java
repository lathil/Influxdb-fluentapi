package com.ptoceti.influxdb.factory.jersey;

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
import com.ptoceti.influxdb.client.jersey.converter.BatchMessageBodyWriter;
import com.ptoceti.influxdb.client.jersey.converter.PointMessageBodyWriter;
import com.ptoceti.influxdb.client.jersey.converter.QueryMessageBodyWriter;
import com.ptoceti.influxdb.client.jersey.resource.JerseyPingResource;
import com.ptoceti.influxdb.client.jersey.resource.JerseyQueryResource;
import com.ptoceti.influxdb.client.jersey.resource.JerseyWriteResource;
import com.ptoceti.influxdb.client.resources.PingResource;
import com.ptoceti.influxdb.client.resources.QueryResource;
import com.ptoceti.influxdb.client.resources.WriteResource;
import com.ptoceti.influxdb.factory.InfluxDbResourceFactory;
import com.ptoceti.influxdb.ql.Query;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.net.URL;

public class JerseyInfluxDbResourceFactory implements InfluxDbResourceFactory {

    protected URL target;

    protected String dbName;

    protected String username;

    protected String password;

    protected Boolean pretty = false;

    protected Client client;



    public JerseyInfluxDbResourceFactory(URL target) {

        client = ClientBuilder.newClient();
        client.register(new BatchMessageBodyWriter());
        client.register(new PointMessageBodyWriter());
        client.register(new QueryMessageBodyWriter());
        this.target = target;

    }


    @Override
    public WriteResource getWriteResource(Consistency consistency, String database, Precision precision, String retentionPolicy) {

        WebTarget wtarget = client.target(this.target.toExternalForm());

        if (consistency != null ) {
            wtarget = wtarget.queryParam(CONSISTENCYPARAMNAME, consistency.getValue());
        }

        if (database != null && !database.isEmpty()) {
            wtarget = wtarget.queryParam(WRITEDBNAMEPARAM, database);
        } else if (dbName != null) {
            wtarget = wtarget.queryParam(WRITEDBNAMEPARAM, dbName);
        }

        if (password != null && !password.isEmpty()) {
            wtarget = wtarget.queryParam(PASSWORDPARAMNAME, password);
        }

        if (precision != null ) {
            wtarget = wtarget.queryParam(PRECISONPARAMNAME, precision.getValue());
        }

        if (retentionPolicy != null && !retentionPolicy.isEmpty()) {
            wtarget = wtarget.queryParam(RETENTIONPOLICYPARAMNAME, retentionPolicy);
        }

        if (username != null && !username.isEmpty()) {
            wtarget = wtarget.queryParam(USERPARAMNAME, username);
        }

        wtarget = wtarget.path(WriteResource.path);


        JerseyWriteResource wResource = new JerseyWriteResource(wtarget.request());
        return wResource;
    }

    @Override
    public WriteResource getWriteResource() {
        return getWriteResource(null, null, null, null);
    }

    @Override
    public QueryResource getQueryResource() {

        WebTarget wtarget = client.target(this.target.toExternalForm());

        if (pretty) {
            wtarget = wtarget.queryParam(PRETTY, "true");
        }

        wtarget = wtarget.path(QueryResource.path);

        Invocation.Builder builder = wtarget.request(MediaType.APPLICATION_JSON);

        JerseyQueryResource qResource = new JerseyQueryResource(builder);

        return qResource;

    }

    @Override
    public QueryResource getQueryResource(Query query) {
        return getQueryResource(query, null, null);
    }

    @Override
    public QueryResource getQueryResource(Query query, Epoch epoch, String retentionPolicy) {

        WebTarget wtarget = client.target(this.target.toExternalForm());

        if (pretty) {
            wtarget = wtarget.queryParam(PRETTY, "true");
        }

        wtarget = wtarget.queryParam(WRITEDBNAMEPARAM, getDbName());

        if (epoch != null ) {
            wtarget = wtarget.queryParam(EPOCH, epoch.getValue());
        }

        if (retentionPolicy != null && !retentionPolicy.isEmpty()) {
            wtarget = wtarget.queryParam(RETENTIONPOLICYPARAMNAME, retentionPolicy);
        }

        wtarget = wtarget.queryParam(QUERYPARAMNAME, query.toQL());

        wtarget = wtarget.path(QueryResource.path);

        Invocation.Builder builder = wtarget.request(MediaType.APPLICATION_JSON);

        JerseyQueryResource qResource = new JerseyQueryResource(builder);

        return qResource;

    }

    @Override
    public PingResource getPingResource() {

        WebTarget wtarget = client.target(this.target.toExternalForm());
        wtarget = wtarget.path(PingResource.path);

        JerseyPingResource pResource = new JerseyPingResource(wtarget.request());

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

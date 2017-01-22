package com.ptoceti.influxdb.factory.restlet;

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


import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.restlet.Client;
import org.restlet.Context;
import org.restlet.data.MediaType;
import org.restlet.data.Protocol;
import org.restlet.engine.Engine;
import org.restlet.resource.ClientResource;

import com.ptoceti.influxdb.Consistency;
import com.ptoceti.influxdb.Epoch;
import com.ptoceti.influxdb.Precision;
import com.ptoceti.influxdb.client.resources.PingResource;
import com.ptoceti.influxdb.client.resources.QueryResource;
import com.ptoceti.influxdb.client.resources.WriteResource;
import com.ptoceti.influxdb.client.restlet.converter.InfluxDbConverter;
import com.ptoceti.influxdb.client.restlet.resource.RestletPingResource;
import com.ptoceti.influxdb.client.restlet.resource.RestletQueryResource;
import com.ptoceti.influxdb.client.restlet.resource.RestletWriteResource;
import com.ptoceti.influxdb.factory.InfluxDbResourceFactory;
import com.ptoceti.influxdb.ql.Query;

/**
 * A factory to create query, write and ping resource for a influxdb server.
 * 
 * This implementation use Restlet as its client provider.
 * 
 * @author LATHIL
 * 
 * 
 * 
 */
public class RestletInfluxDbResourceFactory implements InfluxDbResourceFactory {


    protected URL target;

    protected String dbName;

    protected String username;

    protected String password;

    protected Boolean pretty = false;

    /**
     * The client connector use by all resources created by this factory
     */
    protected Client client;

    /**
     * Create a resource factory based on the url of the influxdb server
     * 
     * An underlying client connector is created that will communicate with the
     * Server. Restlet with select the appropriate client depending on what is
     * in your class path. If no external connector is found, it will use its
     * own internal connector.
     * 
     * @see https 
     *      ://restlet.com/technical-resources/restlet-framework/guide/2.3/core
     *      /base/connectors
     * 
     * @param target
     *            the url of the targeted influxdb server.
     */
    public RestletInfluxDbResourceFactory(URL target) {

	List<Protocol> protocols = new ArrayList<Protocol>();
	String protocol = target.getProtocol();
	if (protocol != null && !protocol.isEmpty()) {
	    protocols.add(Protocol.valueOf(protocol));
	} else {
	    protocols.add(Protocol.HTTP);
	    protocols.add(Protocol.HTTPS);
	}
	client = new Client(new Context(), protocols);

	this.target = target;

	Engine.getInstance().getRegisteredConverters().add(new InfluxDbConverter());

    }

    /**
     * {@inheritDoc}
     */
    public WriteResource getWriteResource(Consistency consistency, String database, Precision precision, String retentionPolicy) {

	ClientResource clientResource = new ClientResource(target.toExternalForm());
	clientResource.setNext(client);

	if (consistency != null ) {
	    clientResource.addQueryParameter(CONSISTENCYPARAMNAME, consistency.getValue());
	}

	if (database != null && !database.isEmpty()) {
	    clientResource.addQueryParameter(WRITEDBNAMEPARAM, database);
	} else if (dbName != null) {
	    clientResource.addQueryParameter(WRITEDBNAMEPARAM, dbName);
	}

	if (password != null && !password.isEmpty()) {
	    clientResource.addQueryParameter(PASSWORDPARAMNAME, password);
	}

	if (precision != null ) {
	    clientResource.addQueryParameter(PRECISONPARAMNAME, precision.getValue());
	}

	if (retentionPolicy != null && !retentionPolicy.isEmpty()) {
	    clientResource.addQueryParameter(RETENTIONPOLICYPARAMNAME, retentionPolicy);
	}

	if (username != null && !username.isEmpty()) {
	    clientResource.addQueryParameter(USERPARAMNAME, username);
	}

	clientResource.addSegment(WriteResource.path);

	RestletWriteResource wResource = new RestletWriteResource(clientResource);
	return wResource;

    }

    /**
     * {@inheritDoc}
     */
    public WriteResource getWriteResource() {
	return getWriteResource(null, null, null, null);
    }

    /**
     * {@inheritDoc}
     */
    public QueryResource getQueryResource() {

	ClientResource clientResource = new ClientResource(target.toExternalForm());
	clientResource.accept(MediaType.APPLICATION_ALL_JSON);
	clientResource.accept(MediaType.APPLICATION_JSON);
	clientResource.setNext(client);

	if (pretty) {
	    clientResource.addQueryParameter(PRETTY, "true");
	}

	clientResource.addSegment(QueryResource.path);
	RestletQueryResource qResource = new RestletQueryResource(clientResource);

	return qResource;

    }

    /**
     * {@inheritDoc}
     */
    public QueryResource getQueryResource(Query query) {

	return getQueryResource(query, null, null);

    }

    /**
     * {@inheritDoc}
     */
    public QueryResource getQueryResource(Query query, Epoch epoch, String retentionPolicy) {

	ClientResource clientResource = new ClientResource(target.toExternalForm());
	clientResource.accept(MediaType.APPLICATION_ALL_JSON);
	clientResource.accept(MediaType.APPLICATION_JSON);
	clientResource.setNext(client);

	clientResource.addSegment(QueryResource.path);
	clientResource.addQueryParameter(WRITEDBNAMEPARAM, getDbName());

	if (epoch != null ) {
	    clientResource.addQueryParameter(EPOCH, epoch.getValue());
	}

	if (retentionPolicy != null && !retentionPolicy.isEmpty()) {
	    clientResource.addQueryParameter(RETENTIONPOLICYPARAMNAME, retentionPolicy);
	}

	clientResource.addQueryParameter(QUERYPARAMNAME, query.toQL());

	if (pretty) {
	    clientResource.addQueryParameter(PRETTY, "true");
	}

	RestletQueryResource qResource = new RestletQueryResource(clientResource);

	return qResource;

    }

   /**
    * {@inheritDoc}
    */
    public PingResource getPingResource() {

	ClientResource clientResource = new ClientResource(target.toExternalForm());
	clientResource.setNext(client);

	clientResource.addSegment(PingResource.path);

	RestletPingResource pResource = new RestletPingResource(clientResource);
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

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


import java.net.URL;

/**
 * A builder to configure a RestletInfluxDbResourceFactory in a fluent api manner.
 * 
 * @author LATHIL
 *
 */
public class InfluxDbFactoryBuilder {

    private InfluxDbResourceFactory factory = null;

    /**
     * Create the builder with the provided factory
     * 
     * @param factory the factory to configure
     */
    private InfluxDbFactoryBuilder(InfluxDbResourceFactory factory) {
	this.factory = factory;
    }

    /**
     * Create a builder with the provided influxdb url
     * 
     * @param target the url of the influxdb server
     * 
     * @return a factory builder
     */
    public static InfluxDbFactoryBuilder build(URL target) {
	ResourceFactory newFactory = new ResourceFactory(target);
	return new InfluxDbFactoryBuilder(newFactory);
    }

    /**
     * Get the configured factory
     * 
     * @return the influxdb factory
     */
    public InfluxDbResourceFactory getFactory() {
	return factory;
    }

    /**
     * Configure the factory with the name of database
     * 
     * @param dbName the name of the influxdb database
     * 
     * @return the factory builder
     */
    public InfluxDbFactoryBuilder dbName(String dbName) {
	factory.setDbName(dbName);
	return this;
    }

    /**
     * Configure the factory with the username to use when submiting requests
     * 
     * @param userName the username to use in cas of authentification
     * @return the factory builder
     */
    public InfluxDbFactoryBuilder userName(String userName) {
	factory.setUsername(userName);
	return this;
    }

    /**
     * Configure the factory with the password to use when submitting request
     * 
     * @param password the password to use in case of authentification
     * @return the factory builder
     */
    public InfluxDbFactoryBuilder password(String password) {
	factory.setPassword(password);
	return this;
    }
    
    /**
     * Configure the factory with the flag for prettyformatting of query results
     * 
     * @return the factory builder
     */
    public InfluxDbFactoryBuilder pretty() {
	factory.setPretty(true);
	return this;
    }
}

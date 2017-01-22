package com.ptoceti.influxdb.factory;

import java.net.URL;

import com.ptoceti.influxdb.factory.restlet.RestletInfluxDbResourceFactory;

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
	RestletInfluxDbResourceFactory newFactory = new RestletInfluxDbResourceFactory(target);
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

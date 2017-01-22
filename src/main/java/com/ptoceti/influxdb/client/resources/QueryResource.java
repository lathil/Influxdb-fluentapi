package com.ptoceti.influxdb.client.resources;

import com.ptoceti.influxdb.QueryResults;
import com.ptoceti.influxdb.client.exception.InfluxDbApiBadrequestException;
import com.ptoceti.influxdb.client.exception.InfluxDbApiNotFoundException;
import com.ptoceti.influxdb.client.exception.InfluxDbTransportException;
import com.ptoceti.influxdb.ql.Query;

/**
 * A resource to send queries to an Influxdb server
 * 
 * @author LATHIL
 * 
 */
public interface QueryResource {

    static String path = "query";

    /**
     * Send the query to the server and parse the response. The query is sent
     * through GET method
     * 
     * @return the parsed query result
     * @throws InfluxDbApiNotFoundException
     *             resource or database not found
     * @throws InfluxDbApiBadrequestException
     *             request unacceptable, not understood.
     */
    QueryResults get() throws InfluxDbApiNotFoundException, InfluxDbApiBadrequestException, InfluxDbTransportException;

    /**
     * Send the query to the server and parse the reponse. the query is sent
     * through POST method
     * 
     * @param query
     *            the query to post
     * @return the parsed query result
     * @throws InfluxDbApiNotFoundException
     *             resource or database not found
     * @throws InfluxDbApiBadrequestException
     *             request unacceptable, not understood.
     */
    QueryResults post(Query query) throws InfluxDbApiNotFoundException, InfluxDbApiBadrequestException, InfluxDbTransportException;

    /**
     * Free linked resources
     * 
     */
    void close();
}

package com.ptoceti.influxdb.client.resources;

/*
 * #%L
 * InfluxDb-FluentApi
 * %%
 * Copyright (C) 2016 - 2018 Ptoceti
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

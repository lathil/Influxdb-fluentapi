package com.ptoceti.influxdb.factory;

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


import com.ptoceti.influxdb.Consistency;
import com.ptoceti.influxdb.Epoch;
import com.ptoceti.influxdb.Precision;
import com.ptoceti.influxdb.client.resources.PingResource;
import com.ptoceti.influxdb.client.resources.QueryResource;
import com.ptoceti.influxdb.client.resources.WriteResource;
import com.ptoceti.influxdb.ql.Query;


/**
 * A factory to create query, write and ping resource for a influxdb server.
 * 
 * @author LATHIL
 *
 */
public interface InfluxDbResourceFactory {

    static final String QUERYPARAMNAME = "q";
    static final String WRITEDBNAMEPARAM = "db";
    static final String PRECISONPARAMNAME = "precision";
    static final String RETENTIONPOLICYPARAMNAME = "rp";
    static final String CONSISTENCYPARAMNAME = "consistency";
    static final String PASSWORDPARAMNAME = "p";
    static final String USERPARAMNAME = "u";
    static final String PRETTY = "pretty";
    static final String EPOCH = "epoch";
    
    /**
     * Configure a resource to write a point in the configured database
     * 
     * curl -i -XPOST 'http://localhost:8086/write?db=mydb' --data-binary
     * 'cpu_load_short,host=server01,region=us-west value=0.64
     * 1434055562000000000'
     * 
     * @param consistency
     *            Sets the write consistency for the point
     * @param database
     *            Sets the target database for the write.
     * @param precision
     *            Sets the precision for the supplied Unix time values.
     * @param retentionPolicy
     *            Sets the target retention policy for the write
     * @return the configured resource
     */
    WriteResource getWriteResource(Consistency consistency, String database, Precision precision, String retentionPolicy);
    
    /**
     * Configure a resource to write a point in the configured database
     * 
     * curl -i -XPOST 'http://localhost:8086/write?db=mydb' --data-binary
     * 'cpu_load_short,host=server01,region=us-west value=0.64
     * 1434055562000000000'
     * 
     * @return the configured resource
     */
    WriteResource getWriteResource();
    
    /**
     * Prepare a resource for sending queries through POST method to the
     * influxdb server.
     * 
     * ex: curl -XPOST 'http://localhost:8086/query' --data-urlencode 'q=CREATE
     * DATABASE "mydb"'
     * 
     * Should be used for any query with statement other that SELECT or SHOW.
     * Statement such as SELECT * INTO should be send throught POST as well.
     * 
     * @return the prepared query.
     */
    QueryResource getQueryResource();
    
    /**
     * Prepare a query to be sent by GET method to the influxdb server The query
     * must be sent as url-encoded in the query string:
     * 
     * ex: curl -GET 'http://localhost:8086/query?pretty=true' --data-urlencode
     * "db=mydb" --data-urlencode
     * "q=SELECT \"value\" FROM \"cpu_load_short\" WHERE \"region\"='us-west'"
     * 
     * Should normaly only be used for query with SELECT or SHOW statements.
     * 
     * @param query
     *            the SELECT or SHOW query
     * @return the prepared query.
     */
    QueryResource getQueryResource(Query query);
    
    /**
     * Prepare a query to be sent by GET method to the influxdb server The query
     * must be sent as url-encoded in the query string:
     * 
     * ex: curl -GET 'http://localhost:8086/query?pretty=true' --data-urlencode
     * "db=mydb" --data-urlencode
     * "q=SELECT \"value\" FROM \"cpu_load_short\" WHERE \"region\"='us-west'"
     * 
     * Should normaly only be used for query with SELECT or SHOW statements.
     * 
     * @param query
     *            the SELECT or SHOW query
     * @param epoch
     *            return timestamp in the specified format. Default format is
     *            RFC3389. epoch=[h,m,s,ms,u,ns]
     * @param retentionPolicy
     *            specify the target retention policy to use. If not specified
     *            influx use the database default policy
     * @return the prepared query.
     */
    QueryResource getQueryResource(Query query, Epoch epoch, String retentionPolicy);
    
    /**
     * Create a query resource to ping targeted influxdb server.
     * 
     * @return the ping resource.
     */
    PingResource getPingResource();
    
    
    /**
     * Property accessor
     * 
     * @return the database name the factory is configured with
     */
    String getDbName();
    
    /**
     * Set the name of the database to use when building request.
     * 
     * @param dbName
     */
    void setDbName(String dbName);
    
    /**
     * Property accessor
     * 
     * @return the username the factory is configured with
     */
    String getUsername();
    
    /**
     * Set username property if authentification is needed to post request to
     * influxdb
     * 
     * @param username
     *            the username to send to influxdb
     */
    void setUsername(String username);
    
    /**
     * Property accessor
     * 
     * @return the password the factory is configured with
     */
    String getPassword();
    
    /**
     * Set password property if authentification is needed to post request to
     * influxdb
     * 
     * @param password
     *            the password to send to influxdb
     */
    void setPassword(String password);
    
    /**
     * Property accessor
     * 
     * @return the pretty flag the factory is configured with
     */
    Boolean getPretty();
    
    /**
     * By default the factory create \query queries without the pretty flag. Set
     * the flag to true to ask influxdb to return query result in pretty format.
     * 
     * @param pretty
     *            true for pretty formated results
     */
    void setPretty(Boolean pretty);
}

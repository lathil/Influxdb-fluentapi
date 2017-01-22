package com.ptoceti.influxdb.client.resources;

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



import com.ptoceti.influxdb.Batch;
import com.ptoceti.influxdb.Point;
import com.ptoceti.influxdb.client.exception.InfluxDbApiBadrequestException;
import com.ptoceti.influxdb.client.exception.InfluxDbApiNotFoundException;
import com.ptoceti.influxdb.client.exception.InfluxDbTransportException;


/**
 * A resource to write points and batch to an Influxdb srever
 * 
 * @author LATHIL
 *
 */
public interface WriteResource {
    
    static String  path = "write";
    
    /**
     * Send the point to the server using the line-protocol format
     * 
     * @param point the point to be sent
     * 
     * @throws InfluxDbApiNotFoundException
     *             resource or database not found
     * @throws InfluxDbApiBadrequestException
     *             request unacceptable, not understood.
     */
    void write(Point point) throws InfluxDbApiNotFoundException, InfluxDbApiBadrequestException, InfluxDbTransportException;
    
    /**
     * Send the batch to the server using the line-protocol format
     * 
     * @param batch the batch to be sent
     * 
     * @throws InfluxDbApiNotFoundException
     *             resource or database not found
     * @throws InfluxDbApiBadrequestException
     *             request unacceptable, not understood.
     */
    void write(Batch batch) throws InfluxDbApiNotFoundException, InfluxDbApiBadrequestException, InfluxDbTransportException;

    /**
     * Free linked resources
     * 
     */
    void close();
}

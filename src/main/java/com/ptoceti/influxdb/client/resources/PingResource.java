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


import com.ptoceti.influxdb.client.exception.InfluxDbApiBadrequestException;
import com.ptoceti.influxdb.client.exception.InfluxDbApiNotFoundException;

/**
 * A resource to ping a Influxdb server and test the connection
 * 
 * @author LATHIL
 *
 */
public interface PingResource {

    static String  path = "ping";
    
    /**
     * Send ping to the server
     * 
     * @return true if the server responded correctly
     * 
     * @throws InfluxDbApiNotFoundException resource or database not found
     * @throws InfluxDbApiBadrequestException request unacceptable, not understood.
     */
    boolean ping() throws InfluxDbApiNotFoundException, InfluxDbApiBadrequestException;
    
    /**
     * Free linked resources
     * 
     */
    void close();
}

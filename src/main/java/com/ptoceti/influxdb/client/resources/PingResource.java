package com.ptoceti.influxdb.client.resources;

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

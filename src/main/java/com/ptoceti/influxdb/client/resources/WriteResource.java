package com.ptoceti.influxdb.client.resources;


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

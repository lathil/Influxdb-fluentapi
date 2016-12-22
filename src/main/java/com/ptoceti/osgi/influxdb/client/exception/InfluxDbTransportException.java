package com.ptoceti.osgi.influxdb.client.exception;

public class InfluxDbTransportException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public InfluxDbTransportException(String message, Throwable cause){
	super(message, cause);
    }
    
}

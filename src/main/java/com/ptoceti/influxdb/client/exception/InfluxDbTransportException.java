package com.ptoceti.influxdb.client.exception;

public class InfluxDbTransportException extends Exception {

    /**
     * An Exception return when generating the request to InfluxDb format causes an exception
     */
    private static final long serialVersionUID = 1L;

    public InfluxDbTransportException(String message, Throwable cause){
	super(message, cause);
    }
    
}

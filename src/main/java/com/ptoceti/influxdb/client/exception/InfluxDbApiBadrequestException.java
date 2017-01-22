package com.ptoceti.influxdb.client.exception;

import java.util.List;

import org.restlet.resource.Status;

import com.ptoceti.influxdb.Result;

/**
 * An Exception that Influxdb return when it has not understood the request.
 * 
 * InfluxDb return a message in a error field of the direct response and in each
 * error field of result elements.
 * 
 * @author LATHIL
 * 
 */
public class InfluxDbApiBadrequestException extends Exception {

    /**
     * An Exception that Influxdb return when it has not understood the request.
     * 
     * influxDb return a message in a error field of the direct response and in
     * each error field of result elements.
     * 
     */
    private static final long serialVersionUID = 1L;

    private List<Result> results;
    private String error;

    InfluxDbApiBadrequestException() {
	super();
    }

    @Override
    public String getMessage() {
	return getError();
    }

    /**
     * Getter
     * 
     * @return the error
     */
    public String getError() {
	return error;
    }

    /**
     * Set the error as return by Influx
     * 
     * @param error
     *            influx error message
     */
    public void setError(String error) {
	this.error = error;
    }

    /**
     * Getter
     * 
     * @return the list of results
     */
    public List<Result> getResults() {
	return results;
    }

    /**
     * Set the results as returned by Influx
     * 
     * @return the list of results from influx
     */
    public void setResults(List<Result> results) {
	this.results = results;
    }
}

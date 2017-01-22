package com.ptoceti.influxdb;

import java.util.List;

/**
 * Represent the results of a query from the /query endpoint
 * 
 * @author LATHIL
 *
 */
public class QueryResults {
    
    private List<Result> results;
    private String error;
    
    /**
     * Get the error for the query if http 400 or 404 is return
     * 
     * @return the error message
     */
    public String getError() {
	return error;
    }
    
   
    public void setError(String error) {
	this.error = error;
    }
    
    /**
     * Get the list of results;
     * 
     * @return a list of result
     */
    public List<Result> getResults() {
	return results;
    }
    public void setResults(List<Result> results) {
	this.results = results;
    }
    

}

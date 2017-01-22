package com.ptoceti.influxdb;

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

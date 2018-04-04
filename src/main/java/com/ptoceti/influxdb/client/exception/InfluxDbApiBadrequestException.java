package com.ptoceti.influxdb.client.exception;

/*
 * #%L
 * InfluxDb-FluentApi
 * %%
 * Copyright (C) 2016 - 2018 Ptoceti
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

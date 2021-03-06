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



/**
 * Epoch enum, for use in /query attributes
 * 
 * @author LATHIL
 *
 */
public enum Epoch {

    HOURS("h"),
    MINUTE("m"),
    SECOND("s"),
    MILLISECOND("ms"),
    MICROS("u"),
    NANOS("ns");
    
    private String value;

    private Epoch(String value) {
	this.value = value;
    }
    
    public String getValue(){
	return value;
    }
}

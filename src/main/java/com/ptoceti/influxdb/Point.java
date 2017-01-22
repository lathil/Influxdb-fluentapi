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


import java.util.Calendar;
import java.util.Collections;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Point {

    // the name of the table
    private String measurement;
    // tags are indexed, used for searching
    private SortedMap<String, String> tags = null;
    // fields are the values
    private SortedMap<String, Object> fields = null;

    private long timestamp;

    public Point(String measurement) {
	this(measurement, 
		Collections.synchronizedSortedMap(new TreeMap<String, String>()), 
		Collections.synchronizedSortedMap(new TreeMap<String, Object>()));

    }
    
    public Point(String measurement, SortedMap<String, String> tags, SortedMap<String, Object> fields){
	this.measurement = measurement;
	this.timestamp = (Calendar.getInstance().getTimeInMillis());
	
	this.tags = tags;
	this.fields = fields;
    }
    
    public String getMeasurement(){
	return measurement;
    }
    
    public Map<String, String> getTags(){
	return tags;
    }
    
    public Map<String, Object> getFields(){
	return fields;
    }

    public void addTag(String tagName, String tagValue) {
	tags.put(tagName, tagValue);
    }

    public void addValue(String fieldName, Object fieldValue) {
	fields.put(fieldName, fieldValue);
    }

    public void addValue(String fieldName, Object fieldValue, long newTimeStampMillis) {
	fields.put(fieldName, fieldValue);
	timestamp = newTimeStampMillis;
    }
    
    
    public Object getFieldValue(String key){
	return fields.get(key);
    }
    
    public Object getTagValue(String key){
	return tags.get(key);
    }

    public long getTimestamp() {
	return timestamp;
    }
    
    public void setTimestamp(long timestamp){
	this.timestamp = timestamp;
    }

}

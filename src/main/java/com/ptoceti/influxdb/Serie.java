package com.ptoceti.influxdb;

/*
 * #%L
 * InfluxDb-FluentApi
 * %%
 * Copyright (C) 2016 - 2019 Ptoceti
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
import java.util.Map;

public class Serie {

    private String name;
    private List<String> columns;
    private Map<String, String> tags;
    private List<List<String>> values;
    
    public String getName() {
	return name;
    }
    
    public void setName(String name) {
	this.name = name;
    }
    
    public List<String> getColumns() {
	return columns;
    }
    
    public void setColomns(List<String> columns) {
	this.columns = columns;
    }
    
    public List<List<String>> getValues() {
	return values;
    }
    
    public void setValues(List<List<String>> values) {
	this.values = values;
    }

    public Map<String, String> getTags() {
	return tags;
    }

    public void setTags(Map<String, String> tags) {
	this.tags = tags;
    }
    
    public int size(){
	return this.values.size();
    }
    
}

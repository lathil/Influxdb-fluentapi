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


/**
 * An internal DSL builder for creating points.
 * 
 * @author LATHIL
 *
 */
public class PointBuilder {

    private Point point;
    private BatchBuilder batchBuilder;
    
    
    public PointBuilder(BatchBuilder builder, String measurement){
	this.batchBuilder = builder;
	point = new Point(measurement);
    }
    
    public PointBuilder(String measurement){
	point = new Point(measurement);
    }
    
    public static PointBuilder Point(String measurement){
	return new PointBuilder(measurement);
    }
    
    public PointBuilder addTag(String tagName, String tagValue){
	point.addTag(tagName, tagValue);
	return this;
    }
    
    public PointBuilder addField(String fieldName, Object fieldValue){
	point.addValue(fieldName, fieldValue);
	return this;
    }
    
    public PointBuilder setTimestamp(long timestamp){
	point.setTimestamp(timestamp);
	return this;
    }
    
    public Point getPoint(){
	return point;
    }
    
    public BatchBuilder add(){
	return batchBuilder;
    }
}

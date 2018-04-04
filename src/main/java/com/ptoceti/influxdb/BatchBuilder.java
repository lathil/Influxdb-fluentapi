package com.ptoceti.influxdb;

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



/**
 * An internal DSL builder for creating batchs of points.
 * 
 * 
 * @author LATHIL
 *
 */
public class BatchBuilder {

    private Batch batch;
    
    public BatchBuilder(){
	batch = new Batch();
    }
    
    public static BatchBuilder Batch(){
	return new BatchBuilder();
    }
    
    public PointBuilder point(String measurement){
	PointBuilder builder = new PointBuilder(this, measurement);
	batch.addPoint(builder.getPoint());
	return builder;
    }
    
    public Batch getBatch(){
	return this.batch;
    }
}

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


import java.util.Iterator;
import java.util.List;


public class RetentionPolicySerie extends SerieWrapper implements Iterator<RetentionPolicySerie.RetentionPolicy>, Iterable<RetentionPolicySerie.RetentionPolicy> {

    protected static final String NAMEFIELD = "name";
    protected static final String DURATIONFIELD = "duration";
    protected static final String SHARDGROUPDURATIONFIELD = "shardGroupDuration";
    protected static final String REPLICANFIELD = "replicaN";
    protected static final String DEFAULTFIELD = "default";

    public RetentionPolicySerie(Serie serie) {
	super(serie);
    }
    
    @Override
    public Iterator<RetentionPolicy> iterator() {
	return this;
    }

    @Override
    public boolean hasNext() {
	return delegate.hasNext();
    }

    @Override
    public void remove() {
	delegate.remove();

    }

    @Override
    public RetentionPolicy next() {
	return new RetentionPolicy(delegate.next());
    }

    public class RetentionPolicy {

	private String name;
	private String duration;
	private String shardGroupDuration;
	private Integer replicaN;
	private Boolean isdefault;
	
	public RetentionPolicy(List<String> values) {

	    setName( (String)values.get(fields.get(NAMEFIELD)));
	    setDuration( (String)values.get(fields.get(DURATIONFIELD)));
	    setShardGroupDuration( (String)values.get(fields.get(SHARDGROUPDURATIONFIELD)));
	    setReplicaN( Integer.getInteger((String)values.get(fields.get(REPLICANFIELD))));
	    setIsdefault( Boolean.getBoolean((String)values.get(fields.get(DEFAULTFIELD))));
	    
	}
	
	public String getName() {
	    return name;
	}
	public void setName(String name) {
	    this.name = name;
	}
	public String getDuration() {
	    return duration;
	}
	public void setDuration(String duration) {
	    this.duration = duration;
	}
	public String getShardGroupDuration() {
	    return shardGroupDuration;
	}
	public void setShardGroupDuration(String shardGroupDuration) {
	    this.shardGroupDuration = shardGroupDuration;
	}
	public Integer getReplicaN() {
	    return replicaN;
	}
	public void setReplicaN(Integer replicaN) {
	    this.replicaN = replicaN;
	}
	public Boolean getIsdefault() {
	    return isdefault;
	}
	public void setIsdefault(Boolean isdefault) {
	    this.isdefault = isdefault;
	}
    }
}

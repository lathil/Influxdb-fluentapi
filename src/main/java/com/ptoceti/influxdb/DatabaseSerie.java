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


import java.util.Iterator;
import java.util.List;


public class DatabaseSerie extends SerieWrapper implements Iterator<DatabaseSerie.Database>, Iterable<DatabaseSerie.Database>{

    protected static final String SERIENAME = "databases";
    protected static final String NAMEFIELD = "name";
    
    public DatabaseSerie(Serie serie) {
	super(serie);
    }
    
    @Override
    public Iterator<Database> iterator() {
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
    public DatabaseSerie.Database next(){
	 return new Database(delegate.next());
    }
    
    public static String getSerieName(){
	return SERIENAME;
    }
    
    public class Database {
	
	private String name;
	
	Database(List<String> values){
	    setName( (String)values.get(fields.get(NAMEFIELD)));
	}

	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
	}
    }
 
}

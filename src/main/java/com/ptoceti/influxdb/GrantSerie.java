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

public class GrantSerie extends SerieWrapper implements Iterator<GrantSerie.Grant>, Iterable<GrantSerie.Grant>{

    protected static final String DATABASEFIELD = "database";
    protected static final String PRIVILEGEFIELD = "privilege";
    
    public GrantSerie(Serie serie) {
	super(serie);
    }
    
    @Override
    public Iterator<Grant> iterator() {
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
    public Grant next(){
	 return new Grant(delegate.next());
    }
    
    public class Grant{
	
	private String database;
	private String privilege;
	
	public Grant(List<String> values){
	    setDatabase( (String)values.get(fields.get(DATABASEFIELD)));
	    setPrivilege( (String)values.get(fields.get(PRIVILEGEFIELD)));
	}

	public String getDatabase() {
	    return database;
	}

	public void setDatabase(String database) {
	    this.database = database;
	}

	public String getPrivilege() {
	    return privilege;
	}

	public void setPrivilege(String privilege) {
	    this.privilege = privilege;
	}
    }


}

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


import java.util.Iterator;
import java.util.List;

public class UserSerie extends SerieWrapper implements Iterator<UserSerie.User>, Iterable<UserSerie.User>{

    protected static final String USERFIELD = "user";
    protected static final String ADMINFIELD = "admin";
    
    
    public UserSerie(Serie serie) {
	super(serie);
    }
    
    @Override
    public Iterator<User> iterator() {
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
    public UserSerie.User next(){
	 return new User(delegate.next());
    }
    
    public class User {
	
	User(List<String> values){
	    setUser( (String)values.get(fields.get(USERFIELD)));
	    setAdmin( Boolean.valueOf((String)values.get(fields.get(ADMINFIELD))));
	}
	
	private String user;
	private Boolean admin;
	public String getUser() {
	    return user;
	}
	public void setUser(String user) {
	    this.user = user;
	}
	public Boolean getAdmin() {
	    return admin;
	}
	public void setAdmin(Boolean admin) {
	    this.admin = admin;
	}
    }

   
}

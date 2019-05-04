package com.ptoceti.influxdb.impl;

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


import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import java.util.Date;

import com.ptoceti.influxdb.Serie;
import com.ptoceti.influxdb.SerieWrapper;
import com.ptoceti.influxdb.TimeStampHelper;


public class NOAASerie extends SerieWrapper implements Iterator<NOAASerie.Noaa>, Iterable<NOAASerie.Noaa>{

    protected static final String TIME = "time";
    
    public TimeStampHelper timeStampHelper = new TimeStampHelper();
    
    public class Noaa {

	private Date time;
	
	public Noaa(List<String> values){
	    
	    
	    String rf3339time = (String)values.get(fields.get(TIME));
	    try {
		setTime( timeStampHelper.parseRfc3339(rf3339time));
	    } catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    
	}

	public Date getTime() {
	    return time;
	}

	public void setTime(Date time) {
	    this.time = time;
	}
    }

    public NOAASerie(Serie serie) {
	super(serie);
	// TODO Auto-generated constructor stub
    }

    @Override
    public Iterator<Noaa> iterator() {
	return this;
    }

    @Override
    public boolean hasNext() {
	return delegate.hasNext();
    }

    @Override
    public Noaa next() {
	return new Noaa(delegate.next());
    }

    @Override
    public void remove() {
	delegate.remove();
    }

}

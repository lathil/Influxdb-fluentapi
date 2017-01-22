package com.ptoceti.influxdb.impl;

import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import java.util.Date;

import com.ptoceti.influxdb.Serie;
import com.ptoceti.influxdb.SerieWrapper;
import com.ptoceti.influxdb.TimeStampHelper;


public class NOAASerie extends SerieWrapper implements Iterator<NOAASerie.Noaa>, Iterable<NOAASerie.Noaa>{

    protected static final String TIME = "time";
    
    public class Noaa {

	private Date time;
	
	public Noaa(List<String> values){
	    
	    
	    String rf3339time = (String)values.get(fields.get(TIME));
	    try {
		setTime( TimeStampHelper.parseRfc3339(rf3339time));
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

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

public class MeasurementSerie extends SerieWrapper implements Iterator<MeasurementSerie.Measurement>,
	Iterable<MeasurementSerie.Measurement> {

    protected static final String SERIENAME = "measurements";
    protected static final String NAMEFIELD = "name";

    public MeasurementSerie(Serie serie) {
	super(serie);
    }

    @Override
    public Iterator<Measurement> iterator() {
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
    public MeasurementSerie.Measurement next() {
	return new Measurement(delegate.next());
    }

    public static String getSerieName() {
	return SERIENAME;
    }

    public class Measurement {
	private String name;

	Measurement(List<String> values) {
	    setName((String) values.get(fields.get(NAMEFIELD)));
	}

	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
	}
    }
}

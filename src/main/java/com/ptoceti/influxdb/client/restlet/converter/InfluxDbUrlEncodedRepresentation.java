package com.ptoceti.influxdb.client.restlet.converter;

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


import java.io.IOException;
import java.io.Writer;

import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.WriterRepresentation;
import org.restlet.resource.Resource;

import com.ptoceti.influxdb.ql.Query;

public class InfluxDbUrlEncodedRepresentation<T> extends WriterRepresentation {

    /** The (parsed) object to format. */
    private T object;

    /** The object class to instantiate. */
    private Class<T> objectClass;

    /** The InfluxDb representation to parse. */
    private Representation influxdbRepresentation;

    private Resource resource;

    public InfluxDbUrlEncodedRepresentation(MediaType mediaType, T object, Resource resource) {
	this(mediaType);
	this.object = object;
	this.objectClass = (Class<T>) ((object == null) ? null : object.getClass());
	this.influxdbRepresentation = null;
	this.resource = resource;
    }

    public InfluxDbUrlEncodedRepresentation(MediaType mediaType) {
	super(mediaType);
    }

    @Override
    public void write(Writer writer) throws IOException {
	if (this.influxdbRepresentation != null)
	    this.influxdbRepresentation.write(writer);
	else if (this.object != null){

	    if (object instanceof Query) {
		String formParam = "q=" + ((Query) this.object).toQL();
		writer.write(formParam);
	    }
	}

    }

}

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
import java.util.List;

import org.restlet.data.MediaType;
import org.restlet.engine.converter.ConverterHelper;
import org.restlet.engine.resource.VariantInfo;
import org.restlet.representation.Representation;
import org.restlet.representation.Variant;
import org.restlet.resource.Resource;

import com.ptoceti.influxdb.Batch;
import com.ptoceti.influxdb.Point;
import com.ptoceti.influxdb.ql.Query;

public class InfluxDbConverter extends ConverterHelper {

    private static final VariantInfo VARIANT_OCTET_STREAM = new VariantInfo(MediaType.APPLICATION_OCTET_STREAM);

    private static final VariantInfo APPLICATION_WWW_FORM = new VariantInfo(MediaType.APPLICATION_WWW_FORM);

    @Override
    public List<Class<?>> getObjectClasses(Variant source) {
	List<Class<?>> result = null;

	if (VARIANT_OCTET_STREAM.isCompatible(source)) {

	    result = addObjectClass(result, Object.class);
	    result = addObjectClass(result, InfluxDbLineProtocolRepresentation.class);
	}

	return result;
    }

    @Override
    public List<VariantInfo> getVariants(Class<?> source) throws IOException {
	List<VariantInfo> result = null;

	if (Batch.class.getName().equals(source.getName()) || Point.class.getName().equals(source.getName())) {
	    result = addVariant(result, VARIANT_OCTET_STREAM);
	} else if (Query.class.getName().equals(source.getName())) {
	    result = addVariant(result, APPLICATION_WWW_FORM);
	}

	return result;
    }

    @Override
    public float score(Object source, Variant target, Resource resource) {
	float result = -1.0F;

	if (source instanceof Point || source instanceof Batch || source instanceof Query) {
	    result = 1.0F;
	} 

	return result;
    }

    @Override
    public <T> float score(Representation source, Class<T> target, Resource resource) {
	float result = -1.0F;

	if (source instanceof InfluxDbLineProtocolRepresentation<?>) {
	    result = 1.0F;
	} else if ((target != null) && InfluxDbLineProtocolRepresentation.class.isAssignableFrom(target)) {
	    result = 1.0F;
	} else if (VARIANT_OCTET_STREAM.isCompatible(source)) {
	    result = 1.0F;
	}

	return result;
    }

    @Override
    public <T> T toObject(Representation source, Class<T> target, Resource resource) throws IOException {
	Object result = null;

	return (T) result;
    }

    @Override
    public Representation toRepresentation(Object source, Variant target, Resource resource) throws IOException {
	Representation result = null;

	if ((source instanceof Point || source instanceof Batch) && VARIANT_OCTET_STREAM.isCompatible(target)) {
	    InfluxDbLineProtocolRepresentation<Object> obixRepresentation = createLineProtocol(target.getMediaType(),
		    source, resource);
	    result = obixRepresentation;
	} else if (source instanceof Query && APPLICATION_WWW_FORM.isCompatible(target)) {
	    InfluxDbUrlEncodedRepresentation<Object> obixRepresentation = createUrlEncoded(target.getMediaType(),
		    source, resource);
	    result = obixRepresentation;
	}

	return result;
    }

    protected <T> InfluxDbLineProtocolRepresentation<T> createLineProtocol(MediaType mediaType, T source,
	    Resource resource) {
	return new InfluxDbLineProtocolRepresentation<T>(mediaType, source, resource);
    }

    protected <T> InfluxDbUrlEncodedRepresentation<T> createUrlEncoded(MediaType mediaType, T source, Resource resource) {
	return new InfluxDbUrlEncodedRepresentation<T>(mediaType, source, resource);
    }

}

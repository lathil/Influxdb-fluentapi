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


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

public class SerieWrapper {

    protected Serie serie;
    protected Map<String, Integer> fields;

    protected Iterator<List<String>> delegate;

    public SerieWrapper(Serie serie) {
	this.serie = serie;
	this.fields = new HashMap<String, Integer>();

	for (int columnsindex = 0; columnsindex < serie.getColumns().size(); columnsindex++) {
	    fields.put(serie.getColumns().get(columnsindex), columnsindex);
	}

	delegate = serie.getValues().iterator();

    }

    protected <T extends String> List<T> getValuesForField(String fieldName, Class<T> returnType) {

	List<T> values = new ArrayList<T>();
	Integer fieldIndex = fields.get(fieldName);

	if (fieldIndex != null) {
	    for (List<String> dbinfos : serie.getValues()) {
		values.add(returnType.cast(dbinfos.get(fieldIndex)));
	    }
	}

	return values;
    }

    public int size() {

	return serie.getValues().size();
    }
}

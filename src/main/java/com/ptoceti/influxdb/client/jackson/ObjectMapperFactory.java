package com.ptoceti.influxdb.client.jackson;

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


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperFactory {

    private static ObjectMapper objectMapper;

    public static ObjectMapper configure() {

        if( objectMapper == null ) {

            JsonFactory factory = new JsonFactory();
            factory.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);

            objectMapper = new ObjectMapper(factory);

            /**
             * objectMapper.configure(MapperFeature.AUTO_DETECT_FIELDS, true);
             * objectMapper.configure(MapperFeature.AUTO_DETECT_GETTERS, false);
             * objectMapper.configure(MapperFeature.AUTO_DETECT_IS_GETTERS,
             * false); objectMapper.configure(MapperFeature.AUTO_DETECT_SETTERS,
             * false);
             *
             *
             * objectMapper.setVisibility(PropertyAccessor.FIELD,
             * JsonAutoDetect.Visibility.PROTECTED_AND_PUBLIC);
             **/

            objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        }

        return objectMapper;
    }

}

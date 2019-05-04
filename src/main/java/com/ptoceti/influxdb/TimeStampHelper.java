package com.ptoceti.influxdb;

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
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * An parser able to decode RC3339 Influxdb timestamps
 * 
 * 
 * @author LATHIL
 *
 */
public class TimeStampHelper {

    protected static final String rfc3339Pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX";
    protected SimpleDateFormat  rfc3339Format = new SimpleDateFormat(rfc3339Pattern);
    
    public Date parseRfc3339(String dateTime) throws ParseException{
	
	Date result = null;
	
	String normalizedDateTime = dateTime.replace(" ", "T");
	normalizedDateTime.replace("t", "T");
	normalizedDateTime.replace("z", "Z");
	// Influxdb timestamps always reported as UTC, so will always have Z
	int fractSeparatorIndex = normalizedDateTime.indexOf(".");
	int zindex = normalizedDateTime.indexOf("Z");
	if( fractSeparatorIndex > -1) {
	    // we have fractions
	    String fractions = normalizedDateTime.substring(fractSeparatorIndex, zindex);
	    if(( zindex - fractSeparatorIndex) == 1 ){
		fractions =  "00" + fractions;
	    } else  if(( zindex - fractSeparatorIndex) == 2 ){
		fractions =  "0" + fractions;
	    } else {
		// more than 2 digits, we only keep three
		fractions = fractions.substring(0, 3);
	    }
	    
	    normalizedDateTime = normalizedDateTime.substring(0, zindex) + fractions + normalizedDateTime.substring(zindex, normalizedDateTime.length());
	} else {
	    // no fractions, need paddings
	    
	    normalizedDateTime = normalizedDateTime.substring(0, zindex) + ".000" + normalizedDateTime.substring(zindex, normalizedDateTime.length());
	    
	}
	
	result = rfc3339Format.parse(normalizedDateTime);
	
	return result;
    }
}

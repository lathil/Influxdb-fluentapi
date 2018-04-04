package com.ptoceti.influxdb.ql;

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
import java.util.List;

public class Clause {

    
    List<String> identifiers;
    ClauseName clauseName;

    public static enum ClauseName {

	FROM("FROM "),
	GROUPBY("GROUP BY"),
	INTO("INTO"),
	LIMIT("LIMIT"),
	OFFSET("OFFSET"),
	SLIMIT("SLIMIT"),
	SOFFSET("SOFFSET"),
	ON("ON"),
	ORDERBY("ORDER BY"),
	TO("TO"),
	WHERE("WHERE"),
	WITH("WITH"),
	WITHMEASUREMENT("WITH MEASUREMENT"),
	WITHKEY("WITH KEY"),
	WITHPASSWORD("WITH PASSWORD"),
	WITHALLPRIVILEGES("WITH ALL PRIVILEGES"),
	BEGIN("BEGIN"),
	END("END"),
	RESAMPLE("RESAMPLE");
	
	private String name;

	private ClauseName(String name) {
	    this.name = name;
	}
	
	public String getName(){
	    return name;
	}
    }

    public Clause(ClauseName name) {
	this.clauseName = name;
	identifiers = new ArrayList<String>();
    }

    public void addIdentifier(String identifier) {
	identifiers.add(identifier);
    }
    
    public void toQL(StringBuffer qlBuff){
	
	qlBuff.append(this.clauseName.getName());
	boolean firstIdentifier = true;
	for( String identifier: identifiers){
	 // separate first identifier with space
	    if( firstIdentifier) qlBuff.append(" ");
	 // separate identifiers with comma
	    if( !firstIdentifier) qlBuff.append(",");
	    qlBuff.append(identifier);
	    firstIdentifier = false;
	}
    }
    
    
    public String toQL(){
	StringBuffer ql = new StringBuffer();
	toQL(ql);
	return ql.toString();
	
    }

}

package com.ptoceti.influxdb.ql;

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


import java.util.ArrayList;
import java.util.List;


public class Query {

    private List<Statement> statements;
    
    public Query(){
	statements = new ArrayList<Statement>();
    }
    
    
    public void addStatement(Statement statement){
	statements.add(statement);
    }
    
    public String toQL(){
	StringBuffer qlbuff = new StringBuffer();
	
	boolean isfirst = true;
	for( Statement statement : statements){
	    if(!isfirst) qlbuff.append(";");
	    statement.toQL(qlbuff);
	    isfirst= false;
	}
	
	
	return qlbuff.toString();
    }
}

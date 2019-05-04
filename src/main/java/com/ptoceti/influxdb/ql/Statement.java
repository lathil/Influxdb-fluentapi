package com.ptoceti.influxdb.ql;

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


import java.util.ArrayList;
import java.util.List;

public class Statement {

    StatementName statementName;
    List<String> identifiers;
    List<Clause> clauses;
    List<RetentionPolicy> policies;
    
    public static enum StatementName{
	
	ALTERRETENTIONPOLICY("ALTER RETENTION POLICY"),
	CREATECONTINUOUSQUERY("CREATE CONTINUOUS QUERY"),
	CREATEDATABASE("CREATE DATABASE"),
	CREATERETENTIONPOLICY("CREATE RETENTION POLICY"),
	CREATESUBSCRIPTION("CREATE SUBSCRIPTION"),
	CREATEUSER("CREATE USER"),
	DELETE("DELETE"),
	DROPCONTINUOUSQUERY("DROP CONTINUOUS QUERY"),
	DROPDATABASE("DROP DATABASE"),
	DROPMEASUREMENT("DROP MEASUREMENT"),
	DROPRETENTIONPOLICY("DROP RETENTION POLICY"),
	DROPSERIES("DROP SERIES"),
	DROPSHARD("DROP SHARD"),
	DROPSUBSCRIPTION("DROP SUBSCRIPTION"),
	DROPUSER("DROP USER"),
	GRANT("GRANT"),
	KILLQUERY("KILL QUERY"),
	REVOKE("REVOKE"),
	SHOWCONTINUOUSQUERIES("SHOW CONTINUOUS QUERIES"),
	SHOWDATABASE("SHOW DATABASES"),
	SHOWFIELDKEYS("SHOW FIELD KEYS"),
	SHOWGRANTSFOR("SHOW GRANTS FOR"),
	SHOWMEASUREMENTS("SHOW MEASUREMENTS"),
	SHOWQUERIES("SHOW QUERIES"),
	SHOWRETENTIONPOLICIES("SHOW RETENTION POLICIES"),
	SHOWSERIES("SHOW SERIES"),
	SHOWSHARDGROUPS("SHOW SHARD GROUPS"),
	SHOWSHARDS("SHOW SHARDS"),
	SHOWSUBSCRIPTIONS("SHOW SUBSCRIPTIONS"),
	SHOWTAGKEYS("SHOW TAG KEYS"),
	SHOWTAGVALUES("SHOW TAG VALUES"),
	SHOWUSERS("SHOW USERS"),
	
	SELECT("SELECT");
	
	private String name;
	
	private StatementName(String name){
	    this.name = name;
	}
	
	public String getName(){
	    return name;
	}
    }
    
    public Statement(StatementName name){
	this.statementName = name;
	identifiers = new ArrayList<String>();
	clauses = new ArrayList<Clause>();
	policies = new ArrayList<RetentionPolicy>();
    }
    
    public void addIdentifier(String identifier){
	identifiers.add(identifier);
    }
    
    public void addClause(Clause clause){
	clauses.add(clause);
    }
    
    public void addRetentionPolicy(RetentionPolicy policy){
	policies.add(policy);
    }
    
    
    
    public void toQL(StringBuffer qlBuff){

	qlBuff.append(this.statementName.getName());
	boolean firstIdentifier = true;
	for( String identifier: identifiers){
	    // separate first identifier with space
	    if( firstIdentifier) qlBuff.append(" ");
	    // separate identifiers with comma
	    if( !firstIdentifier) qlBuff.append(",");
	    qlBuff.append(identifier);
	    firstIdentifier = false;
	}
	
	if( !clauses.isEmpty()){
	    for( Clause clause : clauses){
		qlBuff.append(" ");
		clause.toQL(qlBuff);
	    }
	}
	
	if( !policies.isEmpty()){
	    for( RetentionPolicy policy : policies){
		qlBuff.append(" ");
		policy.toQL(qlBuff);
	    }
	}
    }
    
    public String toQL(){
	StringBuffer ql = new StringBuffer();
	toQL(ql);
	
	return ql.toString();
	
    }
}

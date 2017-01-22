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


public class OnBeginClauseBuilder extends ClauseBuilder {

    /**
     * Build a On ... clause and add it to the chain
     * 
     * @param statementBuilder
     * @param identifier
     */
    public OnBeginClauseBuilder(StatementBuilder statementBuilder, String identifier) {
	super(statementBuilder);

	Clause clause = new Clause(Clause.ClauseName.ON);
	clause.addIdentifier(identifier);
	this.setClause(clause);
    }
    
    /**
     * Add Resample... clause to the chain.
     * 
     * @param identifiers
     * @return
     */
    public ResampleClauseBuilder Resample(String identifier) {
	ResampleClauseBuilder resample = new ResampleClauseBuilder(this.statementBuilder, identifier);
	this.statementBuilder.addClause(resample.getClause());
	return resample;
    }
    
    
    
    
    /**
     * Add Begin... clause to the chain.
     * 
     * @param identifiers
     * @return
     */
    public BeginEndClauseBuilder Begin(Query query) {
	BeginEndClauseBuilder begin = new BeginEndClauseBuilder(this.statementBuilder, query);
	this.statementBuilder.addClause(begin.getClause());
	return begin;
    }
}

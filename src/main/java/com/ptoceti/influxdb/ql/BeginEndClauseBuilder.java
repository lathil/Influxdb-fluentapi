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


public class BeginEndClauseBuilder extends ClauseBuilder {

    /**
     * Build a Begin ... clause and add it to the chain
     * 
     * @param statementBuilder
     * @param identifier
     */
    public BeginEndClauseBuilder(StatementBuilder statementBuilder, String identifier) {
	super(statementBuilder);

	Clause clause = new Clause(Clause.ClauseName.BEGIN);
	clause.addIdentifier(identifier);
	this.setClause(clause);
    }
    
    public BeginEndClauseBuilder(StatementBuilder statementBuilder, Query query) {
	super(statementBuilder);

	Clause clause = new Clause(Clause.ClauseName.BEGIN);
	clause.addIdentifier(query.toQL());
	this.setClause(clause);
    }

    /**
     * Add End... clause to the chain.
     * 
     * @param identifiers
     * @return
     */
    public EndClauseBuilder End() {
	EndClauseBuilder end = new EndClauseBuilder(this.statementBuilder);
	this.statementBuilder.addClause(end.getClause());
	return end;
    }
}

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


public class IntoClauseBuilder extends ClauseBuilder {

    public IntoClauseBuilder(StatementBuilder statementBuilder, String... identifiers) {

	super(statementBuilder);

	Clause clause = new Clause(Clause.ClauseName.INTO);
	for (String identifier : identifiers) {
	    clause.addIdentifier(identifier);
	}

	this.setClause(clause);
    }

    /**
     * Add From... clause that also allows furthers clauses such as Where...GroupBy..OrderBy...Limit...
     * 
     * @param identifiers
     * @return
     */
    public FromWhereGroupByClauseBuilder From(String... identifiers) {

	FromWhereGroupByClauseBuilder from = new FromWhereGroupByClauseBuilder(this.statementBuilder, identifiers);
	this.statementBuilder.addClause(from.getClause());
	return from;
    }

}

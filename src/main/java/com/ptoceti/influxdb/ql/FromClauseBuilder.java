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


/**
 * Basic From clause builder. No other clauses proposed after.
 * 
 * @author LATHIL
 *
 */
public class FromClauseBuilder extends ClauseBuilder {

    /**
     * Build a From ... clause and add it to the chain
     * 
     * @param statementBuilder
     * @param identifiers
     */
    public FromClauseBuilder(StatementBuilder statementBuilder, String... identifiers) {
	super(statementBuilder);

	Clause clause = new Clause(Clause.ClauseName.FROM);
	for (String identifier : identifiers) {
	    clause.addIdentifier(identifier);
	}

	this.setClause(clause);
    }

}

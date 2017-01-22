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


public class FromWithKeyClauseBuilder extends FromClauseBuilder {

    /**
     * Build a From ... clause and add it to the chain
     * 
     * @param statementBuilder
     * @param identifiers
     */
    public FromWithKeyClauseBuilder(StatementBuilder statementBuilder, String... identifiers) {
	super(statementBuilder, identifiers);
    }
    
    /**
     * Add a With Key ... clause to the chain
     * 
     * @param identifiers
     * @return
     */
    public WithKeyClauseBuilder WithKey(String... identifiers) {
	WithKeyClauseBuilder where = new WithKeyClauseBuilder(this.statementBuilder, identifiers);
	this.statementBuilder.addClause(where.getClause());
	return where;
    }

}

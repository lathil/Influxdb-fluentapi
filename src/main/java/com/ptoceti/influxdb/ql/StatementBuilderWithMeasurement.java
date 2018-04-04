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


public class StatementBuilderWithMeasurement extends StatementBuilder {

    public StatementBuilderWithMeasurement(QueryBuilder queryBuilder, Statement statement) {
	super(queryBuilder, statement);
    }

    public WithMeasurementClauseBuilder WithMeasurement() {

	WithMeasurementClauseBuilder clauseBuilder = new WithMeasurementClauseBuilder(this);
	statement.addClause(clauseBuilder.getClause());
	return clauseBuilder;
    }

    /**
     * Add a Where ... clause to the chain
     * 
     * @param identifiers
     * @return
     */
    public WhereOffsetClauseBuilder Where(String... identifiers) {
	WhereOffsetClauseBuilder where = new WhereOffsetClauseBuilder(this, identifiers);
	this.addClause(where.getClause());
	return where;
    }
    
    /**
     * Add limit clause to the chain
     * 
     * @param limit
     * @return
     */
    public LimitOffsetSLimitClauseBuilder Limit(int limit) {
	LimitOffsetSLimitClauseBuilder builder = new LimitOffsetSLimitClauseBuilder(this, limit);
	this.addClause(builder.getClause());
	return builder;
    }
    
    /**
     * Add offset clause to the chain
     * 
     * @param limit
     * @return
     */
    public OffsetSLimitClauseBuilder Offset(int limit) {
	OffsetSLimitClauseBuilder builder = new OffsetSLimitClauseBuilder(this, limit);
	this.addClause(builder.getClause());
	return builder;
    }
}

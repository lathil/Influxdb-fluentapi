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


/**
 * GroupBy ... clause builder that propose OrderBy, Limit and SLimit clauses.
 * 
 * @author LATHIL
 *
 */
public class GroupByClauseBuilder extends ClauseBuilder {

    public GroupByClauseBuilder(StatementBuilder statementBuilder, String... identifiers) {

	super(statementBuilder);

	Clause clause = new Clause(Clause.ClauseName.GROUPBY);
	for (String identifier : identifiers) {
	    clause.addIdentifier(identifier);
	}

	this.setClause(clause);
    }

    /**
     * Add OrderBy... clause to the chain
     * 
     * @param identifiers
     * @return
     */
    public OrderByClauseBuilder OrderBy(String... identifiers) {
	OrderByClauseBuilder orderBy = new OrderByClauseBuilder(this.statementBuilder, identifiers);
	this.statementBuilder.addClause(orderBy.getClause());
	return orderBy;
    }

    /**
     * Add limit clause to the chain
     * 
     * @param limit
     * @return
     */
    public LimitOffsetSLimitClauseBuilder Limit(int limit) {
	LimitOffsetSLimitClauseBuilder builder = new LimitOffsetSLimitClauseBuilder(this.statementBuilder, limit);
	this.statementBuilder.addClause(builder.getClause());
	return builder;
    }

    /**
     * Add slimit clause to the chain
     * 
     * @param limit
     * @return
     */
    public SLimitClauseBuilder SLimit(int limit) {
	SLimitClauseBuilder builder = new SLimitClauseBuilder(this.statementBuilder, limit);
	this.statementBuilder.addClause(builder.getClause());
	return builder;
    }
}

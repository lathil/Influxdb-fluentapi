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
 * Clause builder that extends on From clause builder and propose clauses Where, GroupBy, OrderBy and Limit
 * 
 * 
 * @author LATHIL
 *
 */
public class FromWhereGroupByClauseBuilder extends FromClauseBuilder {

    public FromWhereGroupByClauseBuilder(StatementBuilder statementBuilder, String... identifiers) {
	super(statementBuilder, identifiers);
    }

    /**
     * Add Where... clause to the chain.
     * 
     * @param identifiers
     * @return
     */
    public WhereGroupByClauseBuilder Where(String... identifiers) {
	WhereGroupByClauseBuilder where = new WhereGroupByClauseBuilder(this.statementBuilder, identifiers);
	this.statementBuilder.addClause(where.getClause());
	return where;
    }

    /**
     * Add GroupBy... clause to the chain
     * 
     * @param identifiers
     * @return
     */
    public GroupByClauseBuilder GroupBy(String... identifiers) {
	GroupByClauseBuilder groupBy = new GroupByClauseBuilder(this.statementBuilder, identifiers);
	this.statementBuilder.addClause(groupBy.getClause());
	return groupBy;
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
	LimitOffsetSLimitClauseBuilder orderBy = new LimitOffsetSLimitClauseBuilder(this.statementBuilder, limit);
	this.statementBuilder.addClause(orderBy.getClause());
	return orderBy;
    }
}

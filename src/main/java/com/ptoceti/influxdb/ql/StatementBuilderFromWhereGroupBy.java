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
 * Statement builder that allow full suite of From...Where...Into...GroupBy...
 * 
 * @author LATHIL
 *
 */
public class StatementBuilderFromWhereGroupBy extends StatementBuilder {

    public StatementBuilderFromWhereGroupBy(QueryBuilder queryBuilder, Statement statement) {
	super(queryBuilder, statement);
    }

    public StatementBuilderFromWhereGroupBy(Statement statement) {
	super(statement);
    }

    /**
     * Add From... clause that also allows furthers clauses such as Where...GroupBy..OrderBy...Limit...
     * 
     * @param identifiers
     * @return
     */
    public FromWhereGroupByClauseBuilder From(String ... identifiers ) {
	
	FromWhereGroupByClauseBuilder from = new  FromWhereGroupByClauseBuilder(this, identifiers);

	statement.addClause(from.getClause());
	return from;
    }

    /**
     * Add Into... clause that also allows furthers From...Where...GroupBy...OrderBy...Limit...
     * 
     * @param identifiers
     * @return
     */
    public IntoClauseBuilder Into(String ... identifiers ) {
	IntoClauseBuilder into = new  IntoClauseBuilder(this, identifiers);

	statement.addClause(into.getClause());
	return into;
    }
}
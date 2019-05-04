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
 * Statement builder that allows From or Where clauses but no other clauses
 * 
 * @author LATHIL
 * 
 */
public class StatementBuilderFromWhere extends StatementBuilderFrom {

    public StatementBuilderFromWhere(QueryBuilder queryBuilder, Statement statement) {
	super(queryBuilder, statement);
    }

    public StatementBuilderFromWhere(Statement statement) {
	super(statement);
    }

    /**
     * Add a clause that allow From .. Where...
     * 
     * @param identifiers
     * @return
     */
    public FromWhereClauseBuilder From(String... identifiers) {

	FromWhereClauseBuilder from = new FromWhereClauseBuilder(this, identifiers);

	statement.addClause(from.getClause());
	return from;
    }

    /**
     * Add a clause that allow Where...
     * 
     * @param identifiers
     * @return
     */
    public WhereClauseBuilder Where(String... identifiers) {
	WhereClauseBuilder where = new WhereClauseBuilder(this, identifiers);

	statement.addClause(where.getClause());
	return where;
    }
}

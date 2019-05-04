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
 * Statement builder that allows single From clause
 * 
 * @author LATHIL
 *
 */
public class StatementBuilderFrom extends StatementBuilder {

    public StatementBuilderFrom(QueryBuilder queryBuilder, Statement statement) {
	super(queryBuilder, statement);
    }

    public StatementBuilderFrom(Statement statement) {
	super(statement);
    }

    /**
     * Add simple From clause
     * 
     * @param identifiers
     * @return
     */
    public FromClauseBuilder From(String... identifiers) {

	FromClauseBuilder from = new FromClauseBuilder(this, identifiers);

	statement.addClause(from.getClause());
	return from;
    }

}

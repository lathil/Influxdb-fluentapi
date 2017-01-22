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
 * Statement builder that allows a On.. and From... clauses
 * 
 * @author LATHIL
 *
 */
public class StatementBuilderOnFrom extends StatementBuilder {

    public StatementBuilderOnFrom(QueryBuilder queryBuilder, Statement statement) {
	super(queryBuilder, statement);
    }

    /**
     * Add a clause that allow On... From...
     * 
     * @param identifiers
     * @return
     */
    public OnFromClauseBuilder On(String identifiers) {

	OnFromClauseBuilder on = new OnFromClauseBuilder(this, identifiers);
	statement.addClause(on.getClause());
	return on;
    }

    /**
     * Add a clause that allow From ..
     * 
     * @param identifiers
     * @return
     */
    public FromClauseBuilder To(String identifiers) {

	FromClauseBuilder from = new FromClauseBuilder(this, identifiers);
	statement.addClause(from.getClause());
	return from;
    }

}

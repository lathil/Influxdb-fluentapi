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


public class StatementBuilderOnRetention extends StatementBuilder {

    public StatementBuilderOnRetention(QueryBuilder queryBuilder, Statement statement) {
	super(queryBuilder, statement);
    }

    /**
     * Add a clause that allow On ..
     * 
     * @param identifiers
     * @return
     */
    public OnRetentionPolicyClauseBuilder On(String identifiers) {

	OnRetentionPolicyClauseBuilder on = new OnRetentionPolicyClauseBuilder(this, identifiers);
	statement.addClause(on.getClause());
	return on;
    }
    
}

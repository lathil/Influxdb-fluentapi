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


public class LimitOffsetClauseBuilder extends LimitClauseBuilder {

    public LimitOffsetClauseBuilder(StatementBuilder statementBuilder, int limit) {
	super(statementBuilder, limit);
    }

    /**
     * Add offset clause to the chain
     * 
     * @param limit
     * @return
     */
    public LimitClauseBuilder Offset(int limit) {
	LimitClauseBuilder builder = new LimitClauseBuilder(this.statementBuilder, limit);
	this.statementBuilder.addClause(builder.getClause());
	return builder;
    }

}

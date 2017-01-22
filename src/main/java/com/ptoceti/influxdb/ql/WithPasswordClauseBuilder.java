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


public class WithPasswordClauseBuilder extends ClauseBuilder  {

    public WithPasswordClauseBuilder(StatementBuilder statementBuilder, String password) {
	super(statementBuilder);
	
	// Password must be enclosed with "'"
	Clause clause = new Clause(Clause.ClauseName.WITHPASSWORD);
	StringBuffer quotedPassword = new StringBuffer();
	if( !password.startsWith("\'")){
	    quotedPassword.append("\'");
	}
	quotedPassword.append(password);
	if( !password.endsWith("\'")){
	    quotedPassword.append("\'");
	}
	clause.addIdentifier(quotedPassword.toString());

	this.setClause(clause);
    }

    public WithAllPrivilegeClauseBuilder WhitAllPrivileges() {
	WithAllPrivilegeClauseBuilder clauseBuilder = new WithAllPrivilegeClauseBuilder(this.statementBuilder);
	this.statementBuilder.addClause(clauseBuilder.getClause());
	return clauseBuilder;
    }
    
}

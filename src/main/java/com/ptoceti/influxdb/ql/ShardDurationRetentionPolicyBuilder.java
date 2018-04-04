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


public class ShardDurationRetentionPolicyBuilder extends RetentionPolicyBuilder {

    public ShardDurationRetentionPolicyBuilder(StatementBuilder statementBuilder, String identifier) {
	super(statementBuilder);

	RetentionPolicy policy = new RetentionPolicy(RetentionPolicy.PolicyName.SHARDDURATION);
	policy.addIdentifier(identifier);
	this.setRetentionPolicy(policy);

    }

    public DefaultRetentionPolicyBuilder Default() {
	DefaultRetentionPolicyBuilder builder = new DefaultRetentionPolicyBuilder(this.statementBuilder);
	this.statementBuilder.addPolicy(builder.getRetentionPolicy());
	return builder;
    }
}

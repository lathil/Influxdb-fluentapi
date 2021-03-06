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


public class DurationNameRetentionPolicyBuilder extends RetentionPolicyBuilder {

    public DurationNameRetentionPolicyBuilder(StatementBuilder statementBuilder, String identifier) {
	super(statementBuilder);

	RetentionPolicy policy = new RetentionPolicy(RetentionPolicy.PolicyName.DURATION);
	policy.addIdentifier(identifier);

	this.setRetentionPolicy(policy);
    }

    public ReplicationNameRetentionPolicyBuilder Replication(String identifier) {
	ReplicationNameRetentionPolicyBuilder builder = new ReplicationNameRetentionPolicyBuilder(
		this.statementBuilder, identifier);
	this.statementBuilder.addPolicy(builder.getRetentionPolicy());
	return builder;
    }

    public ShardDurationNameRetentionPolicyBuilder ShardDuration(String identifier) {
	ShardDurationNameRetentionPolicyBuilder builder = new ShardDurationNameRetentionPolicyBuilder(
		this.statementBuilder, identifier);
	this.statementBuilder.addPolicy(builder.getRetentionPolicy());
	return builder;
    }

    public NameRetentionPolicyBuilder Name(String identifier) {
	NameRetentionPolicyBuilder builder = new NameRetentionPolicyBuilder(this.statementBuilder, identifier);
	this.statementBuilder.addPolicy(builder.getRetentionPolicy());
	return builder;
    }

}

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


public class RetentionPolicy {

    String identifier;
    PolicyName policyName;

    public static enum PolicyName {

	DURATION("DURATION"), REPLICATION("REPLICATION"), SHARDDURATION("SHARD DURATION"), DEFAULT("DEFAULT"), NAME(
		"NAME");

	private String name;

	private PolicyName(String name) {
	    this.name = name;
	}

	public String getName() {
	    return name;
	}
    }

    public RetentionPolicy(PolicyName name) {
	this.policyName = name;
    }

    public void addIdentifier(String identifier) {
	this.identifier = identifier;
    }

    public void toQL(StringBuffer qlBuff) {

	qlBuff.append(this.policyName.getName());
	if (identifier != null && !identifier.isEmpty()) {
	    qlBuff.append(" ");
	    qlBuff.append(identifier);
	}

    }

    public String toQL() {
	StringBuffer ql = new StringBuffer();
	toQL(ql);
	return ql.toString();

    }
}

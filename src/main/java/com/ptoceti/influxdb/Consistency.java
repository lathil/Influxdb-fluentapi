package com.ptoceti.influxdb;

/**
 * Consistency enum. For use in /write query attributes
 * 
 * @author LATHIL
 * 
 */
public enum Consistency {

    ANY("any"), ONE("one"), ALL("all"), QUORUM("quorum");

    private String value;

    private Consistency(String value) {
	this.value = value;
    }

    public String getValue() {
	return value;
    }
}

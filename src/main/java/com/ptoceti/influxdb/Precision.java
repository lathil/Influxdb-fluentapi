package com.ptoceti.influxdb;

/**
 * Precision enum. For use in /write query attributes.
 * 
 * @author LATHIL
 * 
 */
public enum Precision {

    HOURS("h"), MINUTE("m"), SECOND("s"), MILLISECOND("ms"), MICROS("u"), NANOS("n");

    private String value;

    private Precision(String value) {
	this.value = value;
    }

    public String getValue() {
	return value;
    }
}

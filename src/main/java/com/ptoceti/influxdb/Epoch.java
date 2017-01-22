package com.ptoceti.influxdb;


/**
 * Epoch enum, for use in /query attributes
 * 
 * @author LATHIL
 *
 */
public enum Epoch {

    HOURS("h"),
    MINUTE("m"),
    SECOND("s"),
    MILLISECOND("ms"),
    MICROS("u"),
    NANOS("ns");
    
    private String value;

    private Epoch(String value) {
	this.value = value;
    }
    
    public String getValue(){
	return value;
    }
}

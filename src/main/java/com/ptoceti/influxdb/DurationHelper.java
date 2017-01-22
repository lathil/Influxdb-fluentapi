package com.ptoceti.influxdb;

public class DurationHelper {

    public static final String DURATIONUNITMICROSECOND = "u";
    public static final String DURATIONUNITMILLISECOND = "ms";
    public static final String DURATIONUNITSECOND = "s";
    public static final String DURATIONUNITMMINUTE = "m";
    public static final String DURATIONUNITHHOUR = "h";
    public static final String DURATIONUNITDAY = "d";
    public static final String DURATIONUNITWEEK = "w";
    
    public static enum Duration {

	MICROS(DURATIONUNITMICROSECOND, 1, 0),
	MILLIS(DURATIONUNITMILLISECOND, 1000, 1 ),
	SECONDS(DURATIONUNITSECOND, 1000 * 1000, 1000),
	MINUTES(DURATIONUNITMMINUTE, 60 * 1000 * 1000, 60 * 1000),
	HOURS(DURATIONUNITHHOUR, 60 * 60 * 1000 * 1000, 60 * 60 * 1000),
	DAYS(DURATIONUNITDAY, 24 * 60 * 60 * 1000 * 1000, 24 * 60 * 60 * 1000),
	WEEKS(DURATIONUNITWEEK, 7 * 24 * 60 * 60 * 1000 * 1000, 7 * 24 * 60 * 60 * 1000);
	
   	
   	private String name;
   	private long micros;
   	private long millis;

   	private Duration(String name, long micros, long millis) {
   	    this.name = name;
   	    this.micros = micros;
   	    this.millis = millis;
   	}
   	
   	public String getName(){
   	    return name;
   	}
   	
   	public long getMicros(){
   	    return micros;
   	}
   	
   	public long getMillis(){
   	    return millis;
   	}
   	
       }
    
    public static long convertToMicros( String durationString){
	long result = -1;
	
	Duration unit = null;
	
	if( durationString.endsWith(DURATIONUNITMICROSECOND)){
	    unit = Duration.MICROS;
	    result = Long.parseLong(durationString.substring(0, durationString.length() - DURATIONUNITMICROSECOND.length()));
	} else if ( durationString.endsWith(DURATIONUNITMILLISECOND)){
	    unit = Duration.MILLIS;
	    result = Long.parseLong(durationString.substring(0, durationString.length() - DURATIONUNITMILLISECOND.length())) * unit.getMicros();
	} else if ( durationString.endsWith(DURATIONUNITSECOND)){
	    unit = Duration.SECONDS;
	    result = Long.parseLong(durationString.substring(0, durationString.length() - DURATIONUNITSECOND.length())) * unit.getMicros();
	} else if ( durationString.endsWith(DURATIONUNITMMINUTE)){
	    unit = Duration.MINUTES;
	    result = Long.parseLong(durationString.substring(0, durationString.length() - DURATIONUNITMMINUTE.length())) * unit.getMicros();
	} else if ( durationString.endsWith(DURATIONUNITHHOUR)){
	    unit = Duration.SECONDS;
	    result = Long.parseLong(durationString.substring(0, durationString.length() - DURATIONUNITHHOUR.length())) * unit.getMicros();
	} else if ( durationString.endsWith(DURATIONUNITDAY)){
	    unit = Duration.DAYS;
	    result = Long.parseLong(durationString.substring(0, durationString.length() - DURATIONUNITDAY.length())) * unit.getMicros();
	} else if ( durationString.endsWith(DURATIONUNITWEEK)){
	    unit = Duration.WEEKS;
	    result = Long.parseLong(durationString.substring(0, durationString.length() - DURATIONUNITWEEK.length())) * unit.getMicros();
	}
	
	return result;
    }
    
    public static long convertToMillis( String durationString){
   	long result = -1;
   	
   	Duration unit = null;
   	
   	if( durationString.endsWith(DURATIONUNITMICROSECOND)){
   	    unit = Duration.MICROS;
   	    long micros = Long.parseLong(durationString.substring(0, durationString.length() - DURATIONUNITMICROSECOND.length()));
   	    if( micros < 1000){
   		result = 0;
   	    } else {
   		result = micros / 1000;
   	    }
   	} else if ( durationString.endsWith(DURATIONUNITMILLISECOND)){
   	    unit = Duration.MILLIS;
   	    result = Long.parseLong(durationString.substring(0, durationString.length() - DURATIONUNITMILLISECOND.length()));
   	} else if ( durationString.endsWith(DURATIONUNITSECOND)){
   	    unit = Duration.SECONDS;
   	    result = Long.parseLong(durationString.substring(0, durationString.length() - DURATIONUNITSECOND.length())) * unit.getMillis();
   	} else if ( durationString.endsWith(DURATIONUNITMMINUTE)){
   	    unit = Duration.MINUTES;
   	    result = Long.parseLong(durationString.substring(0, durationString.length() - DURATIONUNITMMINUTE.length())) * unit.getMillis();
   	} else if ( durationString.endsWith(DURATIONUNITHHOUR)){
   	    unit = Duration.SECONDS;
   	    result = Long.parseLong(durationString.substring(0, durationString.length() - DURATIONUNITHHOUR.length())) * unit.getMillis();
   	} else if ( durationString.endsWith(DURATIONUNITDAY)){
   	    unit = Duration.DAYS;
   	    result = Long.parseLong(durationString.substring(0, durationString.length() - DURATIONUNITDAY.length())) * unit.getMillis();
   	} else if ( durationString.endsWith(DURATIONUNITWEEK)){
   	    unit = Duration.WEEKS;
   	    result = Long.parseLong(durationString.substring(0, durationString.length() - DURATIONUNITWEEK.length())) * unit.getMillis();
   	}
   	
   	return result;
       }
}

package com.ptoceti.influxdb;

public interface InfluxDbService {

    void writePoint(Point point);
}

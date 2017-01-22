# Influxdb-fluentapi
A Java client fluent api to work with InfluxDb. Compatible with InfluxDb 1.0+

## Features

- Builders for composing complex InfluxDB queries: SELECT ..., SHOW, ....
- Handling of converting queries and responses to and from InfluxDb format (line protocol i, Json responses)
- Complete Handling of the three endpoint: /ping, /query and /write


## Examples

### For use of the /query endpoint
Write a query to create a database

```Java
Query query = QueryBuilder.Query().CreateDataBase(TESTDATABASENAME).With().Duration("1w").Replication("1")
		.Name(TESTRETENTIONPOLICY1WNAME).getQuery();
```

Write a query to create a retention policy

```Java
Query query = QueryBuilder.Query().CreateRetentionPolicy(TESTRETENTIONPOLICY100WNAME).On(TESTDATABASENAME)
		.Duration("100w").Replication("1").Default().getQuery();
```

Write a query to create a continuous query

```Java
Query query = QueryBuilder.Query().CreateContinuousQuery("OneHour_WaterLevel").On(TESTDATABASENAME).Begin(
		QueryBuilder.Query().Select("mean(\"water_level\")").Into(TESTRETENTIONPOLICY200WNAME + ".h2o_feet_200").From(TESTRETENTIONPOLICY100WNAME + ".h2o_feet").GroupBy("time(1h)").getQuery()).End().getQuery();
```


.. or simply reading measurements:

```Java
Query query = QueryBuilder.Query().Select("*").From("test." + TESTRETENTIONPOLICY100WNAME + ".h2o_feet")
		.Where("location = 'coyote_creek' AND time > " + from + "ms AND time < " + to + "ms").getQuery();
```

To send the query to the server, obtain the /query endpoint from the factory and send the query thought it:

```Java
QueryResource resource = factory.getQueryResource();
results = resource.post(query);
```

### For use of the /write endoint

Points can be build simply this way:

```Java
Point point = PointBuilder.Point("testmeasurement").addTag("testTag", "tag1").addField("testfield", 1)
		    .addField("testfield2", 1).getPoint();
```

... same with batchs:

```Java
Batch batch = BatchBuilder.Batch().point("testmeasurement").addTag("testTag", "tag1")
		    .addField("testfield", 2).addField("testfield2", 2).add().point("testmeasurement")
		    .addTag("testTag", "tag1").addField("testfield", 3).addField("testfield2", 3).add().getBatch();
```
    
Then send them to the server by obtaining the /write endpoint from the factory:

```Java
WriteResource resource = factory.getWriteResource();
resource.write(batch);
```

### build a factory to create the endpoints

Use a factory to create a endpoint resource. The factory can be configure with default attributes: database name, user, password, etc ...

```Java
InfluxDbResourceFactory factory = InfluxDbFactoryBuilder.build(new URL("http://127.0.0.1:8086")).dbName(TESTDATABASENAME).getFactory();
```

The endpoint created by the factory can be reused for more than one request.


## Build
* Java 1.7
* Maven 3

The jar produced contains Osgi meta-data so that it can be imported in a Osgi container.


## Import in you project though Maven dependencies
```XML
<dependency>
			<groupId>com.ptoceti</groupId>
			<artifactId>influxdb-fluentapi</artifactId>
			<version>$Version$</version>
		</dependency>
```

### Test - other example:

See src/test/java/InfluxDbResourceFactory for more thorough tests and example. To run the tests you need a locally running Influxdb instance.







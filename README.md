demo-mapserver
==============

A demo mapserver for use in the big data course.  This is a simple Impala backed restful Dropwizard framework.

To run:
  1. ```mvn package``` 
  2. edit the ```conf/mapserver-demo.yml```
  3. ```java -jar target/mapserver-1.0-SNAPSHOT.jar server conf/map-demo.yml```
  4. point your browser at http://localhost:8080/ for all data or http://localhost:8080/?month=1 for e.g. January.

The Hive metastore should have a ```occurrence_month``` table in the default database.  This can be created by:
  1. Downloading the sample data from http://dx.doi.org/10.5281/zenodo.12975
  2. Creating a table ```sample_data``` using that CSV
  3. Creating the final table using the following SQL

```
CREATE TABLE default.occurrence_month 
AS SELECT lat,lng,month,sum(count) AS count 
FROM sample_data
GROUP BY lat,lng,month
```

Please contact me on Skype @timrobertson100 for any questions on this.

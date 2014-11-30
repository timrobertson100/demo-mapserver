package org.gbif.demo.dao;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

/**
 * Provides the Java to SQL mapping interface.
 * We do some basic math ops to snap lat and lng to 0.5 degrees only to improve performance in this demo.
 */
@RegisterMapper(RecordMapper.class)
public interface MapDAO {

  @SqlQuery("SELECT count(*) FROM occurrence_month")
  int countAll();

  @SqlQuery("SELECT cast((2*lat) AS INT)/2 AS lat,cast((2*lng) AS INT)/2 AS lng,sum(count) AS count FROM occurrence_month WHERE month = :month GROUP BY lat,lng")
  List<Record> findByMonth(@Bind("month") int month);

  @SqlQuery("SELECT cast((2*lat) AS INT)/2 AS lat,cast((2*lng) AS INT)/2 AS lng,SUM(count) AS count FROM occurrence_month GROUP BY lat,lng limit 50000")
  List<Record> findAll();
}

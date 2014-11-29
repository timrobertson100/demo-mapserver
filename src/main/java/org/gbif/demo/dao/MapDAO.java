package org.gbif.demo.dao;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

/**
 * Provides the Java to SQL mapping interface.
 */
@RegisterMapper(RecordMapper.class)
public interface MapDAO {

  @SqlQuery("SELECT count(*) FROM occurrence_month")
  int countAll();

  @SqlQuery("SELECT lat,lng,month,count FROM occurrence_month WHERE month = :month LIMIT 100")
  List<Record> findByMonth(@Bind("month") int month);

  @SqlQuery("SELECT lat,lng,SUM(count) AS count FROM occurrence_month GROUP BY lat,lng LIMIT 100")
  List<Record> findAll();
}

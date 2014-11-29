package org.gbif.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * Record mapper that builds records from a row of data in the ResultSet.
 */
public class RecordMapper implements ResultSetMapper<Record> {
  public Record map(int index, ResultSet r, StatementContext ctx) throws SQLException {
    return new Record(r.getDouble("lat"), r.getDouble("lng"), r.getInt("count"));
  }
}

package org.gbif.demo.health;

import org.gbif.demo.dao.MapDAO;
import org.gbif.demo.dao.Record;

import java.util.List;

import com.codahale.metrics.health.HealthCheck;

/**
 * Simple health check for verifying Impala can be reached.
 */
public class ImpalaHealth extends HealthCheck {
  private final MapDAO dao;

  public ImpalaHealth(MapDAO dao) {
    this.dao = dao;
  }
  @Override
  protected Result check() throws Exception {
    try {
      int count = dao.countAll();
      if (count == 0) {
        return Result.unhealthy("Can connect to Impala, but table is empty");
      } else {
        return Result.healthy();
      }
    } catch (RuntimeException e) {
      return Result.unhealthy("Cannot connect to Impala: " + e.getMessage());
    }
  }
}

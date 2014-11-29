package org.gbif.demo.resources;

import org.gbif.demo.dao.MapDAO;
import org.gbif.demo.dao.Record;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class MapResource {
  private final MapDAO dao;

  public MapResource(MapDAO dao) {
    this.dao = dao;
  }

  @GET
  @Timed
  public List<Record> month(@QueryParam("month") Optional<Integer> month) {
    return month.isPresent() ? dao.findByMonth(month.get()) : dao.findAll();
  }
}

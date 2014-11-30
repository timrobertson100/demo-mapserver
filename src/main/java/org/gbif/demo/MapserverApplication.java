package org.gbif.demo;

import org.gbif.demo.dao.MapDAO;
import org.gbif.demo.health.ImpalaHealth;
import org.gbif.demo.resources.MapResource;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

/**
 * A simple rest application, that uses JDBI to access data in Impala.
 */
public class MapserverApplication extends Application<MapserverConfiguration> {
  @Override
  public String getName() {
    return "demo-mapserver";
  }

  @Override
  public void initialize(Bootstrap<MapserverConfiguration> bootstrap) {
    // set up the client app
    bootstrap.addBundle(new AssetsBundle("/app", "/", "index.html"));
  }

  public static void main(String[] args) throws Exception {
    new MapserverApplication().run(args);
  }

  @Override
  public void run(MapserverConfiguration config, Environment environment) throws Exception {
    // set the web services to come from /api
    environment.jersey().setUrlPattern("/api/*");

    // configure the data access
    final DBIFactory factory = new DBIFactory();
    final DBI jdbi = factory.build(environment, config.getDataSourceFactory(), "impala");
    final MapDAO dao = jdbi.onDemand(MapDAO.class);

    // register resources
    environment.jersey().register(new MapResource(dao));

    // register health checks
    environment.healthChecks().register("impala", new ImpalaHealth(dao));
  }
}

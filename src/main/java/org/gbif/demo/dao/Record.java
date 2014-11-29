package org.gbif.demo.dao;

import javax.annotation.concurrent.Immutable;

@Immutable
public class Record {
  private final double lat;
  private final double lng;
  private final int count;

  public Record(double lat, double lng, int count) {
    this.lat = lat;
    this.lng = lng;
    this.count = count;
  }

  public double getLat() {
    return lat;
  }

  public double getLng() {
    return lng;
  }

  public int getCount() {
    return count;
  }
}

package com.example.sampleproject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "trip_breakup")
public class TripBreakup {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "source_id")
  private Location sourceId;
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "destination_id")
  private Location destinationId;
  @Column(name = "mode")
  private String mode;
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "trip_id", referencedColumnName = "id")
  @JsonIgnore
  private TripInfo trip;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Location getSourceId() {
    return sourceId;
  }

  public void setSourceId(Location sourceId) {
    this.sourceId = sourceId;
  }

  public Location getDestinationId() {
    return destinationId;
  }

  public void setDestinationId(Location destinationId) {
    this.destinationId = destinationId;
  }

  public String getMode() {
    return mode;
  }

  public void setMode(String mode) {
    this.mode = mode;
  }

  public TripInfo getTrip() {
    return trip;
  }

  public void setTrip(TripInfo trip) {
    this.trip = trip;
  }

}

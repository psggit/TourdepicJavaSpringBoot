package com.example.sampleproject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pit_stops")
public class Pitstop {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "breakup_id")
  @JsonIgnore
  private TripBreakup breakup;
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "trip_id")
  @JsonIgnore
  private TripInfo trip;
  @Column(name = "stop_type")
  private String type;
  @Column(name = "pitstop_name")
  private String name;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "location_id")
  private Location location;
  @OneToMany(fetch = FetchType.EAGER, mappedBy="pitstop",cascade = CascadeType.ALL)
  private Set<ImageDetails> imageDetails;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public TripBreakup getBreakup() {
    return breakup;
  }

  public void setBreakup(TripBreakup breakup) {
    this.breakup = breakup;
  }

  public TripInfo getTrip() {
    return trip;
  }

  public void setTrip(TripInfo trip) {
    this.trip = trip;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public Set<ImageDetails> getImageDetails() {
    return imageDetails;
  }

  public void setImageDetails(Set<ImageDetails> imageDetails) {
    this.imageDetails = imageDetails;
  }
}


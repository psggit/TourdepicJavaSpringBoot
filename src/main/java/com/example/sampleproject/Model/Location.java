package com.example.sampleproject.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "location")
public class Location {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(name = "location_identifier")
  private String locationIdentifier;
  @Column(name = "name")
  private String name;
  @Column(name = "lng")
  private String longitude;
  @Column(name = "lat")
  private String latitude;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public String getLocationIdentifier() {
    return locationIdentifier;
  }

  public void setLocationIdentifier(String locationIdentifier) {
    this.locationIdentifier = locationIdentifier;
  }
}

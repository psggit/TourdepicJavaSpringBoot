package com.example.sampleproject;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PitstopRequest {
  @NotNull(message = "Trip id cannot be null")
  private Integer tripId;
  @NotNull(message = "Trip breakup id cannot be null")
  private Integer tripBreakupId;
  @NotEmpty(message = "Pitstop type cannot be empty or null")
  private String type;
  @NotEmpty(message = "Pitstop name cannot be empty or null")
  private String name;
  @NotEmpty(message = "Location identifier cannot be empty or null")
  private String locationIdentifier;
  @NotEmpty(message = "Location name cannot be empty or null")
  private String locationName;
  @NotEmpty(message = "Latitude cannot be empty or null")
  private String latitude;
  @NotEmpty(message = "Longitude cannot be empty or null")
  private String longitude;

  public Integer getTripId() {
    return tripId;
  }

  public void setTripId(Integer tripId) {
    this.tripId = tripId;
  }

  public Integer getTripBreakupId() {
    return tripBreakupId;
  }

  public void setTripBreakupId(Integer tripBreakupId) {
    this.tripBreakupId = tripBreakupId;
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

  public String getLocationIdentifier() {
    return locationIdentifier;
  }

  public void setLocationIdentifier(String locationIdentifier) {
    this.locationIdentifier = locationIdentifier;
  }

  public String getLocationName() {
    return locationName;
  }

  public void setLocationName(String locationName) {
    this.locationName = locationName;
  }

  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }
}

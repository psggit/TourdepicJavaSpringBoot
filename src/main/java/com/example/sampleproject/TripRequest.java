package com.example.sampleproject;

import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TripRequest {

  @NotNull(message = "User id cannot be null")
  private Integer userId;
  @NotEmpty(message = "From date cannot be empty or null")
  private String fromDate;
  @NotEmpty(message = "To date cannot be empty or null")
  private String toDate;
  private Map<String, Object> fromLocation;
  private Map<String, Object> toLocation;
  private String description;
  @NotEmpty(message = "Trip breakup cannot be empty or null")
  private List<Map<String, Object>> tripBreakup;

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getFromDate() {
    return fromDate;
  }

  public void setFromDate(String fromDate) {
    this.fromDate = fromDate;
  }

  public String getToDate() {
    return toDate;
  }

  public void setToDate(String toDate) {
    this.toDate = toDate;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Map<String, Object>> getTripBreakup() {
    return tripBreakup;
  }

  public void setTripBreakup(List<Map<String, Object>> tripBreakup) {
    this.tripBreakup = tripBreakup;
  }
  public Map<String, Object> getFromLocation() {
    return fromLocation;
  }

  public void setFromLocation(Map<String, Object> fromLocation) {
    this.fromLocation = fromLocation;
  }

  public Map<String, Object> getToLocation() {
    return toLocation;
  }

  public void setToLocation(Map<String, Object> toLocation) {
    this.toLocation = toLocation;
  }
}

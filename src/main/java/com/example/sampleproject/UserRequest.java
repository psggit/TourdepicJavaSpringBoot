package com.example.sampleproject;

import javax.validation.constraints.NotEmpty;

public class UserRequest {

  private Integer id;
  @NotEmpty(message = "Name cannot be empty or null")
  private String name;
  @NotEmpty(message = "DOB cannot be empty or null")
  private String dob;
  @NotEmpty(message = "Gender cannot be empty or null")
  private String gender;
  @NotEmpty(message = "Mobile cannot be empty or null")
  private String mobile;
  private Integer locationId;
  @NotEmpty(message = "Location identifier cannot be empty or null")
  private String locationIdentifier;
  @NotEmpty(message = "Location name cannot be empty or null")
  private String locationName;
  @NotEmpty(message = "Latitude cannot be empty or null")
  private String latitude;
  @NotEmpty(message = "Longitude cannot be empty or null")
  private String longitude;
  private String profile;
  private String description;

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

  public String getDob() {
    return dob;
  }

  public void setDob(String dob) {
    this.dob = dob;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public Integer getLocationId() {
    return locationId;
  }

  public void setLocationId(Integer locationId) {
    this.locationId = locationId;
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

  public String getProfile() {
    return profile;
  }

  public void setProfile(String profile) {
    this.profile = profile;
  }

  public String getLocationIdentifier() {
    return locationIdentifier;
  }

  public void setLocationIdentifier(String locationIdentifier) {
    this.locationIdentifier = locationIdentifier;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}

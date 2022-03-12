package com.example.sampleproject;

import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ImageRequest {
  @NotNull(message = "User id cannot be null")
  private Integer userId;
  @NotNull(message = "Trip id cannot be null")
  private Integer tripId;
  @NotNull(message = "Pitstop id cannot be null")
  private Integer pitstopId;
  @NotEmpty(message = "Images cannot be empty or null")
  private List<Map<String, Object>> images;
//  @NotEmpty(message = "Url cannot be empty or null")
//  private String url;
//  private String description;

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Integer getTripId() {
    return tripId;
  }

  public void setTripId(Integer tripId) {
    this.tripId = tripId;
  }

  public Integer getPitstopId() {
    return pitstopId;
  }

  public void setPitstopId(Integer pitstopId) {
    this.pitstopId = pitstopId;
  }

  public List<Map<String, Object>> getImages() {
    return images;
  }

  public void setImages(List<Map<String, Object>> images) {
    this.images = images;
  }

//  public String getUrl() {
//    return url;
//  }
//
//  public void setUrl(String url) {
//    this.url = url;
//  }
//
//  public String getDescription() {
//    return description;
//  }
//
//  public void setDescription(String description) {
//    this.description = description;
//  }
}

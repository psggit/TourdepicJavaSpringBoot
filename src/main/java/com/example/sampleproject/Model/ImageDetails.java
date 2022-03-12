package com.example.sampleproject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "image_details")
public class ImageDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(name = "description")
  private String description;
  @Column(name = "url")
  private String url;
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id")
  @JsonIgnore
  private User user;
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "trip_id")
  @JsonIgnore
  private TripInfo trip;
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "pitstop_id", referencedColumnName = "id")
  @JsonIgnore
  private Pitstop pitstop;
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "location_id")
  private Location locationId;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public TripInfo getTrip() {
    return trip;
  }

  public void setTrip(TripInfo trip) {
    this.trip = trip;
  }

  public Location getLocationId() {
    return locationId;
  }

  public void setLocationId(Location locationId) {
    this.locationId = locationId;
  }

  public Pitstop getPitstop() {
    return pitstop;
  }

  public void setPitstop(Pitstop pitstop) {
    this.pitstop = pitstop;
  }
}

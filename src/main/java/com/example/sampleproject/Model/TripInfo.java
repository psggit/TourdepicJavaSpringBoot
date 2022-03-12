package com.example.sampleproject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Date;
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
@Table(name = "trip_info")
public class TripInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "source_id")
  private Location sourceId;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "destination_id")
  private Location destinationId;
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id")
  @JsonIgnore
  private User user;
  @OneToMany(fetch = FetchType.EAGER, mappedBy="trip",cascade = CascadeType.ALL)
  private Set<TripBreakup> tripBreakups;
  @Column(name = "status_id")
  private Integer statusId;
  @Column(name = "from_date")
  private Date fromDate;
  @Column(name = "to_date")
  private Date toDate;
  @Column(name = "description")
  private String description;
  @Column(name = "likes")
  private String likes;
  @Column(name = "views")
  private String views;

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

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Integer getStatusId() {
    return statusId;
  }

  public void setStatusId(Integer statusId) {
    this.statusId = statusId;
  }

  public Date getFromDate() {
    return fromDate;
  }

  public void setFromDate(Date fromDate) {
    this.fromDate = fromDate;
  }

  public Date getToDate() {
    return toDate;
  }

  public void setToDate(Date toDate) {
    this.toDate = toDate;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getLikes() {
    return likes;
  }

  public void setLikes(String likes) {
    this.likes = likes;
  }

  public String getViews() {
    return views;
  }

  public void setViews(String views) {
    this.views = views;
  }

  public Set<TripBreakup> getTripBreakups() {
    return tripBreakups;
  }

  public void setTripBreakups(Set<TripBreakup> tripBreakups) {
    this.tripBreakups = tripBreakups;
  }
}

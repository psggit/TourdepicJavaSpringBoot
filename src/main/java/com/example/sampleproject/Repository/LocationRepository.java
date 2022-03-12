package com.example.sampleproject.Repository;

import com.example.sampleproject.Model.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository<Location, Integer> {

  @Query(value = "SELECT * FROM location l where l.location_identifier =:locationIdentifier and l.name=:name and l.lat=:latitude and l.lng=:longitude", nativeQuery = true)
  Location isLocationExists(String locationIdentifier, String name, String latitude, String longitude);
}

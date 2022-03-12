package com.example.sampleproject.Repository;

import com.example.sampleproject.Model.Pitstop;
import com.example.sampleproject.Model.TripInfo;
import com.example.sampleproject.Model.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface PitstopRepository extends CrudRepository<Pitstop, Integer> {
  List<Pitstop> findPitstopByTrip(TripInfo trip);
}

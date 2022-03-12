package com.example.sampleproject.Repository;

import com.example.sampleproject.Model.TripInfo;
import com.example.sampleproject.Model.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface TripInfoRepository extends CrudRepository<TripInfo, Integer> {

  List<TripInfo> findTripInfoByUser(User user);


}

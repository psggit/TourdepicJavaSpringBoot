package com.example.sampleproject.Repository;

import com.example.sampleproject.Model.TripBreakup;
import com.example.sampleproject.Model.TripInfo;
import java.util.List;
import org.springframework.data.repository.CrudRepository;


public interface TripBreakupRepository extends CrudRepository<TripBreakup, Integer> {
}


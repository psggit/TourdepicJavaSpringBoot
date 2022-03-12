package com.example.sampleproject.Repository;

import com.example.sampleproject.Model.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

//  @Modifying
//  @Query(value="select * from User", nativeQuery = true)
//  List<User> findAllUsers();

  List<User> findByMobileIn(List<String> mobileList);
}

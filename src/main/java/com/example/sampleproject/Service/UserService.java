package com.example.sampleproject.Service;

import com.example.sampleproject.FriendResponse;
import com.example.sampleproject.LoginRequest;
import com.example.sampleproject.LoginResponse;
import com.example.sampleproject.Model.Location;
import com.example.sampleproject.Model.User;
import com.example.sampleproject.Repository.LocationRepository;
import com.example.sampleproject.Repository.UserRepository;
import com.example.sampleproject.UserRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final LocationRepository locationRepository;

  public UserService(UserRepository userRepository,
      LocationRepository locationRepository) {
    this.userRepository = userRepository;
    this.locationRepository = locationRepository;
  }

//  public List<User> fetchUsers() {
//    return (List<User>) userRepository.findAll();
//  }

  private Location saveLocation(UserRequest userRequest) {
    Integer locationId = userRequest.getLocationId();
    String locationName = userRequest.getLocationName();
    String latitude = userRequest.getLatitude();
    String longitude = userRequest.getLongitude();
    String locationIdentifier = userRequest.getLocationIdentifier();

    Location record = locationRepository
        .isLocationExists(locationIdentifier, locationName, latitude, longitude);

    if (record == null) {
      Location location = new Location();
      location.setId(locationId);
      location.setLocationIdentifier(locationIdentifier);
      location.setName(locationName);
      location.setLatitude(latitude);
      location.setLongitude(longitude);

      return locationRepository.save(location);
    }

    return record;
  }

  public User createUser(UserRequest userRequest) {
    Location location = saveLocation(userRequest);

    User user = new User();
    user.setId(null != userRequest.getId() ? userRequest.getId() : 0);
    user.setName(userRequest.getName());
    user.setDescription(userRequest.getDescription());
    user.setMobile(userRequest.getMobile());
    user.setProfile(userRequest.getProfile());
    user.setDob(userRequest.getDob());
    user.setGender(userRequest.getGender());
    user.setLocation(location);

    return userRepository.save(user);
  }

  public LoginResponse loginUser(LoginRequest loginRequest) {
    List<String> mobileList = new ArrayList();
    mobileList.add(loginRequest.getMobile());

    List<User> userList = userRepository.findByMobileIn(mobileList);
    LoginResponse loginResponse = new LoginResponse();

    if (userList != null && !userList.isEmpty()) {
      loginResponse.setMessage("Existing user");
      loginResponse.setUser(userList.get(0));
    } else {
      loginResponse.setMessage("New user");
    }

    return loginResponse;
  }

  public List<User> syncContact(List<String> mobileList) {
    return userRepository.findByMobileIn(mobileList);
  }

  public String addFriend(Integer userId, Integer friendId) throws Exception {
    String message = "Error";
    try {
      Optional<User> userRecord = userRepository.findById(userId);
      if (userRecord.isPresent()) {
        String friends = userRecord.get().getFriendList();
        Optional<User> friendRecord = userRepository.findById(friendId);
        if(friendRecord.isPresent()) {
          if (friends != null) {
            friends = friends.concat("," + friendId);
            String[] uniqueList = Arrays.stream(friends.split("[,]")).distinct()
                .toArray(String[]::new);
            userRecord.get().setFriendList(String.join(",", uniqueList));
          } else {
            userRecord.get().setFriendList(String.valueOf(friendId));
          }
          userRepository.save(userRecord.get());
          message = "Success";
        } else {
          message = "Friend record not found";
        }
      }
    } catch (Exception e) {
      throw new Exception("Error in adding friend" + e);
    }
    return message;
  }


  public String blockFriend(Integer userId, Integer friendId) throws Exception {
    String message = "Error";
    try {
      Optional<User> userRecord = userRepository.findById(userId);
      //check if user exists
      if (userRecord.isPresent()) {
        String blockedFriends = userRecord.get().getBlockedList();
        Optional<User> friendRecord = userRepository.findById(friendId);
        //check if friend exists
        if(friendRecord.isPresent()) {
          if (blockedFriends != null) {
            blockedFriends = blockedFriends.concat("," + friendId);
            String[] uniqueList = Arrays.stream(blockedFriends.split("[,]")).distinct()
                .toArray(String[]::new);
            userRecord.get().setBlockedList(String.join(",", uniqueList));
          } else {
            userRecord.get().setBlockedList(friendId.toString());
          }
          String friends = userRecord.get().getFriendList();
          String[] friendList = friends.split("[,]");
          List<String> updatedFriendList = Arrays.asList(friendList);
          List<String> finalList = new ArrayList<>();
          finalList.addAll(updatedFriendList);
          finalList.remove(String.valueOf(friendId));
          userRecord.get().setFriendList(String.join(",", finalList));
          userRepository.save(userRecord.get());
          message = "Success";
        } else {
          message = "Friend record not found";
        }
      }
    } catch (Exception e) {
      throw new Exception("Error in removing friend" + e);
    }
    return message;
  }

  public FriendResponse getFriends(Integer userId) throws Exception {
    try {
      Optional<User> userRecord = userRepository.findById(userId);
      FriendResponse friendResponse = new FriendResponse();
      if (userRecord.isPresent()) {
        friendResponse.setFriendList(userRecord.get().getFriendList());
        friendResponse.setBlockedList(userRecord.get().getBlockedList());
      }
      return friendResponse;
    } catch(Exception  e) {
      throw new Exception("Bad request exception");
    }
  }
}

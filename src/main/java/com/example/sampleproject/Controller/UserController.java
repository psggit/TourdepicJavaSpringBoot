package com.example.sampleproject.Controller;

import com.example.sampleproject.FriendResponse;
import com.example.sampleproject.LoginRequest;
import com.example.sampleproject.LoginResponse;
import com.example.sampleproject.Model.User;
import com.example.sampleproject.Service.UserService;
import com.example.sampleproject.UserRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

//  @GetMapping
//  public ResponseEntity<List<User>> fetchUserList() {
//    List<User> userListResponse = userService.fetchUsers();
//    return new ResponseEntity<>(userListResponse, HttpStatus.OK);
//  }

  @PostMapping(value = "/create")
  public ResponseEntity<Map<String, Integer>> createUser(@RequestBody @Valid UserRequest userRequest) {
    User user = userService.createUser(userRequest);
    Map<String, Integer> response = new HashMap<>();
    response.put("id", user.getId());
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @PostMapping(value = "/login")
  public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
    LoginResponse loginResponse = userService.loginUser(loginRequest);
    return new ResponseEntity<>(loginResponse, HttpStatus.OK);
  }

  @PostMapping(value = "/sync")
  public ResponseEntity<List<User>> syncContact(@RequestBody Map<String, List<String>> mobileMap) {
    System.out.print("user" + mobileMap);
    List<User> userList = userService.syncContact(mobileMap.get("mobile"));
    return new ResponseEntity<>(userList, HttpStatus.OK);
  }

  @PostMapping(value = "/addfriend")
  public ResponseEntity<Map<String,String>> addFriend(@RequestBody Map<String, Integer> inputRequest)
      throws Exception {
    Integer userId = inputRequest.get("userId");
    Integer friendId = inputRequest.get("friendId");
    String message = userService.addFriend(userId, friendId);
    Map<String, String> response = new HashMap<>();
    response.put("message", message);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping(value = "/blockfriend")
  public ResponseEntity<Map<String,String>> blockFriend(@RequestBody Map<String, Integer> inputRequest)
      throws Exception {
    Integer userId = inputRequest.get("userId");
    Integer friendId = inputRequest.get("friendId");
    String message = userService.blockFriend(userId, friendId);
    Map<String, String> response = new HashMap<>();
    response.put("message", message);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping(value = "/friends")
  public ResponseEntity<FriendResponse> getFriends(@RequestBody Map<String, Integer> inputRequest)
      throws Exception {
    Integer userId = inputRequest.get("userId");
    FriendResponse friendResponse = userService.getFriends(userId);
    return new ResponseEntity<>(friendResponse, HttpStatus.OK);
  }
}

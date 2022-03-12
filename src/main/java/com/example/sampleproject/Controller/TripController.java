package com.example.sampleproject.Controller;

import com.example.sampleproject.ImageRequest;
import com.example.sampleproject.Model.Pitstop;
import com.example.sampleproject.Model.TripInfo;
import com.example.sampleproject.Model.User;
import com.example.sampleproject.PitstopRequest;
import com.example.sampleproject.Service.TripService;
import com.example.sampleproject.TripRequest;
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
@RequestMapping(value = "/trip")
public class TripController {

  private final TripService tripService;

  public TripController(TripService tripService) {

    this.tripService = tripService;
  }

  @PostMapping(value = "/create")
  public ResponseEntity<TripInfo> createTrip(@RequestBody @Valid TripRequest tripRequest)
      throws Exception {
    //TripResponse tripResponse = tripService.createTrip(tripRequest);
    TripInfo tripInfo = tripService.createTrip(tripRequest);
    return new ResponseEntity<>(tripInfo, HttpStatus.OK);
  }

  @PostMapping(value = "/createPitstop")
  public ResponseEntity<Map<String, Integer>> createPitstop(@RequestBody @Valid PitstopRequest pitstopRequest)
      throws Exception {
    Pitstop pitstop = tripService.createPitstop(pitstopRequest);
    Map<String, Integer> response = new HashMap<>();
    response.put("id", pitstop.getId());
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping(value = "/createImages")
  public ResponseEntity<Map<String, String>> createImages(@RequestBody @Valid ImageRequest imageRequest)
      throws Exception {
    Map<String, String> response = new HashMap<>();
    response.put("message", tripService.createImages(imageRequest));
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping(value = "/postTrip")
  public ResponseEntity<Map<String, String>> postTrip(@RequestBody Map<String, Integer> inputRequest)
      throws Exception {
    Map<String, String> response = new HashMap<>();
    response.put("message", tripService.postTrip(inputRequest));
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping(value = "/likePost")
  public ResponseEntity<Map<String, String>> likePost(@RequestBody Map<String, Object> inputRequest)
      throws Exception {
    Map<String, String> response = new HashMap<>();
    response.put("message", tripService.likePost(inputRequest));
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping(value = "/viewPost")
  public ResponseEntity<Map<String, String>> viewPost(@RequestBody Map<String, Object> inputRequest)
      throws Exception {
    Map<String, String> response = new HashMap<>();
    response.put("message", tripService.viewPost(inputRequest));
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping(value = "/searchByLocation")
  public ResponseEntity<Map<String, List<User>>> searchByLocation(@RequestBody Map<String, Object> inputRequest)
      throws Exception {
    List<User> userList = tripService.searchByLocation(inputRequest);
    Map<String, List<User>> response = new HashMap<>();
    response.put("userList", userList);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping(value = "/getTrips")
  public ResponseEntity<Map<String, List<TripInfo>>> getTrips(@RequestBody Map<String, Integer> inputRequest)
      throws Exception {
    Map<String, List<TripInfo>> response = tripService.getTrips(inputRequest);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping(value = "/getTripDetails")
  public ResponseEntity<Map<String,  List<Pitstop>>> getTripDetails(@RequestBody Map<String, Integer> inputRequest)
      throws Exception {
    Map<String,  List<Pitstop>> response = new HashMap<>();
    response.put("pitstops", tripService.getTripDetails(inputRequest));
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}

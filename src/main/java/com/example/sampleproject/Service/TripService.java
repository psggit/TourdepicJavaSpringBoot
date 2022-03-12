package com.example.sampleproject.Service;

import com.example.sampleproject.ImageRequest;
import com.example.sampleproject.Model.ImageDetails;
import com.example.sampleproject.Model.Location;
import com.example.sampleproject.Model.Pitstop;
import com.example.sampleproject.Model.TripBreakup;
import com.example.sampleproject.Model.TripInfo;
import com.example.sampleproject.Model.User;
import com.example.sampleproject.PitstopRequest;
import com.example.sampleproject.Repository.ImageDetailsRepository;
import com.example.sampleproject.Repository.LocationRepository;
import com.example.sampleproject.Repository.PitstopRepository;
import com.example.sampleproject.Repository.TripBreakupRepository;
import com.example.sampleproject.Repository.TripInfoRepository;
import com.example.sampleproject.Repository.UserRepository;
import com.example.sampleproject.TripRequest;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class TripService {

  private final TripInfoRepository tripInfoRepository;
  private final TripBreakupRepository tripBreakupRepository;
  private final LocationRepository locationRepository;
  private final UserRepository userRepository;
  private final PitstopRepository pitstopRepository;
  private final ImageDetailsRepository imageDetailsRepository;

  private static final String LOCATION_ID = "id";
  private static final String LOCATION_NAME = "name";
  private static final String LOCATION_LATITUDE = "latitude";
  private static final String LOCATION_LONGITUDE = "longitude";
  private static final String LOCATION_IDENTIFIER = "locationIdentifier";

  public TripService(TripInfoRepository tripInfoRepository,
      TripBreakupRepository tripBreakupRepository,
      LocationRepository locationRepository,
      UserRepository userRepository,
      PitstopRepository pitstopRepository,
      ImageDetailsRepository imageDetailsRepository) {
    this.tripInfoRepository = tripInfoRepository;

    this.tripBreakupRepository = tripBreakupRepository;
    this.locationRepository = locationRepository;
    this.userRepository = userRepository;
    this.pitstopRepository = pitstopRepository;
    this.imageDetailsRepository = imageDetailsRepository;
  }

  private Location saveLocation(Map<String, Object> locationReq) {
    Integer locationId = (Integer) locationReq.get(LOCATION_ID);
    String locationName = String.valueOf(locationReq.get(LOCATION_NAME));
    String latitude = String.valueOf(locationReq.get(LOCATION_LATITUDE));
    String longitude = String.valueOf(locationReq.get(LOCATION_LONGITUDE));
    String locationIdentifier = String.valueOf(locationReq.get(LOCATION_IDENTIFIER));

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

  private Location isLocationExist(Map<String, Object> locationReq) {
    String locationName = String.valueOf(locationReq.get(LOCATION_NAME));
    String latitude = String.valueOf(locationReq.get(LOCATION_LATITUDE));
    String longitude = String.valueOf(locationReq.get(LOCATION_LONGITUDE));
    String locationIdentifier = String.valueOf(locationReq.get(LOCATION_IDENTIFIER));

    Location record = locationRepository
        .isLocationExists(locationIdentifier, locationName, latitude, longitude);

    return record;
  }

//  public TripResponse createTrip(TripRequest tripRequest) {
//    TripResponse tripResponse = new TripResponse();
//    List<Object> breakupList = new ArrayList<>();
//
//    TripInfo tripInfo = new TripInfo();
//
//    Optional<User> user = userRepository.findById(tripRequest.getUserId());
//
//    if (user.isPresent()) {
//      Location sourceLocation = saveLocation(tripRequest.getFromLocation());
//      Location destinationLocation = saveLocation(tripRequest.getToLocation());
//
//      //SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//
//      LocalDate fromDate = Date.valueOf(tripRequest.getFromDate()).toLocalDate();
//      LocalDate toDate = Date.valueOf(tripRequest.getToDate()).toLocalDate();
//
//      tripInfo.setFromDate(Date.valueOf(fromDate));
//      tripInfo.setToDate(Date.valueOf(toDate));
//      tripInfo.setDescription(tripRequest.getDescription());
//      tripInfo.setStatusId(4);
//      tripInfo.setUserId(user.get());
//      tripInfo.setDestinationId(destinationLocation);
//      tripInfo.setSourceId(sourceLocation);
//      tripInfo.setLikes("");
//      tripInfo.setViews("");
//
//      TripInfo createdTrip = tripInfoRepository.save(tripInfo);
//
//      tripRequest.getTripBreakup().forEach(item -> {
//        TripBreakup tripBreakup = new TripBreakup();
//        Map<String, Object> tripBreakupInfo = new HashMap<>();
//
//        Location sourceBreakupLocation = saveLocation(
//            (Map<String, Object>) item.get("fromLocation"));
//        Location destinationBreakupLocation = saveLocation(
//            (Map<String, Object>) item.get("toLocation"));
//
//        tripBreakup.setMode(String.valueOf(item.get("travelMode")));
//        tripBreakup.setTripId(createdTrip);
//        tripBreakup.setSourceId(sourceBreakupLocation);
//        tripBreakup.setDestinationId(destinationBreakupLocation);
//
//        TripBreakup createdTripBreakup = tripBreakupRepository.save(tripBreakup);
//
//        tripBreakupInfo.put("breakupId", createdTripBreakup.getId());
//        tripBreakupInfo.put("sourceLocation", sourceBreakupLocation);
//        tripBreakupInfo.put("destinationLocation", destinationBreakupLocation);
//        breakupList.add(tripBreakupInfo);
//      });
//
//      tripResponse.setTripId(createdTrip.getId());
//      tripResponse.setTripBreakups(breakupList);
//    }
//    return tripResponse;
//  }

  public TripInfo createTrip(TripRequest tripRequest) throws Exception {
    TripInfo tripInfo = new TripInfo();

    Optional<User> user = userRepository.findById(tripRequest.getUserId());

    try {
      if (user.isPresent()) {
        Location sourceLocation = saveLocation(tripRequest.getFromLocation());
        Location destinationLocation = saveLocation(tripRequest.getToLocation());

        //SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        LocalDate fromDate = Date.valueOf(tripRequest.getFromDate()).toLocalDate();
        LocalDate toDate = Date.valueOf(tripRequest.getToDate()).toLocalDate();

        tripInfo.setFromDate(Date.valueOf(fromDate));
        tripInfo.setToDate(Date.valueOf(toDate));
        tripInfo.setDescription(tripRequest.getDescription());
        tripInfo.setStatusId(4);
        tripInfo.setUser(user.get());
        tripInfo.setDestinationId(destinationLocation);
        tripInfo.setSourceId(sourceLocation);
        tripInfo.setLikes("");
        tripInfo.setViews("");

        Set<TripBreakup> breakupSet = new HashSet();
        tripRequest.getTripBreakup().forEach(item -> {
          TripBreakup tripBreakup = new TripBreakup();

          Location sourceBreakupLocation = saveLocation(
              (Map<String, Object>) item.get("fromLocation"));
          Location destinationBreakupLocation = saveLocation(
              (Map<String, Object>) item.get("toLocation"));

          tripBreakup.setMode(String.valueOf(item.get("travelMode")));
          tripBreakup.setSourceId(sourceBreakupLocation);
          tripBreakup.setDestinationId(destinationBreakupLocation);
          tripBreakup.setTrip(tripInfo);

          breakupSet.add(tripBreakup);
        });

        tripInfo.setTripBreakups(breakupSet);
        return tripInfoRepository.save(tripInfo);
      }
    } catch (Exception e) {
      throw new Exception("Error in creating trip" + e);
    }
    return null;
  }

  public Pitstop createPitstop(PitstopRequest pitstopRequest) throws Exception {
    Optional<TripInfo> trip = tripInfoRepository.findById(pitstopRequest.getTripId());
    Pitstop pitstop = new Pitstop();
    try {
      pitstop.setName(pitstopRequest.getName());
      pitstop.setType(pitstopRequest.getType());
      pitstop.setTrip(trip.get());

      Optional<TripBreakup> tripBreakup = tripBreakupRepository
          .findById(pitstopRequest.getTripBreakupId());
      pitstop.setBreakup(tripBreakup.get());

      Map<String, Object> locationDetail = new HashMap<>();
      locationDetail.put(LOCATION_NAME, pitstopRequest.getLocationName());
      locationDetail.put(LOCATION_LATITUDE, pitstopRequest.getLatitude());
      locationDetail.put(LOCATION_LONGITUDE, pitstopRequest.getLongitude());
      locationDetail.put(LOCATION_IDENTIFIER, pitstopRequest.getLocationIdentifier());

      Location location = saveLocation(locationDetail);
      pitstop.setLocation(location);
    } catch (Exception e) {
      throw new Exception("No record found" + e);
    }
    return pitstopRepository.save(pitstop);
  }

  public String createImages(ImageRequest imageRequest) throws Exception {
    String message = "Error";

    try {
      Optional<Pitstop> pitstop = pitstopRepository.findById(imageRequest.getPitstopId());

      if (pitstop.isPresent()) {
        imageRequest.getImages().forEach(item -> {
          ImageDetails imageDetails = new ImageDetails();
          imageDetails.setDescription(String.valueOf(item.get("description")));
          imageDetails.setUrl(String.valueOf(item.get("url")));

          Optional<TripInfo> trip = tripInfoRepository.findById(imageRequest.getTripId());
          imageDetails.setPitstop(pitstop.get());

          imageDetails.setTrip(trip.get());

          Optional<User> user = userRepository.findById(imageRequest.getUserId());
          imageDetails.setUser(user.get());

          Location location = saveLocation((Map<String, Object>) item.get("location"));
          imageDetails.setLocationId(location);

          imageDetailsRepository.save(imageDetails);
        });
        message = "Success";
      } else {
        message = "Pitstop is not found";
      }
    } catch (Exception e) {
      throw new Exception("Error in adding image" + e);
    }

    return message;
  }

  public String postTrip(Map<String, Integer> inputRequest) throws Exception {
    String message = "Error";
    TripInfo tripInfo = tripInfoRepository.findById(inputRequest.get("tripId")).orElse(null);

    try {
      if (null != tripInfo) {
        tripInfo.setStatusId(inputRequest.get("statusId"));
        tripInfoRepository.save(tripInfo);
        message = "Success";
      } else {
        message = "Trip details not found";
      }
    } catch (Exception e) {
      throw new Exception("Error in updating trip post status" + e);
    }

    return message;
  }


  public String likePost(Map<String, Object> inputRequest) throws Exception {
    String message = "Error";
    Boolean like = (Boolean) inputRequest.get("like");
    Integer userId = (Integer) inputRequest.get("userId");
    Integer tripId = (Integer) inputRequest.get("tripId");

    TripInfo tripInfo = tripInfoRepository.findById(tripId).orElse(null);

    try {
      if (null != tripInfo) {
        String likes = tripInfo.getLikes();
        Optional<User> userRecord = userRepository.findById(userId);
        if (userRecord.isPresent()) {
          if (like) {
            if (likes.isEmpty()) {
              tripInfo.setLikes(String.valueOf(userId));
            } else {
              likes = likes.concat("," + userId);
              String[] uniqueList = Arrays.stream(likes.split("[,]")).distinct()
                  .toArray(String[]::new);
              tripInfo.setLikes(String.join(",", uniqueList));
            }
          } else if (!like) {
            String[] likeList = likes.split("[,]");
            List<String> updatedFriendList = Arrays.asList(likeList);
            List<String> finalList = new ArrayList<>();
            finalList.addAll(updatedFriendList);
            finalList.remove(String.valueOf(userId));
          }
          userRepository.save(userRecord.get());
          message = "Success";
        } else {
          message = "User details not found";
        }
      } else {
        message = "Trip details not found";
      }
    } catch (Exception e) {
      throw new Exception("Error in like post" + e);
    }

    return message;
  }

  public String viewPost(Map<String, Object> inputRequest) throws Exception {
    String message = "Error";
    Integer userId = (Integer) inputRequest.get("userId");
    Integer tripId = (Integer) inputRequest.get("tripId");

    TripInfo tripInfo = tripInfoRepository.findById(tripId).orElse(null);

    try {
      if (null != tripInfo) {
        String views = tripInfo.getViews();
        Optional<User> userRecord = userRepository.findById(userId);
        if (userRecord.isPresent()) {
          if (views.isEmpty()) {
            tripInfo.setViews(String.valueOf(userId));
          } else {
            views = views.concat("," + userId);
            String[] uniqueList = Arrays.stream(views.split("[,]")).distinct()
                .toArray(String[]::new);
            tripInfo.setViews(String.join(",", uniqueList));
          }
          userRepository.save(userRecord.get());
          message = "Success";
        } else {
          message = "User details not found";
        }
      } else {
        message = "Trip details not found";
      }
    } catch (Exception e) {
      throw new Exception("Error in view post" + e);
    }

    return message;
  }

  public List<User> searchByLocation(Map<String, Object> inputRequest) throws Exception {
    Integer userId = (Integer) inputRequest.get("userId");

    List<User> responseList = new ArrayList<>();

    User userRecord = userRepository.findById(userId).orElse(null);
    Location location = isLocationExist(inputRequest);

    try {
      if (userRecord != null) {
        String friends = userRecord.getFriendList();
        String[] friendList = friends.split("[,]");
        Arrays.stream(friendList).forEach(item -> {
          Integer friendId = Integer.valueOf(item);
          User friendDetails = userRepository.findById(friendId).orElse(null);
          if (null != friendDetails && null != location && location.getId() == friendDetails
              .getLocation().getId()) {
            responseList.add(friendDetails);
          }
        });
      } else {
        throw new Exception("User record not found");
      }
    } catch (Exception e) {
      throw new Exception("Error in searching by location" + e);
    }

    return responseList;
  }

  public Map<String, List<TripInfo>> getTrips(Map<String, Integer> inputRequest) throws Exception {
    Integer userId = Integer.valueOf(inputRequest.get("userId"));

    User userRecord = userRepository.findById(userId).orElse(null);

    Map<String, List<TripInfo>> tripResponse = new HashMap<>();

    try {
      if (null != userRecord) {
        //Map<String, Object> detailedTripMap = new HashMap<>();

        List<TripInfo> trips = tripInfoRepository.findTripInfoByUser(userRecord);

        tripResponse.put("userTrips", trips);

        String friends = userRecord.getFriendList();
        String[] friendList = friends.split("[,]");

        //Map<String, Object> detailedFriendTripMap = new HashMap<>();
        List<TripInfo> friendTripList = new ArrayList<>();

        Arrays.stream(friendList).forEach(item -> {
          User friendRecord = userRepository.findById(Integer.valueOf(item)).orElse(null);
          List<TripInfo> friendTrips = tripInfoRepository.findTripInfoByUser(friendRecord);
          friendTripList.addAll(friendTrips);
        });

        tripResponse.put("friendTrips", friendTripList);
        return tripResponse;
      } else {
        throw new Exception("User not found");
      }
    } catch (Exception e) {
      throw new Exception("Error in getting trips", e);
    }
  }

  public List<Pitstop> getTripDetails(Map<String, Integer> inputRequest) throws Exception {
    Integer tripId = Integer.valueOf(inputRequest.get("tripId"));

    TripInfo tripInfo = tripInfoRepository.findById(tripId).orElse(null);

    try {
      if(null != tripInfo) {
        List<Pitstop> pitstop = pitstopRepository.findPitstopByTrip(tripInfo);
        return pitstop;
      }
    } catch(Exception e) {
      throw new Exception("Error in getting trip details"+e);
    }
    return null;
  }
}



package com.example.sampleproject;

import com.example.sampleproject.Model.User;

public class LoginResponse {

  private String message;
  private User user;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}

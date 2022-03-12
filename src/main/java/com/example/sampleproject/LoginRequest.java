package com.example.sampleproject;

import javax.validation.constraints.NotEmpty;

public class LoginRequest {

  @NotEmpty(message = "Mobile cannot be empty or null")
  private String mobile;
  @NotEmpty(message = "Token cannot be empty or null")
  private String token;

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}

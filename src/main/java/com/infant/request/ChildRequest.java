package com.infant.request;

import com.infant.constants.Gender;
import java.time.LocalDate;

/**
 * Created by sujith on 09-07-2023
 */
public class ChildRequest {

  private String name;

  private LocalDate dateOfBirth;

  private Gender gender;

  private Long userId;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }
}

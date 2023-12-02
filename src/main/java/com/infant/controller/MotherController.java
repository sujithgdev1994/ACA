package com.infant.controller;

import com.infant.request.MotherRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sujith on 09-07-2023
 */
//@RestController
@RequestMapping("v1/mother")
public class MotherController {

  @PostMapping
  public void createMother(@RequestBody MotherRequest motherRequest) {

  }

}

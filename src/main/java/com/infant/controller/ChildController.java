package com.infant.controller;

import com.infant.converter.ChildEntityToChildResponseConverter;
import com.infant.converter.ChildRequestToChildEntityConverter;
import com.infant.entity.ChildEntity;
import com.infant.entity.MotherEntity;
import com.infant.request.ChildRequest;
import com.infant.response.BasicResponse;
import com.infant.response.ChildResponse;
import com.infant.service.ChildService;
import com.infant.service.MotherService;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by sujith on 09-07-2023
 */
//@RestController
@RequestMapping("v1/child")
public class ChildController {

  @Autowired
  private ChildService childService;

  @Autowired
  private MotherService motherService;

  @Autowired
  private ChildEntityToChildResponseConverter childEntityToChildResponseConverter;

  @Autowired
  private ChildRequestToChildEntityConverter childRequestToChildEntityConverter;

  @PostMapping(consumes = "multipart/form-data")
  public ResponseEntity<BasicResponse> createChild(@RequestParam(value = "image", required = false) MultipartFile file,
      ChildRequest childRequest)
      throws IOException {

    ChildEntity childEntity;

    childEntity = childRequestToChildEntityConverter.convert(childRequest, file);

    childService.createChild(childEntity, childRequest.getUserId());

    return ResponseEntity.status(HttpStatusCode.valueOf(HttpStatus.CREATED.value()))
        .body(new BasicResponse("Status", "Child successfully created"));
  }

  @GetMapping("mother/{motherId}")
  public List<ChildResponse> getAllChildByMother(@PathVariable Long motherId) {
    MotherEntity mother = motherService.getMotherById(motherId);
    List<ChildEntity> childEntityList = childService.getChildByMother(mother);
   return childEntityToChildResponseConverter.convert(childEntityList);
  }

}

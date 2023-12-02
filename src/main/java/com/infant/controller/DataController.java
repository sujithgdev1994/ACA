package com.infant.controller;

import com.infant.converter.DataEntityToDataResponseConverter;
import com.infant.converter.DataRequestToDataEntityConverter;
import com.infant.entity.Data;
import com.infant.exception.BadRequestException;
import com.infant.request.ActivityRequest;
import com.infant.response.BasicResponse;
import com.infant.response.DataParentResponse;
import com.infant.response.DataResponse;
import com.infant.service.DataService;
import com.infant.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SecurityRequirement(name = "bearerAuth")
//@RestController
@RequestMapping("v1/data")
public class DataController {

  @Autowired
  private DataRequestToDataEntityConverter dataRequestToDataEntityConverter;
  @Autowired
  private DataEntityToDataResponseConverter dataEntityToDataResponseConverter;
  @Autowired
  private DataService dataService;
  @Autowired
  private UserService userService;

  @PostMapping
  public ResponseEntity<BasicResponse> saveData(@RequestBody ActivityRequest activityRequest) {

    // Data validation
		if (activityRequest == null) {
			throw new BadRequestException("Data should not empty");
		}

    // User validation
		if (StringUtils.isEmpty(activityRequest.getUsername())) {
			throw new BadRequestException("Username is required");
		}
    // Validate user
    userService.getUserByUsername(activityRequest.getUsername());

    List<Data> dataList = dataRequestToDataEntityConverter.convert(activityRequest);

    dataService.saveData(dataList, activityRequest.getUsername());
    return ResponseEntity.status(HttpStatusCode.valueOf(HttpStatus.CREATED.value()))
        .body(new BasicResponse("Success", "Data saved"));
  }

  @Operation
  @GetMapping("user/{username}")
  public ResponseEntity<DataParentResponse> getData(@PathVariable String username,
      @RequestParam(defaultValue = "500", required = false) Integer pageSize,
      @RequestParam(required = false, defaultValue = "0") Integer pageNumber) {
    PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
    Page<Data> dataAsPage = dataService.getData(pageRequest, username);
    List<DataResponse> dataResponses = dataEntityToDataResponseConverter
        .convert(dataAsPage.getContent());
    return ResponseEntity
        .ok(new DataParentResponse(dataResponses, dataAsPage.getTotalElements(),
            dataAsPage.getSize()));
  }

  @GetMapping
  public ResponseEntity<List<DataResponse>> getDataByIdList(@RequestParam(name = "id") List<Long> idList) {
    List<Data> dataList = dataService.fetchAllRecordsById(idList);
    return ResponseEntity.ok(dataEntityToDataResponseConverter.convert(dataList));
  }


}
package com.infant.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infant.dto.FCMBase;
import com.infant.dto.FCMMessage;
import com.infant.entity.User;
import java.util.List;

import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import com.infant.entity.Data;
import com.infant.repository.DataRepository;
import org.springframework.web.client.RestTemplate;

@Service
public class DataServiceImpl implements DataService {

    public static final String TITLE = "New Data";

    @Autowired
    private DataRepository dataRepository;

    @Autowired
    private UserService userService;

    @Value("${fcm.server}")
    private String fcmServerKey;

    @Autowired
    private NotificationService notificationService;

    @Override
    public void saveData(List<Data> dataList, String username) {
        List<Data> savedDataList = dataRepository.saveAll(dataList);
        User user = userService.getUserByUsername(username);
        String idString = savedDataList.stream().map(Data::getId).map(Objects::toString)
            .collect(Collectors.joining(","));
        notificationService.sendNotification(idString, user.getFcmToken(), "android");
    }

    @Override
    public Page<Data> getData(PageRequest pageRequest, String username) {
        return dataRepository.findByUsername(pageRequest, username);
    }

    @Override
    public List<Data> fetchAllRecordsById(List<Long> ids) {
       return dataRepository.findByIdIn(ids);
    }
}
package com.infant.service;

import com.infant.dto.FCMBase;
import com.infant.dto.FCMMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by sujith on 27-05-2023
 */
@Service
public class NotificationService {

  private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);

  public static final String TITLE = "New Data";

  @Value("${fcm.server}")
  private String fcmServerKey;

  public <T> void sendNotification(T message, String fcmId, String platform) {
    String fcmUrl = "https://fcm.googleapis.com/fcm/send";
    FCMMessage<T> fcmMessage = new FCMMessage<>();
    fcmMessage.setTitle(TITLE);
    fcmMessage.setBody(message);

    FCMBase<T> fcmBase = new FCMBase<>();
    fcmBase.setTo(fcmId);
    if (platform.equalsIgnoreCase("android")) {
      fcmBase.setData(fcmMessage);
    } else {
      fcmBase.setNotification(fcmMessage);
    }

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.set(HttpHeaders.AUTHORIZATION, fcmServerKey);
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<FCMBase<T>> httpEntity = new HttpEntity<>(fcmBase, httpHeaders);
    String response = new RestTemplate().postForObject(fcmUrl, httpEntity, String.class);
    LOGGER.info(response);
  }
}

package com.infant.service;

import com.infant.entity.MotherEntity;

/**
 * Created by sujith on 09-07-2023
 */
public interface MotherService {

   MotherEntity getMotherByUserId(Long userId);

   MotherEntity getMotherById(Long motherId);

}

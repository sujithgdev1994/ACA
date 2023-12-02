package com.infant.service;

import com.infant.entity.MotherEntity;
import com.infant.exception.NotFoundException;
import com.infant.repository.MotherRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sujith on 09-07-2023
 */
@Service
public class MotherServiceImpl implements MotherService {

  @Autowired
  private MotherRepository motherRepository;

  public MotherEntity getMotherByUserId(Long userId) {
    Optional<MotherEntity> optionalMother = motherRepository.findByUserId(userId);
    return optionalMother.orElseThrow(() -> new NotFoundException("Mother not found"));
  }

  @Override
  public MotherEntity getMotherById(Long motherId) {
   return   motherRepository.findById(motherId).orElseThrow(() -> new NotFoundException("Mother not found"));
  }

}

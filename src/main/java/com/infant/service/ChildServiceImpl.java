package com.infant.service;

import com.infant.entity.ChildEntity;
import com.infant.entity.MotherEntity;
import com.infant.repository.ChildRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sujith on 09-07-2023
 */
@Service
public class ChildServiceImpl implements ChildService {

  @Autowired
  private ChildRepository childRepository;

  @Autowired
  private MotherService motherService;

  @Override
  public void createChild(ChildEntity childEntity, Long motherUserId) {
    //Validate mother
    MotherEntity motherEntity = motherService.getMotherByUserId(motherUserId);
    childEntity.setMother(motherEntity);
    childRepository.save(childEntity);
  }

  @Override
  public List<ChildEntity> getChildByMother(MotherEntity mother) {
  return   childRepository.findByMother(mother);
  }
}

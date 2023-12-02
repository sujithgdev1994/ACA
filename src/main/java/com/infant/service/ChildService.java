package com.infant.service;

import com.infant.entity.ChildEntity;
import com.infant.entity.MotherEntity;
import java.util.List;

/**
 * Created by sujith on 09-07-2023
 */
public interface ChildService {

  void createChild(ChildEntity childEntity, Long motherUserId);

  List<ChildEntity> getChildByMother(MotherEntity mother);
}

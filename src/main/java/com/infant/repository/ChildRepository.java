package com.infant.repository;

import com.infant.entity.ChildEntity;
import com.infant.entity.MotherEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sujith on 09-07-2023
 */
public interface ChildRepository extends JpaRepository<ChildEntity, Long> {

  List<ChildEntity> findByMother(MotherEntity mother);
}

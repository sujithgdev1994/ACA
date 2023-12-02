package com.infant.repository;

import com.infant.entity.MotherEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sujith on 09-07-2023
 */
public interface MotherRepository extends JpaRepository<MotherEntity, Long> {

  Optional<MotherEntity> findByUserId(Long userId);
}

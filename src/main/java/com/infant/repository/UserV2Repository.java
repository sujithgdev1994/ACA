package com.infant.repository;

import com.infant.entity.Userv2;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sujith on 04-11-2023
 */
public interface UserV2Repository  extends JpaRepository<Userv2, Long> {

  Userv2 findByUsername(String username);

  Userv2 findByUsernameAndPasswordAndStatus(String username, String password, String status);
}

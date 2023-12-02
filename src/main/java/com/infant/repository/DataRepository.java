package com.infant.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import com.infant.entity.Data;

public interface DataRepository extends JpaRepository<Data, Long> {

	Page<Data> findByUsername(PageRequest pageRequest, String username);

  List<Data> findByIdIn(List<Long> ids);
}
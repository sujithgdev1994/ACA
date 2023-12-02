package com.infant.service;

import com.infant.entity.Data;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface DataService {

  void saveData(List<Data> dataList, String username);

  Page<Data> getData(PageRequest pageRequest, String username);

  List<Data> fetchAllRecordsById(List<Long> ids);
}
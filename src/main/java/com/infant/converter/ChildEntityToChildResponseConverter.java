package com.infant.converter;

import com.infant.entity.ChildEntity;
import com.infant.response.ChildResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by sujith on 14-07-2023
 */
@Component
public class ChildEntityToChildResponseConverter implements
    Converter<ChildEntity, ChildResponse> {

  @Override
  public ChildResponse convert(ChildEntity source) {
    ChildResponse childResponse = new ChildResponse();
    childResponse.setChildId(source.getId());
    childResponse.setChildName(source.getName());
    childResponse.setDateOfBirth(source.getDateOfBirth());
    childResponse.setImage(source.getImage());
    return  childResponse;
  }

  public List<ChildResponse> convert(List<ChildEntity> childList) {
   return childList.stream().map(this::convert).collect(Collectors.toList());
  }
}

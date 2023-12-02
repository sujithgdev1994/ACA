package com.infant.converter;

import com.infant.entity.ChildEntity;
import com.infant.request.ChildRequest;
import java.io.IOException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by sujith on 09-07-2023
 */
@Component
public class ChildRequestToChildEntityConverter  {


  public ChildEntity convert(ChildRequest source, MultipartFile image) throws IOException {
    ChildEntity childEntity = new ChildEntity();
    childEntity.setName(source.getName());
    childEntity.setGender(source.getGender());
    childEntity.setDateOfBirth(source.getDateOfBirth());
    if(image != null) {
      childEntity.setImage(image.getBytes());
    }
    return childEntity;
  }
}

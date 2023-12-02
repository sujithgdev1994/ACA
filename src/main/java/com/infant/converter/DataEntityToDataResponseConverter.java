package com.infant.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.infant.entity.Data;
import com.infant.response.DataResponse;

@Component
public class DataEntityToDataResponseConverter implements Converter<Data, DataResponse> {
    @Override
    public DataResponse convert(Data source) {
        DataResponse dataResponse = new DataResponse();
        dataResponse.setActivity(source.getActivity());
        dataResponse.setDate(source.getDate());
        dataResponse.setIllness(source.getIllness());
        dataResponse.setTemparature(source.getTemparature());
        dataResponse.setTime(source.getTime());
        dataResponse.setId(source.getId());
        return dataResponse;
    }

    public List<DataResponse> convert(List<Data> datas) {
        return datas.stream().map(this::convert).collect(Collectors.toList());
    }
}
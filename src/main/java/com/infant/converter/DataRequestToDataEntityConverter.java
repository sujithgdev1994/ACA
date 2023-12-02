package com.infant.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import com.infant.entity.Data;
import com.infant.request.ActivityRequest;
import com.infant.request.DataRequest;


@Component
public class DataRequestToDataEntityConverter  {

    public Data convert(DataRequest source, String username) {
        Data data = new Data();
        data.setActivity(source.getActivity());
        data.setDate(source.getDate());
        data.setIllness(source.getIllness());
        data.setTemparature(source.getTemparature());
        data.setTime(source.getTime());
        data.setUsername(username);
        return data;
    }

    public List<Data> convert(ActivityRequest activityRequest) {
        return activityRequest.getDataRequests().stream().map(data -> this.convert(data, activityRequest.getUsername())).collect(Collectors.toList());
    }
}
package com.infant.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ActivityRequest {

	@JsonProperty("Username")
	private String username;

	@JsonProperty("Data")
	private List<DataRequest> dataRequests;

	@JsonProperty("Data")
	public List<DataRequest> getDataRequests() {
		return dataRequests;
	}

	public void setDataRequests(List<DataRequest> dataRequests) {
		this.dataRequests = dataRequests;
	}

	@JsonProperty("Username")
	public String getUsername() {
		return username;
	}

	@JsonProperty("Username")
	public void setUsername(String username) {
		this.username = username;
	}

}

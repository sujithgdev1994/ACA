package com.infant.request;

import java.io.Serializable;

public class AuthenticationRequest implements Serializable {

	private static final long serialVersionUID = 1472633284574190278L;

	private String username;
	private String password;
	private String fcmToken;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AuthenticationRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public AuthenticationRequest() {
	}

	public String getFcmToken() {
		return fcmToken;
	}

	public void setFcmToken(String fcmToken) {
		this.fcmToken = fcmToken;
	}

	
}

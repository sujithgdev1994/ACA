package com.infant.response;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {

	private static final long serialVersionUID = 3420393284500860121L;

	private String jwtToken;

	private long userId;

	public AuthenticationResponse(String jwtToken, long userId) {
		this.jwtToken = jwtToken;
		this.userId = userId;
	}

	public String getToken() {
		return this.jwtToken;
	}

	public AuthenticationResponse() {
		super();
	}

	public long getUserId() {
		return userId;
	}
}

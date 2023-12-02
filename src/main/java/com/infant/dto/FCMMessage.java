package com.infant.dto;



public class FCMMessage<T> {



	private String title;
	private T body;
	private final boolean mutable_content=true;
	private final boolean content_available=true;



	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}

	public boolean isMutable_content() {
		return mutable_content;
	}

	public boolean isContent_available() {
		return content_available;
	}
}

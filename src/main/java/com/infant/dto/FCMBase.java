package com.infant.dto;


public class FCMBase<T> {

	private String to;
	private FCMMessage<T> data;
	private FCMMessage<T> notification;

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public FCMMessage<T> getData() {
		return data;
	}

	public void setData(FCMMessage<T> data) {
		this.data = data;
	}

	public FCMMessage<T> getNotification() {
		return notification;
	}

	public void setNotification(FCMMessage<T> notification) {
		this.notification = notification;
	}
}

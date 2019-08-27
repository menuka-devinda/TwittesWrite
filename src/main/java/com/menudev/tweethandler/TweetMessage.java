package com.menudev.tweethandler;

public class TweetMessage {
	
	private final int id;
	private final String message;

	public TweetMessage(int id, String message) {
		super();
		this.id = id;
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

}

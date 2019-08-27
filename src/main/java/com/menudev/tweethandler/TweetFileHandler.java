package com.menudev.tweethandler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.menudev.tweethandler.svc.FileHandlerSvc;

public class TweetFileHandler implements FileHandlerSvc, Runnable {

	private final String topic;
	private final TweetMessage message;

	public TweetFileHandler(String topic, TweetMessage msg) {
		this.topic = topic;
		this.message = msg;
	}

	/**
	 * Depending on the tweet topic it writes to its topic file
	 */
	public void writeTweet() {

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFileName(), true))) {
			writer.newLine();
			writer.write("[id :] " + message.getId() + " [message : ]" + message.getMessage());
			writer.newLine();

		} catch (IOException e) {
			// run after the resources declared have been closed.
			e.printStackTrace();
		} 
	}

	public String getFileName() {
		return topic + ".txt";
	}

	@Override
	public void run() {

		writeTweet();
	}
}

package com.menudev.tweethandler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

 public class TweetFileHandler implements FileHandlerSvc {

	/**
	 * Depending on the tweet topic it writes to its topic file
	 */
	public void writeTweet(String topic, TweetMessage message) {

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(topic + ".txt", true))) {
			writer.newLine();
			writer.write("[id :] " + message.getId() + " [message : ]" + message.getMessage());
			writer.newLine();

		} catch (IOException e) {
			// run after the resources declared have been closed.
			e.printStackTrace();
		} 
	}
	 /**
	  * this method is responsible to write tweets to different files depending on the topic
	  * In this version topic set as the twitter message body.
	  * In the next version we can improve the logic of recognizing the correct topic from message
	  * body.
	  *
	  * @param tweet
	  */
/*	 public void writeTweetsToFiles(final TweetMessage tweet) {

		 String topic = tweet.getMessage();
		 Runnable runnable = new TweetFileHandler(topic, tweet);
		 excutor.submit(runnable);
		 // shuts down the executor immediately.
	 }

	 public void shutdownExecutorService() {
		 System.out.println("Shutting down tweet writing ");
		 excutor.shutdownNow();
	 }
	public String getFileName() {
		return topic + ".txt";
	}

	@Override
	public void run() {

		writeTweet();
	}*/
}

package com.menudev.tweethandler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class responsible to business logic of handling Tweet messages
 * 
 * 
 * @author menuk
 *
 */
public class TweetConsumer implements Runnable {

	private final BlockingQueue<TweetMessage> queue;
	private final ExecutorService excutor = Executors.newFixedThreadPool(5);
	
	public TweetConsumer(BlockingQueue<TweetMessage> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			try {
				TweetMessage take = queue.take();
				System.out.println("[Consumer] Take : " + take.getId() +" : "+ take.getMessage());
				Thread.sleep(500);
				writeTweetsToFiles(take);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				System.out.println("Consumer is stopped !");
			}

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
	public void writeTweetsToFiles(final TweetMessage tweet) {

		String topic = tweet.getMessage();
		Runnable runnable = new TweetFileHandler(topic, tweet);
		excutor.submit(runnable);
		// shuts down the executor immediately.
	}
	
	public void shutdownExecutorService() {
		System.out.println("Shutting down tweet writing ");
		excutor.shutdownNow();
	}
}

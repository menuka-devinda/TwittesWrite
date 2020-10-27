package com.menudev.tweethandler.withoutRx;

import com.menudev.tweethandler.TweetFileHandler;
import com.menudev.tweethandler.TweetMessage;

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
 class TweetConsumer implements Runnable {

	private final BlockingQueue<TweetMessage> queue;
	private final ExecutorService excutor = Executors.newFixedThreadPool(5);
	private final TweetFileHandler tweetFileHandler = new TweetFileHandler();
	
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
				tweetFileHandler.writeTweet(take.getMessage(),  take);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				System.out.println("Consumer is stopped !");
			}

		}
	}


}

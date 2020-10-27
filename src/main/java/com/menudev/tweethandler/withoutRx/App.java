package com.menudev.tweethandler.withoutRx;

import com.menudev.tweethandler.TweetMessage;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 
 */
public class App {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Provide 5 topics");
		String[] topics = initializeApp();
		System.out.println("Press enter to quit once the program is started");
		Thread.sleep(3000);

		BlockingQueue<TweetMessage> queue = new LinkedBlockingQueue<>(10);
		TweetProducer producer = new TweetProducer(queue, topics);
		TweetConsumer consumer = new TweetConsumer(queue);
		Thread t = new Thread(producer);
		Thread t2 = new Thread(consumer);
		t.start();
		t2.start();

		shutdownMessageProcessing(consumer, t, t2);

	}

	private static void shutdownMessageProcessing(TweetConsumer consumer, Thread t, Thread t2) {
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t.interrupt();
		t2.interrupt();

	}

	private static String[] initializeApp() {
		String[] topics = new String[5];

		try (Scanner in = new Scanner(System.in)) {

			System.out.println("Enter topic 1 :");
			topics[0] = in.nextLine();
			System.out.println("Enter topic 2 :");
			topics[1] = in.nextLine();
			System.out.println("Enter topic 3 :");
			topics[2] = in.nextLine();
			System.out.println("Enter topic 4:");
			topics[3] = in.nextLine();
			System.out.println("Enter topic 5 :");
			topics[4] = in.nextLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return topics;
	}
}

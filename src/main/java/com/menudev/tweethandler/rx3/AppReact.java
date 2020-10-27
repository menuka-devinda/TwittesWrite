package com.menudev.tweethandler.rx3;

import java.util.Scanner;

/**
 * 
 */
public class AppReact {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Provide 5 topics");
		String[] topics = initializeApp();
		System.out.println("Press enter to quit once the program is started");
		Thread.sleep(3000);

		TweetReact tweetReact = new TweetReact(topics);
		tweetReact.reactiveTweetProcess();
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

package com.menudev.tweethandler.withoutRx;

import com.menudev.tweethandler.TweetMessage;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class produce tweet messages randomly. Tweet message content is set to topics randomly
 *
 *
 */
 class TweetProducer implements Runnable {

    private final BlockingQueue<TweetMessage> queue;
    private final String[] topics;
    public TweetProducer(BlockingQueue<TweetMessage> queue, String[] topics) {
        this.queue = queue;
        this.topics = topics;
    }

    @Override
    public void run() {

        AtomicInteger atomicInteger = new AtomicInteger(1);
        while (!Thread.currentThread().isInterrupted()) {
            try {
                int id = atomicInteger.getAndIncrement();
                //this method mock genarating tweets message
                String message = generateRandomMessage();
                System.out.println("[Producer] Put : " + atomicInteger.getAndIncrement() + " message: " + message);
                queue.put(new TweetMessage(id, message));
                //System.out.println("[Producer] remain-capacity\t" + queue.remainingCapacity());
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Producer is stopped !");
            }
        }
    }

    /**
     * In the next version of this implementation we use this method to stream
     * tweets from tweeter API
     *
     * @return
     */
    private String generateRandomMessage() {
        // TODO Auto-generated method stub

        return topics[new Random().nextInt(5)];
    }
}

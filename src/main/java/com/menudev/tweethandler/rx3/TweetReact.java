package com.menudev.tweethandler.rx3;

import com.menudev.tweethandler.TweetFileHandler;
import com.menudev.tweethandler.TweetMessage;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class TweetReact {
  private final String[] topics;
  private TweetFileHandler tweetFileHandler = new TweetFileHandler();

  public TweetReact(String[] topics) {
    this.topics = topics;
  }

  public void reactiveTweetProcess() throws InterruptedException {

    //produce tweets as Flowable
    Flowable.generate(() -> new AtomicInteger(0),
            (state, emitter) -> {
              int current = state.addAndGet(1);
              emitter.onNext(new TweetMessage(current, generateRandomMessage()));
            })
            .doOnNext(b -> System.out.println(b))
            .observeOn(Schedulers.io())
            .subscribe(i -> {
              sleep(100);
              System.out.println("received " + i);
              TweetMessage message = (TweetMessage) i;
              tweetFileHandler.writeTweet(message.getMessage(), message);
            });
    sleep(200_000);
  }

  private static void sleep(int i) throws InterruptedException {
    Thread.sleep(i);
  }

  public static @NonNull Flowable<Object> rangeReverse(int upperBound, int lowerBound) {

    return Flowable.generate(() -> new AtomicInteger(upperBound + 1),
            (state, emitter) -> {
              int current = state.decrementAndGet();
              emitter.onNext(current);
              if (current == lowerBound) {
                emitter.onComplete();
              }
            });
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

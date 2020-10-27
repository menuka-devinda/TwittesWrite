package com.menudev.tweethandler;

import com.menudev.tweethandler.TweetMessage;

public interface FileHandlerSvc {
	void writeTweet(String topic, TweetMessage msg);
}

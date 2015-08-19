package com.shuffle.thread;

import java.util.concurrent.ThreadFactory;

public class DaemonThreadFactory implements ThreadFactory {

	public DaemonThreadFactory(String poolname) {
		Thread.currentThread().setName(poolname);
	}

	@Override
	public Thread newThread(Runnable r) {
		return new Thread(r);
	}

}

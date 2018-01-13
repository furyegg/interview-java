package interview.concurrency.blockingqueue;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.LockSupport;

public class SimpleBlockingQueueTest {
	public static void main(String[] args) {
		Random rnd = new Random();
		
		Producer<Integer> producer = () -> {
			int p = rnd.nextInt(10000);
			System.out.println(" >+++> produced: " + p);
			return p;
		};
		Consumer<Integer> consumer = new SimpleConsumer();
		
//		BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue(3);
		BlockingQueue<Integer> blockingQueue = new MyArrayBlockingQueue(2);
		
		run(producer, 10, consumer, 1, blockingQueue);
	}
	
	private static void run(
			Producer<Integer> producer,
			int produceInterval,
			Consumer<Integer> consumer,
			int consumeInterval,
			BlockingQueue<Integer> blockingQueue) {
		
		Random rnd = new Random();
		
		Thread produceAction = new Thread() {
			@Override
			public void run() {
				while (true) {
					int interval = rnd.nextInt(produceInterval * 1000);
					try {
						park(this, interval);
						blockingQueue.put(producer.produce());
					} catch (InterruptedException e) {
						throw new IllegalStateException(e);
					}
				}
			}
		};
		
		Thread consumeAction = new Thread() {
			@Override
			public void run() {
				while (true) {
					int interval = rnd.nextInt(consumeInterval * 1000);
					try {
						park(this, interval);
						consumer.consume(blockingQueue.take());
					} catch (InterruptedException e) {
						throw new IllegalStateException(e);
					}
				}
			}
		};
		
		consumeAction.start();
		produceAction.start();
	}
	
	private static int getNanos(int interval) {
		return interval * 1000000;
	}
	
	private static void park(Thread t, int interval) throws InterruptedException {
		Thread.sleep(interval);
		// LockSupport.parkNanos(t, getNanos(interval));
	}
}
package interview.globalid.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicID {
	private static final AtomicLong ID = new AtomicLong(0);
	
	public Long next() {
//		return ID.getAndAdd(2);
		return ID.getAndIncrement();
	}
	
	public static void main(String[] args) throws InterruptedException {
		AtomicID id = new AtomicID();
		
		List<Callable<Long>> tasks = new ArrayList();
		for (int i = 0; i < 100; ++i) {
			tasks.add(() -> id.next());
		}
		ExecutorService executor = Executors.newCachedThreadPool();
		List<Future<Long>> futures = executor.invokeAll(tasks);
		futures.stream().map(f -> {
			try {
				return f.get();
			} catch (InterruptedException | ExecutionException e) {
				throw new IllegalStateException(e);
			}
		}).sorted().forEach(System.out::println);
	}
}

package interview.concurrency.blockingqueue;

public interface Consumer<T> {
	void consume(T value);
}
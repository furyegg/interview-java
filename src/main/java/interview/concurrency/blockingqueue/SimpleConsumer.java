package interview.concurrency.blockingqueue;

public class SimpleConsumer<T> implements Consumer<T> {
	@Override
	public void consume(T value) {
		System.out.println(" <---< Consumed: " + value);
	}
}
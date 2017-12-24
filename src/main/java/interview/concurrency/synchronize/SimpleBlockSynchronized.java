package interview.concurrency.synchronize;

public class SimpleBlockSynchronized {
	public void method() {
		synchronized (this) {
			System.out.println("Method 1 start");
		}
	}
}

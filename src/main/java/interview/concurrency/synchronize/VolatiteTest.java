package interview.concurrency.synchronize;

public class VolatiteTest {
	private volatile int a = 0;
	
	public static void main(String[] args) {
		VolatiteTest v = new VolatiteTest();
		v.a += 1;
	}
}

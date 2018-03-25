package interview.concurrency.synchronize;

public class TestSyncHard implements Runnable {
	
	int b = 100;
	
	synchronized void m1() throws InterruptedException {
		System.out.println("enter m1 ...");
		b = 1000;
		Thread.sleep(500);
		System.out.println("b = " + b);
	}
	
	synchronized void m2() throws InterruptedException {
		System.out.println("enter m2  ...");
		Thread.sleep(250);
		b = 2000;
	}
	
	public static void main(String[] args) throws InterruptedException {
		TestSyncHard tt = new TestSyncHard();
		Thread t = new Thread(tt);
		t.start();
		tt.m2();
		System.out.println("main thread b = " + tt.b);
	}
	
	@Override
	public void run() {
		try {
			m1();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
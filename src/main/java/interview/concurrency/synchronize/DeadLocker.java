package interview.concurrency.synchronize;

import java.util.Date;

class Deadlocker {
	public static String obj1 = "obj1";
	public static String obj2 = "obj2";
	
	public static void main(String[] args) throws InterruptedException {
		LockA la = new LockA();
		Thread t = new Thread(la);
		t.start();
//		LockB lb = new LockB();
//		new Thread(lb).start();
	}
}

class LockA implements Runnable {
	public void run() {
		try {
			Deadlocker.obj1.wait();
			System.out.println(new Date().toString() + " LockA 开始执行");
			synchronized (Deadlocker.obj1) {
				System.out.println(new Date().toString() + " LockA 锁住 obj1");
				Thread.sleep(3000); // 此处等待是给B能锁住机会
				synchronized (Deadlocker.obj2) {
					System.out.println(new Date().toString() + " LockA 锁住 obj2");
					Thread.sleep(60 * 1000); // 为测试，占用了就不放
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class LockB implements Runnable {
	public void run() {
		try {
			System.out.println(new Date().toString() + " LockB 开始执行");
			synchronized (Deadlocker.obj2) {
				System.out.println(new Date().toString() + " LockB 锁住 obj2");
				Thread.sleep(3000); // 此处等待是给A能锁住机会
				synchronized (Deadlocker.obj1) {
					System.out.println(new Date().toString() + " LockB 锁住 obj1");
					Thread.sleep(60 * 1000); // 为测试，占用了就不放
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}  
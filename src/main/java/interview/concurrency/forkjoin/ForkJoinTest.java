package interview.concurrency.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTest {
	public static void main(String[] args) {
		int size = 100;
		Integer[] nums = new Integer[size];
		for (int i = 0; i < size; ++i) {
			nums[i] = i * 2;
		}
		
		System.out.println(sum(nums, 0, size));
		
		ForkJoinPool pool = ForkJoinPool.commonPool();
		Task task = new Task(nums, 0, size);
		Integer result = pool.invoke(task);
		System.out.println(result);
	}
	
	private static class Task extends RecursiveTask<Integer> {
		private Integer[] nums;
		private int start;
		private int end;
		
		public Task(Integer[] nums, int start, int end) {
			this.nums = nums;
			this.start = start;
			this.end = end;
			// System.out.println(String.format("created new task, start: %d, end: %d", start, end));
		}
		
		@Override
		protected Integer compute() {
			if (end - start < 10) {
				return sum(nums, start, end);
			}
			int mid = (start + end) / 2;
			Task l = new Task(nums, start, mid);
			Task r = new Task(nums, mid, end);
			l.fork();
			r.fork();
			return l.join() + r.join();
		}
	}
	
	private static int sum(Integer[] nums, int start, int end) {
		int sum = 0;
		for (int i = start; i < end; ++i) {
			sum += nums[i];
		}
		return sum;
	}
}
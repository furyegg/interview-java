package interview.algorithm;

import java.util.List;

/**
 * 全排列
 */
public class TotalSorting {
	public static void main(String[] args) {
		int[] nums = {1,2,3,4};
		List<List<Integer>> results = totalSorting(nums);
		for (List<Integer> res : results) {
			System.out.println(res);
		}
	}
	
	private static List<List<Integer>> totalSorting(int[] nums) {
		return null;
	}
	
	private static int[] arrange(int[] nums, int start, int end) {
		if (nums.length == 2) {
			return nums;
		}
		
		int[] arr = new int[nums.length];
		return null;
	}
	
	
}
package algorithms;

public class ProductExceptSelf {
	/**
	 * Original question can be found here: 
	 * https://leetcode.com/problems/product-of-array-except-self/
	 * 
	 * This uses a simple solution to speed up the process using two 
	 * loops that runs in linear time. 
	 * 
	 * The intuition behind this is shifting the array to the right 
	 * by one place to multiply everything to its left in the first
	 * iteration, and everything to its right in the second.  
	 * 
	 * For example, given the array [1, 9, 3, 4]:
	 * 
	 * Imagine we had
	 * Multiplied: 	[0, 0, 0, 0] 
	 * Original: 	[1, 9, 3, 4]
	 * 
	 * -->[1, 9, 3, 4]
	 * 
	 * On the first iteration, we have
	 * accrued = 1, 			result[0] = accrued
	 * accrued *= result[0], 	result[1] = 1 * accrued = 1
	 * accrued *= result[1], 	result[2] = 1 * accrued = 9
	 * accrued *= result[2], 	result[3] = 9 * accrued = 27
	 * 
	 * And
	 * Multiplied: 	[1, 1, 9, 27]
	 * Original: 	[1, 9, 3, 4]
	 * 			 [1, 9, 3, 4]<--
	 * 
	 * On the second iteration, we have
	 * accrued = 1, 			result[3] = 27 * accrued = 27
	 * accrued *= result[3], 	result[2] = 9 * accrued = 36
	 * accrued *= result[2], 	result[1] = 1 * accrued = 12
	 * accrued *= result[1], 	result[0] = 1 * accrued = 108
	 * result[1] = 1 * result[2] * 
	 * @param nums
	 * @return
	 */
	public static int[] productExceptSelf(int nums[]) {
		int length = nums.length;
		int result[] = new int[nums.length];
		
		//	calculate this first
		/*
		 * result[0]	= 1
		 * result[1]	= nums[0]
		 * result[2]	= nums[0] * nums[1]
		 * ...
		 * result[n-1]	= nums[0] * nums[1] * ... * nums[n-2]
		 */
		int accrued = 1;
		
		for (int i = 0; i < length; ++i) {
			result[i] = accrued;
			accrued *= nums[i];
		}
		
		
		//	then calculate this
		/*
		 * (in reverse order)
		 * result[n-1] 	*= 1
		 * result[n-2]	*= nums[n-1]
		 * ...
		 * result[0]	*= nums[n-2] * nums[n-3] * ... * nums[1] 
		 */
		accrued = 1;
		
		for (int i = length - 1; i >= 0; --i) {
			result[i] *= accrued;
			accrued *= nums[i];
		}
		
		return result;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] i1 = {1, 9, 3, 4};
		
		int[] result = productExceptSelf(i1);
		
		for (int current : result) 
			System.out.print(current + ", ");

	}

}

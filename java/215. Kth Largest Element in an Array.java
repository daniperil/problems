/**
 *Problem: 215. Kth Largest Element in an Array
 *Given an integer array nums and an integer k, return the kth largest element in the array.
 *Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *Can you solve it without sorting?
 */

public class Solution {
    //Quickselect
    public int findKthLargest(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        Random rand = new Random();
        while (true) {
            int pivot_index = left + rand.nextInt(right - left + 1);
            int new_pivot_index = partition(nums, left, right, pivot_index);
            if (new_pivot_index == nums.length - k) {
                return nums[new_pivot_index];
            } else if (new_pivot_index > nums.length - k) {
                right = new_pivot_index - 1;
            } else {
                left = new_pivot_index + 1;
            }
        }
    }

    private int partition(int[] nums, int left, int right, int pivot_index) {
        int pivot = nums[pivot_index];
        swap(nums, pivot_index, right);
        int stored_index = left;
        for (int i = left; i < right; i++) {
            if (nums[i] < pivot) {
                swap(nums, i, stored_index);
                stored_index++;
            }
        }
        swap(nums, right, stored_index);
        return stored_index;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
/**
Time Complexity:

Best and Average Case: $$O(N)$$

Worst Case: $$O(N^2)$$

The average performance is linear. However, in the worst case (very rare, especially with randomized pivot), the algorithm can degrade to $$O(N^2)$$.

Space Complexity: $$O(1)$$

The space used is constant. The algorithm modifies the original list in place and doesn't utilize any significant additional data structures. The recursive stack calls (in the worst case) are also bounded by the depth of the list, making it $$O(\log N)$$, but this is typically considered as $$O(1)$$ space complexity in QuickSelect.
*/

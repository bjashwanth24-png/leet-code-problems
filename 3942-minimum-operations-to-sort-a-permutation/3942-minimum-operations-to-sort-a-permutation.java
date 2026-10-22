class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;

        int normal = findRotation(nums);

        int[] reversed = new int[n];
        for (int i = 0; i < n; i++) {
            reversed[i] = nums[n - 1 - i];
        }

        int reverseRotation = findRotation(reversed);

        int ans = Integer.MAX_VALUE;

        // Case 1: Rotate the original array
        if (normal != -1) {
            ans = Math.min(ans, Math.min(normal, n - normal + 2));
        }

        // Case 2: Reverse, then rotate
        if (reverseRotation != -1) {
            ans = Math.min(
                ans,
                Math.min(reverseRotation, n - reverseRotation) + 1
            );
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private int findRotation(int[] arr) {
        int n = arr.length;
        int k = -1;

        // Find the position of 0
        for (int i = 0; i < n; i++) {
            if (arr[i] == 0) {
                k = i;
                break;
            }
        }

        // Check whether rotating left by k sorts the array
        for (int i = 0; i < n; i++) {
            if (arr[(k + i) % n] != i) {
                return -1;
            }
        }

        return k;
    }
}
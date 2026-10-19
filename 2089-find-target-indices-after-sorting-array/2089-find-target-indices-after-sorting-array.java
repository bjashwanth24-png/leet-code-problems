import java.util.*;

class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int less = 0, equal = 0;

        for (int num : nums) {
            if (num < target) less++;
            else if (num == target) equal++;
        }

        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < equal; i++) {
            ans.add(less + i);
        }

        return ans;
    }
}
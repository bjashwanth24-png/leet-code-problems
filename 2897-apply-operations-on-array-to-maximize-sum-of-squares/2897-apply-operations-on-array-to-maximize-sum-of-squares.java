import java.util.List;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        final long MOD = 1_000_000_007L;
        int[] bitCount = new int[32];

        for (int num : nums) {
            for (int bit = 0; bit < 32; bit++) {
                if (((num >>> bit) & 1) == 1) {
                    bitCount[bit]++;
                }
            }
        }

        long ans = 0;

        for (int i = 0; i < k; i++) {
            long value = 0;

            for (int bit = 31; bit >= 0; bit--) {
                if (bitCount[bit] > 0) {
                    value |= (1L << bit);
                    bitCount[bit]--;
                }
            }

            ans = (ans + value * value % MOD) % MOD;
        }

        return (int) ans;
    }
}
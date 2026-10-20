class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;

        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int ans = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) continue;

            boolean ok = true;

            for (int start = 0; start < n; start += k) {
                int[] block = Arrays.copyOfRange(nums, start, start + k);
                int[] target = Arrays.copyOfRange(sorted, start, start + k);

                if (!isRotation(block, target)) {
                    ok = false;
                    break;
                }
            }

            if (ok) ans += k;
        }

        return ans;
    }

    private boolean isRotation(int[] a, int[] b) {
        int k = a.length;
        if (k != b.length) return false;

        int[] text = new int[2 * k];
        for (int i = 0; i < k; i++) {
            text[i] = a[i];
            text[i + k] = a[i];
        }

        return kmp(text, b);
    }

    private boolean kmp(int[] text, int[] pattern) {
        int m = pattern.length;

        int[] lps = new int[m];
        for (int i = 1, len = 0; i < m; ) {
            if (pattern[i] == pattern[len]) {
                lps[i++] = ++len;
            } else if (len > 0) {
                len = lps[len - 1];
            } else {
                lps[i++] = 0;
            }
        }

        for (int i = 0, j = 0; i < text.length; ) {
            if (text[i] == pattern[j]) {
                i++;
                j++;

                if (j == m) return true;
            } else if (j > 0) {
                j = lps[j - 1];
            } else {
                i++;
            }
        }

        return false;
    }
}
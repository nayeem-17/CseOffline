package level2_term1.DSA.offline10.java;

import java.util.List;

public class DiceThrow {
    public static final long MAX_RESULT = 1000000000 + 7;

    public static long countDiceThrow(long[][] dp, List<Integer> faces, int sum) {
        long count = 0;
        int size = faces.size();
        if (sum <= 0)
            return 0;
        if (size == 1) {
            if (faces.get(0) >= sum)
                return 1;
            return 0;
        }
        int len = faces.get(size - 1);
        for (int i = 1; i <= len; i++) {
            if (sum - i < 0)
                break;
            if (dp[size - 1][sum - i] == -1) {
                long data = countDiceThrow(dp, faces.subList(0, size - 1), sum - i);
                data = data % MAX_RESULT;
                dp[size - 1][sum - i] = data;
            }
            count += dp[size - 1][sum - i];
        }
        count = count % MAX_RESULT;
        return count;
    }

}

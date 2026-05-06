class Solution {
    public int[] solution(int e, int[] starts) {
        int[] answer = new int[starts.length];

        // 각 수의 약수의 개수
        int[] divisors = new int[e + 1];

        // dp[i]: i ~ e 사이 수 중에서 약수가 가장 많은수
        int[] dp = new int[e + 1];

        // 1 ~ e 까지 각 수의 약수 개수 구하기.
        for (int i = 1; i <= e; i++) {
            for (int j = i; j <= e; j += i) {
                divisors[j] += 1;
            }
        }

        // DP 구축
        dp[e] = e;

        for (int i = e - 1; i > 0; i--) {
            if (divisors[i] >= divisors[dp[i + 1]]) {
                dp[i] = i;
            } else {
                dp[i] = dp[i + 1];
            }
        }

        // 해 구하기
        for (int i = 0; i < answer.length; i++) {
            answer[i] = dp[starts[i]];
        }

        return answer;
    }
}
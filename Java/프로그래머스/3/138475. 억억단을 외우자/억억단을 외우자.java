import java.util.*;

class Solution {
    public int[] numOfDivisors(int num) {
        ArrayList<Integer> primes = new ArrayList<>();

        int[] spf = new int[num + 1];   // 최소 소인수
        int[] exp = new int[num + 1];   // 최소 소인수의 지수
        int[] div = new int[num + 1];   // 약수 개수

        div[1] = 1;


        for (int n = 2; n <= num; n++) {

            // 소수인 경우
            if (spf[n] == 0) {
                spf[n] = n;
                exp[n] = 1;

                // 소수의 약수 개수는 2개
                div[n] = 2;

                primes.add(n);
            }

            for (int prime : primes) {
                int composite = prime * n;

                if (composite > num) {
                    break;
                }

                spf[composite] = prime;

                // prime이 n의 최소 소인수인 경우
                if (n % prime == 0) {

                    // exponent 증가
                    exp[composite] = exp[n] + 1;

                    /*
                     * n = m × prime^k
                     *
                     * d(n) = d(m) × (k + 1)
                     *
                     * composite = m × prime^(k + 1)
                     *
                     * d(composite)
                     * = d(m) × (k + 2)
                     */
                    div[composite] = div[n] / (exp[n] + 1) * (exp[n] + 2);

                    break;
                }

                // 새로운 소인수가 추가되는 경우
                exp[composite] = 1;

                // 약수 개수 2배
                div[composite] = div[n] * 2;
            }
        }

        return div;
    }
    
    public int[] solution(int e, int[] starts) {
        int[] answer = new int[starts.length];

        // 각 수의 약수의 개수
        int[] divisors = numOfDivisors(e);

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
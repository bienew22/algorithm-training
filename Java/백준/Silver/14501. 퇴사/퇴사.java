/**
 * 퇴사
 * <p>
 * DP 문제
 * `i`일차의 상담을 수락한 경우 얻을 수 있는 최대 이익은
 * `i + T_i`일차에 얻을 수 있는 최대 이익 + P_i
 *
 * `i`일차의 상담을 수락하지 않는 경우 얻을 수 있는 최대 이익은
 * `i + 1`일차에 얻을 수있는 최대 이익.
 * <p>
 * -- 오류 로그 --
 */

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    void solve() throws Exception {
        // 변수 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] data = new int[N][];

        for (int i = 0; i < N; i++) {
            data[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }

        int[] DP = new int[N + 1];

        // DP 탐색
        for (int i = N - 1; i >= 0; i--) {
            if (i + data[i][0] > N) {
                // 이미 퇴사해서 상담을 완료 할 수 없는 겨우.
                DP[i] = DP[i + 1];
            } else {
                DP[i] = Math.max(
                        DP[i + 1], // 상담을 수락하지 않은 경우
                        data[i][1] + DP[i + data[i][0]] // 상담을 수락한 경우
                );
            }
        }

        // 출력
        System.out.println(DP[0]);

    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}
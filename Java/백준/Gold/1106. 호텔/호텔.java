/**
 * 호텔
 * <p>
 * Knapsack 문제와 아주 유사, DP
 * <p>
 * -- 해결 로그 --
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 초기화
        StringTokenizer st = new StringTokenizer(br.readLine());

        int C = Integer.parseInt(st.nextToken());   // 늘릴 고객수
        int N = Integer.parseInt(st.nextToken());   // 도시의 개수

        int[][] cities = new int[N][2]; // 0: 홍보 비용, 1: 증가 고객수
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            cities[i][0] = Integer.parseInt(st.nextToken());
            cities[i][1] = Integer.parseInt(st.nextToken());
        }

        // DP[i]: 고객 i명 늘리는데 필요한 최소 비용.
        int[] DP = new int[C + 1];
        Arrays.fill(DP, Integer.MAX_VALUE - 1000);
        DP[0] = 0;

        //== 처리시작
        for (int client = 1; client < DP.length; client++) {
            for (int[] city: cities) {
                if (client - city[1] < 0) {
                    // 해당 city를 통해서 요구 인원 모두 채울 수 있는 경우.
                    DP[client] = Math.min(city[0], DP[client]);
                } else {
                    // 해당 city를 통해서 안되고 기존에 추가로 해당 city 홍보하는 경우.
                    DP[client] = Math.min(city[0] + DP[client - city[1]], DP[client]);
                }
            }
        }
        
        System.out.println(DP[C]);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}
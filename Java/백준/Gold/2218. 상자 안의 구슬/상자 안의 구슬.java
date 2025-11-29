/**
 * 상자 안의 구슬
 * <p>
 * DP 문제.
 * <p>
 * -- 해결 로그 --
 * 4% 틀: 초기 DP에 score 초기화를 잘 못함.
 *  score[queueA[a]][queueB[b]] 이걸 단순히 score[a][b]로 잘 못 초기화함.
 * 4% 틀: DP를 좀 더 직관적이게 수정.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 초기화
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int[][] score = new int[N][];
        for (int i = 0; i < N; i++) {
            score[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }

        int[] queueA = Arrays.stream(br.readLine().split(" "))
                .mapToInt(v -> Integer.parseInt(v) - 1).toArray();
        int[] queueB = Arrays.stream(br.readLine().split(" "))
                .mapToInt(v -> Integer.parseInt(v) - 1).toArray();

        // DP[a][b] : A의 a 번째 index과 B의 b번째 index에 도달 했을 때 얻을 수 있는 최대 점수
        int[][] DP = new int[A + 1][B + 1];

        // 최대 점수를 얻기 위하여 이전 좌표에서 수행한 작업 번호를 의미.
        int[][] CMD = new int[A + 1][B + 1];

        // [a][0]는 1번 [0][b]는 2번을 수행해야 만 도착 가능.
        for (int a = 1; a <= A; a++) {
            CMD[a][0] = 1;
        }
        for (int b = 1; b <= B; b++) {
            CMD[0][b] = 2;
        }

        //== DP 계산하기.
        for (int a = 1; a <= A; a++) {
            for (int b = 1; b <= B; b++) {
                int base = score[queueA[a - 1]][queueB[b - 1]];

                // 1번 작업을 통하여 현재 위치에 도착한 경우.
                if (DP[a][b] <= DP[a - 1][b]) {
                    DP[a][b] = DP[a - 1][b];
                    CMD[a][b] = 1;
                }

                // 2번 작업을 통하여 현재 위치에 도착한 경우.
                if (DP[a][b] <= DP[a][b - 1]) {
                    DP[a][b] = DP[a][b - 1];
                    CMD[a][b] = 2;
                }

                // 3번 작업을 통하여 현재 위치에 도착한 경우.
                if (DP[a][b] <= DP[a - 1][b - 1] + base) {
                    DP[a][b] = DP[a - 1][b - 1] + base;
                    CMD[a][b] = 3;
                }
            }
        }

        System.out.println(DP[A][B]);
        System.out.println(getPath(CMD, A, B));

        // test 출력
//        for(int[][] dp: DP) {
//            for(int[] d: dp) {
//                System.out.print(Arrays.toString(d));
//            }
//            System.out.println();
//        }
    }

    String getPath(int[][] CMD, int sizeA, int sizeB) {
        StringBuilder sb = new StringBuilder();

        while (sizeA >= 0 && sizeB >= 0) {
            if (CMD[sizeA][sizeB] == 1) {
                sb.append("1 ");
                sizeA -= 1;
            } else if (CMD[sizeA][sizeB] == 2) {
                sb.append("2 ");
                sizeB -= 1;
            } else if (CMD[sizeA][sizeB] == 3) {
                sb.append("3 ");
                sizeB -= 1;
                sizeA -= 1;
            } else {
                break;
            }
        }
        return sb.reverse().toString().strip();
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}
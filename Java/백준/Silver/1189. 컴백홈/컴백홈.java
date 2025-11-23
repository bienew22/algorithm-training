/**
 * 컴백홈
 * <p>
 * 간단한 DFS/BFS 문제.
 * <p>
 * -- 해결 로그 --
 * 80% 틀 : 탐색 범위에서 idx >= 0 를 idx > 0으로 만 함.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    char[][]  board;
    int R, C, K;

    int dfs(int y, int x, int depth) {
        if (y == R - 1 && x == C - 1 && depth == K) {
            return 1;
        }
        int ans = 0;

        board[y][x] = '-';

        if (y - 1 >= 0 && board[y - 1][x] == '.') {
            ans += dfs(y - 1, x, depth + 1);
        }
        if (y + 1 < R && board[y + 1][x] == '.') {
            ans += dfs(y + 1, x, depth + 1);
        }
        if (x - 1 >= 0 && board[y][x - 1] == '.') {
            ans += dfs(y, x - 1, depth + 1);
        }
        if (x + 1 < C && board[y][x + 1] == '.') {
            ans += dfs(y, x + 1, depth + 1);
        }

        board[y][x] = '.';

        return ans;
    }

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 초기화
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());   // 행의 개수
        C = Integer.parseInt(st.nextToken());   // 열의 개수
        K = Integer.parseInt(st.nextToken());   // 목표 걸음 수

        board = new char[R][];

        // 반대로 입력 받기.
        // -> 시작 좌표를 (0, 0)으로 목표 좌표를 (R - 1, C - 1)으로 설정하기 위함.
        for (int i = R; i > 0; i--) {
            board[i - 1]  = br.readLine().toCharArray();
        }

        int ans = dfs(0, 0, 1);

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}
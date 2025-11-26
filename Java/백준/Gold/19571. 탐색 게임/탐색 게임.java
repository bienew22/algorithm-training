/**
 * 탐색 게임
 * <p>
 * Ad-hoc 문제 같음.
 * 간단하게 뒤에서 부터 큰 숫자를 N-룩 문제 처럼 배치하면 됨.
 * <p>
 * -- 해결 로그 --
 */

public class Main {

    boolean validateData(int[][] board, int size) {
        boolean[] allNum = new boolean[size * size];

        int idx = 0;
        for (int[] line: board) {
            for (int d: line) {
                allNum[d - 1] = true;
            }
        }

        for (boolean verify: allNum) {
            if (!verify) {
                return false;
            }
        }

        return true;
    }

    boolean allVisit(boolean[][] visit) {
        for (boolean[] v: visit) {
            for (boolean isV: v) {
                if (!isV)
                    return  false;
            }
        }

        return true;
    }

    // 점수 검증용 함수.
    int calcScore(int[][] board, int size) {
        boolean[][] visit = new boolean[size][size];

        int sx = 0, sy = 0;
        visit[sy][sx] = true;

        int score = 0;

        while (!allVisit(visit)) {
            while (true) {
                int min = Integer.MAX_VALUE, nx = -1, ny = -1;

                for (int i = 0; i < size; i++) {
                    if (!visit[sy][i] && board[sy][i] < min) {
                        min = board[sy][i];
                        nx = i;
                        ny = sy;
                    }

                    if (!visit[i][sx] && board[i][sx] < min) {
                        min = board[i][sx];
                        nx = sx;
                        ny = i;
                    }
                }

                if (nx == -1 && ny == -1) {
                    break;
                }

                sx = nx;
                sy = ny;
                visit[sy][sx] = true;
            }

            score += 1;

            board[sy][sx] = Integer.MAX_VALUE;

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (!visit[i][j] && board[i][j] < board[sy][sx]) {
                        sy = i;
                        sx = j;
                    }
                }
            }
        }

        return score;
    }

    void solve() throws Exception {
        int size = 100;

        int[][] board = new int[size][size];

        int cnt = 1;
        int rCnt = size * size;

        board[size / 2][0] = rCnt--;
        board[size / 2 - 1][size - 1] = rCnt--;

        for (int i = 0; i < size / 2; i++) {
            int sy = i, sx = i;

            if (i != size / 2 - 1) {
                board[sy][sx + 1] = rCnt--;
                board[size - sy - 1][size - sx - 2] = rCnt--;
            }

            // x inc
            for (; sx < size - i; sx++) {
                if (board[sy][sx] == 0) {
                    board[sy][sx] = cnt++;
                }
            }
            sx--;
            sy++;

            // y inc
            for (; sy < size - i; sy++) {
                if (board[sy][sx] == 0) {
                    board[sy][sx] = cnt++;
                }
            }
            sy--;
            sx--;

            // x dec
            for (; sx >= i; sx--) {
                if (board[sy][sx] == 0) {
                    board[sy][sx] = cnt++;
                }
            }
            sx++;
            sy--;

            // y dec
            for (; sy >= i; sy--) {
                if (board[sy][sx] == 0) {
                    board[sy][sx] = cnt++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int[] line: board) {
            for (int d: line) {
                sb.append(d).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}
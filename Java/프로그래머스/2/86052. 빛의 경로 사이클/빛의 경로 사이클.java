import java.util.*;


class Solution {
    public int findCycle(int y, int x, int dir, String[] grid, int[][][] DP) {
        // 우 하 좌 상
        int[][] directions = new int[][]{{0, 1}, {-1, 0}, {0, -1}, {1, 0}};

        int sy = y, sx = x, sDir = dir;
        int yMax = grid.length, xMax = grid[0].length();
        int cycle = 1;

        while (true) {
            // 이동
            DP[y][x][dir] = 1;
            y = (yMax + y + directions[dir][0]) % yMax;
            x = (xMax + x + directions[dir][1]) % xMax;

            if (grid[y].charAt(x) == 'L') {
                dir = (4 + dir - 1) % 4;
            } else if (grid[y].charAt(x) == 'R') {
                dir = (4 + dir + 1) % 4;
            }

            if (x == sx && y == sy && sDir == dir) {
                break;
            }
            cycle += 1;
        }

        return cycle;
    }

    public int[] solution(String[] grid) {
        ArrayList<Integer> answer = new ArrayList<>();

        // DP[y][x][dir] : gird[y][x]에 dir 방향으로 빛이 통과 여부
        int[][][] DP = new int[grid.length][grid[0].length()][4];

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length(); x++) {
                for (int dir = 0; dir < 4; dir++ ) {
                    // grid[y][x] 에 dir으로 빛을 쏠 에정

                    // 이미 해당 경로가 다른 사이클에 포함되어 있음.
                    if (DP[y][x][dir] != 0) {
                        continue;
                    }

                    answer.add(findCycle(y, x, dir, grid, DP));
                }
            }
        }

        return answer.stream()
                .sorted()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
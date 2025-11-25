/**
 * DSHS Bank
 * <p>
 * 맨해튼 거리 구하는 문제.
 * <p>
 * -- 해결 로그 --
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    long[] calcDistance(int[] loc, Integer[] offset, int N) {
        long[] resDistance = new long[N];

        long sumLoc = Arrays.stream(loc).sum();

        long leftSum = 0;
        for (int off = 0; off < N; off++) {
            int idx = offset[off];   // 실제 좌표의 위치 (주어진 좌표의 순번)
            long idxLoc = loc[idx];  // 실제 좌표 값.

            // 현재 좌표 기준 왼쪽에 있는 첨들의 맨해튼 거리.
            long left = idxLoc * off - leftSum;

            // 현재 좌표 기준 오른쪽에 있는 점들의 맨해튼 거리.
            long right = (sumLoc - leftSum - idxLoc) - idxLoc * (N - 1 - off);

            resDistance[idx] = left + right;

            leftSum += idxLoc;
        }

        return resDistance;
    }

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 초기화
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] xLoc = new int[N];    // x 좌표 모음
        int[] yLoc = new int[N];    // y 좌표 모음

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            xLoc[i] = Integer.parseInt(st.nextToken());
            yLoc[i] = Integer.parseInt(st.nextToken());
        }

        // 각 x축, y축 좌표의 offset 저장. 정렬 후에도 index를 기억하기 위함.
        Integer[] xOffset = new Integer[N];
        Integer[] yOffset = new Integer[N];

        for (int i = 0; i < N; i++) {
            xOffset[i] = i;
            yOffset[i] = i;
        }

        // 각 축 좌표를 오름차순으로 정렬.
        Arrays.sort(xOffset, Comparator.comparingInt(i -> xLoc[i]));
        Arrays.sort(yOffset, Comparator.comparingInt(i -> yLoc[i]));

        //== 로직 처리.

        // 각 축 좌표간의 거리 구하기.
        long[] xDistance = calcDistance(xLoc, xOffset, N);
        long[] yDistance = calcDistance(yLoc, yOffset, N);

        int minIdx = N;
        long minDistance = Long.MAX_VALUE;

        for (int i = N - 1; i >= 0; i--) {
            if (xDistance[i] + yDistance[i] < minDistance) {
                minIdx = i;
                minDistance = xDistance[i] + yDistance[i];
            }
        }

        System.out.println(minIdx + 1);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}
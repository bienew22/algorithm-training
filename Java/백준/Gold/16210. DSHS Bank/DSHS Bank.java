/**
 * DSHS Bank
 * <p>
 * 맨해튼 거리 구하는 문제.
 * <p>
 * -- 해결 로그 --
 * 12점 : x축, y축 맨해튼 거리를 구하고 따로 합치는 것이 아닌 구할 때 부터 합침.
 * 32% : 오른쪽 좌표 합 계산 최적화.
 * 38% : offset 초기화 부분을 입력 받는 부분이랑 합침.
 * 38% : sum 구하는 부분을 초기화 하면서 구함.
 * 38% : N -> 0 순으로 탐색하므로 < 가 아닌 <= 으로 갱신해야 함.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    void calcDistance(int[] loc, Integer[] offset, long[] distance, long rightSum) {
        int N = distance.length;

        long leftSum = 0;
        for (int off = 0; off < N; off++) {
            int idx = offset[off];   // 실제 좌표의 위치 (주어진 좌표의 순번)
            long idxLoc = loc[idx];  // 실제 좌표 값.

            rightSum -= idxLoc;

            // 현재 좌표 기준 좌/우에 있는 첨들의 맨해튼 거리.
            long left = idxLoc * off - leftSum;
            long right = rightSum - idxLoc * (N - 1 - off);

            distance[idx] += (left + right);

            leftSum += idxLoc;
        }
    }

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 초기화
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] xLoc = new int[N];    // x 좌표 모음
        int[] yLoc = new int[N];    // y 좌표 모음

        long xSum = 0, ySum = 0;

        // 각 x축, y축 좌표의 offset 저장. 정렬 후에도 index를 기억하기 위함.
        Integer[] xOffset = new Integer[N];
        Integer[] yOffset = new Integer[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            xLoc[i] = x;
            yLoc[i] = y;

            xSum += x;
            ySum += y;

            xOffset[i] = i;
            yOffset[i] = i;
        }

        // 각 축 좌표를 오름차순으로 정렬.
        Arrays.sort(xOffset, Comparator.comparingInt(i -> xLoc[i]));
        Arrays.sort(yOffset, Comparator.comparingInt(i -> yLoc[i]));

        //== 로직 처리.

        // 각 축 좌표간의 거리 구하기.
        long[] distance = new long[N];

        calcDistance(xLoc, xOffset, distance, xSum);
        calcDistance(yLoc, yOffset, distance, ySum);

        // 가장 짧은 거리 구하기.
        int minIdx = N;
        long minDistance = Long.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            if (distance[i] < minDistance) {
                minIdx = i;
                minDistance = distance[i];
            }
        }

        System.out.println(minIdx + 1);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}
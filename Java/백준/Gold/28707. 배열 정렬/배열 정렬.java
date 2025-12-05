/**
 * 배열 정렬
 * <p>
 * 다익스트라 문제, 배열의 데이터 순서를 하나의 정점으로 보는 것.
 * 조작을 상태에서 다른 상태로 이동하는 간선으로 봄.
 * <p>
 * -- 해결 로그 --
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


class Main {

    int arrToStatus(int[] data) {
        int res = 0;

        for (int v: data) {
            res = res * 10 + v;
        }
        return res;
    }

    void statusToArr(int data, int[] res) {
        int i = res.length - 1;

        while (data > 0) {
            res[i--] = data % 10;
            data /= 10;
        }
        while (i >= 0) {
            res[i--] = 0;
        }
    }

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 초기화

        // 데이터 정보 입력 받기.
        int N = Integer.parseInt(br.readLine());

        // -1 하는 이유: 원소의 범위를 0 ~ 9으로 설정하여 자릿수를 한 자리로 만들기 위함.
        int[] data = Arrays.stream(br.readLine().split(" "))
                .mapToInt(a -> Integer.parseInt(a) - 1).toArray();

        // 조작 정보 입력 받기.
        int M = Integer.parseInt(br.readLine());
        int[][] path = new int[M][];
        for (int i = 0; i < M; i++) {
            // -1 하는 이유: 인덱스 범위로 조정하기 위함.
            path[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(a -> Integer.parseInt(a) - 1).toArray();
        }

        // key: 상태, value: 비용
        HashMap<Integer, Integer> cost = new HashMap<>();

        // int[2] { 현재 상태, 현재 비용 }
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));

        // 매번 새로운 배열로 만들지 않고 재활용하기 위함.
        int[] tempArrPath = new int[N];

        //== 처리하기
        int startPath = arrToStatus(data); // 초기 상태.
        Arrays.sort(data); // 목표 상태.

        cost.put(startPath, 0);
        queue.add(new int[] {startPath, 0});

        // 다익스트라 시작.
        while (!queue.isEmpty()) {
            int[] nowStatus = queue.poll();

            if (nowStatus[1] > cost.getOrDefault(nowStatus[0], Integer.MAX_VALUE)) {
                continue;
            }

            for (int[] p: path) {
                // intPath to arrPath -> 이런 식으로 재사용.
                statusToArr(nowStatus[0], tempArrPath);

                // swap
                int tmp = tempArrPath[p[0]];
                tempArrPath[p[0]] = tempArrPath[p[1]];
                tempArrPath[p[1]] = tmp;

                // 새로운 상태에 대한 비용 계산.
                int intPath = arrToStatus(tempArrPath);
                int value = nowStatus[1] + p[2] + 1;

                if (!cost.containsKey(intPath) || cost.get(intPath) > value) {
                    cost.put(intPath, value);
                    queue.add(new int[] {intPath, value});
                }
            }
        }
        
        System.out.println(cost.getOrDefault(arrToStatus(data), -1));
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}
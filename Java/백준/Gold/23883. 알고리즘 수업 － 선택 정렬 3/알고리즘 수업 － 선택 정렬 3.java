/**
 * 알고리즘 수업 - 선택 정렬 3
 * <p>
 * 선택 정렬을 O(N^2) -> O(NlogN)으로 변경하여 처리하는 문제
 * <p>
 * -- 해결 로그 --
 */

import java.util.*;
import java.lang.*;
import java.io.*;


class Main {

    void solve() throws Exception {
        // 변수 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 배열 크기
        int K = Integer.parseInt(st.nextToken());   // 교환 횟수

        int[] A = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        HashMap<Integer, Integer> numIdx = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            numIdx.put(A[i], i);
        }

        int[] sortedA = Arrays.stream(A)
                .sorted().toArray();

        for (int i = A.length - 1; i >= 0; i--) {
            // 교환 발생.
            if (A[i] != sortedA[i]) {
                K -= 1;

                if (K == 0) {
                    System.out.println(A[i] + " " + sortedA[i]);
                    break;
                }

                // 실제 숫자가 이동할 위치.
                int loc = numIdx.get(sortedA[i]);

                A[loc] = A[i];

                // 실제 숫자 이동환 위치 인덱스 업데이트
                numIdx.put(A[i], loc);
            }
        }

        if (K != 0) {
            System.out.println(-1);
        }
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}
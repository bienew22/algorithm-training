/**
 * 중복 빼고 정렬하기
 * <p>
 * Counting Sort 문제
 * <p>
 * -- 해결 로그 --
 */

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 초기화
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        boolean[] data = new boolean[2001];
        for (int i = 0; i  < N; i++) {
            data[Integer.parseInt(st.nextToken()) + 1000] = true;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            if (data[i]) {
                sb.append(i - 1000).append(' ');
            }
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}
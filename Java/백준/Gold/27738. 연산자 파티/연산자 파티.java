/**
 * 연산자 파티
 * <p>
 * i가 F의 배수가 되면 결국 X은 0으로 초기화 되는 것을 의미합니다.
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

        long N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        long C = Long.parseLong(st.nextToken());
        long D = Long.parseLong(st.nextToken());
        long E = Long.parseLong(st.nextToken());
        long F = Long.parseLong(st.nextToken());

        long start = (N / F) * F + 1;
        long X = 0;

        for (;start <= N; start++) {
            if (start % A == 0) {
                X += start;
            }
            if (start % B == 0) {
                X %= start;
            }
            if (start % C == 0) {
                X &= start;
            }
            if (start % D == 0) {
                X ^= start;
            }
            if (start % E == 0) {
                X |= start;
            }
            if (start % F == 0) {
                X = 0;
            }
        }

        System.out.println(X);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}
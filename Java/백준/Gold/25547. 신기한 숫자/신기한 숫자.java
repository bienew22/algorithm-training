/**
 * 신기한 숫자
 * <p>
 * GCD(A, B) = GCD(A, C)
 * LCM(A, B) = LCM(B, C) 를 만족 시키는 정수 C의 개수를 구하시오.
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

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        // b가 a의 배수 아닌 경우 0
        if (b % a != 0) {
            System.out.println(0);
        } else {
            // 배수 만큼 c 가능
            int range = b / a;
            int cnt = 0;

            for (int i = 1; i * i <= range; i++) {
                if (range % i == 0) {
                    if (range / i == i) {
                        cnt += 1;
                    } else {
                        cnt += 2;
                    }
                }
            }

            System.out.println(cnt);
        }
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}
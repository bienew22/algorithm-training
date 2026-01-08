/**
 * 연산자 파티
 * <p>
 * i가 F의 배수가 되면 결국 X은 0으로 초기화 되는 것을 의미합니다.
 * <p>
 * -- 해결 로그 --
 * 10점 : for(;start <= N; start++) 대신 -> 힙을 이용하여 처리
 * 10점 : 성능문제가 아니라 런타임에러 이라..
 */

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    void solve() throws Exception {
        // 변수 초기화
        Scanner sc = new Scanner(System.in);

        long N = sc.nextLong();
        long A = sc.nextLong();
        long B = sc.nextLong();
        long C = sc.nextLong();
        long D = sc.nextLong();
        long E = sc.nextLong();
        long F = sc.nextLong();

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
        }

        System.out.println(X);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}
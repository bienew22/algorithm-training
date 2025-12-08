/**
 * ISBN
 * <p>
 * 스읍... 수학 문제
 * <p>
 * -- 해결 로그 --
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 초기화
        char[] data = br.readLine().toCharArray();

        int pre = 1;
        int tmp = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i] >= '0' && data[i] <= '9') {
                tmp += (i % 2 == 0 ? 1 : 3) * (data[i] - '0');
            } else {
                pre = i % 2 == 0 ? 1 : 3;
            }
        }

        for (int i = 0; i < 10; i++) {
            if ((tmp + pre * i) % 10 == 0) {
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}
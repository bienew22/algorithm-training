/**
 * 피아노
 *
 * 순차적으로 쳐야하므로 한 번에 놓았을 때 움직이지 않고 한 번에 많이
 * 칠수 있는 위치 찾는 문제으로 해석.
 *
 * 순차적으로 x개의 건반을 움직이지 않고 칠수 있다고 가정할 때
 * 이게 성립하려면 x개의 건반에서 최저 위치와 최고 위치의 차이가 K 보다 작으면 됨.
 *
 * 순차적으로 건반을 추가하여 범위내 최저 위치과 최고 위치의 차이(diff) 검색 .
 * diff >= K -> 마지막 건반을 제외하고 추가한 건반들은 한 번 놓은 위치에서 다 칠수 있음을 의미
 * diff < K -> 다음 건반을 추가할 수 있음.
 * 2.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {



    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 초기화
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 눌러야 하는 건반 수
        int K = Integer.parseInt(st.nextToken());   // 손가락 길이

        // 순서대로 눌러야하는 키.
        int[] keys = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        int ans = 0;

        // 현재 한 번에 치려고 하는 키들에서 최소와 최대
        int minKey = keys[0], maxKey = keys[0];

        for (int key: keys) {
            minKey = Math.min(key, minKey);
            maxKey = Math.max(key, maxKey);
            
            // 차이가 K 이상일 때 손을 움직여야 함.
            // 또한 치려고하는 키들이 현재키로 초기화.
            if (maxKey - minKey >= K) {
                ans += 1;
                minKey = maxKey = key;
            }
        }

        System.out.println(ans);

    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}


class Solution {
        public long solution(int n) {
                // before : n -1 도달 경우의 수, 기본값 0칸
                // now : n 도달 경우의 수, 기본값 1칸
                long before = 1, now = 1;

                for (int i = 2; i < n + 1; i++) {
                        long tmp = now;

                        // 현재 i 칸에 도달 하는 경우의 수는
                        // i - 1에서 한 칸 뛰기, i - 2에서 두 칸 뛴 경우
                        now = (before + now) % 1234567;
                        before = tmp;
                }
                return now;
        }
}
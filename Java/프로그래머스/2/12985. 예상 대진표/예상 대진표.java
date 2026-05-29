class Solution {
        public int solution(int n, int a, int b) {
                int round = 0;

                while (true) {
                        round += 1;

                        a = (a - 1) / 2 + 1;
                        b = (b - 1) / 2 + 1;

                        if (a == b) {
                                break;
                        }
                }

                return round;
        }
}

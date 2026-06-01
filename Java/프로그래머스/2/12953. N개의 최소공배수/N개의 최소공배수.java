class Solution {
        int gcd(int a, int b) {

                while (b != 0) {
                        int tmp = b;
                        b = a % b;
                        a = tmp;
                }

                return a;
        }
        public int solution(int[] arr) {
                int ans = arr[0];

                for (int a: arr) {
                        int div = gcd(ans, a);
                        ans = (ans * a) / div;
                }

                return ans;
        }
}
class Solution {
    public int solution(int n) {
        int b2 = 0, b1 = 1, now = 1;
        
        for (int i = 2;  i < n; i++) {
            b2 = b1;
            b1 = now;
            now = (b2 + b1) % 1234567;
        }
            
        return now;
    }
}
class Solution {
    public int[] solution(int brown, int yellow) {
        int ansY = -1, ansX = -1;
        
        // 카펫의 전체 넓이
        int total = brown + yellow;
        
        for (int y = 2; y * y <= total; y++) {
            // 현재 세로 길이가 카펫 넓이 배수 여부 확인.
            if (total  % y != 0) {
                continue;
            }
            
            int x = total / y;
            
            // 현재 카펫을 [y, x] 크기의 카펫으로 가정했을 때
            // 해당 카펫에 노란색 격자의 개수는 다음과 같음.
            int yellowCnt = (y - 2) * (x - 2);
            
            if (yellow != yellowCnt) {
                continue;
            }
            
            // 가로 길이 >= 세로 길이
            if (y > x) {
                ansX = y;
                ansY = x;
            } else {
                ansX = x;
                ansY = y;
            }
            
            break;  // 여기까지 도달은 해를 찾음을 의미.
        }
        
        return new int[]{ansX, ansY};
    }
}
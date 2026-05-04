import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;

        Arrays.sort(A);
        Arrays.sort(B);

        int aIdx = 0;

        for (int value : B) {
            if (A[aIdx] < value) {
                answer += 1;
                aIdx += 1;
            }
        }
        
        return answer;
    }
}
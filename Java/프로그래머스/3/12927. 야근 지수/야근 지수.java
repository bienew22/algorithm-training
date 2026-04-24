import java.util.PriorityQueue;
import java.util.Collections;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        for (int w: works) {
            queue.add(w);
        }
        
        for (int i = 0; i < n; i++) {
            if (queue.isEmpty()) {
                break;
            }
            
            int max = queue.poll() - 1;
            
            if (max != 0)
                queue.add(max);
        }
        
        long answer = 0;
        
        for (int d : queue) {
            answer += (long) d * d;
        }
        
        return answer;
    }
}
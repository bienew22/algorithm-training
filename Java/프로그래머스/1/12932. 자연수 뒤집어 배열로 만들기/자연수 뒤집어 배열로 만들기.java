import java.util.ArrayList;
import java.util.List;


class Solution {
    public int[] solution(long n) {
        List<Integer> ans = new ArrayList<>();

        while (n > 0) {
            ans.add((int) (n % 10));
            n /= 10;
        }

        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}
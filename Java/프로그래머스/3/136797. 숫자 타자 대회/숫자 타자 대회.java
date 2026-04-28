import java.util.*;

class Solution {
    public int solution(String numbers) {
        int INF = 70_000_000;

        HashMap<Integer, int[]> location = new HashMap<>();
        location.put(1, new int[]{0, 0});
        location.put(2, new int[]{1, 0});
        location.put(3, new int[]{2, 0});
        location.put(4, new int[]{0, 1});
        location.put(5, new int[]{1, 1});
        location.put(6, new int[]{2, 1});
        location.put(7, new int[]{0, 2});
        location.put(8, new int[]{1, 2});
        location.put(9, new int[]{2, 2});
        location.put(0, new int[]{1, 3});

        HashMap<Integer, Integer> DP = new HashMap<>();

        DP.put(46, 0);

        for (int i = 0; i < numbers.length(); i++) {
            int toMove = numbers.charAt(i) - 48;
            
            HashMap<Integer, Integer> next = new HashMap<>();
            
            for (Integer key: DP.keySet()) {
                int l = key / 10;
                int r = key % 10;
                
                // 왼손을 이동.
                if (r != toMove) {
                    int before = next.getOrDefault(toMove * 10 + r, INF);
                    int value = calcValue(location, l, toMove) + DP.get(key);
                    
                    if (value < before) {
                        next.put(toMove * 10 + r, value);
                    }
                }
                
                // 오른손을 이동.
                if (l != toMove) {
                    int before = next.getOrDefault(l * 10 + toMove, INF);
                    int value = calcValue(location, r, toMove) + DP.get(key);
                    
                    if (value < before) {
                        next.put(l * 10 + toMove, value);
                    }
                }
            }
            
            DP = next;
        }

        int ans = INF;

        for (Integer value: DP.values()) {
            ans = Math.min(ans, value);
        }

        return ans;
    }

    public int calcValue(HashMap<Integer, int[]> loc, int a, int b) {
        if (a == b) {
            return 1;
        }

        int[] aLoc = loc.get(a);
        int[] bLoc = loc.get(b);

        int distanceX = Math.abs(aLoc[0] - bLoc[0]);
        int distanceY = Math.abs(aLoc[1] - bLoc[1]);

        int minDistance = Math.min(distanceX, distanceY);
        int maxDistance = Math.max(distanceX, distanceY);

        return minDistance * 3 + (maxDistance - minDistance) * 2;
    }
}
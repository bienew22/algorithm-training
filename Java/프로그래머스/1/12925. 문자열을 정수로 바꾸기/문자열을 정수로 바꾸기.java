class Solution {
    public int solution(String s) {
        int answer = 0;
        int mul = 1;
        int idx = 0;
        
        if (s.charAt(0) == '-') {
            mul = -1;
            idx = 1;
        } else if (s.charAt(0) == '+') {
            idx = 1;
        } 
        
        for (int i = idx; i < s.length(); i++) {
            answer = answer * 10 + s.charAt(i) - '0';
        }
        
        return answer * mul;
    }
}
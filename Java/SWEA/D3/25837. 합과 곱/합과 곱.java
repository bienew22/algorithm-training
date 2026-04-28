
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.StringTokenizer;

class Solution
{
    public static void main(String args[]) throws Exception
    {
        // System.setIn(new FileInputStream("src/input"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long S = Long.parseLong(st.nextToken());
            long P = Long.parseLong(st.nextToken());

            boolean flag = false;
            for (long i = 1; i <= (long) Math.sqrt(P); i++) {
                if (P % i == 0 && i + P / i == S) {
                    sb.append("Yes\n");
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                sb.append("No\n");
            }
        }

        System.out.println(sb);
    }
}
import java.util.*;
import java.io.*;
//TAGS: dynamicProgramming

public class hayturn {

	public static void main(String[] args) throws IOException {

		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());

		int n = Integer.parseInt(st.nextToken());

		int[] w = new int[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(f.readLine());
			w[i] = Integer.parseInt(st.nextToken());
		}

		f.close();

		long[][] dp = new long[2][n];
		dp[0][n - 1] = w[n - 1];
		for (int i = n - 1; i > 0; i--)
			if (dp[1][i] + w[i - 1] >= dp[0][i]) {
				dp[0][i - 1] = dp[1][i] + w[i - 1];
				dp[1][i - 1] = dp[0][i];
			} else {
				dp[0][i - 1] = dp[0][i];
				dp[1][i - 1] = dp[1][i];
			}

		System.out.println(dp[0][0] + " " + dp[1][0]);

	}

}

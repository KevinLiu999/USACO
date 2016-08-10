import java.util.*;
import java.io.*;

/*
ID: kevinyl1
LANG: JAVA
TASK: rockers
*/

public class rockers {

	public static void main(String[] args) throws IOException {

		/* Read in Input */
		BufferedReader f = new BufferedReader(new FileReader("rockers.in"));
		StringTokenizer st = new StringTokenizer(f.readLine());

		int n = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[] s = new int[n];

		st = new StringTokenizer(f.readLine());
		for (int i = 0; i < n; i++) {
			s[i] = Integer.parseInt(st.nextToken());
		}

		f.close();

		int ans = 0;

		/* DP to Solve */
		int[][][] dp = new int[n + 1][m][t + 1];
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j < m; j++) {
				for (int k = 0; k <= t; k++) {

					ans = Math.max(ans, dp[i][j][k]);

					if (i + 1 <= n && j < m && k <= t) {
						dp[i + 1][j][k] = Math.max(dp[i + 1][j][k], dp[i][j][k]);
					}
					if (i + 1 <= n && j + 1 < m && k + s[i] > t && s[i] <= t) {
						dp[i + 1][j + 1][s[i]] = Math.max(dp[i + 1][j + 1][s[i]], dp[i][j][k] + 1);
					} else if (i + 1 <= n && j < m && k + s[i] <= t) {
						dp[i + 1][j][k + s[i]] = Math.max(dp[i + 1][j][k + s[i]], dp[i][j][k] + 1);
					}

				}
			}
		}

		PrintWriter pw = new PrintWriter(new File("rockers.out"));
		pw.println(ans);
		pw.close();
		System.exit(0);

	}

}

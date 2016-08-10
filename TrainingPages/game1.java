import java.util.*;
import java.io.*;

/*
ID: kevinyl1
LANG: JAVA
TASK: game1
*/

public class game1 {

	public static void main(String[] args) throws IOException {

		/* Read in Input */
		BufferedReader f = new BufferedReader(new FileReader("game1.in"));
		StringTokenizer st = new StringTokenizer(f.readLine());

		int n = Integer.parseInt(st.nextToken());

		int[] v = new int[n];

		int ct = 0;
		while (ct < n) {

			st = new StringTokenizer(f.readLine());

			while (st.hasMoreTokens()) {
				v[ct++] = Integer.parseInt(st.nextToken());
			}

		}

		f.close();

		/* DP to Solve */
		int[][][] dp = new int[2][n][n];
		for (int i = 0; i < n; i++) {
			dp[0][i][i] = v[i];
		}

		for (int len = 0; len < n; len++) {
			for (int l = 0; l < n - len; l++) {
				int r = len + l;
				if (l - 1 >= 0 && dp[0][l - 1][r] < dp[1][l][r] + v[l - 1]) {
					dp[0][l - 1][r] = dp[1][l][r] + v[l - 1];
					dp[1][l - 1][r] = dp[0][l][r];
				}
				if (r + 1 < n && dp[0][l][r + 1] < dp[1][l][r] + v[r + 1]) {
					dp[0][l][r + 1] = dp[1][l][r] + v[r + 1];
					dp[1][l][r + 1] = dp[0][l][r];
				}
			}
		}

		int ans1 = dp[0][0][n - 1];
		int ans2 = dp[1][0][n - 1];

		PrintWriter pw = new PrintWriter(new File("game1.out"));
		pw.println(ans1 + " " + ans2);
		pw.close();
		System.exit(0);

	}

}

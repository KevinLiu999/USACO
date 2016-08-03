import java.util.*;
import java.io.*;

public class baric {

	public static void main(String[] args) throws IOException {

		/* Read in Input */
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());

		int n = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());

		int[] m = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(f.readLine());
			m[i] = Integer.parseInt(st.nextToken());
		}

		f.close();

		/* Compute Values */
		int[] a = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j < i; j++) {
				a[i] += 2 * Math.abs(m[j] - m[i]);
			}
		}
		int[][] b = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				for (int k = i + 1; k <= j - 1; k++) {
					b[i][j] += Math.abs(2 * m[k] - (m[i] + m[j]));
				}
			}
		}
		int[] c = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = i + 1; j <= n; j++) {
				c[i] += 2 * Math.abs(m[j] - m[i]);
			}
		}

		/* DP to Solve */
		int[][] dp = new int[n + 1][n + 1];
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= n; j++) {
				dp[i][j] = 1 << 30;
			}
		}
		dp[0][0] = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (dp[i][j] != 1 << 30) {
					for (int k = i + 1; k <= n + 1; k++) {
						if (i == 0 && k != n + 1) {
							dp[k][j + 1] = Math.min(dp[k][j + 1], dp[i][j] + a[k]);
						} else if (i != 0 && k != n + 1) {
							dp[k][j + 1] = Math.min(dp[k][j + 1], dp[i][j] + b[i][k]);
						} else if (i != 0 && k == n + 1) {
							dp[n][j] = Math.min(dp[n][j], dp[i][j] + c[i]);
						}
					}
				}
			}
		}

		/* Find Answer */
		int ans1 = 0;
		int ans2 = 0;
		for (int i = 1; i <= n; i++) {
			if (dp[n][i] <= e) {
				ans1 = i;
				ans2 = dp[n][i];
				break;
			}
		}

		System.out.println(ans1 + " " + ans2);

	}

}

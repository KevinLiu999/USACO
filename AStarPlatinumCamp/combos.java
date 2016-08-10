import java.util.*;
import java.io.*;

public class combos {

	public static void main(String[] args) throws IOException {

		/* Read in Input */
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));

		// Check for Blank Input
		StringTokenizer st;
		try {
			st = new StringTokenizer(f.readLine());
		} catch (Exception e) {
			return;
		}

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		String[] s = new String[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(f.readLine());
			s[i] = st.nextToken();
		}

		f.close();

		/* Compute Values */
		int[][] nxt = new int[n][n];
		int[][] p = new int[n][16];

		for (int i = 0; i < n; i++) {

			for (int j = 0; j < n; j++) {

				String si = s[i];
				String sj = s[j];
				int li = s[i].length();
				int lj = s[j].length();

				for (int l = Math.max(1, lj - li); l <= lj; l++) {
					if (li - lj + l >= 0 && si.substring(li - lj + l, li).equals(sj.substring(0, lj - l))) {
						nxt[i][j] = l;
						break;
					}
				}

				for (int l = li; l >= 0; l--) {
					if (l - lj >= 0 && si.substring(l - lj, l).equals(sj)) {
						for (int m = li - l + 1; m <= li; m++) {
							p[i][m]++;
						}
					}
				}

			}

		}

		/* DP to Solve */
		int[][] dp = new int[k + 1][n];
		for (int i = 0; i <= k; i++) {
			for (int j = 0; j < n; j++) {
				dp[i][j] = -1;
			}
		}
		for (int i = 0; i < n; i++) {
			dp[s[i].length()][i] = p[i][s[i].length()];
		}

		for (int i = 0; i <= k; i++) {
			for (int j = 0; j < n; j++) {
				if (dp[i][j] != -1) {
					for (int l = 0; l < n; l++) {
						if (i + nxt[j][l] <= k) {
							dp[i + nxt[j][l]][l] = Math.max(dp[i + nxt[j][l]][l], dp[i][j] + p[l][nxt[j][l]]);
						}
					}
				}
			}
		}

		int ans = 0;
		for (int i = 0; i < n; i++) {
			ans = Math.max(ans, dp[k][i]);
		}

		System.out.println(ans);

	}

}

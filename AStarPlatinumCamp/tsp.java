import java.util.*;
import java.io.*;

public class tsp {

	public static int[][] dp;
	public static int[][] adj;

	public static void main(String[] args) throws IOException {

		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		adj = new int[n][n];
		for (int i = 0; i < m; i++) {

			st = new StringTokenizer(f.readLine());

			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int l = Integer.parseInt(st.nextToken());

			adj[a][b] = adj[b][a] = l;

		}

		f.close();

		dp = new int[n][1 << n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(dp[i], 1 << 30);
			dp[i][1 << i] = 0;
		}

		for (int i = 0; i < 1 << n; i++)
			for (int j = 0; j < n; j++)
				if (dp[j][i] != 1 << 30 && (i >> j & 1) == 1)
					for (int k = 0; k < n; k++)
						if (adj[j][k] != 0 && (i >> k & 1) == 0)
							dp[k][i | 1 << k] = Math.min(dp[k][i | 1 << k], dp[j][i] + adj[j][k]);

		int ans = 1 << 30;
		for (int i = 0; i < n; i++)
			ans = Math.min(ans, dp[i][(1 << n) - 1]);

		System.out.println(ans);

	}

	public static class Edge {

		public int n, d;

		public Edge(int n, int d) {
			this.n = n;
			this.d = d;
		}

	}

}

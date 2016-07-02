import java.util.*;

public class vacation {

	public static ArrayList<ArrayList<Integer>> adj;
	public static int[][] dp;
	public static boolean[] vis;

	public static int dfs(int x) {
		if (vis[x])
			return 0;
		vis[x] = true;
		for (int i : adj.get(x)) {
			if (!vis[i]) {
				dfs(i);
				dp[0][x] += Math.max(dp[0][i], dp[1][i]);
				dp[1][x] += dp[0][i];
			}
		}
		dp[1][x]++;
		return Math.max(dp[0][x], dp[1][x]);
	}

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		int n = input.nextInt();

		adj = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i <= n; i++) {
			ArrayList<Integer> tl = new ArrayList<Integer>();
			adj.add(tl);
		}

		for (int i = 1; i < n; i++) {

			int a = input.nextInt();
			int b = input.nextInt();

			adj.get(a).add(b);
			adj.get(b).add(a);

		}

		input.close();

		dp = new int[2][n + 1];
		vis = new boolean[n + 1];

		int ans = dfs(1);

		System.out.println(ans);

	}

}

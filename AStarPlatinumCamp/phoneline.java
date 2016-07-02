import java.util.*;

public class phoneline {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		int n = input.nextInt();
		int p = input.nextInt();
		int k = input.nextInt();

		ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();
		for (int i = 0; i <= n; i++) {
			ArrayList<Edge> tl = new ArrayList<Edge>();
			adj.add(tl);
		}

		for (int i = 0; i < p; i++) {

			int a = input.nextInt();
			int b = input.nextInt();
			int l = input.nextInt();

			adj.get(a).add(new Edge(b, l));
			adj.get(b).add(new Edge(a, l));

		}

		input.close();

		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		int[][] dp = new int[k + 1][n + 1];
		for (int i = 0; i <= k; i++)
			Arrays.fill(dp[i], 1 << 30);
		dp[0][1] = 0;

		pq.add(1);

		while (!pq.isEmpty()) {
			int tn = pq.poll();
			for (Edge i : adj.get(tn))
				for (int j = 0; j <= k; j++)
					if (dp[j][tn] != 1 << 30) {
						if (j < k)
							if (dp[j][tn] < dp[j + 1][i.n]) {
								dp[j + 1][i.n] = dp[j][tn];
								if (!pq.contains(i.n))
									pq.add(i.n);
							}
						if (Math.max(dp[j][tn], i.d) < dp[j][i.n]) {
							dp[j][i.n] = Math.max(dp[j][tn], i.d);
							if (!pq.contains(i.n))
								pq.add(i.n);
						}
					}
		}

		int ans = 1 << 30;
		for (int i = 0; i <= k; i++)
			ans = Math.min(ans, dp[i][n]);

		if (ans == 1 << 30)
			System.out.println(-1);
		else
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

import java.util.*;
//TAGS: graphTheory, dfs

public class gather {

	public static int n;
	public static long ans = (long) 1 << 50;

	public static long[] c;
	public static long[] size;
	public static long[] d;
	public static long[] dp;
	public static boolean[] v;

	public static ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();

	public static long dfs1(long node) {
		v[(int) node] = true;
		for (Edge i : adj.get((int) node))
			if (!v[(int) i.n])
				size[(int) node] += dfs1(i.n);
		return size[(int) node];
	}

	public static void dfs2(long node, long dist) {
		d[(int) node] = dist;
		for (Edge i : adj.get((int) node))
			if (d[(int) i.n] == -1)
				dfs2(i.n, dist + i.d);
	}

	public static void dfs3(long node, long best) {
		dp[(int) node] = best;
		ans = Math.min(ans, best);
		for (Edge i : adj.get((int) node))
			if (dp[(int) i.n] == 0)
				dfs3(i.n, dp[(int) node] - size[(int) i.n] * i.d + (size[1] - size[(int) i.n]) * i.d);
	}

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		n = input.nextInt();

		c = new long[n + 1];
		size = new long[n + 1];
		d = new long[n + 1];
		dp = new long[n + 1];
		v = new boolean[n + 1];
		for (long i = 1; i <= n; i++)
			c[(int) i] = size[(int) i] = input.nextLong();

		Arrays.fill(d, -1);
		d[1] = 0;

		for (long i = 0; i <= n; i++) {
			ArrayList<Edge> tl = new ArrayList<Edge>();
			adj.add(tl);
		}

		for (long i = 0; i < n - 1; i++) {

			long a = input.nextLong();
			long b = input.nextLong();
			long l = input.nextLong();

			adj.get((int) a).add(new Edge(b, l));
			adj.get((int) b).add(new Edge(a, l));

		}

		input.close();

		dfs1(1);
		dfs2(1, 0);
		for (long i = 1; i <= n; i++)
			dp[1] += d[(int) i] * c[(int) i];
		dfs3(1, dp[1]);

		System.out.println(ans);

	}

	public static class Edge {

		public long n, d;

		public Edge(long n, long d) {
			this.n = n;
			this.d = d;
		}

	}

}

import java.util.*;
import java.io.*;
//TAGS: graphTheory, dfs
//ALGORITHMS: Dijkstra's

public class relocate {

	public static int n, k;

	public static int ans = 1 << 30;

	public static int[] c;

	public static int[][] d;

	public static void recurs(String s) {

		if (s.length() == k)
			for (int i = 1; i <= n; i++) {

				int ct = 0;
				for (int j = 0; j < k; j++)
					if (i != c[j])
						ct++;
				if (ct < k)
					continue;

				int cur = d[s.charAt(0) - 48][i];
				for (int j = 0; j < k - 1; j++)
					cur += d[s.charAt(j) - 48][c[s.charAt(j + 1) - 48]];
				cur += d[s.charAt(k - 1) - 48][i];
				ans = Math.min(ans, cur);

			}

		boolean[] v = new boolean[k];
		for (int i = 0; i < s.length(); i++)
			if (!v[s.charAt(i) - 48])
				v[s.charAt(i) - 48] = true;

		for (int i = 0; i < k; i++)
			if (!v[i])
				recurs(s + i);

	}

	public static void main(String[] args) throws IOException {

		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());

		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		c = new int[k];
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(f.readLine());
			c[i] = Integer.parseInt(st.nextToken());
		}

		ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();
		for (int i = 0; i <= n; i++) {
			ArrayList<Edge> tl = new ArrayList<Edge>();
			adj.add(tl);
		}

		for (int i = 0; i < m; i++) {

			st = new StringTokenizer(f.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());

			adj.get(a).add(new Edge(b, l));
			adj.get(b).add(new Edge(a, l));

		}

		f.close();

		d = new int[k][n + 1];
		for (int i = 0; i < k; i++) {

			Arrays.fill(d[i], 1 << 30);
			d[i][c[i]] = 0;
			PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
			pq.add(new Edge(c[i], 0));
			while (!pq.isEmpty()) {

				int tn = pq.peek().n;
				int td = pq.peek().d;
				pq.poll();

				for (Edge j : adj.get(tn))
					if (td + j.d < d[i][j.n]) {
						d[i][j.n] = td + j.d;
						pq.add(new Edge(j.n, td + j.d));
					}

			}

		}

		for (int i = 0; i < k; i++)
			recurs(Integer.toString(i));

		System.out.println(ans);

	}

	public static class Edge implements Comparable<Edge> {

		public int n, d;

		public Edge(int n, int d) {
			this.n = n;
			this.d = d;
		}

		public int compareTo(Edge o) {
			if (d == o.d)
				return 0;
			else
				return d < o.d ? -1 : 1;
		}

	}

}

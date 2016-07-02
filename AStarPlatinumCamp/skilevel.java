import java.util.*;
import java.io.*;

public class skilevel {

	public static int[] mark;
	public static int[] parent;
	public static int[] size;

	public static void union(int a, int b) {

		a = find(a);
		b = find(b);

		parent[a] = b;
		size[b] += size[a];
		mark[b] += mark[a];

	}

	public static int find(int x) {
		if (x == parent[x])
			return x;
		else
			return parent[x] = find(parent[x]);
	}

	public static void main(String[] args) throws IOException {

		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());

		int[][] graph = new int[n + 1][m + 1];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(f.readLine());
			for (int j = 1; j <= m; j++)
				graph[i][j] = Integer.parseInt(st.nextToken());
		}
		mark = new int[n * m + 1];
		parent = new int[n * m + 1];
		size = new int[n * m + 1];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(f.readLine());
			for (int j = 1; j <= m; j++)
				mark[(i - 1) * m + j] = Integer.parseInt(st.nextToken());
		}

		f.close();

		ArrayList<Edge> e = new ArrayList<Edge>();
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= m; j++) {
				if (j != m)
					e.add(new Edge((i - 1) * m + j, (i - 1) * m + j + 1, Math.abs(graph[i][j] - graph[i][j + 1])));
				if (i != n)
					e.add(new Edge((i - 1) * m + j, i * m + j, Math.abs(graph[i][j] - graph[i + 1][j])));
			}

		Collections.sort(e);

		for (int i = 1; i <= n * m; i++)
			parent[i] = i;
		Arrays.fill(size, 1);

		long ans = 0;
		for (Edge i : e)
			if (find(i.a) != find(i.b)) {
				union(i.a, i.b);
				if (mark[find(i.a)] > 0 && size[find(i.a)] >= t) {
					ans += (long) (mark[find(i.a)]) * (long) (i.d);
					mark[find(i.a)] = 0;
				}
			}

		System.out.println(ans);

	}

	public static class Edge implements Comparable<Edge> {

		public int a, b, d;

		public Edge(int a, int b, int d) {
			this.a = a;
			this.b = b;
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

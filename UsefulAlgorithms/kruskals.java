import java.util.*;
import java.io.*;

public class kruskals {

	public static int[] parent;

	public static void union(int a, int b) {
		parent[find(a)] = find(b);
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

		parent = new int[n + 1];

		ArrayList<Edge> e = new ArrayList<Edge>();
		for (int i = 0; i < m; i++) {

			st = new StringTokenizer(f.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());

			e.add(new Edge(a, b, l));

		}

		f.close();

		Collections.sort(e);

		for (int i = 1; i <= n; i++)
			parent[i] = i;

		int ans = 0;
		for (Edge i : e)
			if (find(i.a) != find(i.b)) {
				union(i.a, i.b);
				ans += i.l;
			}

		System.out.println(ans);

	}

	public static class Edge implements Comparable<Edge> {

		public int a, b, l;

		public Edge(int a, int b, int l) {
			this.a = a;
			this.b = b;
			this.l = l;
		}

		public int compareTo(Edge o) {
			if (l == o.l)
				return 0;
			else
				return l < o.l ? -1 : 1;
		}

	}

}

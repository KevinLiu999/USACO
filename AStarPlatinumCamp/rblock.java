import java.util.*;

public class rblock {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		int n = input.nextInt();
		int m = input.nextInt();

		int[][] graph = new int[n + 1][n + 1];

		for (int i = 0; i < m; i++) {

			int a = input.nextInt();
			int b = input.nextInt();
			int l = input.nextInt();

			graph[a][b] = graph[b][a] = l;

		}

		input.close();

		int[] d = new int[n + 1];
		Arrays.fill(d, 1 << 30);
		d[1] = 0;
		int[] prev = new int[n + 1];
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		pq.add(new Edge(1, 0));
		while (!pq.isEmpty()) {

			int tn = pq.peek().n;
			pq.poll();

			for (int i = 1; i <= n; i++)
				if (d[tn] + graph[tn][i] < d[i] && graph[tn][i] != 0) {
					d[i] = d[tn] + graph[tn][i];
					prev[i] = tn;
					pq.add(new Edge(i, d[i]));
				}

		}

		int tmp = d[n];

		int ans = 0;
		for (int i = 1; i <= n; i++) {

			graph[i][prev[i]] *= 2;
			graph[prev[i]][i] *= 2;

			Arrays.fill(d, 1 << 30);
			d[1] = 0;
			pq.add(new Edge(1, 0));
			while (!pq.isEmpty()) {

				int tn = pq.peek().n;
				pq.poll();

				for (int j = 1; j <= n; j++)
					if (d[tn] + graph[tn][j] < d[j] && graph[tn][j] != 0) {
						d[j] = d[tn] + graph[tn][j];
						pq.add(new Edge(j, d[j]));
					}

			}

			ans = Math.max(ans, d[n]);

			graph[i][prev[i]] /= 2;
			graph[prev[i]][i] /= 2;

		}

		ans -= tmp;

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

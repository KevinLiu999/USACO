import java.util.*;

public class cheer {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		int n = input.nextInt();
		int m = input.nextInt();

		long[] c = new long[n + 1];
		long s = 1;
		for (int i = 1; i <= n; i++) {
			c[i] = input.nextLong();
			if (c[(int) s] > c[i])
				s = i;
		}

		ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();
		for (long i = 0; i <= n; i++) {
			ArrayList<Edge> tl = new ArrayList<Edge>();
			adj.add(tl);
		}

		for (long i = 0; i < m; i++) {

			long a = input.nextLong();
			long b = input.nextLong();
			long l = input.nextLong();

			adj.get((int) a).add(new Edge(b, c[(int) a] + c[(int) b] + 2 * l));
			adj.get((int) b).add(new Edge(a, c[(int) a] + c[(int) b] + 2 * l));

		}

		input.close();

		long ans = 0;
		boolean[] v = new boolean[n + 1];
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		pq.add(new Edge(s, 0));
		while (!pq.isEmpty()) {

			long tn = pq.peek().n;
			long td = pq.peek().d;
			pq.poll();

			if (v[(int) tn])
				continue;

			ans += td;
			v[(int) tn] = true;

			for (Edge i : adj.get((int) tn))
				if (!v[(int) i.n])
					pq.add(i);

		}

		ans += c[(int) s];

		System.out.println(ans);

	}

	public static class Edge implements Comparable<Edge> {

		public long n, d;

		public Edge(long n, long d) {
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

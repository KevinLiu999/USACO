import java.util.*;
import java.io.*;

public class bellmanFord {

	public static void main(String[] args) throws IOException {

		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		ArrayList<Edge> e = new ArrayList<Edge>();
		for (int i = 0; i < m; i++) {

			st = new StringTokenizer(f.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());

			e.add(new Edge(a, b, l));

		}

		int[] d = new int[n + 1];
		Arrays.fill(d, 1 << 30);
		d[1] = 0;

		for (int i = 0; i < n; i++) {
			for (Edge j : e) {
				if (d[j.a] + j.l < d[j.b]) {
					d[j.b] = d[j.a] + j.l;
				}
			}
		}

		for (Edge i : e) {
			if (d[i.a] + i.l < d[i.b]) {
				d[i.b] = 1 << 30;
			}
		}

		for (int i = 1; i <= n; i++) {
			System.out.println(d[i]);
		}

	}

	public static class Edge {

		public int a, b, l;

		public Edge(int a, int b, int l) {
			this.a = a;
			this.b = b;
			this.l = l;
		}

	}

}

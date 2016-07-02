import java.util.*;
import java.io.*;

public class bigmac {

	public static void main(String[] args) throws IOException {

		Scanner input = new Scanner(System.in);

		int n = input.nextInt();
		int m = input.nextInt();
		double v = Math.log10(input.nextDouble());
		int a = input.nextInt();
		int b = input.nextInt();

		ArrayList<Edge> adj = new ArrayList<Edge>();
		for (int i = 0; i < m; i++) {

			int x = input.nextInt();
			int y = input.nextInt();
			double l = Math.log10(input.nextDouble());

			adj.add(new Edge(x, y, l));

		}

		input.close();

		double[] ans = new double[n + 1];
		Arrays.fill(ans, 1 << 30);
		ans[a] = v;
		for (int i = 0; i < n; i++)
			for (Edge j : adj)
				ans[j.b] = Math.min(ans[j.b], ans[j.a] + j.d);

		for (Edge i : adj)
			if (ans[i.b] > ans[i.a] + i.d) {
				System.out.println(0);
				return;
			}

		System.out.println((long) Math.pow(10, ans[b]));

	}

	public static class Edge {

		public int a, b;
		public double d;

		public Edge(int a, int b, double d) {
			this.a = a;
			this.b = b;
			this.d = d;
		}

	}

}

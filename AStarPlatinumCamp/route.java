import java.util.*;
import java.io.*;

public class route {

	public static void main(String[] args) throws IOException {

		/* Read in Input */
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());

		long[] dpa = new long[n + 1];
		long[] dpb = new long[m + 1];

		int[] a = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(f.readLine());
			dpa[i] = a[i] = Integer.parseInt(st.nextToken());
		}

		int[] b = new int[m + 1];
		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(f.readLine());
			dpb[i] = b[i] = Integer.parseInt(st.nextToken());
		}

		ArrayList<Route> p = new ArrayList<Route>();
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(f.readLine());
			p.add(new Route(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}

		f.close();

		Collections.sort(p);

		/* DP to Solve */
		for (int i = 0; i < r; i++) {
			int x = p.get(i).x;
			int y = p.get(i).y;
			long u = dpb[y] + a[x];
			long v = dpa[x] + b[y];
			dpa[x] = Math.max(dpa[x], u);
			dpb[y] = Math.max(dpb[y], v);
		}

		long ans = 0;
		for (int i = 1; i <= n; i++)
			ans = Math.max(ans, dpa[i]);
		for (int i = 1; i <= m; i++)
			ans = Math.max(ans, dpb[i]);

		System.out.println(ans);

	}

	public static class Route implements Comparable<Route> {

		public int x, y;

		public Route(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int compareTo(Route o) {
			if (x == o.x) {
				if (y == o.y)
					return 0;
				else
					return y < o.y ? -1 : 1;
			} else
				return x < o.x ? -1 : 1;
		}

	}

}

import java.util.*;
import java.io.*;

public class lazy {

	public static ArrayList<Integer> arr = new ArrayList<Integer>();

	public static int[] maxy = new int[1 << 19];
	public static int[] miny = new int[1 << 19];

	public static int[] lazy = new int[1 << 19];
	public static int[] max = new int[1 << 19];

	public static void push(int p) {

		if (lazy[p] == 0) {
			return;
		}

		max[p] += lazy[p];

		if (p * 2 + 1 < 1 << 19) {
			lazy[p * 2] += lazy[p];
			lazy[p * 2 + 1] += lazy[p];
		}
		lazy[p] = 0;

		return;

	}

	public static void build(int p, int l, int r) {

		if (l == r) {
			maxy[p] = arr.get(l);
			miny[p] = arr.get(l - 1);
			return;
		}

		int mid = (l + r) / 2;
		build(p * 2, l, mid);
		build(p * 2 + 1, mid + 1, r);

		maxy[p] = Math.max(maxy[p * 2], maxy[p * 2 + 1]);
		miny[p] = Math.min(miny[p * 2], miny[p * 2 + 1]);

	}

	public static void update(int p, int a, int b, int v) {

		push(p);

		if (a >= maxy[p] || b <= miny[p]) {
			return;
		} else if (a <= miny[p] && b >= maxy[p]) {
			lazy[p] += v;
			push(p);
			return;
		}

		update(p * 2, a, b, v);
		update(p * 2 + 1, a, b, v);

		max[p] = Math.max(max[p * 2], max[p * 2 + 1]);

	}

	public static void main(String[] args) throws IOException {

		/* Read in Input */
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		ArrayList<Event> e = new ArrayList<Event>();
		TreeSet<Integer> s = new TreeSet<Integer>();

		for (int i = 0; i < n; i++) {

			st = new StringTokenizer(f.readLine());

			int g = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			int lx = 2 * (x - k - y) - 1;
			int ly = 2 * (x - k + y) - 1;
			int hx = 2 * (x + k - y) + 1;
			int hy = 2 * (x + k + y) + 1;

			e.add(new Event(lx, ly, hy, g));
			e.add(new Event(hx, ly, hy, -g));

			s.add(ly);
			s.add(hy);

		}

		f.close();

		Collections.sort(e);

		while (!s.isEmpty()) {
			arr.add(s.pollFirst());
		}

		/* Build Segment Tree */
		build(1, 1, arr.size() - 1);

		/* Update and Query Segment Tree to Solve */
		int ans = 0;
		for (Event i : e) {
			update(1, i.ly, i.hy, i.g);
			ans = Math.max(ans, max[1]);
		}

		System.out.println(ans);

	}

	public static class Event implements Comparable<Event> {

		public int x, ly, hy, g;

		public Event(int x, int ly, int hy, int g) {
			this.x = x;
			this.ly = ly;
			this.hy = hy;
			this.g = g;
		}

		public int compareTo(Event o) {
			if (x == o.x) {
				return 0;
			} else {
				return x < o.x ? -1 : 1;
			}
		}

	}

}

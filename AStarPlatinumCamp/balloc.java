import java.util.*;
//TAGS: segmentTree, greedy

public class balloc {

	public static int[] arr = new int[1 << 18];
	public static int[] lazy = new int[1 << 19];
	public static int[] min = new int[1 << 19];

	public static void push(int p, int l, int r) {

		if (lazy[p] == 0)
			return;

		min[p] += lazy[p];

		if (l != r) {
			lazy[p * 2] += lazy[p];
			lazy[p * 2 + 1] += lazy[p];
		}
		lazy[p] = 0;

	}

	public static void build(int p, int l, int r) {

		if (l == r) {
			min[p] = arr[l];
			return;
		}

		int mid = (l + r) / 2;
		build(2 * p, l, mid);
		build(2 * p + 1, mid + 1, r);

		min[p] = Math.min(min[2 * p], min[2 * p + 1]);

	}

	public static void update(int p, int l, int r, int a, int b) {

		push(p, l, r);

		if (l > b || r < a)
			return;

		if (l >= a && r <= b) {
			lazy[p] -= 1;
			push(p, l, r);
			return;
		}

		int mid = (l + r) / 2;
		update(p * 2, l, mid, a, b);
		update(p * 2 + 1, mid + 1, r, a, b);

		min[p] = Math.min(min[p * 2], min[p * 2 + 1]);

	}

	public static int query(int p, int l, int r, int a, int b) {

		push(p, l, r);

		if (l > b || r < a)
			return 1 << 30;

		if (l >= a && r <= b)
			return min[p];

		int mid = (l + r) / 2;
		return Math.min(query(p * 2, l, mid, a, b), query(p * 2 + 1, mid + 1, r, a, b));

	}

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		int n = input.nextInt();
		int m = input.nextInt();

		for (int i = 1; i <= n; i++)
			arr[i] = input.nextInt();

		build(1, 1, n);

		ArrayList<Segment> s = new ArrayList<Segment>();
		for (int i = 0; i < m; i++)
			s.add(new Segment(input.nextInt(), input.nextInt()));

		input.close();

		Collections.sort(s);

		int ans = 0;
		for (int i = 0; i < m; i++) {
			int p1 = s.get(i).p1;
			int p2 = s.get(i).p2;
			if (query(1, 1, n, p1, p2) > 0) {
				ans++;
				update(1, 1, n, p1, p2);
			}
		}

		System.out.println(ans);

	}

	public static class Segment implements Comparable<Segment> {

		public int p1, p2;

		public Segment(int p1, int p2) {
			this.p1 = p1;
			this.p2 = p2;
		}

		public int compareTo(Segment o) {
			if (p2 == o.p2)
				return 0;
			else
				return p2 < o.p2 ? -1 : 1;
		}

	}

}

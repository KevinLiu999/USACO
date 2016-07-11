import java.util.*;
import java.io.*;

public class segmentTree {

	public static int n, q;

	public static int[] arr;

	public static int[] lazy = new int[1 << 19];
	public static int[] max = new int[1 << 19];
	public static int[] min = new int[1 << 19];
	public static int[] sum = new int[1 << 19];

	public static void push(int p, int l, int r) {

		if (lazy[p] == 0)
			return;

		max[p] += lazy[p];
		min[p] += lazy[p];
		sum[p] += lazy[p] * (r - l + 1);

		if (l != r) {
			lazy[p * 2] += lazy[p];
			lazy[p * 2 + 1] += lazy[p];
		}
		lazy[p] = 0;

		return;

	}

	public static void build(int p, int l, int r) {

		if (l == r) {
			max[p] = arr[l];
			min[p] = arr[l];
			sum[p] = arr[l];
			return;
		}

		int mid = (l + r) / 2;
		build(p * 2, l, mid);
		build(p * 2 + 1, mid + 1, r);

		max[p] = Math.max(max[p * 2], max[p * 2 + 1]);
		min[p] = Math.min(min[p * 2], min[p * 2 + 1]);
		sum[p] = sum[p * 2] + sum[p * 2 + 1];

	}

	public static void update(int p, int l, int r, int a, int b, int v) {

		push(p, l, r);

		if (a > r || b < l)
			return;
		else if (a <= l && b >= r) {
			lazy[p] += v;
			push(p, l, r);
			return;
		}

		int mid = (l + r) / 2;
		update(p * 2, l, mid, a, b, v);
		update(p * 2 + 1, mid + 1, r, a, b, v);

		max[p] = Math.max(max[p * 2], max[p * 2 + 1]);
		min[p] = Math.min(min[p * 2], min[p * 2 + 1]);
		sum[p] = sum[p * 2] + sum[p * 2 + 1];

	}

	public static int maxQuery(int p, int l, int r, int a, int b) {

		push(p, l, r);

		if (a > r || b < l)
			return 0;
		else if (a <= l && b >= r)
			return max[p];

		int mid = (l + r) / 2;
		return Math.max(maxQuery(p * 2, l, mid, a, b), maxQuery(p * 2 + 1, mid + 1, r, a, b));

	}

	public static int minQuery(int p, int l, int r, int a, int b) {

		push(p, l, r);

		if (a > r || b < l)
			return 1 << 30;
		else if (a <= l && b >= r)
			return min[p];

		int mid = (l + r) / 2;
		return Math.min(minQuery(p * 2, l, mid, a, b), minQuery(p * 2 + 1, mid + 1, r, a, b));

	}

	public static int sumQuery(int p, int l, int r, int a, int b) {

		push(p, l, r);

		if (a > r || b < l)
			return 0;
		else if (a <= l && b >= r)
			return sum[p];

		int mid = (l + r) / 2;
		return sumQuery(p * 2, l, mid, a, b) + sumQuery(p * 2 + 1, mid + 1, r, a, b);

	}

	public static void main(String[] args) throws IOException {

		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());

		n = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());

		arr = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(f.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
		}

		build(1, 1, n);

		for (int i = 0; i < q; i++) {

			st = new StringTokenizer(f.readLine());

			int x = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (x == 0) {
				int v = Integer.parseInt(st.nextToken());
				update(1, 1, n, a, b, v);
			} else if (x == 1)
				System.out.println(maxQuery(1, 1, n, a, b));
			else if (x == 2)
				System.out.println(minQuery(1, 1, n, a, b));
			else
				System.out.println(sumQuery(1, 1, n, a, b));

		}

		f.close();

	}

}

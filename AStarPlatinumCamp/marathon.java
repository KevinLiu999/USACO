import java.util.*;
import java.io.*;
//TAGS: segmentTree

public class marathon {

	public static int[] sumarr = new int[1 << 18];
	public static int[] minarr = new int[1 << 18];

	public static int[] sum = new int[1 << 19];
	public static int[] min = new int[1 << 19];

	public static void build(int p, int l, int r) {

		if (l == r) {
			sum[p] = sumarr[l];
			min[p] = minarr[l];
			return;
		}

		int mid = (l + r) / 2;
		build(2 * p, l, mid);
		build(2 * p + 1, mid + 1, r);

		sum[p] = sum[2 * p] + sum[2 * p + 1];
		min[p] = Math.min(min[2 * p], min[2 * p + 1]);

	}

	public static void update(int p, int l, int r, int a, int v) {

		if (a < l || a > r)
			return;

		if (l == r) {
			if (v == 1)
				sum[p] = sumarr[l];
			else
				min[p] = minarr[l];
			return;
		}

		int mid = (l + r) / 2;
		update(p * 2, l, mid, a, v);
		update(p * 2 + 1, mid + 1, r, a, v);

		if (v == 1)
			sum[p] = sum[p * 2] + sum[p * 2 + 1];
		else
			min[p] = Math.min(min[p * 2], min[p * 2 + 1]);

	}

	public static int sumQuery(int p, int l, int r, int a, int b) {

		if (r < a || l > b)
			return 0;

		if (l >= a && r <= b)
			return sum[p];

		int mid = (l + r) / 2;
		return sumQuery(2 * p, l, mid, a, b) + sumQuery(2 * p + 1, mid + 1, r, a, b);

	}

	public static int minQuery(int p, int l, int r, int a, int b) {

		if (r < a || l > b)
			return 1 << 30;

		if (l >= a && r <= b)
			return min[p];

		int mid = (l + r) / 2;
		return Math.min(minQuery(2 * p, l, mid, a, b), minQuery(2 * p + 1, mid + 1, r, a, b));

	}

	public static void main(String[] args) throws IOException {

		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());

		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());

		Arrays.fill(min, 1 << 30);

		int[] x = new int[n];
		int[] y = new int[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(f.readLine());
			x[i] = Integer.parseInt(st.nextToken());
			y[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i < n; i++)
			sumarr[i] = Math.abs(x[i] - x[i - 1]) + Math.abs(y[i] - y[i - 1]);

		for (int i = 2; i < n; i++)
			minarr[i - 1] = Math.abs(x[i] - x[i - 2]) + Math.abs(y[i] - y[i - 2]) - sumarr[i] - sumarr[i - 1];

		build(1, 1, n);

		for (int i = 0; i < q; i++) {

			st = new StringTokenizer(f.readLine());

			char c = st.nextToken().charAt(0);

			if (c == 'U') {

				int a = Integer.parseInt(st.nextToken());
				x[a - 1] = Integer.parseInt(st.nextToken());
				y[a - 1] = Integer.parseInt(st.nextToken());

				if (a > 1) {
					sumarr[a - 1] = Math.abs(x[a - 1] - x[a - 2]) + Math.abs(y[a - 1] - y[a - 2]);
					update(1, 1, n, a - 1, 1);
				}
				if (a < n) {
					sumarr[a] = Math.abs(x[a] - x[a - 1]) + Math.abs(y[a] - y[a - 1]);
					update(1, 1, n, a, 1);
				}

				if (a > 2) {
					minarr[a - 2] = Math.abs(x[a - 1] - x[a - 3]) + Math.abs(y[a - 1] - y[a - 3]) - sumarr[a - 1]
							- sumarr[a - 2];
					update(1, 1, n, a - 2, 0);
				}
				if (a > 1 && a < n) {
					minarr[a - 1] = Math.abs(x[a] - x[a - 2]) + Math.abs(y[a] - y[a - 2]) - sumarr[a] - sumarr[a - 1];
					update(1, 1, n, a - 1, 0);
				}
				if (a < n - 1) {
					minarr[a] = Math.abs(x[a + 1] - x[a - 1]) + Math.abs(y[a + 1] - y[a - 1]) - sumarr[a + 1]
							- sumarr[a];
					update(1, 1, n, a, 0);
				}

			} else {

				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken()) - 1;

				int ans = sumQuery(1, 1, n, a, b) + minQuery(1, 1, n, a, b - 1);
				System.out.println(ans);

			}

		}

		f.close();

	}

}

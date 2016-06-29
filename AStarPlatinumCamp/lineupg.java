import java.util.*;
import java.io.*;

public class lineupg {

	public static int[] arr = new int[1 << 17];

	public static int[] min = new int[1 << 18];
	public static int[] max = new int[1 << 18];

	public static void build(int p, int l, int r) {

		if (l == r) {
			min[p] = arr[l];
			max[p] = arr[l];
			return;
		}

		int mid = (l + r) / 2;
		build(p * 2, l, mid);
		build(p * 2 + 1, mid + 1, r);

		min[p] = Math.min(min[p * 2], min[p * 2 + 1]);
		max[p] = Math.max(max[p * 2], max[p * 2 + 1]);

	}

	public static int minQuery(int p, int l, int r, int a, int b) {

		if (r < a || l > b)
			return 1 << 30;

		if (l >= a && r <= b)
			return min[p];

		int mid = (l + r) / 2;
		return Math.min(minQuery(p * 2, l, mid, a, b), minQuery(p * 2 + 1, mid + 1, r, a, b));

	}

	public static int maxQuery(int p, int l, int r, int a, int b) {

		if (r < a || l > b)
			return 0;

		if (l >= a && r <= b)
			return max[p];

		int mid = (l + r) / 2;
		return Math.max(maxQuery(p * 2, l, mid, a, b), maxQuery(p * 2 + 1, mid + 1, r, a, b));

	}

	public static void main(String[] args) throws IOException {

		try {

			BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(f.readLine());

			int n = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());

			Arrays.fill(min, 1 << 30);

			for (int i = 1; i <= n; i++) {
				st = new StringTokenizer(f.readLine());
				arr[i] = Integer.parseInt(st.nextToken());
			}

			build(1, 1, n);

			for (int i = 0; i < q; i++) {

				st = new StringTokenizer(f.readLine());

				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				int ans = maxQuery(1, 1, n, a, b) - minQuery(1, 1, n, a, b);
				System.out.println(ans);

			}

			f.close();

		} catch (Exception e) {

		}

	}

}

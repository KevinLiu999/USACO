import java.util.*;
import java.io.*;

public class lites {

	public static int[] lazy = new int[1 << 19];
	public static int[] sum = new int[1 << 19];

	public static void push(int p, int l, int r) {

		if (lazy[p] == 0)
			return;

		sum[p] = r - l + 1 - sum[p];

		if (l != r) {
			lazy[p * 2] ^= 1;
			lazy[p * 2 + 1] ^= 1;
		}
		lazy[p] = 0;

	}

	public static void update(int p, int l, int r, int a, int b) {

		push(p, l, r);

		if (l > b || r < a)
			return;

		if (l >= a && r <= b) {
			lazy[p] ^= 1;
			push(p, l, r);
			return;
		}

		int mid = (l + r) / 2;
		update(p * 2, l, mid, a, b);
		update(p * 2 + 1, mid + 1, r, a, b);

		sum[p] = sum[p * 2] + sum[p * 2 + 1];

	}

	public static int query(int p, int l, int r, int a, int b) {

		push(p, l, r);

		if (l > b || r < a)
			return 0;

		if (l >= a && r <= b)
			return sum[p];

		int mid = (l + r) / 2;
		return query(p * 2, l, mid, a, b) + query(p * 2 + 1, mid + 1, r, a, b);

	}

	public static void main(String[] args) throws IOException {

		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());

		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());

		for (int i = 0; i < q; i++) {

			st = new StringTokenizer(f.readLine());

			int x = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (x == 0)
				update(1, 1, n, a, b);
			else
				System.out.println(query(1, 1, n, a, b));

		}

		f.close();

	}

}

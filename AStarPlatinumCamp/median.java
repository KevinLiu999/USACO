import java.util.*;
//TAGS: binaryIndexTree

public class median {

	public static long[] data = new long[1 << 19];

	public static void update(int x, long v) {
		while (x < data.length) {
			data[x] += v;
			x += x & -x;
		}
	}

	public static long query(int x) {
		return x > 0 ? data[x] + query(x - (x & -x)) : 0;
	}

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		int n = input.nextInt();
		int h = input.nextInt();

		long[] sum = new long[n + 1];
		for (int i = 1; i <= n; i++)
			if (i > 0)
				sum[i] = sum[i - 1] + (input.nextLong() >= h ? 1 : -1);
			else
				sum[i] = input.nextLong() >= h ? 1 : -1;

		input.close();

		long ans = 0;
		for (int i = 0; i <= n; i++) {
			ans += query((int) (sum[i] + n + 1));
			update((int) (sum[i] + n + 1), 1);
		}

		System.out.println(ans);

	}

}

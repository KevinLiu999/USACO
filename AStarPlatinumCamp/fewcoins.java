import java.util.*;

public class fewcoins {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		int n = input.nextInt();
		int t = input.nextInt();

		int[] v = new int[n];
		for (int i = 0; i < n; i++)
			v[i] = input.nextInt();

		int[] c = new int[n];
		for (int i = 0; i < n; i++)
			c[i] = input.nextInt();

		input.close();

		ArrayList<Integer> vn = new ArrayList<Integer>();
		ArrayList<Integer> cn = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			int cur = 0;
			for (int j = 1; cur <= c[i]; j *= 2) {
				cur += j;
				if (cur > c[i]) {
					cur -= j;
					break;
				}
				vn.add(v[i] * j);
				cn.add(j);
			}
			if (c[i] - cur > 0) {
				vn.add(v[i] * (c[i] - cur));
				cn.add(c[i] - cur);
			}
		}

		long[] dp = new long[30001];
		Arrays.fill(dp, 1 << 30);
		dp[0] = 0;
		for (int i = 0; i < vn.size(); i++)
			for (int j = 30000; j >= 0; j--)
				if (dp[j] != 1 << 30 && j + vn.get(i) <= 30000)
					dp[j + vn.get(i)] = Math.min(dp[j + vn.get(i)], dp[j] + cn.get(i));

		long[] rem = new long[30001];
		Arrays.fill(rem, 1 << 30);
		rem[t] = 0;
		for (int i = t; i <= 30000; i++)
			for (int j = 0; j < vn.size(); j++)
				if (rem[i] != 1 << 30 && i + vn.get(j) <= 30000)
					rem[i + vn.get(j)] = Math.min(rem[i + vn.get(j)], rem[i] + cn.get(j));

		long ans = 1 << 30;
		for (int i = t; i <= 30000; i++)
			ans = Math.min(ans, dp[i] + rem[i]);

		if (ans == 1 << 30)
			System.out.println(-1);
		else
			System.out.println(ans);

	}

}

import java.util.*;
//TAGS: dynamicProgramming

public class candy {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		int n = input.nextInt();
		int m = input.nextInt();
		int f = input.nextInt();
		int x = input.nextInt();

		int[] c = new int[m];
		for (int i = 0; i < m; i++)
			c[i] = input.nextInt();

		boolean[] fn = new boolean[1 << 17];
		for (int i = 0; i < f; i++)
			fn[input.nextInt()] = true;

		input.close();

		int[] dp = new int[1 << 17];
		int[] v = new int[1 << 17];
		Arrays.fill(dp, -1);
		dp[n] = 0;
		int ans = 0;
		for (int i = n; i >= 0; i--) {

			ans = Math.max(ans, dp[i]);

			if (v[i] > 1 << 8) {
				System.out.println(-1);
				return;
			} else if (dp[i] == -1)
				continue;
			else if (fn[i] && dp[i] != 0) {
				if (dp[i + x] < dp[i]) {
					dp[i + x] = dp[i];
					v[i]++;
					i += x + 1;
				} else
					v[i]++;
				continue;
			} else
				for (int j = 0; j < m; j++)
					if (i - c[j] >= 0)
						dp[i - c[j]] = Math.max(dp[i - c[j]], dp[i] + c[j]);

		}

		System.out.println(ans);

	}

}

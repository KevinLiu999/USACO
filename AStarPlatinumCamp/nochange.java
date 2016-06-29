import java.util.*;

public class nochange {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		int k = input.nextInt();
		int n = input.nextInt();

		int[] coins = new int[k];
		for (int i = 0; i < k; i++)
			coins[i] = input.nextInt();

		int[] coinsum = new int[1 << k];
		for (int i = 0; i < 1 << k; i++)
			for (int j = 0; j < k; j++)
				if ((i >> j & 1) == 0)
					coinsum[i] += coins[j];

		TreeSet<Integer> sum = new TreeSet<Integer>();
		int csum = 0;
		for (int i = 0; i < n; i++) {
			csum += input.nextInt();
			sum.add(csum);
		}

		input.close();

		/* Dynamic Programming to Solve Problem */
		int[] dp = new int[1 << k];
		int ans = -1;
		for (int i = 0; i < 1 << k; i++) {

			if (dp[i] == sum.last())
				ans = Math.max(ans, coinsum[i]);

			for (int j = 0; j < k; j++) {

				if ((i >> j & 1) == 1)
					continue;

				if (dp[i] + coins[j] < sum.first())
					continue;

				int nxt = 1 << j | i;
				dp[nxt] = Math.max(dp[nxt], sum.floor(dp[i] + coins[j]));

			}

		}

		System.out.println(ans);

	}

}

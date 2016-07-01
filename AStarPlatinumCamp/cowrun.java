import java.util.*;

public class cowrun {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		int n = input.nextInt();
		int m = input.nextInt();

		int[] d = new int[n];
		for (int i = 0; i < n; i++)
			d[i] = input.nextInt();

		input.close();

		int[][][] dp = new int[2][n + 1][m + 1];
		for (int h = 0; h < 2; h++)
			for (int i = 0; i <= n; i++)
				Arrays.fill(dp[h][i], -1);
		dp[0][0][0] = 0;

		for (int i = 0; i < n; i++)
			for (int j = 0; j <= m; j++)
				for (int h = 0; h < 2; h++) {
					
					if (j == 0 && h == 1)
						continue;
					if (dp[h][i][j] == -1)
						continue;

					if (h == 1) {
						if (j == 1)
							dp[0][i + 1][0] = Math.max(dp[0][i + 1][0], dp[h][i][j]);
						else
							dp[h][i + 1][j - 1] = Math.max(dp[h][i + 1][j - 1], dp[h][i][j]);
						continue;
					}

					if (j == 0)
						dp[h][i + 1][j] = Math.max(dp[h][i + 1][j], dp[h][i][j]);
					else if (j == 1)
						dp[0][i + 1][0] = Math.max(dp[0][i + 1][0], dp[h][i][j]);
					else
						dp[1][i + 1][j - 1] = Math.max(dp[1][i + 1][j - 1], dp[h][i][j]);
					if (j < m)
						dp[h][i + 1][j + 1] = Math.max(dp[h][i + 1][j + 1], dp[h][i][j] + d[i]);
					
				}

		System.out.println(dp[0][n][0]);

	}

}

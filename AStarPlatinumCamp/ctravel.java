import java.util.*;

public class ctravel {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		int n = input.nextInt();
		int m = input.nextInt();
		int t = input.nextInt();

		String[] graph = new String[n];
		for (int i = 0; i < n; i++)
			graph[i] = input.next();

		int r1 = input.nextInt() - 1;
		int c1 = input.nextInt() - 1;
		int r2 = input.nextInt() - 1;
		int c2 = input.nextInt() - 1;

		input.close();

		int[][][] dp = new int[t + 1][n][m];
		dp[0][r1][c1] = 1;

		int[][] dir = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
		for (int i = 0; i < t; i++)
			for (int j = 0; j < n; j++)
				for (int k = 0; k < m; k++) {

					if (dp[i][j][k] == 0)
						continue;

					for (int l = 0; l < 4; l++) {

						int nx = j + dir[l][0];
						int ny = k + dir[l][1];

						if (nx < 0 || nx >= n || ny < 0 || ny >= m)
							continue;
						if (graph[nx].charAt(ny) == '*')
							continue;

						dp[i + 1][nx][ny] += dp[i][j][k];

					}

				}

		System.out.println(dp[t][r2][c2]);

	}

}

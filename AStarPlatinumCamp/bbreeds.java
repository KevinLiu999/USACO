import java.util.*;
//TAGS: dynamicProgramming

public class bbreeds {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		String s = input.next();
		int n = s.length();

		input.close();

		int[][] dp = new int[n][n];
		dp[0][0] = 1;
		dp[0][1] = 1;
		int ct = 1;
		for (int i = 0; i < n - 1; i++) {

			if (s.charAt(i + 1) == '(')
				ct++;
			else
				ct--;

			for (int j = 0; j < n; j++) {

				if (dp[i][j] == 0)
					continue;

				if (s.charAt(i + 1) == '(' && j < n) {
					dp[i + 1][j + 1] += dp[i][j];
					dp[i + 1][j + 1] %= 2012;
				} else if (j > 0) {
					dp[i + 1][j - 1] += dp[i][j];
					dp[i + 1][j - 1] %= 2012;
				}
				if (ct - j >= 0 || s.charAt(i + 1) == '(') {
					dp[i + 1][j] += dp[i][j];
					dp[i + 1][j] %= 2012;
				}

			}

		}

		System.out.println(dp[n - 1][0]);

	}

}

import java.util.*;
//TAGS: dynamicProgramming

public class buyhay {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		int n = input.nextInt();
		int h = input.nextInt();

		int[] p = new int[n];
		int[] c = new int[n];
		for (int i = 0; i < n; i++) {
			p[i] = input.nextInt();
			c[i] = input.nextInt();
		}

		input.close();

		int[] dp = new int[h + 1];
		Arrays.fill(dp, 1 << 30);
		dp[0] = 0;
		for (int i = 0; i <= h; i++)
			for (int j = 0; j < n; j++)
				if (i + p[j] > h)
					dp[h] = Math.min(dp[h], dp[i] + c[j]);
				else
					dp[i + p[j]] = Math.min(dp[i + p[j]], dp[i] + c[j]);

		System.out.println(dp[h]);

	}

}

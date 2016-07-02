import java.util.*;

public class diet {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		int h = input.nextInt();
		int n = input.nextInt();

		int[] w = new int[n];
		for (int i = 0; i < n; i++)
			w[i] = input.nextInt();

		input.close();

		boolean[] dp = new boolean[h + 1];
		dp[0] = true;
		for (int i = 0; i < n; i++)
			for (int j = h; j >= 0; j--)
				if (dp[j] && j + w[i] <= h)
					dp[j + w[i]] = true;

		int ans = 0;
		for (int i = h; i >= 0; i--)
			if (dp[i]) {
				ans = i;
				break;
			}

		System.out.println(ans);

	}

}

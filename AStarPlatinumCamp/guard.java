import java.util.*;
import java.io.*;
//TAGS: dynamicProgramming

public class guard {

	public static void main(String[] args) throws IOException {

		Scanner input = new Scanner(System.in);

		int n = input.nextInt();
		int h = input.nextInt();

		int[] ch = new int[n];
		int[] cw = new int[n];
		int[] cs = new int[n];

		for (int i = 0; i < n; i++) {
			ch[i] = input.nextInt();
			cw[i] = input.nextInt();
			cs[i] = input.nextInt();
		}

		input.close();

		int[] dp = new int[1 << n];
		int[] th = new int[1 << n];
		Arrays.fill(dp, -1);

		int ans = -1;

		int ct = 0;
		for (int i = 1; i < 1 << n; i *= 2) {
			dp[i] = cs[ct];
			th[i] = ch[ct++];
		}

		for (int i = 0; i < 1 << n; i++) {

			if (dp[i] == -1)
				continue;

			if (th[i] >= h)
				ans = Math.max(ans, dp[i]);

			for (int j = 0; j < n; j++) {

				if ((i >> j & 1) == 1)
					continue;

				int nxt = i | 1 << j;
				dp[nxt] = Math.max(dp[nxt], Math.min(cs[j], dp[i] - cw[j]));
				th[nxt] = th[i] + ch[j];

			}

		}

		if (ans == -1)
			System.out.println("Mark is too tall");
		else
			System.out.println(ans);

	}

}

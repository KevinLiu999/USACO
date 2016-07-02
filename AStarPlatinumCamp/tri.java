import java.util.*;
import java.io.*;

public class tri {

	public static void main(String[] args) throws IOException {

		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		long[][] sum = new long[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(f.readLine());
			for (int j = 1; j <= i; j++)
				sum[i][j] = (long) (Integer.parseInt(st.nextToken())) + sum[i][j - 1];
		}

		f.close();

		long ans = -1 << 30;
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= i; j++) {
				long tot = 0;
				long avg = 0;
				for (int nxt = 0; nxt <= 2 * k; nxt++) {
					if (i + nxt > n)
						break;
					tot += sum[i + nxt][j + nxt] - sum[i + nxt][j - 1];
					avg += nxt + 1;
					if (nxt + 1 >= k)
						ans = Math.max(ans, tot / avg);
				}
				tot = 0;
				avg = 0;
				for (int nxt = 0; nxt <= 2 * k; nxt++) {
					if (j > i - nxt || j - nxt - 1 < 0)
						break;
					tot += sum[i - nxt][j] - sum[i - nxt][j - nxt - 1];
					avg += nxt + 1;
					if (nxt + 1 >= k)
						ans = Math.max(ans, tot / avg);
				}
			}

		System.out.println(ans);

	}

}

import java.util.*;
//TAGS: dynamicProgramming

public class dec {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		int n = input.nextInt();
		int b = input.nextInt();

		ArrayList<State> l = new ArrayList<State>();
		for (int i = 0; i < b; i++)
			l.add(new State(input.nextInt(), input.nextInt(), input.nextInt()));

		Collections.sort(l);

		int[][] s = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				s[i][j] = input.nextInt();

		input.close();

		int[] dp = new int[1 << n];
		Arrays.fill(dp, -1);
		dp[0] = 0;

		for (int h = 0; h < 1 << n; h++)
			if (dp[h] != -1)
				for (int i = 0; i < n; i++)
					if ((h >> i & 1) == 0) {
						int nxt = h | 1 << i;
						int pts = 0;
						int tot = Integer.bitCount(h);
						if (tot < n)
							for (State j : l)
								if (tot + 1 == j.k && dp[h] + s[i][tot] + pts >= j.p)
									pts += j.a;
						dp[nxt] = Math.max(dp[nxt], dp[h] + s[i][tot] + pts);
					}

		System.out.println(dp[(1 << n) - 1]);

	}

	public static class State implements Comparable<State> {

		public int k, p, a;

		public State(int k, int p, int a) {
			this.k = k;
			this.p = p;
			this.a = a;
		}

		public int compareTo(State o) {
			if (p == o.p) {
				if (a == o.a) {
					if (k == o.k)
						return 0;
					else
						return k < o.k ? -1 : 1;
				} else
					return a < o.a ? 1 : -1;
			} else
				return p < o.p ? -1 : 1;
		}

	}

}

import java.util.*;
//TAGS: graphTheory, dfs

public class ranking {

	public static boolean[] v;
	public static boolean[][] mark;

	public static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();

	public static void dfs(int a, int b) {

		v[b] = true;
		mark[a][b] = mark[b][a] = true;

		for (int i : adj.get(b))
			if (!v[i])
				dfs(a, i);

	}

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		int n = input.nextInt();
		int m = input.nextInt();

		v = new boolean[n + 1];
		mark = new boolean[n + 1][n + 1];

		for (int i = 0; i <= n; i++) {
			ArrayList<Integer> tl = new ArrayList<Integer>();
			adj.add(tl);
		}

		for (int i = 0; i < m; i++)
			adj.get(input.nextInt()).add(input.nextInt());

		input.close();

		for (int i = 1; i <= n; i++) {
			dfs(i, i);
			Arrays.fill(v, false);
		}

		int ans = 0;
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= n; j++)
				if (!mark[i][j] && !mark[j][i])
					ans++;

		ans /= 2;

		System.out.println(ans);

	}

}

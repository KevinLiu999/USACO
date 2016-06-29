import java.util.*;

public class meetplace {

	public static int[] depth;
	public static int[][] anc;
	public static int[] parent;
	public static boolean[] v;

	public static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();

	public static void dfs(int node, int depth) {
		v[node] = true;
		meetplace.depth[node] = depth;
		for (int i : adj.get(node))
			if (!v[i]) {
				anc[i][0] = node;
				dfs(i, depth + 1);
			}
	}

	public static int lca(int a, int b) {

		if (depth[a] > depth[b])
			return lca(b, a);

		int d = depth[b] - depth[a];
		for (int i = 0; i <= 17; i++)
			if ((d & (1 << i)) > 0)
				b = anc[b][i];

		if (a == b)
			return a;
		for (int i = 17; i >= 0; i--)
			if (anc[a][i] != anc[b][i]) {
				a = anc[a][i];
				b = anc[b][i];
			}

		return parent[a];

	}

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		int n = input.nextInt();
		int m = input.nextInt();

		for (int i = 0; i <= n; i++) {
			ArrayList<Integer> tl = new ArrayList<Integer>();
			adj.add(tl);
		}

		depth = new int[n + 1];
		anc = new int[n + 1][19];
		parent = new int[n + 1];
		v = new boolean[n + 1];
		for (int i = 2; i <= n; i++) {

			int x = input.nextInt();
			parent[i] = x;
			adj.get(i).add(x);
			adj.get(x).add(i);

		}

		dfs(1, 0);

		for (int k = 0; k < 17; k++)
			for (int i = 1; i <= n; i++)
				anc[i][k + 1] = anc[anc[i][k]][k];

		for (int i = 0; i < m; i++) {

			int a = input.nextInt();
			int b = input.nextInt();

			System.out.println(lca(a, b));

		}

		input.close();

	}

}

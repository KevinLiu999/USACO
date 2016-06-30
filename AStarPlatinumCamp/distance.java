import java.util.*;
import java.io.*;
//TAGS: graphTheory, dfs
//ALGORITHMS: Tarjan's Offline LCA Algorithm

public class distance {

	public static int[] depth;
	public static int[][] anc;
	public static int[] parent;
	public static boolean[] v;

	public static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();

	public static void dfs(int node, int depth) {
		v[node] = true;
		distance.depth[node] = depth;
		for (int i : adj.get(node))
			if (!v[i]) {
				anc[i][0] = parent[i] = node;
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

	public static void main(String[] args) throws IOException {

		try {
			
			BufferedReader f = new BufferedReader(new InputStreamReader(System.in));

			StringTokenizer st = new StringTokenizer(f.readLine());

			int n = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());

			for (int i = 0; i <= n; i++) {
				ArrayList<Integer> tl = new ArrayList<Integer>();
				adj.add(tl);
			}

			depth = new int[n * 2];
			anc = new int[n * 2][50];
			parent = new int[n * 2];
			v = new boolean[n * 2];
			for (int i = 0; i < n - 1; i++) {

				st = new StringTokenizer(f.readLine());

				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				adj.get(a).add(b);
				adj.get(b).add(a);

			}

			dfs(1, 0);

			for (int k = 0; k < 17; k++)
				for (int i = 1; i <= n; i++)
					anc[i][k + 1] = anc[anc[i][k]][k];

			int[] ans = new int[q];
			for (int i = 0; i < q; i++) {

				st = new StringTokenizer(f.readLine());

				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				ans[i] = depth[a] + depth[b] - 2 * depth[lca(a, b)];

			}

			for (int i = 0; i < q; i++)
				System.out.println(ans[i]);

			f.close();

		} catch (Exception e) {

		}

	}

}

import java.util.*;
import java.io.*;

public class lca {

	public static ArrayList<ArrayList<Integer>> adj;

	public static int[] parent;
	public static int[] depth;
	public static boolean[] v;
	public static int[][] anc;

	public static void dfs(int node, int depth) {

		lca.depth[node] = depth;
		v[node] = true;

		for (int i : adj.get(node)) {
			if (!v[i]) {
				parent[i] = anc[i][0] = node;
				dfs(i, depth + 1);
			}
		}

	}

	public static int findLCA(int a, int b) {

		if (depth[a] > depth[b]) {
			findLCA(b, a);
		}

		int d = depth[b] = depth[a];
		for (int i = 0; i <= 17; i++) {
			if ((d >> i & 1) == 1) {
				b = anc[b][i];
			}
		}

		if (a == b) {
			return a;
		}

		for (int i = 17; i >= 0; i--) {
			if (anc[a][i] != anc[b][i]) {
				a = anc[a][i];
				b = anc[b][i];
			}
		}

		return parent[a];

	}

	public static void main(String[] args) throws IOException {

		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());

		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());

		adj = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i <= n; i++) {
			ArrayList<Integer> tl = new ArrayList<Integer>();
			adj.add(tl);
		}

		for (int i = 0; i < n - 1; i++) {

			st = new StringTokenizer(f.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			adj.get(a).add(b);

		}

		parent = new int[n + 1];
		depth = new int[n + 1];
		v = new boolean[n + 1];
		anc = new int[n + 1][18];

		dfs(1, 0);

		for (int i = 0; i < 17; i++) {
			for (int j = 1; j <= n; j++) {
				anc[j][i + 1] = anc[anc[j][i]][i];
			}
		}

		for (int i = 0; i < q; i++) {

			st = new StringTokenizer(f.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			System.out.println(findLCA(a, b));

		}

		f.close();

	}

}

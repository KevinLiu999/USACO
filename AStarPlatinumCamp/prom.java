import java.util.*;
//TAGS: graphTheory, dfs
//ALGORITHMS: Tarjan's Offline LCA Algorithm

public class prom {

	public static int[] num, low;

	public static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
	public static ArrayList<ArrayList<Integer>> scc = new ArrayList<ArrayList<Integer>>();

	public static int ct = 0;

	public static void SCC(int x, Stack<Integer> tscc) {

		num[x] = low[x] = ++ct;
		tscc.push(x);

		for (int i : adj.get(x)) {
			if (num[i] == 0)
				SCC(i, tscc);
			low[x] = Math.min(low[x], low[i]);
		}

		if (num[x] == low[x]) {
			ArrayList<Integer> c = new ArrayList<Integer>();
			int w = -1;
			while (!tscc.isEmpty() && w != x) {
				w = tscc.pop();
				low[w] = 1 << 30;
				c.add(w);
			}
			scc.add(c);
		}

	}

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		int n = input.nextInt();
		int m = input.nextInt();

		for (int i = 0; i <= n; i++) {
			ArrayList<Integer> tl = new ArrayList<Integer>();
			adj.add(tl);
		}

		for (int i = 0; i < m; i++)
			adj.get(input.nextInt()).add(input.nextInt());

		input.close();

		num = new int[n + 1];
		low = new int[n + 1];
		for (int i = 1; i <= n; i++)
			if (num[i] == 0) {
				Stack<Integer> tscc = new Stack<Integer>();
				SCC(i, tscc);
			}

		int ans = 0;
		for (ArrayList<Integer> i : scc) {
			if (i.size() > 1)
				ans++;
		}

		System.out.println(ans);

	}

}

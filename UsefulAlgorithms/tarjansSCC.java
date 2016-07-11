import java.util.*;
import java.io.*;

public class tarjansSCC {

	public static ArrayList<ArrayList<Integer>> adj, scc;

	public static int[] num, low;
	public static boolean[] v;

	public static int ct, ans;

	public static void SCC(int x, Stack<Integer> s) {

		num[x] = low[x] = ++ct;
		s.add(x);

		for (int i : adj.get(x)) {
			if (num[i] == 0)
				SCC(i, s);
			low[x] = Math.min(low[x], low[i]);
		}

		if (num[x] == low[x]) {
			int w = -1;
			ArrayList<Integer> tl = new ArrayList<Integer>();
			while (w != x) {
				w = s.pop();
				tl.add(w);
				low[w] = 1 << 30;
			}
			Collections.sort(tl);
			scc.add(tl);
		}

	}

	public static void main(String[] args) throws IOException {

		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		adj = new ArrayList<ArrayList<Integer>>();
		scc = new ArrayList<ArrayList<Integer>>();

		num = new int[n + 1];
		low = new int[n + 1];
		v = new boolean[n + 1];

		ct = 0;
		ans = 0;

		for (int i = 0; i <= n; i++) {
			ArrayList<Integer> tl = new ArrayList<Integer>();
			adj.add(tl);
		}

		for (int i = 0; i < m; i++) {

			st = new StringTokenizer(f.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			adj.get(a).add(b);

		}

		f.close();

		for (int i = 1; i <= n; i++)
			if (num[i] == 0) {
				Stack<Integer> s = new Stack<Integer>();
				SCC(i, s);
			}

		for (ArrayList<Integer> i : scc) {
			for (int j : i)
				System.out.print(j + " ");
			System.out.println();
		}

	}

}

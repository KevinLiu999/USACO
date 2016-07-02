import java.util.*;
import java.io.*;

public class grass {

	public static int v, e;

	public static int[] low, sccidx;
	public static boolean[] vis;

	public static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
	public static ArrayList<ArrayList<Integer>> scc = new ArrayList<ArrayList<Integer>>();

	public static ArrayList<ArrayList<Integer>> sccadj = new ArrayList<ArrayList<Integer>>();
	public static ArrayList<ArrayList<Integer>> radj = new ArrayList<ArrayList<Integer>>();

	public static int s;
	public static int sccct, ct = 0;

	public static int[][] dp;

	public static void genSCC(int v, Stack<Integer> tscc) {

		int tlow = low[v] = ct++;
		tlow++;
		vis[v] = true;
		tscc.push(v);

		for (int i = 0; i < adj.get(v).size(); i++)
			if (!vis[adj.get(v).get(i)]) {
				genSCC(adj.get(v).get(i), tscc);
				tlow = Math.min(tlow, low[adj.get(v).get(i)]);
			} else
				tlow = Math.min(tlow, low[adj.get(v).get(i)]);

		if (tlow < low[v]) {
			low[v] = tlow;
			return;
		}

		ArrayList<Integer> c = new ArrayList<Integer>();
		int w = -1;
		while (w != v && !tscc.isEmpty()) {
			w = tscc.pop();
			sccidx[w] = sccct++;
			low[w] = grass.v;
			c.add(w);
			if (w != v && tscc.size() > 0)
				sccct--;
		}
		scc.add(c);

	}

	public static void DPSolve(int val, int v) {

		if (val == 0)
			for (int i = 0; i < sccadj.get(v).size(); i++) {
				dp[0][sccadj.get(v).get(i)] = Math.max(dp[0][sccadj.get(v).get(i)],
						dp[0][v] + scc.get(sccadj.get(v).get(i)).size());
				DPSolve(val, sccadj.get(v).get(i));
			}
		else
			for (int i = 0; i < radj.get(v).size(); i++) {
				dp[1][radj.get(v).get(i)] = Math.max(dp[1][radj.get(v).get(i)],
						dp[1][v] + scc.get(radj.get(v).get(i)).size());
				DPSolve(val, radj.get(v).get(i));
			}
	}

	public static void main(String[] args) throws IOException {

		Scanner input = new Scanner(System.in);

		v = input.nextInt();
		e = input.nextInt();

		low = new int[v + 1];
		sccidx = new int[v + 1];
		vis = new boolean[v + 1];
		Arrays.fill(sccidx, -1);

		/* Create Node Adjacency List */
		for (int i = 0; i <= v; i++) {
			ArrayList<Integer> tl = new ArrayList<Integer>();
			adj.add(tl);
		}

		for (int i = 0; i < e; i++)
			adj.get(input.nextInt()).add(input.nextInt());

		input.close();

		/* Generate SCC Graph */
		for (int i = 1; i <= v; i++)
			if (!vis[i]) {
				Stack<Integer> tscc = new Stack<Integer>();
				genSCC(i, tscc);
			}

		/* Create SCC Adjacency List */
		for (int i = 0; i < scc.size(); i++) {
			ArrayList<Integer> tadj = new ArrayList<Integer>();
			sccadj.add(tadj);
			ArrayList<Integer> tradj = new ArrayList<Integer>();
			radj.add(tradj);
		}

		for (int i = 1; i <= v; i++)
			for (int j = 0; j < adj.get(i).size(); j++)
				if (sccidx[i] != sccidx[adj.get(i).get(j)]) {
					sccadj.get(sccidx[i]).add(sccidx[adj.get(i).get(j)]);
					radj.get(sccidx[adj.get(i).get(j)]).add(sccidx[i]);
				}

		/* Dynamic Programming to Solve Problem */
		s = sccidx[1];
		dp = new int[2][scc.size()];
		dp[0][s] = dp[1][s] = scc.get(s).size();

		DPSolve(0, s);
		DPSolve(1, s);

		int ans = -1;
		for (int i = 1; i <= v; i++)
			for (int j = 0; j < adj.get(i).size(); j++)
				if (dp[0][sccidx[adj.get(i).get(j)]] != 0 && dp[1][sccidx[i]] != 0)
					ans = Math.max(ans, dp[0][sccidx[adj.get(i).get(j)]] + dp[1][sccidx[i]]);
		ans -= scc.get(s).size();

		System.out.println(ans);

	}

}

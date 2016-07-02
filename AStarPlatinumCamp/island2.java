import java.util.*;
 
public class island2 {

	public static void main(String[] args) {

		/* Read in Input */
		Scanner input = new Scanner(System.in);

		int r = input.nextInt();
		int c = input.nextInt();

		String[] graph = new String[r];
		for (int i = 0; i < r; i++)
			graph[i] = input.next();

		input.close();

		int[][] g = new int[r][c];
		for (int i = 0; i < r; i++)
			for (int j = 0; j < c; j++)
				if (graph[i].charAt(j) != '.')
					g[i][j] = graph[i].charAt(j) == 'X' ? 1 : 2;

		/* BFS Islands */
		int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
		int ct = 0;
		boolean[][] v = new boolean[r][c];
		int[][] d = new int[r][c];
		for (int i = 0; i < r; i++)
			Arrays.fill(d[i], -1);

		for (int i = 0; i < r; i++)
			for (int j = 0; j < c; j++)
				if (!v[i][j] && g[i][j] == 1) {

					PriorityQueue<State> pq = new PriorityQueue<State>();
					pq.add(new State(i, j, ct++));
					while (!pq.isEmpty()) {

						int tx = pq.peek().x;
						int ty = pq.peek().y;
						int td = pq.peek().d;
						pq.poll();

						if (v[tx][ty])
							continue;

						v[tx][ty] = true;
						d[tx][ty] = td;

						for (int k = 0; k < 4; k++) {

							int nx = tx + dir[k][0];
							int ny = ty + dir[k][1];

							if (nx < 0 || nx >= r || ny < 0 || ny >= c)
								continue;
							if (g[nx][ny] != 1 || v[nx][ny])
								continue;

							pq.add(new State(nx, ny, td));

						}

					}

				}

		/* BFS Distance Between Islands */
		int[][] adj = new int[ct][ct];
		int[][] dist = new int[r][c];
		boolean[] vis = new boolean[ct];
		for (int i = 0; i < ct; i++) {
			Arrays.fill(adj[i], 1 << 30);
			adj[i][i] = 0;
		}

		for (int i = 0; i < r; i++)
			for (int j = 0; j < c; j++)
				if (d[i][j] > 0 && !vis[d[i][j]]) {

					for (int k = 0; k < r; k++)
						Arrays.fill(dist[k], 1 << 30);

					int cur = d[i][j];
					vis[cur] = true;

					PriorityQueue<State> pq = new PriorityQueue<State>();
					pq.add(new State(i, j, 0));
					while (!pq.isEmpty()) {

						int tx = pq.peek().x;
						int ty = pq.peek().y;
						int td = pq.peek().d;
						pq.poll();

						if (dist[tx][ty] <= td)
							continue;

						dist[tx][ty] = td;

						for (int k = 0; k < 4; k++) {

							int nx = tx + dir[k][0];
							int ny = ty + dir[k][1];

							if (nx < 0 || nx >= r || ny < 0 || ny >= c)
								continue;
							if (g[nx][ny] == 0)
								continue;

							if (g[nx][ny] == 1 && d[nx][ny] == cur && dist[nx][ny] > dist[tx][ty])
								pq.add(new State(nx, ny, td));
							else if (g[nx][ny] == 1)
								adj[cur][d[nx][ny]] = adj[d[nx][ny]][cur] = Math
										.min(Math.min(adj[cur][d[nx][ny]], adj[d[nx][ny]][cur]), td);
							else if (dist[nx][ny] > dist[tx][ty] + 1)
								pq.add(new State(nx, ny, td + 1));

						}

					}

				}

		/* Dynamic Programming to Solve */
		int[][] dp = new int[ct][1 << ct];
		for (int i = 0; i < ct; i++) {
			Arrays.fill(dp[i], 1 << 30);
			dp[i][1 << i] = 0;
		}

		for (int i = 0; i < 1 << ct; i++)
			for (int j = 0; j < ct; j++)
				if (dp[j][i] != 1 << 30 && (i >> j & 1) == 1)
					for (int k = 0; k < ct; k++)
						if (adj[j][k] != 1 << 30)
							dp[k][i | 1 << k] = Math.min(dp[k][i | 1 << k], dp[j][i] + adj[j][k]);

		int ans = 1 << 30;
		for (int i = 0; i < ct; i++)
			ans = Math.min(ans, dp[i][(1 << ct) - 1]);

		System.out.println(ans);

	}

	public static class State implements Comparable<State> {

		public int x, y, d;

		public State(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}

		public int compareTo(State o) {
			if (d == o.d)
				return 0;
			else
				return d < o.d ? -1 : 1;
		}

	}

}

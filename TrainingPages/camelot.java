import java.util.*;
import java.io.*;

/*
ID: kevinyl1
LANG: JAVA
TASK: camelot
*/

public class camelot {

	public static void main(String[] args) throws IOException {

		/* Read in Input */
		BufferedReader f = new BufferedReader(new FileReader("camelot.in"));
		StringTokenizer st = new StringTokenizer(f.readLine());

		int c = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(f.readLine());
		int kx = st.nextToken().charAt(0) - '@';
		int ky = Integer.parseInt(st.nextToken());

		int ct = 0;

		ArrayList<Point> p = new ArrayList<Point>();
		while (f.ready()) {
			st = new StringTokenizer(f.readLine());
			while (st.hasMoreTokens()) {
				int a = st.nextToken().charAt(0) - '@';
				int b = Integer.parseInt(st.nextToken());
				p.add(new Point(a, b, ct++));
			}
		}

		f.close();

		/* Compute Distances for Knights */
		int[][][][] d = new int[r + 1][c + 1][r + 1][c + 1];
		for (int i = 1; i <= r; i++)
			for (int j = 1; j <= c; j++)
				for (int k = 1; k <= r; k++)
					for (int l = 1; l <= c; l++)
						d[i][j][k][l] = 1 << 30;

		int[][] kn = { { -2, -1 }, { -1, -2 }, { -2, 1 }, { -1, 2 }, { 2, 1 }, { 1, 2 }, { 2, -1 }, { 1, -2 } };
		for (int i = 1; i <= r; i++)
			for (int j = 1; j <= c; j++) {

				PriorityQueue<State> pq = new PriorityQueue<State>();
				pq.add(new State(i, j, 0));
				while (!pq.isEmpty()) {

					int tx = pq.peek().x;
					int ty = pq.peek().y;
					int td = pq.peek().d;
					pq.poll();

					if (d[i][j][tx][ty] != 1 << 30)
						continue;

					d[i][j][tx][ty] = td;

					for (int k = 0; k < 8; k++) {

						int nx = tx + kn[k][0];
						int ny = ty + kn[k][1];
						int nd = td + 1;

						if (nx <= 0 || nx > r || ny <= 0 || ny > c)
							continue;
						if (d[i][j][nx][ny] <= nd)
							continue;

						pq.add(new State(nx, ny, nd));

					}

				}

			}

		/* Compute Distances for King */
		int[][] kd = new int[r + 1][c + 1];
		for (int i = 1; i <= r; i++)
			for (int j = 1; j <= c; j++)
				kd[i][j] = 1 << 30;

		int[][] ki = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 } };

		PriorityQueue<State> pq = new PriorityQueue<State>();
		pq.add(new State(kx, ky, 0));
		while (!pq.isEmpty()) {

			int tx = pq.peek().x;
			int ty = pq.peek().y;
			int td = pq.peek().d;
			pq.poll();

			if (td > 2 || kd[tx][ty] != 1 << 30)
				continue;

			kd[tx][ty] = td;

			for (int i = 0; i < 8; i++) {

				int nx = tx + ki[i][0];
				int ny = ty + ki[i][1];
				int nd = td + 1;

				if (nx <= 0 || nx > r || ny <= 0 || ny > c)
					continue;
				if (kd[nx][ny] <= nd || nd > 2)
					continue;

				pq.add(new State(nx, ny, nd));

			}

		}

		/* Solve */
		int ans = 1 << 30;
		for (int i = 1; i <= r; i++)
			for (int j = 1; j <= c; j++) {
				int best = 0;
				for (Point k : p)
					best += d[k.x][k.y][i][j];
				if (best >= ans)
					continue;
				for (int a = Math.max(1, kx - 2); a <= Math.min(kx + 2, r); a++)
					for (int b = Math.max(1, ky - 2); b <= Math.min(ky + 2, c); b++) {
						for (Point k : p)
							if (d[k.x][k.y][i][j] != 1 << 30 && d[k.x][k.y][a][b] != 1 << 30 && d[a][b][i][j] != 1 << 30
									&& kd[a][b] != 1 << 30)
								ans = Math.min(ans,
										best - d[k.x][k.y][i][j] + d[k.x][k.y][a][b] + d[a][b][i][j] + kd[a][b]);
					}
			}

		PrintWriter pw = new PrintWriter(new File("camelot.out"));
		if (ans == 1 << 30)
			pw.println(0);
		else
			pw.println(ans);
		pw.close();
		System.exit(0);

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

	public static class Point {

		public int x, y, i;

		public Point(int x, int y, int i) {
			this.x = x;
			this.y = y;
			this.i = i;
		}

	}

}

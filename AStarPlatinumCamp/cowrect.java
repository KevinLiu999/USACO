import java.util.*;

public class cowrect {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		int n = input.nextInt();

		ArrayList<Point> c = new ArrayList<Point>();
		for (int i = 0; i < n; i++)
			c.add(new Point(input.nextInt(), input.nextInt(), input.next().charAt(0) == 'H' ? 1 : 0));

		input.close();

		Collections.sort(c);

		int ans = 0;
		int barea = 1 << 30;
		for (int i = 0; i < n; i++) {

			if (c.get(i).b == 0)
				continue;

			ArrayList<Point> tc = new ArrayList<Point>();

			int lg = 0;
			int hg = 1000;

			for (int j = i; j < n; j++) {

				int x = c.get(j).x;
				int y = c.get(j).y;

				if (c.get(j).b == 1)
					tc.add(new Point(x, y, 0));
				else {
					if (y <= c.get(i).y)
						lg = Math.max(lg, y);
					if (y >= c.get(i).y)
						hg = Math.min(hg, y);
					continue;
				}

				int lx = 1000;
				int ly = 1000;
				int hx = 0;
				int hy = 0;

				int ct = 0;
				for (int k = 0; k < tc.size(); k++)
					if (tc.get(k).y > lg && tc.get(k).y < hg) {

						ct++;

						lx = Math.min(lx, tc.get(k).x);
						ly = Math.min(ly, tc.get(k).y);
						hx = Math.max(hx, tc.get(k).x);
						hy = Math.max(hy, tc.get(k).y);

					}

				if (ct >= ans)
					if (ct == ans)
						barea = Math.min(barea, (hx - lx) * (hy - ly));
					else {
						ans = ct;
						barea = (hx - lx) * (hy - ly);
					}

			}

		}

		System.out.println(ans);
		System.out.println(barea);

	}

	public static class Point implements Comparable<Point> {

		public int x, y, b;

		public Point(int x, int y, int b) {
			this.x = x;
			this.y = y;
			this.b = b;
		}

		public int compareTo(Point o) {
			if (x < o.x)
				return -1;
			else if (x > o.x)
				return 1;
			else {
				if (y < o.y)
					return 1;
				else if (y > o.y)
					return -1;
				else
					return 0;
			}
		}

	}

}

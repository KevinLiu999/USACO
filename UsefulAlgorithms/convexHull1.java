import java.util.*;
import java.io.*;

public class convexHull1 {

	public static boolean ccw(Point a, Point b, Point c) {
		Point ta = new Point(a.x - b.x, a.y - b.y);
		Point tc = new Point(c.x - b.x, c.y - b.y);
		return (ta.x * tc.y - ta.y * tc.x) <= 0;
	}

	public static void main(String[] args) throws IOException {

		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());

		int n = Integer.parseInt(st.nextToken());

		ArrayList<Point> p = new ArrayList<Point>();
		for (int i = 1; i <= n; i++) {

			st = new StringTokenizer(f.readLine());

			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			p.add(new Point(x, y));

		}

		f.close();

		Collections.sort(p);

		Stack<Point> h1 = new Stack<Point>();
		for (Point i : p) {
			int len = h1.size();
			while (len >= 2 && ccw(h1.get(len - 1), h1.get(len - 2), i)) {
				h1.pop();
			}
			h1.push(i);
		}

		Collections.reverse(p);

		Stack<Point> h2 = new Stack<Point>();
		for (Point i : p) {
			int len = h2.size();
			while (len >= 2 && ccw(h2.get(len - 1), h2.get(len - 2), i)) {
				h2.pop();
			}
			h2.push(i);
		}

		System.out.println("Lower Hull");
		for (Point i : h1) {
			System.out.println(i.x + " " + i.y);
		}
		System.out.println("Upper Hull");
		for (Point i : h2) {
			System.out.println(i.x + " " + i.y);
		}

	}

	public static class Point implements Comparable<Point> {

		public int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int compareTo(Point o) {
			if (x == o.x) {
				if (y == o.y) {
					return 0;
				} else {
					return y < o.y ? -1 : 1;
				}
			} else {
				return x < o.x ? -1 : 1;
			}
		}

	}

}

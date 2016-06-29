import java.util.*;
import java.io.*;

public class curling {

	public static boolean ccw(Point a, Point b, Point c) {
		Point ta = new Point(a.x - b.x, a.y - b.y);
		Point tc = new Point(c.x - b.x, c.y - b.y);
		return (ta.x * tc.y - ta.y * tc.x) <= 0;
	}

	public static void main(String[] args) throws IOException {

		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());

		int n = Integer.parseInt(st.nextToken());

		ArrayList<Point> p1 = new ArrayList<Point>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(f.readLine());
			p1.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}

		ArrayList<Point> p2 = new ArrayList<Point>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(f.readLine());
			p2.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}

		f.close();

		Collections.sort(p1);
		Collections.sort(p2);

		Stack<Point> h1 = new Stack<Point>();
		for (int i = 0; i < n; i++) {
			while (h1.size() >= 2 && ccw(h1.get(h1.size() - 1), h1.get(h1.size() - 2), p1.get(i)))
				h1.pop();
			h1.push(p1.get(i));
		}

		Stack<Point> h2 = new Stack<Point>();
		for (int i = n - 1; i >= 0; i--) {
			while (h2.size() >= 2 && ccw(h2.get(h2.size() - 1), h2.get(h2.size() - 2), p1.get(i)))
				h2.pop();
			h2.push(p1.get(i));
		}

		TreeSet<Point> s1 = new TreeSet<Point>();
		for (int i = 0; i < h1.size(); i++)
			s1.add(h1.get(i));

		TreeSet<Point> s2 = new TreeSet<Point>();
		for (int i = 0; i < h2.size(); i++)
			s2.add(h2.get(i));

		int ans1 = 0;
		for (int i = 0; i < n; i++) {

			long x = p2.get(i).x;
			long y = p2.get(i).y;

			if (s1.floor(new Point(x, y)) == null || s1.ceiling(new Point(x, y)) == null)
				continue;

			Point tmp1 = s1.floor(new Point(x, y));
			Point tmp2 = s1.ceiling(new Point(x, y));

			long x1 = tmp1.x;
			long y1 = tmp1.y;
			long x2 = tmp2.x;
			long y2 = tmp2.y;

			long a1 = y1 - y2;
			long b1 = x2 - x1;
			long c1 = x2 * y1 - x1 * y2;

			if (s2.floor(new Point(x, y)) == null || s2.ceiling(new Point(x, y)) == null)
				continue;

			tmp1 = s2.floor(new Point(x, y));
			tmp2 = s2.ceiling(new Point(x, y));

			x1 = tmp1.x;
			y1 = tmp1.y;
			x2 = tmp2.x;
			y2 = tmp2.y;

			long a2 = y1 - y2;
			long b2 = x2 - x1;
			long c2 = x2 * y1 - x1 * y2;

			if (a1 * x + b1 * y >= c1 && a2 * x + b2 * y <= c2)
				ans1++;

		}

		h1 = new Stack<Point>();
		for (int i = 0; i < n; i++) {
			while (h1.size() >= 2 && ccw(h1.get(h1.size() - 1), h1.get(h1.size() - 2), p2.get(i)))
				h1.pop();
			h1.push(p2.get(i));
		}

		h2 = new Stack<Point>();
		for (int i = n - 1; i >= 0; i--) {
			while (h2.size() >= 2 && ccw(h2.get(h2.size() - 1), h2.get(h2.size() - 2), p2.get(i)))
				h2.pop();
			h2.push(p2.get(i));
		}

		s1 = new TreeSet<Point>();
		for (int i = 0; i < h1.size(); i++)
			s1.add(h1.get(i));

		s2 = new TreeSet<Point>();
		for (int i = 0; i < h2.size(); i++)
			s2.add(h2.get(i));

		int ans2 = 0;
		for (int i = 0; i < n; i++) {

			long x = p1.get(i).x;
			long y = p1.get(i).y;

			if (s1.floor(new Point(x, y)) == null || s1.ceiling(new Point(x, y)) == null)
				continue;

			Point tmp1 = s1.floor(new Point(x, y));
			Point tmp2 = s1.ceiling(new Point(x, y));

			long x1 = tmp1.x;
			long y1 = tmp1.y;
			long x2 = tmp2.x;
			long y2 = tmp2.y;

			long a1 = y1 - y2;
			long b1 = x2 - x1;
			long c1 = x2 * y1 - x1 * y2;

			if (s2.floor(new Point(x, y)) == null || s2.ceiling(new Point(x, y)) == null)
				continue;

			tmp1 = s2.floor(new Point(x, y));
			tmp2 = s2.ceiling(new Point(x, y));

			x1 = tmp1.x;
			y1 = tmp1.y;
			x2 = tmp2.x;
			y2 = tmp2.y;

			long a2 = y1 - y2;
			long b2 = x2 - x1;
			long c2 = x2 * y1 - x1 * y2;

			if (a1 * x + b1 * y >= c1 && a2 * x + b2 * y <= c2)
				ans2++;

		}

		System.out.println(ans1 + " " + ans2);

	}

	public static class Point implements Comparable<Point> {

		public long x, y;

		public Point(long x, long y) {
			this.x = x;
			this.y = y;
		}

		public int compareTo(Point o) {
			if (x < o.x)
				return -1;
			else if (x > o.x)
				return 1;
			else {
				if (y < o.y)
					return -1;
				else if (y > o.y)
					return 1;
				else
					return 0;
			}
		}

	}

}

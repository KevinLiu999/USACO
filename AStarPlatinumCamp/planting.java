import java.util.*;
//TAGS: sweepLine, geometry

public class planting {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		/* Read in Input */
		Scanner input = new Scanner(System.in);

		int n = input.nextInt();

		ArrayList<State> s = new ArrayList<State>();
		ArrayList<Rect> r = new ArrayList<Rect>();
		for (int i = 0; i < n; i++) {

			long x1 = input.nextLong();
			long y1 = input.nextLong();
			long x2 = input.nextLong();
			long y2 = input.nextLong();

			s.add(new State(x1, 1, 0, y1, i));
			s.add(new State(x1, 1, 1, y2, i));
			s.add(new State(x2, 0, 0, y1, i));
			s.add(new State(x2, 0, 1, y2, i));

			r.add(new Rect(x1, y1, x2, y2));

		}

		input.close();

		Collections.sort(s);

		/* Sweep Line to Solve */
		TreeSet<State> ts = new TreeSet<State>();
		ts.add(new State(s.get(0).v, s.get(0).s, s.get(0).s2, -1, s.get(0).i));
		ts.add(new State(s.get(1).v, s.get(1).s, s.get(1).s2, -1, s.get(1).i));

		long ans = 0;
		long prev = s.get(0).x;
		int len = 4 * n;
		for (int i = 2; i < len; i += 2) {

			long sx = s.get(i).x;
			long ss = s.get(i).s;
			long ss2 = s.get(i).s2;
			long sv = s.get(i).v;
			long si = s.get(i).i;

			long nss = s.get(i + 1).s;
			long nss2 = s.get(i + 1).s2;
			long nsv = s.get(i + 1).v;
			long nsi = s.get(i + 1).i;

			TreeSet<State> tsb = new TreeSet<State>();
			TreeSet<State> tsc = new TreeSet<State>();
			tsb = (TreeSet<State>) ts.clone();
			tsb.remove(ts.first());
			tsc.add(ts.first());

			long prevb = ts.first().x;
			for (int j = 0; j < tsb.size();) {

				State tmp = tsb.pollFirst();

				long tx = tmp.x;
				long ts2 = tmp.s2;
				long tsi = tmp.i;

				if (prevb == -1)
					prevb = tx;

				if (ts2 == 1)
					tsc.add(new State(r.get((int) tsi).y2, 1, 1, -1, tsi));
				else
					tsc.remove(new State(r.get((int) tsi).y2, 1, 1, -1, tsi));

				if (tsc.size() == 0) {
					ans += (sx - prev) * (tx - prevb);
					prevb = -1;
				}

			}

			if (ss == 1)
				ts.add(new State(sv, 1, ss2, -1, si));
			else
				ts.remove(new State(sv, 1, ss2, -1, si));
			if (nss == 1)
				ts.add(new State(nsv, 1, nss2, -1, nsi));
			else
				ts.remove(new State(nsv, 1, nss2, -1, nsi));

			prev = sx;

		}

		System.out.println(ans);

	}

	public static class Rect {

		public long x1, y1, x2, y2;

		public Rect(long x1, long y1, long x2, long y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}

	}

	public static class State implements Comparable<State> {

		public long x, s, s2, v, i;

		public State(long x, long s, long s2, long v, long i) {
			this.x = x;
			this.s = s;
			this.s2 = s2;
			this.v = v;
			this.i = i;
		}

		public int compareTo(State o) {
			if (x == o.x) {
				if (v == o.v) {
					if (i == o.i) {
						if (s == o.s) {
							if (s2 == o.s2)
								return 0;
							else
								return s2 < o.s2 ? 1 : -1;
						} else
							return s < o.s ? 1 : -1;
					} else
						return i < o.i ? -1 : 1;
				} else
					return v < o.v ? -1 : 1;
			} else
				return x < o.x ? -1 : 1;
		}

	}

}

import java.util.*;
//TAGS: sweepLine

public class horizon {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		int n = input.nextInt();

		ArrayList<State> l = new ArrayList<State>();
		for (int i = 0; i < n; i++) {

			long a = input.nextLong();
			long b = input.nextLong();
			long h = input.nextLong();

			l.add(new State(a, 1, h, i));
			l.add(new State(b, 0, h, i));

		}

		input.close();

		Collections.sort(l);

		long ans = 0;
		TreeSet<State> pq = new TreeSet<State>();
		TreeMap<State, Integer> map = new TreeMap<State, Integer>();
		pq.add(new State(l.get(0).v, 1, 0, 0));
		map.put(new State(l.get(0).v, 1, 0, 0), 1);
		long prev = l.get(0).x;
		for (int i = 1; i < 2 * n; i++) {

			if (pq.size() > 0)
				ans += pq.last().x * (l.get(i).x - prev);

			State tx = new State(l.get(i).v, 1, l.get(i).i, l.get(i).i);
			if (l.get(i).s == 0) {
				if (map.get(tx) == 1) {
					pq.remove(tx);
					map.remove(tx);
				} else
					map.put(tx, map.get(tx) - 1);
			} else {
				pq.add(tx);
				if (map.containsKey(tx))
					map.put(tx, map.get(tx) + 1);
				else
					map.put(tx, 1);
			}

			prev = l.get(i).x;

		}

		System.out.println(ans);

	}

	public static class State implements Comparable<State> {

		public long x, s, v, i;

		public State(long x, long s, long v, long i) {
			this.x = x;
			this.s = s;
			this.v = v;
			this.i = i;
		}

		public int compareTo(State o) {
			if (x < o.x)
				return -1;
			else if (x > o.x)
				return 1;
			return 0;
		}

	}

}

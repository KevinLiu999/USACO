import java.util.*;

public class job {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		int n = input.nextInt();

		ArrayList<State> s = new ArrayList<State>();
		for (int i = 0; i < n; i++)
			s.add(new State(input.nextInt(), input.nextInt()));

		input.close();

		Collections.sort(s);

		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		int cur = -1;
		int j = 0;
		long ans = 0;
		for (int i = s.get(0).d; i > 0; i--) {
			j = cur + 1;
			if (j < n && s.get(j).d == i) {
				cur = j;
				pq.add(-s.get(j++).p);
			}
			while (j < n && cur < n && s.get(j).d == s.get(cur).d)
				pq.add(-s.get(j++).p);
			if (pq.size() > 0)
				ans -= pq.poll();
			cur = j - 1;
			if (pq.size() == 0 && j < n) {
				cur = j++;
				i = s.get(cur).d + 1;
				pq.add(-s.get(cur).p);
			}
		}

		System.out.println(ans);

	}

	public static class State implements Comparable<State> {

		public int d, p;

		public State(int d, int p) {
			this.d = d;
			this.p = p;
		}

		public int compareTo(State o) {
			if (d < o.d)
				return 1;
			else if (d > o.d)
				return -1;
			else {
				if (p < o.p)
					return -1;
				else if (p > o.p)
					return 1;
				else
					return 0;
			}

		}

	}

}

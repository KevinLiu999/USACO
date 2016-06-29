import java.util.*;

public class mqueue {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		int n = input.nextInt();

		ArrayList<State> s = new ArrayList<State>();
		for (int i = 0; i < n; i++)
			s.add(new State(input.nextInt(), input.nextInt()));

		input.close();

		Collections.sort(s);

		int sa = 0;
		int sb = 0;
		for (int i = 0; i < n; i++)
			sb += s.get(i).b;

		int ans = 0;
		for (int i = 0; i < n; i++) {
			sa += s.get(i).a;
			ans = Math.max(ans, sa + sb);
			sb -= s.get(i).b;
		}

		System.out.println(ans);

	}

	public static class State implements Comparable<State> {

		public int a, b;

		public State(int a, int b) {
			this.a = a;
			this.b = b;
		}

		public int compareTo(State o) {
			int d1 = 0;
			int d2 = 0;
			if (a < b)
				d1 = a;
			else
				d1 = Integer.MAX_VALUE - b;
			if (o.a < o.b)
				d2 = o.a;
			else
				d2 = Integer.MAX_VALUE - o.b;
			if (d1 == d2)
				return 0;
			else
				return d1 < d2 ? -1 : 1;
		}

	}

}

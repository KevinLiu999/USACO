import java.util.*;

public class plank {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		int n = input.nextInt();

		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for (int i = 0; i < n; i++)
			pq.add(input.nextInt());

		input.close();

		long ans = 0;
		while (pq.size() > 1) {
			int p1 = pq.poll();
			int p2 = pq.poll();
			pq.add(p1 + p2);
			ans += (long) (p1 + p2);
		}

		System.out.println(ans);

	}

}

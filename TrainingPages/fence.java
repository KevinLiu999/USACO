import java.util.*;
import java.io.*;

/*
ID: kevinyl1
LANG: JAVA
TASK: fence
*/

public class fence {

	public static void main(String[] args) throws IOException {

		Scanner input = new Scanner(new File("fence.in"));

		int n = input.nextInt();

		ArrayList<PriorityQueue<Integer>> adj = new ArrayList<PriorityQueue<Integer>>();
		for (int i = 0; i <= 500; i++) {
			PriorityQueue<Integer> tl = new PriorityQueue<Integer>();
			adj.add(tl);
		}

		for (int i = 0; i < n; i++) {

			int a = input.nextInt();
			int b = input.nextInt();

			adj.get(a).add(b);
			adj.get(b).add(a);

		}

		input.close();

		int s = 0;
		for (int i = 1; i <= 500; i++)
			if (adj.get(i).size() % 2 == 1) {
				s = i;
				break;
			}

		if (s == 0)
			for (int i = 1; i <= 500; i++)
				if (adj.get(i).size() > 0) {
					s = i;
					break;
				}

		ArrayList<Integer> l = new ArrayList<Integer>();
		ArrayList<Integer> c = new ArrayList<Integer>();
		l.add(s);

		while (!l.isEmpty()) {

			int tn = l.get(l.size() - 1);

			if (adj.get(tn).size() == 0) {
				l.remove(l.size() - 1);
				c.add(tn);
			} else {
				int tmp = adj.get(tn).peek();
				l.add(tmp);
				adj.get(tn).remove(tmp);
				adj.get(tmp).remove(tn);
			}

		}

		PrintWriter pw = new PrintWriter(new File("fence.out"));
		for (int i = c.size() - 1; i >= 0; i--)
			pw.println(c.get(i));
		pw.close();
		System.exit(0);

	}

}

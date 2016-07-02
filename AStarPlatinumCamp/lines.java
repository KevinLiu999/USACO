import java.util.*;

public class lines {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		int n = input.nextInt();

		ArrayList<Point> p = new ArrayList<Point>();
		for (int i = 0; i < n; i++)
			p.add(new Point(input.nextInt(), input.nextInt()));

		input.close();

		TreeSet<Double> s = new TreeSet<Double>();
		for (int i = 0; i < n; i++)
			for (int j = i + 1; j < n; j++)
				if (p.get(i).x - p.get(j).x == 0)
					s.add((1 << 30) * 1.0);
				else if (p.get(i).y - p.get(j).y == 0)
					s.add(0.0);
				else
					s.add((p.get(i).y - p.get(j).y) / (p.get(i).x - p.get(j).x));

		System.out.println(s.size());

	}

	public static class Point {

		public double x, y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}

	}

}

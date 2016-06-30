import java.util.*;
//TAGS: greedy

public class empty {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		int n = input.nextInt();
		int k = input.nextInt();

		int[] s = new int[n];
		for (int i = 0; i < k; i++) {

			int x = input.nextInt();
			int y = input.nextInt();
			long a = input.nextInt();
			long b = input.nextInt();

			for (long j = 1; j <= y; j++)
				s[(int) ((a * j + b) % n)] += x;

		}

		input.close();

		for (int it = 0; it < 2; it++)
			for (int i = 0; i < n; i++)
				if (it == 2 && s[i] == 0)
					break;
				else if (s[i] > 1 && i == n - 1) {
					s[0] += s[i] - 1;
					s[i] = 1;
				} else if (s[i] > 1) {
					s[i + 1] += s[i] - 1;
					s[i] = 1;
				}

		int ans = -1;
		for (int i = 0; i < n; i++)
			if (s[i] == 0) {
				ans = i;
				break;
			}

		System.out.println(ans);

	}

}
